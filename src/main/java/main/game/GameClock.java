package main.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class GameClock {
	private List<GameAction> gameActions = new ArrayList<GameAction>();
	private JFrame jframe;
	private final String TAG = "GameClock";
	private List<GameAction> toBeRemoved = new ArrayList<GameAction>();
	
	public GameClock(JFrame jframe){
		this.jframe = jframe;
	}
	
	public void addGameAction(GameAction gameAction) {
		synchronized(gameActions){
			gameActions.add(gameAction);
		}
	}

	public void run() {
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
		executor.scheduleAtFixedRate(new Runnable() {			
			@Override
			public void run() {
				long start = System.currentTimeMillis();
				synchronized (gameActions) {
					for (GameAction gameAction: gameActions){
						gameAction.doAction();
					}
					synchronized(toBeRemoved){						
						for (Iterator<GameAction> i = toBeRemoved.iterator(); i.hasNext();){
							gameActions.remove(i.next());
							i.remove();
						}
					}
				}
				
				jframe.repaint();
				long time = System.currentTimeMillis() - start;
				if (time > Config.SCREEN_REFRESH_DELAY){
					System.out.println(TAG + ": took time " + (System.currentTimeMillis() - start) + " ms, "
							+ "longer than refresh delay. Optimize something!");
				}
			}
		}, 0, Config.SCREEN_REFRESH_DELAY, TimeUnit.MILLISECONDS);
	}

	public void removeGameAction(GameAction gameAction) {
		synchronized(toBeRemoved){
			toBeRemoved.add(gameAction);
		}		
	}
}
