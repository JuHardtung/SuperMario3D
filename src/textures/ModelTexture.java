package textures;

public class ModelTexture {

	private int textureID;
	private int normalMap;
	private int depthMap;
	
	private float shineDamper = 1;
	private float reflectivity = 0;
	
	private boolean hasTransparency = false;
	private boolean useFakeLighting = false;
	
	private int numberOfRows = 1;
	
	public ModelTexture(int id){
		
		this.textureID = id;
		
	}	
	
    public int getNumberOfRows() {
        return numberOfRows;
    }
 
    public int getNormalMap() {
        return normalMap;
    }
 
    public int getDepthMap() {
		return depthMap;
	}

	public void setDepthMap(int depthMap) {
		this.depthMap = depthMap;
	}

	public void setNormalMap(int normalMap) {
        this.normalMap = normalMap;
    }
    
	public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }
	
	public boolean isUseFakeLighting() {
		return useFakeLighting;
	}

	public void setUseFakeLighting(boolean useFakeLighting) {
		this.useFakeLighting = useFakeLighting;
	}

	public boolean isHasTransparency() {
		return hasTransparency;
	}

	public void setHasTransparency(boolean hasTransparency) {
		this.hasTransparency = hasTransparency;
	}
	
	public float getShineDamper() {
		return shineDamper;
	}

	public void setShineDamper(float shineDamper) {
		this.shineDamper = shineDamper;
	}

	public float getReflectivity() {
		return reflectivity;
	}

	public void setReflectivity(float reflectivity) {
		this.reflectivity = reflectivity;
	}
	
	public int getID(){
		return this.textureID;
	}
}
