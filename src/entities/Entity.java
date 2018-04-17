package entities;

import org.lwjgl.util.vector.Vector3f;

import models.TexturedModel;

public class Entity {
	private TexturedModel model;
	private Vector3f position;
	private Vector3f oldPosition;
	private float rotX, rotY, rotZ;
	private float scale;
	private float xAxisOffset, yAxisOffset, zAxisOffset;
	
	private float xMin, xMax, yMin, yMax, zMin, zMax;
	
	private int textureIndex = 0;
	
	public Entity(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale, float xAxisOffset, float yAxisOffset, float zAxisOffset) {
		super();
		this.model = model;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
		this.xAxisOffset = xAxisOffset;
		this.yAxisOffset = yAxisOffset;
		this.zAxisOffset = zAxisOffset;

	}
	
	public Entity(TexturedModel model, int index, Vector3f position, float rotX, float rotY, float rotZ, float scale, float xAxisOffset, float yAxisOffset, float zAxisOffset) {
		super();
		this.textureIndex = index;
		this.model = model;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
		this.xAxisOffset = xAxisOffset;
		this.yAxisOffset = yAxisOffset;
		this.zAxisOffset = zAxisOffset;
	}
	
	
    public float getTextureXOffset(){
        int column = textureIndex%model.getTexture().getNumberOfRows();
        return (float)column/(float)model.getTexture().getNumberOfRows();
    }
     
    public float getTextureYOffset(){
        int row = textureIndex/model.getTexture().getNumberOfRows();
        return (float)row/(float)model.getTexture().getNumberOfRows();
    }

	public void increasePosition(float dx, float dy, float dz){
		this.position.x+=dx;
		this.position.y+=dy;
		this.position.z+=dz;
	}
	
	public void increaseRotation(float dx, float dy, float dz){
		this.rotX+=dx;
		this.rotY+=dy;
		this.rotZ+=dz;
	}
	
	public TexturedModel getModel() {
		return model;
	}

	public void setModel(TexturedModel model) {
		this.model = model;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}
	
	public Vector3f getMaxBounds() {
		
		xMax = getPosition().x + xAxisOffset;
		yMax = getPosition().y + yAxisOffset;
		zMax = getPosition().z + zAxisOffset;
			
		return new Vector3f(xMax, yMax, zMax);
	}
	
	public Vector3f getMinBounds() {
		
		xMin = getPosition().x - xAxisOffset;
		yMin = getPosition().y - yAxisOffset;
		zMin = getPosition().z - zAxisOffset;
			
		return new Vector3f(xMin, yMin, zMin);
	}

	public float getRotX() {
		return rotX;
	}

	public void setRotX(float rotX) {
		this.rotX = rotX;
	}

	public float getRotY() {
		return rotY;
	}

	public void setRotY(float rotY) {
		this.rotY = rotY;
	}

	public float getRotZ() {
		return rotZ;
	}

	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public Vector3f getOldPosition() {
		return oldPosition;
	}

	public void setOldPosition(Vector3f oldPosition) {
		this.oldPosition = oldPosition;
	}
}
