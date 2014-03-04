package main.game;

import java.awt.Color;
import java.awt.event.KeyEvent;

public final class Config {
	public static final int SCREEN_REFRESH_DELAY = 50;
	public static final int ROOM_COUNT_HORIZONTAL = 8;
	public static final int ROOM_COUNT_VERTICAL = 8;
	public static final int INVENTORY_COUNT_HORIZONTAL = 5;
	public static final int INVENTORY_COUNT_VERTICAL = 6;
	
	//Sizes
	public static final int SIZE_WINDOW_BOARD_WIDTH = 1000;
	public static final int SIZE_WINDOW_BOARD_HEIGHT = 600;
	public static final int SIZE_WINDOW_MINIMAP_WIDTH = 300;
	public static final int SIZE_WINDOW_MINIMAP_HEIGHT = 200;
	public static final int SIZE_WINDOW_KEYBAG_WIDTH = SIZE_WINDOW_BOARD_WIDTH;
	public static final int SIZE_WINDOW_KEYBAG_HEIGHT = 30;	//2*padding for key image + keybag key image size
	public static final int SIZE_WINDOW_PLAYERPANEL_WIDTH = SIZE_WINDOW_MINIMAP_WIDTH;
	public static final int SIZE_WINDOW_PLAYERPANEL_HEIGHT = SIZE_WINDOW_BOARD_HEIGHT + SIZE_WINDOW_KEYBAG_HEIGHT - SIZE_WINDOW_MINIMAP_HEIGHT;
	public static final int SIZE_WINDOW_HEIGHT = SIZE_WINDOW_BOARD_HEIGHT + SIZE_WINDOW_KEYBAG_HEIGHT;
	public static final int SIZE_WINDOW_WIDTH = SIZE_WINDOW_BOARD_WIDTH + SIZE_WINDOW_MINIMAP_WIDTH;
	
	public static final int SIZE_ROOM_WIDTH = 200;
	public static final int SIZE_ROOM_HEIGHT = 200;
	public static final int SIZE_HEALTHBAR_WIDTH = 20;
	public static final int SIZE_HEALTHBAR_HEIGHT = 5;
	public static final int SIZE_DOOR_ROOM = 40;
	public static final int SIZE_DOOR_MINIMAP = 80;	//relative to room width/height
	public static final int SIZE_THICKNESS_WALL = 7; // width/height of wall and corner image
	public static final int SIZE_THICKNESS_DOOR = 8;
	public static final int SIZE_PLAYERPANEL_SWITCHTAB_HEIGHT = 30;
	
	//Paddings
	public static final int PADDING_INVENTORY_INTERNAL = 5;
	public static final int PADDING_INVENTORY_EXTERNAL = 20;
	public static final int PADDING_ROOM_EXTERNAL = 100;
	public static final int PADDING_ROOM_INTERNAL = 20;
	public static final int PADDING_MINIMAP_INTERNAL = 60;	//relative to room width/height
	public static final int PADDING_MINIMAP_EXTERNAL = 100;	//relative to room width/height
	public static final int PADDING_KEY_IMAGE = 5;
	public static final int PADDING_ITEM_PICKUP = 5;
	public static final int PADDING_HEALTHBAR = 5;
	public static final int PADDING_MOUSE_CLICK = 5;
	
	//KeyBindings
	public static final int PLAYER_MOVE_UP = KeyEvent.VK_W;
	public static final int PLAYER_MOVE_DOWN = KeyEvent.VK_S;
	public static final int PLAYER_MOVE_LEFT = KeyEvent.VK_A;
	public static final int PLAYER_MOVE_RIGHT = KeyEvent.VK_D;
	public static final int PLAYER_DROP_GATESTONE_PERSONAL = KeyEvent.VK_Q;
	public static final int PLAYER_TELEPORT_TO_GATESTONE_PERSONAL = KeyEvent.VK_E;
	public static final int PLAYER_DROP_GATESTONE_GROUP = KeyEvent.VK_1;
	public static final int PLAYER_TELEPORT_TO_GATESTONE_GROUP = KeyEvent.VK_2;
	public static final int PLAYER_PICK_UP_ITEM = KeyEvent.VK_SPACE;
	public static final int PLAYER_INTERACT_WITH_DOOR = KeyEvent.VK_SHIFT;
	public static final int PLAYER_TELEPORT_TO_BASE = KeyEvent.VK_H;

	public static final int DEBUG_MOVE_ROOM_UP = KeyEvent.VK_UP;
	public static final int DEBUG_MOVE_ROOM_DOWN = KeyEvent.VK_DOWN;
	public static final int DEBUG_MOVE_ROOM_LEFT = KeyEvent.VK_LEFT;
	public static final int DEBUG_MOVE_ROOM_RIGHT = KeyEvent.VK_RIGHT;
	
	//Colours
	public static final Color COLOR_BACKGROUND = Color.BLACK;
	public static final Color COLOR_ROOM_UNLOCKED = Color.LIGHT_GRAY;
	public static final Color COLOR_ROOM_LOCKED = Color.DARK_GRAY;
	public static final Color COLOR_DOOR = Color.LIGHT_GRAY;
	public static final Color COLOR_ROOM_START = Color.LIGHT_GRAY;
	public static final Color COLOR_HEALTHBAR_HEALTHY = Color.GREEN;
	public static final Color COLOR_HEALTHBAR_DAMAGED = Color.RED;
	
	//Images
	public static final String IMAGES_FOLDER = "/images/";
	public static final String IMAGES_FOLDER_KEYS = "keys/";
	public static final String IMAGES_FOLDER_CREATURES = "creatures/";
	public static final String IMAGES_FOLDER_ITEMS = "items/";
	public static final String IMAGES_FOLDER_ROOMS = "rooms/";
	public static final String IMAGES_FOLDER_OBJECTS = "objects/";
	public static final String IMAGE_ROOM_CORNER = IMAGES_FOLDER_ROOMS + "room_corner.png";
	public static final String IMAGE_ROOM_WALL_WEST = IMAGES_FOLDER_ROOMS + "room_wall_west.png";
	public static final String IMAGE_ROOM_WALL_NORTH = IMAGES_FOLDER_ROOMS + "room_wall_north.png";
	public static final String IMAGE_ROOM_WALL_EAST = IMAGES_FOLDER_ROOMS + "room_wall_east.png";
	public static final String IMAGE_ROOM_WALL_SOUTH = IMAGES_FOLDER_ROOMS + "room_wall_south.png";
	public static final String IMAGE_ROOM_FLOOR = IMAGES_FOLDER_ROOMS + "room_floor.png";
	public static final String IMAGE_ROOM_DOOR_WEST = IMAGES_FOLDER_ROOMS + "room_door_west.png";
	public static final String IMAGE_ROOM_DOOR_NORTH = IMAGES_FOLDER_ROOMS + "room_door_north.png";
	public static final String IMAGE_ROOM_DOOR_EAST = IMAGES_FOLDER_ROOMS + "room_door_east.png";
	public static final String IMAGE_ROOM_DOOR_SOUTH = IMAGES_FOLDER_ROOMS + "room_door_south.png";
	
	public static final int DELAY_TELEPORT_TO_BASE = 4000;
		
	private Config(){
		throw new IllegalAccessError("Config must not be initiated");
	};
}
