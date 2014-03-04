package main.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {				
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
		};
		timer.schedule(timerTask, 0, Config.SCREEN_REFRESH_DELAY);
	}

	public void removeGameAction(GameAction gameAction) {
		synchronized(toBeRemoved){
			toBeRemoved.add(gameAction);
		}		
	}
}
