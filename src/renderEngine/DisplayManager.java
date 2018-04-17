package renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.util.vector.Vector2f;

import static tools.Constants.DISPLAY_WIDTH;
import static tools.Constants.DISPLAY_HEIGHT;
import static tools.Constants.FPS_CAP;

public class DisplayManager {
	
	private static long lastFrameTime;
	private static float delta;
	
	
	public static void createDisplay(){

		ContextAttribs attribs = new ContextAttribs(3,2).withForwardCompatible(true).withProfileCore(true);
		
		try {
		Display.setDisplayMode(new org.lwjgl.opengl.DisplayMode(DISPLAY_WIDTH, DISPLAY_HEIGHT));
		Display.create(new PixelFormat().withSamples(8).withDepthBits(24), attribs);
		Display.setTitle("Super Mario 3D");
		GL11.glEnable(GL13.GL_MULTISAMPLE);
		} catch (LWJGLException e){
			e.printStackTrace();
		}
		
		GL11.glViewport(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
		lastFrameTime = getCurrentTime();
	}
	
	public static void updateDisplay() {
		Display.sync(FPS_CAP);
		Display.update();
		long currentFrameTime = getCurrentTime();
		delta = (currentFrameTime - lastFrameTime)/1000f;
		lastFrameTime = currentFrameTime;
	}
	
	public static float getFrameTimeSeconds(){
		return delta;
	}
	
	public static void closeDisplay() {
		Display.destroy();
	}
	
	private static long getCurrentTime(){
		return Sys.getTime()*1000/Sys.getTimerResolution();
	}
	
	public static Vector2f getNormalizedMouseCoordinates() {
		float normalizedX = -1.0f + 2.0f * (float) Mouse.getX() / (float) Display.getWidth();
		float normalizedY = 1.0f - 2.0f * (float) Mouse.getY() / (float) Display.getHeight();		
		return new Vector2f(normalizedX, normalizedY);

	}
}
