package main.game.maze;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.game.Config;
import main.game.maze.builder.Builder;
import main.game.maze.builder.SimpleBuilder;
import main.game.maze.door.SimpleDoor;
import main.game.maze.interactable.creature.player.Player;
import main.game.maze.interactable.item.Key;
import main.game.maze.room.Room;

public class Maze {
	Room startingRoom;
	int width;
	int height;
	private List<Key> availableKeys = new ArrayList<>(Key.KEYS);
	private Player player;
	private Builder builder = new SimpleBuilder(this);
	private Room bossRoom;
	Map<Integer, HashMap<Integer, Room>> rooms = new HashMap<Integer, HashMap<Integer, Room>>();
	public Maze(){		
		width = Config.ROOM_COUNT_HORIZONTAL;
		height = Config.ROOM_COUNT_VERTICAL;
		build();
		draw();	
	}
	
	private void build() {
		initializeLabyrinth();
		createStartingRoom();
		buildLabyrinth();
		createCriticalPath();
	}
	
	public void rebuild(){
		availableKeys = new ArrayList<>(Key.KEYS);
		Key.resetAllPositions();
		initializeLabyrinth();
		createStartingRoom();
		buildLabyrinth();
		createCriticalPath();		
	}

	private void createCriticalPath() {
		builder.createCriticalPath();
	}
	
	private void buildLabyrinth(){
		builder.buildLabyrinth();
	}
	
	private void createStartingRoom(){
		builder.createStartingRoom();
	}

	private void initializeLabyrinth() {
		for (int i = 0; i < getWidth(); i++){
			rooms.put(i, new HashMap<Integer,Room>());
			for (int j = 0; j < getHeight(); j++){
				rooms.get(i).put(j, null);
			}
		}
	}
	
	public List<Direction> validDirections(Room room){
		List<Direction> directions = new ArrayList<Direction>();
		for (Direction d : Direction.values()){
			if (room.getDoorByDirection(d)!=null){
				continue;
			}
			Point p = d.add(room.getCoordinates()); 
			if (p.x >= 0 && p.x < getWidth()){
				if (p.y >= 0 && p.y < getHeight()){
					if (rooms.get(p.x).get(p.y) == null){
						directions.add(d);
					}
				}
			}
		}
		return directions;
	}
	
	
	protected void draw(){
		String[][] s = new String[getHeight()*2+1][getWidth()*2+1];
		for (int i = 0; i < getHeight()*2+1; i++){
			for (int j = 0; j < getWidth()*2+1; j++){
				s[i][j] = " ";
			}
		}
		//initialize corners
		for (int i = 0; i < getHeight()*2+1; i+=2){
			for (int j = 0; j < getWidth()*2+1; j+=2){
				s[i][j] = "+";
			}
		}
		//initialize all borders
		//horizontal
		for (int i = 0; i < getHeight()*2+1; i+=2){
			for (int j = 1; j < getWidth()*2+1; j+=2){
				s[i][j] = "�-";
			}
		}
		//vertical
		for (int i = 1; i < getHeight()*2+1; i+=2){
			for (int j = 0; j < getWidth()*2+1; j+=2){
				s[i][j] = "|";
			}
		}
		for (int i = 0; i < getHeight(); i++){
			for (int j = 0; j < getWidth(); j++){
				//s[i*2+1][j*2+1] = labyrinth.get(j).get(i).getCoordinates().x+""+labyrinth.get(j).get(i).getCoordinates().y;
				s[i*2+1][j*2+1] = "  ";
				Room room = rooms.get(j).get(i);
				if (room != null){
					if (room.getDoorByDirection(Direction.EAST)!=null){
						s[i*2+1][j*2+2] = " ";
					}
					if (room.getDoorByDirection(Direction.SOUTH)!=null){
						s[i*2+2][j*2+1] = "  ";
					}
				}
				if (room.isCritical()){
					s[i*2+1][j*2+1] = String.format("%02d", room.getDistance());
				} else if (room == bossRoom){
					s[i*2+1][j*2+1] = "XX";
				} else if (room == startingRoom){
					s[i*2+1][j*2+1] = "00";
				}
			}
		}
		//display
		for (int i = 0; i < getHeight()*2+1; i++){
			for (int j = 0; j < getWidth()*2+1; j++){
				System.out.print(s[i][j]);
			}
			System.out.println();
		}
		
	}

	public Room getStartingRoom() {
		return startingRoom;
	}

	public void setStartingRoom(Room startingRoom) {
		this.startingRoom = startingRoom;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Map<Integer, HashMap<Integer, Room>> getRooms() {
		return rooms;
	}

	public List<Key> getAvailableKeys() {
		return availableKeys;
	}
	
	public void removeKey(Key key){
		availableKeys.remove(key);
	}

	public void setPlayer(Player player) {
		this.player = player;
		player.createStartingPosition(startingRoom);
		player.setStartingPosition();
	}
	
	public Player getPlayer(){
		return player;
	}

	protected boolean makeKeyDoor(int currentRoomCount) {		
		return builder.makeKeyDoor(currentRoomCount, width*height);
	}

	protected Key placeKeyInMaze(List<Room> currentRooms) {
		return builder.placeKeyInMaze(currentRooms);
	}

	public void setBossRoom(Room room) {
		bossRoom = room;
	}

	public void addKey(Key key) {
		availableKeys.add(key);
	}

	public void removeKey(Room destinationRoom) {
		if (destinationRoom.getKey() != null){
			Key key = destinationRoom.getKey();
			Room room = findLockingRoom(key);
			room.getPreviousRoom().setDoor(room.getDirectionOfPreviousRoom().getOpposite(), new SimpleDoor(room));
			destinationRoom.setKey(null);
			key.resetPosition();
		}
	}

	private Room findLockingRoom(Key key) {
		for (int i = 0; i < getWidth(); i++){
			for (int j = 0; j < getHeight(); j++){
				Room room = getRooms().get(i).get(j);
				if (room != null){
					if (room.isLockedWithKey()){
						if (room.getLockingKey()==key){
							return room;
						}
					}
				}
			}
		}
		throw new IllegalArgumentException("key has not been used in maze");
	}

	public Room getBossRoom() {
		return bossRoom;
	}
}
