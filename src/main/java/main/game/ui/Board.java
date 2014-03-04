package main.game.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import main.game.Config;
import main.game.GameAction;
import main.game.MainController;
import main.game.maze.Direction;
import main.game.maze.DummyObject;
import main.game.maze.Maze;
import main.game.maze.interactable.Interactable;
import main.game.maze.interactable.Option;
import main.game.maze.interactable.creature.Creature;
import main.game.maze.interactable.creature.monster.Monster;
import main.game.maze.interactable.creature.player.Player;
import main.game.maze.interactable.item.Item;
import main.game.maze.interactable.item.Key;
import main.game.maze.interactable.item.gatestone.GateStone;
import main.game.maze.interactable.object.RoomObject;
import main.game.maze.room.Room;
import main.game.util.Util;

public class Board extends JPanel{
	private static final long serialVersionUID = -5300839540150130114L;
	@SuppressWarnings("unused")
	private static final String TAG = "Board";
	private Maze maze;
	private Dimension totalSize;
	public Board(Maze maze){
		this.maze = maze;
		addKeyListener(new MovementListener());
		addMouseListener(new MouceClickListener());
		setFocusable(true);
		calculateTotalSize();
	}
		
	private void calculateTotalSize() {
		totalSize = new Dimension(2*Config.PADDING_ROOM_EXTERNAL+Config.ROOM_COUNT_HORIZONTAL*Config.SIZE_ROOM_WIDTH+(Config.ROOM_COUNT_HORIZONTAL-1)*Config.PADDING_ROOM_INTERNAL,
				2*Config.PADDING_ROOM_EXTERNAL+Config.ROOM_COUNT_VERTICAL*Config.SIZE_ROOM_HEIGHT+(Config.ROOM_COUNT_VERTICAL-1)*Config.PADDING_ROOM_INTERNAL);
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		setBackground(Config.COLOR_BACKGROUND);
		g.setColor(Config.COLOR_BACKGROUND);
		g.fillRect(0, 0, totalSize.width, totalSize.height);
		drawRooms(g);
		drawDoors(g);
		drawRoomObjects(g);
		drawItems(g);
		drawKeys(g);
		drawGateStones(g);
		drawMonsters(g);
		drawPlayer(g);
		
		//a hack to move player to center of the screen.
		Point temp = getPlayerWindowCorner();
		setBounds(-temp.x, -temp.y, temp.x+Config.SIZE_WINDOW_BOARD_WIDTH, temp.y+Config.SIZE_WINDOW_BOARD_HEIGHT);
		
	}

	private void drawRoomObjects(Graphics g) {
		for (int i = 0; i < maze.getWidth(); i++){
			for (int j = 0; j < maze.getHeight(); j++){
				Room room = maze.getRooms().get(i).get(j);
				if (room != null){
					if (!room.isLocked()){
						Point corner = getRoomCorner(room);
						List<RoomObject> roomObjects = room.getRoomObjects();
						for (RoomObject roomObject: roomObjects){
							Image image = roomObject.getImage();
							Point p = roomObject.getPosition().getPoint();
							Point temp = new Point(corner.x + p.x, corner.y + p.y);
							Util.drawImage(g, image, temp, roomObject.getImageSize());
						}						
					}
				}
			}
		}
	}

	private void drawItems(Graphics g) {
		for (int i = 0; i < maze.getWidth(); i++){
			for (int j = 0; j < maze.getHeight(); j++){
				Room room = maze.getRooms().get(i).get(j);
				if (room != null){
					if (room.hasDroppedItems()){
						if (!room.isLocked()){
							Point corner = getRoomCorner(room);
							List<Item> items = room.getDroppedItems();
							for (Item item:items){
								Image image = item.getImage();
								Point p = item.getPosition().getPoint();
								Point temp = new Point(corner.x + p.x, corner.y + p.y);
								Util.drawImage(g, image, temp, item.getImageSize());
							}
						}
					}
				}
			}
		}
	}

	private void drawGateStones(Graphics g) {
		drawGroupGateStone(g);
		drawPersonalGateStone(g);
	}

	private void drawGroupGateStone(Graphics g) {
		GateStone groupGateStone = maze.getPlayer().getGroupGateStone();
		if (groupGateStone.exists()){
			Image image = groupGateStone.getImage();
			Point corner = getRoomCorner(groupGateStone.getPosition().getRoom());
			Point p = groupGateStone.getPosition().getPoint();
			Point temp = new Point(corner.x + p.x, corner.y + p.y);
			Util.drawImage(g, image, temp, groupGateStone.getImageSize());
			
		}
	}

	private void drawPersonalGateStone(Graphics g) {
		GateStone personalGateStone = maze.getPlayer().getPersonalGateStone();
		if (personalGateStone.exists()){
			Image image = personalGateStone.getImage();
			Point corner = getRoomCorner(personalGateStone.getPosition().getRoom());
			Point p = personalGateStone.getPosition().getPoint();
			Point temp = new Point(corner.x + p.x, corner.y + p.y);
			Util.drawImage(g, image, temp, personalGateStone.getImageSize());
			
		}
	}

	private void drawPlayer(Graphics g) {
		Player player = maze.getPlayer();
		Image image = player.getImage();
		Point corner = getRoomCorner(player.getPosition().getRoom());
		Point p = player.getPosition().getPoint();
		Point temp = new Point(corner.x + p.x, corner.y + p.y);
		Util.drawImage(g, image, temp, player.getImageSize());
		if (player.drawHealthBar()){
			drawHealthBar(g, corner.x, corner.y, player);
		}
		
	}

	private void drawKeys(Graphics g) {
		drawKeysInRooms(g);
		drawKeysOnDoors(g);
	}
	
	
	private void drawKeysInRooms(Graphics g){
		for (int i = 0; i < maze.getWidth(); i++){
			for (int j = 0; j < maze.getHeight(); j++){
				Room room = maze.getRooms().get(i).get(j);
				if (room != null){
					if (room.getKey() != null){
						if (!room.isLocked()){
							Key key = room.getKey();
							Image image = key.getImage();
							Point corner = getRoomCorner(i,j);
							Point temp = new Point(corner.x + key.getPosition().getPoint().x,
									corner.y + key.getPosition().getPoint().y);
							Util.drawImage(g, image, temp, key.getImageSize());
						}
					}
				}
			}
		}
	}
	
	private void drawMonsters(Graphics g) {
		for (int i = 0; i < maze.getWidth(); i++){
			for (int j = 0; j < maze.getHeight(); j++){
				Room room = maze.getRooms().get(i).get(j);
				if (room != null){
					if (!room.isLocked()){
						List<Monster> monsters = room.getMonsters();
						Point corner = getRoomCorner(i,j);
						for (Monster monster: monsters){
							Point p = monster.getPosition().getPoint();
							Image image = monster.getImage();
							Point temp = new Point(corner.x + p.x, corner.y + p.y);
							Util.drawImage(g, image, temp, monster.getImageSize());
							if (monster.drawHealthBar()){
								drawHealthBar(g, corner.x, corner.y, monster);
							}
						}
					}
				}
			}
		}
	}

	private void drawHealthBar(Graphics g, int x, int y, Creature creature) {
		Point p = creature.getPosition().getPoint();
		g.setColor(Config.COLOR_HEALTHBAR_DAMAGED);
		p = new Point(x + p.x, y + p.y-Config.SIZE_HEALTHBAR_HEIGHT-Config.PADDING_HEALTHBAR);
		g.fillRect(p.x, p.y, Config.SIZE_HEALTHBAR_WIDTH, Config.SIZE_HEALTHBAR_HEIGHT);
		g.setColor(Config.COLOR_HEALTHBAR_HEALTHY);
		int width = (int) (((double) creature.getCurrentHealth()/ (double) creature.getMaxHealth())*Config.SIZE_HEALTHBAR_WIDTH);
		g.fillRect(p.x, p.y, width, Config.SIZE_HEALTHBAR_HEIGHT);
	}
	
	private void drawKeysOnDoors(Graphics g) {
		for (int i = 0; i < maze.getWidth(); i++){
			for (int j = 0; j < maze.getHeight(); j++){
				Room room = maze.getRooms().get(i).get(j);
				if (room != null){
					if (room.isLockedWithKey()){
						if (!room.isPreviousRoomLocked()){
							Key key = room.getLockingKey();
							Point corner = getRoomCorner(i,j);
							Direction direction = room.getDirectionOfPreviousRoom();
							Point temp = new Point(corner.x+Config.SIZE_ROOM_WIDTH/2-key.getImageSize().width/2,
									corner.y + Config.SIZE_ROOM_HEIGHT/2-key.getImageSize().height/2);
							Point keyCorner;
							switch (direction){
							case NORTH: keyCorner = new Point(temp.x,temp.y-(Config.PADDING_ROOM_INTERNAL+Config.SIZE_ROOM_HEIGHT)/2); break;
							case EAST: keyCorner = new Point(temp.x+(Config.PADDING_ROOM_INTERNAL+Config.SIZE_ROOM_WIDTH)/2,temp.y); break;
							case SOUTH:keyCorner = new Point(temp.x,temp.y+(Config.PADDING_ROOM_INTERNAL+Config.SIZE_ROOM_HEIGHT)/2); break;
							case WEST: keyCorner = new Point(temp.x-(Config.PADDING_ROOM_INTERNAL+Config.SIZE_ROOM_WIDTH)/2,temp.y); break;
							default:
								throw new IllegalArgumentException("found no direction " + direction.toString());
							}
							Image image = key.getImage();
							Util.drawImage(g, image, keyCorner, key.getImageSize());
						}						
					}
				}
			}
		}
	}
	
	private Point getRoomCorner(Room room){
		Point p = room.getCoordinates();
		return getRoomCorner(p.x, p.y);
	}
	
	private Point getRoomCorner(int i, int j){
		return new Point(Config.PADDING_ROOM_EXTERNAL+i*(Config.PADDING_ROOM_INTERNAL + Config.SIZE_ROOM_WIDTH),
				Config.PADDING_ROOM_EXTERNAL+j*(Config.PADDING_ROOM_INTERNAL + Config.SIZE_ROOM_HEIGHT));
	}

	private void drawDoors(Graphics g) {
		g.setColor(Config.COLOR_DOOR);
		Image imageDoorNorth = Util.readImage(Config.IMAGE_ROOM_DOOR_NORTH);
		Image imageDoorEast = Util.readImage(Config.IMAGE_ROOM_DOOR_EAST);
		Image imageDoorSouth = Util.readImage(Config.IMAGE_ROOM_DOOR_SOUTH);
		Image imageDoorWest = Util.readImage(Config.IMAGE_ROOM_DOOR_WEST);
		Point c = new Point((Config.SIZE_ROOM_WIDTH-Config.SIZE_THICKNESS_DOOR)/2,(Config.SIZE_ROOM_HEIGHT-Config.SIZE_THICKNESS_DOOR)/2);
		Direction[] directions = Direction.values();
		for (int i = 0; i < maze.getWidth(); i++){
			for (int j = 0; j < maze.getHeight(); j++){
				Room room = maze.getRooms().get(i).get(j);
				if (room != null){
					if (!room.isLocked()){
						Point corner = getRoomCorner(i,j);
						for (Direction dir: directions){
							if (room.hasDoorAtDirection(dir)){
								Point d = dir.getCoordinates();
								Point p = new Point(corner.x + c.x + d.x*(Config.SIZE_ROOM_WIDTH+Config.SIZE_THICKNESS_DOOR)/2
										- Math.abs(d.y)*((Config.SIZE_DOOR_ROOM-Config.SIZE_THICKNESS_DOOR)/2),
										corner.y + c.y + d.y*(Config.SIZE_ROOM_HEIGHT+Config.SIZE_THICKNESS_DOOR)/2
										- Math.abs(d.x)*((Config.SIZE_DOOR_ROOM-Config.SIZE_THICKNESS_DOOR)/2));
								Dimension dim = new Dimension(Math.abs(d.x)*Config.SIZE_THICKNESS_DOOR+Math.abs(d.y)*Config.SIZE_DOOR_ROOM,
										Math.abs(d.y)*Config.SIZE_THICKNESS_DOOR+Math.abs(d.x)*Config.SIZE_DOOR_ROOM);
								Image image = null;
								switch (dir){
								case NORTH: image = imageDoorNorth; break;
								case EAST: image = imageDoorEast; break;
								case SOUTH: image = imageDoorSouth; break;
								case WEST: image = imageDoorWest; break;
								}
								Util.drawImage(g, image, p, dim);
							}
						}
					}
				}
				/*
				if (!room.isPreviousRoomLocked()){
					Direction direction = room.getDirectionOfPreviousRoom();
					Point temp = new Point((i+1)*Config.PADDING_ROOM+i*Config.ROOM_WIDTH+Config.ROOM_WIDTH/2,
							(j+1)*Config.PADDING_ROOM+j*Config.ROOM_HEIGHT+Config.ROOM_HEIGHT/2);
					Point doorCorner;
					switch (direction){
					case NORTH: doorCorner = new Point(temp.x-(Config.SIZE_DOOR)/2,temp.y-Config.ROOM_HEIGHT/2-Config.PADDING_ROOM); break;
					case EAST: doorCorner = new Point(temp.x+Config.ROOM_WIDTH/2,temp.y-(Config.SIZE_DOOR)/2); break;
					case SOUTH:doorCorner = new Point(temp.x-(Config.SIZE_DOOR)/2,temp.y+Config.ROOM_HEIGHT/2); break;
					case WEST: doorCorner = new Point(temp.x-Config.ROOM_WIDTH/2-Config.PADDING_ROOM,temp.y-(Config.SIZE_DOOR)/2); break;
					default:
						throw new IllegalArgumentException("found no direction " + direction.toString());
					}
					Point d = direction.getCoordinates();
					Dimension size = new Dimension(Math.abs(d.x*Config.PADDING_ROOM+d.y*Config.SIZE_DOOR),
							Math.abs(d.y*Config.PADDING_ROOM+d.x*Config.SIZE_DOOR));
					g.fillRect(doorCorner.x, doorCorner.y, size.width, size.height);
				}	*/
			}
		}
	}

	private void drawRooms(Graphics g){
		Image imageWallWest = Util.readImage(Config.IMAGE_ROOM_WALL_WEST);
		Image imageWallNorth = Util.readImage(Config.IMAGE_ROOM_WALL_NORTH);
		Image imageWallEast = Util.readImage(Config.IMAGE_ROOM_WALL_EAST);
		Image imageWallSouth = Util.readImage(Config.IMAGE_ROOM_WALL_SOUTH);
		Image imageFloor = Util.readImage(Config.IMAGE_ROOM_FLOOR);
		Image imageCorner = Util.readImage(Config.IMAGE_ROOM_CORNER);
		for (int i = 0; i < maze.getWidth(); i++){
			for (int j = 0; j < maze.getHeight(); j++){
				Room room = maze.getRooms().get(i).get(j);
				if (room != null){
					if (!room.isLocked()){
						Point corner = getRoomCorner(i,j);
						g.drawImage(imageFloor, corner.x, corner.y,
								Config.SIZE_ROOM_WIDTH, Config.SIZE_ROOM_WIDTH, null);
						for (int k = 0; k < 2; k++){
							for (int l = 0; l < 2; l++){
								g.drawImage(imageCorner, corner.x + k*Config.SIZE_ROOM_WIDTH + (k-1)*Config.SIZE_THICKNESS_WALL,
										corner.y + l*Config.SIZE_ROOM_HEIGHT + (l-1)*Config.SIZE_THICKNESS_WALL, 
										Config.SIZE_THICKNESS_WALL, Config.SIZE_THICKNESS_WALL, null);
							}
						}
						g.drawImage(imageWallWest, corner.x - Config.SIZE_THICKNESS_WALL,	corner.y, 
								Config.SIZE_THICKNESS_WALL, Config.SIZE_ROOM_HEIGHT, null);
						g.drawImage(imageWallNorth, corner.x, corner.y - Config.SIZE_THICKNESS_WALL, 
								Config.SIZE_ROOM_WIDTH, Config.SIZE_THICKNESS_WALL, null);
						g.drawImage(imageWallEast, corner.x + Config.SIZE_ROOM_WIDTH, corner.y, 
								 Config.SIZE_THICKNESS_WALL, Config.SIZE_ROOM_HEIGHT, null);
						g.drawImage(imageWallSouth, corner.x, corner.y + Config.SIZE_ROOM_HEIGHT, 
								Config.SIZE_ROOM_WIDTH, Config.SIZE_THICKNESS_WALL, null);
					} else if (!maze.getRooms().get(i).get(j).isPreviousRoomLocked()){
						Point corner = getRoomCorner(i,j);
						g.setColor(Config.COLOR_ROOM_LOCKED);
						g.fillRect(corner.x, corner.y, Config.SIZE_ROOM_WIDTH, Config.SIZE_ROOM_HEIGHT);
					}
				}
			}
		}
	}
	
	private Point getPlayerWindowCorner(){
		Player player = maze.getPlayer();
		Point pos_room = player.getPosition().getPoint();
		Point corner = getRoomCorner(player.getPosition().getRoom());
		int x = corner.x + pos_room.x - Config.SIZE_WINDOW_BOARD_WIDTH/2;
		int y = corner.y + pos_room.y - Config.SIZE_WINDOW_BOARD_HEIGHT/2; 
		if (x < 0){
			x = 0;
		}
		if (y < 0){
			y = 0;
		}		
		if (x > totalSize.width - Config.SIZE_WINDOW_BOARD_WIDTH){
			x = totalSize.width - Config.SIZE_WINDOW_BOARD_WIDTH;
		}	
		if (y > totalSize.height - Config.SIZE_WINDOW_BOARD_HEIGHT){
			y = totalSize.height - Config.SIZE_WINDOW_BOARD_HEIGHT;
		}
		return new Point(x,y);
	}
	
	public class MovementListener extends KeyAdapter implements GameAction{
		private boolean[] keyPressed = new boolean[256];
		private Player player = maze.getPlayer();
		public MovementListener(){
			MainController.addGameAction(this);
		}
		public void keyPressed(KeyEvent e){
			int keyCode = e.getKeyCode();
			switch (keyCode){
			case Config.DEBUG_MOVE_ROOM_UP: player.move(Direction.NORTH); break;
			case Config.DEBUG_MOVE_ROOM_RIGHT: player.move(Direction.EAST); break;
			case Config.DEBUG_MOVE_ROOM_DOWN: player.move(Direction.SOUTH); break;
			case Config.DEBUG_MOVE_ROOM_LEFT: player.move(Direction.WEST); break;
			case Config.PLAYER_PICK_UP_ITEM: player.pickUpItems(); break;
			case Config.PLAYER_DROP_GATESTONE_PERSONAL: player.getPersonalGateStone().dropGateStone(); break;
			case Config.PLAYER_TELEPORT_TO_GATESTONE_PERSONAL: player.getPersonalGateStone().teleportTo(); break;
			case Config.PLAYER_DROP_GATESTONE_GROUP: player.getGroupGateStone().dropGateStone(); break;
			case Config.PLAYER_TELEPORT_TO_GATESTONE_GROUP: player.getGroupGateStone().teleportTo(); break;
			case Config.PLAYER_INTERACT_WITH_DOOR: player.interactWithDoor(); break;
			case Config.PLAYER_TELEPORT_TO_BASE: player.teleportToBase(); break;
			case Config.PLAYER_MOVE_UP: keyPressed[Config.PLAYER_MOVE_UP] = true; break;
			case Config.PLAYER_MOVE_RIGHT: keyPressed[Config.PLAYER_MOVE_RIGHT] = true; break;
			case Config.PLAYER_MOVE_DOWN: keyPressed[Config.PLAYER_MOVE_DOWN] = true; break;
			case Config.PLAYER_MOVE_LEFT: keyPressed[Config.PLAYER_MOVE_LEFT] = true; break;
			default: return;
			}
		}
		public void keyReleased(KeyEvent e){
			int keyCode = e.getKeyCode();
			switch (keyCode){
			case Config.PLAYER_MOVE_UP: keyPressed[Config.PLAYER_MOVE_UP] = false; break;
			case Config.PLAYER_MOVE_RIGHT: keyPressed[Config.PLAYER_MOVE_RIGHT] = false; break;
			case Config.PLAYER_MOVE_DOWN: keyPressed[Config.PLAYER_MOVE_DOWN] = false; break;
			case Config.PLAYER_MOVE_LEFT: keyPressed[Config.PLAYER_MOVE_LEFT] = false; break;
			default: return;
			}
		}
		public void keyTyped(KeyEvent e){}
		
		private void processKeys() {
			int dx = 0, dy = 0;
			if (keyPressed[Config.PLAYER_MOVE_UP]){
				dy--;
			}
			if (keyPressed[Config.PLAYER_MOVE_RIGHT]){
				dx++;
			}
			if (keyPressed[Config.PLAYER_MOVE_DOWN]){
				dy++;
			}
			if (keyPressed[Config.PLAYER_MOVE_LEFT]){
				dx--;
			}
			player.moveInRoom(dx*player.getMovementSpeed(),dy*player.getMovementSpeed());
		}
		@Override
		public void doAction() {
			processKeys();
		}
	}
	
	private class MouceClickListener extends MouseAdapter {
		public void mousePressed(MouseEvent me){
			final Point p = me.getPoint();
			for (int i = 0; i < Config.ROOM_COUNT_HORIZONTAL; i++){
				for (int j = 0; j < Config.ROOM_COUNT_VERTICAL; j++){
					Point corner = getRoomCorner(i,j);
					if (p.x >= corner.x && p.x < corner.x + Config.SIZE_ROOM_WIDTH){
						if (p.y >= corner.y && p.y < corner.y + Config.SIZE_ROOM_HEIGHT){
							final Room room = maze.getRooms().get(i).get(j);
							if (room != null){
								if (!room.isLocked()){
									final Point clickInRoom = new Point(p.x - corner.x, p.y - corner.y);
									if (me.getButton() == MouseEvent.BUTTON1){
										doLeftClick(room, clickInRoom);
									} else if (me.getButton() == MouseEvent.BUTTON3){
										doRightClick(me, room, clickInRoom);	
									}
								}
							}
						}
					}
				}
			}
		}

		private void doRightClick(MouseEvent me, final Room room, final Point clickInRoom) {
			final RightClickMenu menu = new RightClickMenu(room, clickInRoom);
			Point p = new Point(me.getPoint());
			menu.setVisible(true);	//to initalize menu and generate size
			menu.setVisible(false);
			// TODO: Fix
			/*if (p.x > Config.SIZE_WINDOW_BOARD_WIDTH - menu.getWidth()){
				p.setLocation(Config.SIZE_WINDOW_BOARD_WIDTH - menu.getWidth(), p.y);
			}
			if (p.y > Config.SIZE_WINDOW_BOARD_HEIGHT - menu.getHeight()){
				p.setLocation(p.x, Config.SIZE_WINDOW_BOARD_HEIGHT - menu.getHeight());
			}*/
			menu.addMouseListener(new MouseAdapter() {
				Dimension dim = menu.getSize();
				@Override
				public void mouseExited(MouseEvent e) {
					if (!Util.areasOverlap(new Point(0,0), dim, e.getPoint(), new Dimension(1,1))){
						menu.setVisible(false);
					}
				}
			});
			menu.show(me.getComponent(), p.x, p.y);
		}

		private void doLeftClick(final Room room, final Point clickInRoom) {
			if (maze.getPlayer().getPosition().getRoom() == room){
				Interactable interactable = getFirstInteractable(room, clickInRoom);
				maze.getPlayer().interactWith(interactable);
				//System.out.println(TAG + ".doLeftClick: " + room);
			}
		}
	}
	private class RightClickMenu extends JPopupMenu {
		private static final long serialVersionUID = -9032327060459781113L;

		private RightClickMenu(Room room, Point mouseClickInRoomPosition){
			setFocusable(false);
			Player player = maze.getPlayer();
			Interactable[] interactables = getAllInteractables(room, mouseClickInRoomPosition);
			for (Interactable interactable: interactables){
				Option[] options = interactable.getOptions(player);
				for (Option option: options){
					final Option optionFinal = option;
					final Interactable interactableFinal = interactable;
					JMenuItem menuItem = new JMenuItem(optionFinal.toString() + ": " + interactableFinal.getName());
					menuItem.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							interactableFinal.doAction(optionFinal, maze.getPlayer());
						}
					});
			        add(menuItem);
				}
			}
		}
	}

	private Interactable getFirstInteractable(Room room, Point mouseClickInRoomPosition) {
		for (Monster monster: room.getMonsters()){
			if (wasClickedOn(mouseClickInRoomPosition, monster)){
				return monster;
			}
		}
		for (Item item: room.getDroppedItems()){
			if (wasClickedOn(mouseClickInRoomPosition, item)){
				return item;
			}
		}
		for (RoomObject roomObject: room.getRoomObjects()){
			if (wasClickedOn(mouseClickInRoomPosition, roomObject)){
				return roomObject;
			}
		}
		if (room.getKey() != null){
			Key key = room.getKey();
			if (wasClickedOn(mouseClickInRoomPosition, key)){
				return key;
			}
		}
		if (maze.getPlayer().getGroupGateStone().exists()){
			GateStone ggs = maze.getPlayer().getGroupGateStone();
			Room gateStoneRoom = ggs.getPosition().getRoom();
			if (gateStoneRoom == room){
				if (wasClickedOn(mouseClickInRoomPosition, ggs)){
					return ggs;
				}
			}
		}
		return DummyObject.getInstance();
	}

	public Interactable[] getAllInteractables(Room room, Point mouseClickInRoomPosition) {
		List<Interactable> interactables = new ArrayList<Interactable>();
		for (Monster monster: room.getMonsters()){
			if (wasClickedOn(mouseClickInRoomPosition, monster)){
				interactables.add(monster);
			}
		}
		for (Item item: room.getDroppedItems()){
			if (wasClickedOn(mouseClickInRoomPosition, item)){
				interactables.add(item);
			}
		}
		for (RoomObject roomObject: room.getRoomObjects()){
			if (wasClickedOn(mouseClickInRoomPosition, roomObject)){
				interactables.add(roomObject);
			}
		}
		if (room.getKey() != null){
			Key key = room.getKey();
			if (wasClickedOn(mouseClickInRoomPosition, key)){
				interactables.add(key);
			}
		}
		if (maze.getPlayer().getGroupGateStone().exists()){
			GateStone ggs = maze.getPlayer().getGroupGateStone();
			Room gateStoneRoom = ggs.getPosition().getRoom();
			if (gateStoneRoom == room){
				if (wasClickedOn(mouseClickInRoomPosition, ggs)){
					interactables.add(ggs);
				}
			}
		}
		if (maze.getPlayer().getPersonalGateStone().exists()){
			GateStone pgs = maze.getPlayer().getPersonalGateStone();
			Room gateStoneRoom = pgs.getPosition().getRoom();
			if (gateStoneRoom == room){
				if (wasClickedOn(mouseClickInRoomPosition, pgs)){
					interactables.add(pgs);
				}
			}
		}
		return interactables.toArray(new Interactable[0]);
	}

	private boolean wasClickedOn(Point mouseClickInRoomPosition, Interactable interactable) {
		Point p1 = mouseClickInRoomPosition;
		Dimension dim1 = new Dimension(1,1);
		Point p2 = interactable.getPosition().getPoint();
		Dimension dim2 = interactable.getImageSize();
		if (Util.areasOverlap(p1, dim1, p2, dim2,Config.PADDING_MOUSE_CLICK)){
			return true;
		}
		return false;
	}
}
