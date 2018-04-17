package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private float distanceFromPlayer = 100;
	private float angleAroundPlayer = 0;
	
	private Vector3f position = new Vector3f(80,20,0);
	private float pitch = 15;
	private float yaw;
	private float roll;
	private float cameraOffsetY;
	
	private Player player;
	
	public Camera(Player player) {
		this.player = player;
	}
	
	public void move(){
		calculateZoom();
		calculatePitch();
		calculateAngleAroundPlayer();
		float horizontalDistance = calculateHorizontalDistance();
		float verticalDistance = calculateVerticalDistance();
		calculateCameraPosition(horizontalDistance, verticalDistance);
		this.yaw = 180 - (player.getRotY() + angleAroundPlayer);
		
		//CLASSIC PERSPECTIVE
		if (Keyboard.isKeyDown(Keyboard.KEY_E)){
			angleAroundPlayer = 90;
			pitch = 0;
			distanceFromPlayer = 180;
			cameraOffsetY = 70;
		}
		//3D PERSPECTIVE
		if (Keyboard.isKeyDown(Keyboard.KEY_R)){
			angleAroundPlayer = 0;
			pitch = 15;
			distanceFromPlayer = 100;
			cameraOffsetY = 0;
		}
		/*System.out.println("YAW: " + yaw);
		System.out.println("PITCH: " + pitch);
		System.out.println("DISTANCE: " + distanceFromPlayer);*/

	}
	
	public void invertPitch(){
		this.pitch = -pitch;
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	
	private void calculateCameraPosition(float horizDistance, float verticDistance){
		float theta = player.getRotY() + angleAroundPlayer;
		float offsetX = (float) (horizDistance * Math.sin(Math.toRadians(theta)));
		float offsetZ = (float) (horizDistance * Math.cos(Math.toRadians(theta)));
		float offsetY = (float) (verticDistance + cameraOffsetY);
		position.x = player.getPosition().x - offsetX;
		position.y = player.getPosition().y + offsetY;
		position.z = player.getPosition().z - offsetZ;
	}
	
	private float calculateHorizontalDistance(){
		return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
	}
	
	private float calculateVerticalDistance(){
		return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
	}
	
	private void calculateZoom(){
		float zoomLevel = Mouse.getDWheel() * 0.1f;
		distanceFromPlayer -= zoomLevel;
	}
	
	private void calculatePitch(){
		if(Mouse.isButtonDown(1)){
			float pitchChange = Mouse.getDY() * 0.1f;
			pitch -= pitchChange;
		}
	}
	
	private void calculateAngleAroundPlayer(){
		if(Mouse.isButtonDown(0)){
			float angleChange = Mouse.getDX() * 0.3f;
			angleAroundPlayer -= angleChange;
		}
	}
	
	
}
