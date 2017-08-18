package textures;

public class ModelTexturePack {
	
	private ModelTexture modelTexture;
	private ModelTexture shadowTexture;
	
	
	public ModelTexturePack(ModelTexture modelTexture, ModelTexture shadowTexture) {
		super();
		this.modelTexture = modelTexture;
		this.shadowTexture = shadowTexture;
	}


	public ModelTexture getModelTexture() {
		return modelTexture;
	}


	public ModelTexture getShadowTexture() {
		return shadowTexture;
	}
}
