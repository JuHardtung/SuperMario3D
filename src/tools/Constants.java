package tools;

import org.lwjgl.util.vector.Vector4f;


public class Constants {
	
	public static final String RES_LOC = "/res/";
	
	//CANONICAL UNIT VECTORS
	public static final Vector4f UNIT_UP = new Vector4f(0, 1, 0, 0);
	public static final Vector4f UNIT_FORWARD = new Vector4f(0, 0, -1, 0);
	
	//DISPLAY
	public static final int DISPLAY_WIDTH = 1080;
	public static final int DISPLAY_HEIGHT = 720;
	public static final int FPS_CAP = 120;
	
	//PLAYER
	public static final float RUN_SPEED = 150; //150
	public static final float TURN_SPEED = 160;
	public static final float GRAVITY = -200; //-200
	public static final float JUMP_POWER = 150;
	
	//LIGHTS
	public static final int MAX_LIGHTS = 4;
	
	//BLOCKS
	public static final float BLOCK_SIZE = 16.0001f;

	//WATER
    public static final float TILE_SIZE = 300;

    //SHADOW MAPS
	public static final int SHADOW_MAP_SIZE = 8192;
	public static final float SHADOW_DISTANCE = 600.0f;
	public static final float OFFSET = 30;

/* Power of 2
 * 	1024
 * 	2048
 * 	4096
 * 	8192
 * 	16384
 */
	
}
