package renderEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import models.TexturedModel;
import normalMappingRenderer.NormalMappingRenderer;
import parallaxMappingRenderer.ParallaxMappingRenderer;
import shaders.StaticShader;
import shadows.ShadowMapMasterRenderer;
import skybox.SkyboxRenderer;
import terrain.Terrain;
import terrain.TerrainRenderer;
import terrain.TerrainShader;

public class MasterRenderer {
	
	public static final float FOV = 70;
	public static final float NEAR_PLANE = 0.1f;
	public static final float FAR_PLANE = 10000.0f;
	
	public static  float RED = 0.37f;
	public static  float GREEN = 0.58f;
	public static  float BLUE = 0.98f;

	
	private Matrix4f projectionMatrix;
	
	private StaticShader shader = new StaticShader();
	private EntityRenderer renderer;
	
	private TerrainRenderer terrainRenderer;
	private TerrainShader terrainShader = new TerrainShader();
	
	private NormalMappingRenderer normalMapRenderer;
	private ParallaxMappingRenderer parallaxMapRenderer;
	
	
	private HashMap<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel,List<Entity>>();
	private HashMap<TexturedModel, List<Entity>> normalMapEntities = new HashMap<TexturedModel,List<Entity>>();
	private HashMap<TexturedModel, List<Entity>> parallaxMapEntities = new HashMap<TexturedModel,List<Entity>>();


	private List<Terrain> terrains = new ArrayList<Terrain>();
	
	private SkyboxRenderer skyboxRenderer;
	private ShadowMapMasterRenderer shadowMapRenderer;
	
	
	public MasterRenderer(Loader loader, Camera cam){
		
		enableCulling();
		createProjectionMatrix();
		renderer = new EntityRenderer(shader, projectionMatrix);
		terrainRenderer = new TerrainRenderer(terrainShader, projectionMatrix);
		skyboxRenderer = new SkyboxRenderer(loader, projectionMatrix);
		this.shadowMapRenderer = new ShadowMapMasterRenderer(cam);
		normalMapRenderer = new NormalMappingRenderer(projectionMatrix);
		parallaxMapRenderer = new ParallaxMappingRenderer(projectionMatrix);
	}
	
	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}

	public void renderScene(List<Entity> entities, List<Entity> normalEntities, List<Entity> parallaxEntities, Player player, Camera camera, List<Light> lights, Vector4f clipPlane){
		for (Entity entity:entities){
			processEntity(entity);
		}
		for (Entity entity:normalEntities){
			processNormalMapEntity(entity);
		}
		for (Entity entity:parallaxEntities){
			processParallaxMapEntity(entity);
		}
		processEntity(player);
		render(lights, camera, clipPlane);
	}

	public void render(List<Light> lights, Camera camera, Vector4f clipPlane){
		prepare();
		shader.start();
		shader.loadClipPlane(clipPlane);
		shader.loadSkyColour(RED, GREEN, BLUE);
		shader.loadLights(lights);
		shader.loadViewMatrix(camera);
		shader.loadMapSize();
		shader.loadShadowDistance();
		renderer.render(entities, shadowMapRenderer.getToShadowMapSpaceMatrix());
		shader.stop();
		skyboxRenderer.render(camera,RED, GREEN, BLUE);
		normalMapRenderer.render(normalMapEntities, clipPlane, lights, camera);
		parallaxMapRenderer.render(parallaxMapEntities, clipPlane, lights, camera);
//		terrainShader.start();
//		terrainShader.loadLight(sun);
//		terrainShader.loadViewMatrix(camera);
//		terrainRenderer.render(terrains);
//		terrainShader.stop();
//		terrains.clear();
		entities.clear();
		normalMapEntities.clear();
		parallaxMapEntities.clear();
		
	}
	
	public void processTerrain(Terrain terrain){
		terrains.add(terrain);
	}
	
	public void processEntity(Entity entity){
		TexturedModel entityModel = entity.getModel();
		List<Entity> batch = entities.get(entityModel);
		if(batch!=null){
			batch.add(entity);
		} else {
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add(entity);
			entities.put(entityModel, newBatch);
		}
	}
	
	public void processNormalMapEntity(Entity entity){
		TexturedModel entityModel = entity.getModel();
		List<Entity> batch = normalMapEntities.get(entityModel);
		if(batch!=null){
			batch.add(entity);
		} else {
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add(entity);
			normalMapEntities.put(entityModel, newBatch);
		}
	}
	
	public void processParallaxMapEntity(Entity entity){
		TexturedModel entityModel = entity.getModel();
		List<Entity> batch = parallaxMapEntities.get(entityModel);
		if(batch!=null){
			batch.add(entity);
		} else {
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add(entity);
			parallaxMapEntities.put(entityModel, newBatch);
		}
	}
	
	public static void enableCulling(){
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}
	
	public static void disableCulling(){
		GL11.glDisable(GL11.GL_CULL_FACE);
	}
	
	public void renderShadowMap(List<Entity> entityList, List<Entity>  entityNomalList, Player player, Light sun) {
		for(Entity entity : entityList) {
			processEntity(entity);
		}
		for(Entity entity : entityNomalList) {
			processEntity(entity);
		}
		processEntity(player);
		shadowMapRenderer.render(entities, sun);
		entities.clear();
	}
	
	public int getShadowMapTexture() {
		return shadowMapRenderer.getShadowMap();
	}
	
	public void cleanUp(){
		shader.cleanUp();
		terrainShader.cleanUp();
		shadowMapRenderer.cleanUp();
		skyboxRenderer.cleanUp();
		normalMapRenderer.cleanUp();
		parallaxMapRenderer.cleanUp();
	}
	
	public void prepare() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClearColor(RED, GREEN, BLUE, 1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
		GL13.glActiveTexture(GL13.GL_TEXTURE5);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, getShadowMapTexture());
	}
	
	private void createProjectionMatrix(){
		projectionMatrix = new Matrix4f();
		float aspectRatio = (float) Display.getWidth()/(float) Display.getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV/2f))));
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;
		
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
		projectionMatrix.m33 = 0;
	}
}
