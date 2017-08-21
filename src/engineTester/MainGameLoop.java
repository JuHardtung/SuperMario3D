package engineTester;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import audio.AudioMaster;
import buttons.AbstractButton;
import buttons.IButton;
import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;
import fontRendering.TextMaster;
import guis.GuiRenderer;
import guis.GuiTexture;
import mapGeneration.Map;
import menuGeneration.MenuOptions;
import models.RawModel;
import models.TexturedModel;
import normalMappingObjConverter.NormalMappedObjLoader;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import textures.ModelTexture;
import water.WaterFrameBuffers;
import water.WaterRenderer;
import water.WaterShader;
import water.WaterTile;

public class MainGameLoop {
	
	private static final int BLOCK_SIZE = 17;

	public static void main(String[] args) {
				
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		TextMaster.init(loader);
		
		//GUI
		List<GuiTexture> guis = new ArrayList<GuiTexture>();
		GuiTexture gui = new GuiTexture(loader.loadGameTextures("gui"), new Vector2f(0, 0), new Vector2f(1,1));
		guis.add(gui);
		GuiRenderer guiRenderer = new GuiRenderer(loader);
		
		
		//MARIO
		
		RawModel marioModel = OBJLoader.loadObjModel("models/char_mario", loader);
		RawModel marioNormalModel = NormalMappedObjLoader.loadOBJ("models/char_mario", loader);
		TexturedModel marioTexturedNormalModel = new TexturedModel(marioNormalModel, new ModelTexture(loader.loadGameTextures("models/char_mario")));
		marioTexturedNormalModel.getTexture().setNormalMap(loader.loadGameTextures("models/char_marioNormal"));


		TexturedModel marioTexturedModel = new TexturedModel(marioModel, new ModelTexture(loader.loadGameTextures("models/char_mario")));
		
		marioTexturedNormalModel.getTexture().setShineDamper(10);
		marioTexturedNormalModel.getTexture().setReflectivity(0.5f);

		
		Player player = new Player(marioTexturedNormalModel, new Vector3f(0,0,0),0,0,0,1);	
		Camera camera = new Camera(player);
		MasterRenderer renderer = new MasterRenderer(loader, camera);

		

		//LIGHT
		Light sun = new Light(new Vector3f(-300000,200000,-300000), new Vector3f(1f,1f,1f));
		List<Light> lights = new ArrayList<Light>();
		
		lights.add(sun);
//		lights.add(new Light(new Vector3f(0,10,0), new Vector3f(10,0,0), new Vector3f(1,0.01f, 0.002f)));
//		lights.add(new Light(new Vector3f(0,10,25), new Vector3f(0,0,10), new Vector3f(1,0.01f, 0.002f)));

		
		
		//WATER
		WaterFrameBuffers buffers = new WaterFrameBuffers();
		WaterShader waterShader = new WaterShader();
		WaterRenderer waterRenderer = new WaterRenderer(loader, waterShader, renderer.getProjectionMatrix(),buffers);
		List<WaterTile> waters = new ArrayList<WaterTile>();
		
		for (int i=0; i<30;i++){
			int z = -750 + 300*i;
				for(int j=0; j<12;j++){
					int x = -750+300*j;
					waters.add(new WaterTile(x, z, -BLOCK_SIZE));
				}
		}

		//GUI visual for shadow Map
		GuiTexture shadowMap = new GuiTexture(renderer.getShadowMapTexture(), new Vector2f(0.25f, -0.75f), new Vector2f(0.25f, 0.25f));
//		guis.add(shadowMap);
		
		//GUI visual for reflect/refract texture for water
		GuiTexture refraction = new GuiTexture(buffers.getRefractionTexture(), new Vector2f(-0.25f, -0.75f), new Vector2f(0.25f, 0.25f));
		GuiTexture reflection = new GuiTexture(buffers.getReflectionTexture(), new Vector2f(-0.75f, -0.75f), new Vector2f(0.25f, 0.25f));
//		guis.add(refraction);
//		guis.add(reflection);
		

		
		MenuOptions menu = new MenuOptions();
		
		List<GUIText> menuList = menu.generateESCMenu();
		List<GUIText> levelsList = menu.generateLevelsGui();				
		
		//GUIText levelsButton = new GUIText("Levels", 1, marioFont, new Vector2f(0.33f, 0.35f), 0.33f, true, new Vector2f(0.33f,0.04f));
				
		
		/*################################################
		#######           SUPER MARIO 3D           #######
		##################################################*/
		
		
		//AUDIO
		AudioMaster.init();
		AudioMaster.setListenerData(player.getPosition().x,player.getPosition().y,player.getPosition().z);
		AL10.alDistanceModel(AL11.AL_EXPONENT_DISTANCE_CLAMPED);
		
		/*int buffer = AudioMaster.loadSound("audio/bounce.wav");
		Source gumbaAudio = new Source(1f,6f,15f);
		gumbaAudio.setPosition(0, 0, 0);
		gumbaAudio.setLooping(true);
		gumbaAudio.play(buffer);*/
		
		//MAP GENERATION
		Map map = new Map();
		
		List<Entity> allFloorBlocks = map.generateFloor();
		List<Entity> bgBlocks = map.generateBG();
		List<Entity> questionBlocks = map.generateQuestionBlocks();
		List<Entity> brickBlocks = map.generateBrickBlocks();
		List<Entity> items = map.generateItems();
		List<Entity> pipes = map.generatePipes();
		List<Entity> enemys = map.generateEnemys();
		List<Entity> wallBlocks = map.generateWalls();

		List<Entity> entities = new ArrayList<Entity>();
		List<Entity> normalMapEntities = new ArrayList<Entity>();
				
		normalMapEntities.add(player);
		//entities.add(player);

		
		for(Entity entity:allFloorBlocks){
			entities.add(entity);
		}
		for(Entity entity:questionBlocks){
			entities.add(entity);
		}
		for(Entity entity:brickBlocks){
			entities.add(entity);
		}
		for(Entity entity:wallBlocks){
			entities.add(entity);
		}
		for(Entity entity:items){
			entities.add(entity);
		}
		for(Entity entity:pipes){
			entities.add(entity);
		}
		for(Entity entity:enemys){
			entities.add(entity);
		}
		for(Entity entity:bgBlocks){
			entities.add(entity);
		}

		//*************** MAIN GAME LOOP BELOW ****************
		
		while(!Display.isCloseRequested()){

			camera.move();
			player.move();
			
			renderer.renderShadowMap(entities, normalMapEntities, sun);
			AudioMaster.setListenerData(player.getPosition().x,player.getPosition().y,player.getPosition().z);
			
			//gumbaAudio.setPosition(player.getPosition().x, player.getPosition().y, player.getPosition().z);
			
			GL11.glEnable(GL30.GL_CLIP_DISTANCE0);
			
			//buttons

			while(Keyboard.next()) {
			// SHOW MENU IF NOTHING IS SHOWING + ESC PRESSED
				if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && menuList.get(0).isHidden() && levelsList.get(0).isHidden()) {
					
					for(GUIText guiParts:menuList) {
					guiParts.show();
					guiParts.setIsHidden(false);
					}
					
			//HIDE EVERYTHING IF ANYTHING IS SHOWING + ESC PRESSED
				} else if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && (!menuList.get(0).isHidden() || !levelsList.get(0).isHidden())) {

					for(GUIText guiParts:menuList) {
					guiParts.hide();
					guiParts.setIsHidden(true);
					}
					for(GUIText guiParts:levelsList) {
					guiParts.hide();
					guiParts.setIsHidden(true);
					}
				}
			}

			// show LEVELS + hide MENU if MENU.LEVELS is clicked + MENU showing + LEVELS hidden
			if(!menuList.get(0).isHidden() && levelsList.get(0).isHidden() && menuList.get(1).isClicked()) {

				for(GUIText guiParts:menuList) {
				guiParts.hide();
				guiParts.setIsHidden(true);
				}
				for(GUIText guiParts:levelsList) {
				guiParts.show();
				guiParts.setIsHidden(false);
				
				menuList.get(1).setClicked(false);
				}
				
			// show MENU + hide LEVELS if LEVELS.BACK is clicked + LEVELS showing + MENU hidden
			} else if(menuList.get(0).isHidden() && !levelsList.get(0).isHidden() && levelsList.get(3).isClicked()) {

				for(GUIText guiParts:menuList) {
				guiParts.show();
				guiParts.setIsHidden(false);
				}
				for(GUIText guiParts:levelsList) {
				guiParts.hide();
				guiParts.setIsHidden(true);
				}
				levelsList.get(3).setClicked(false);
			}

				
			for(GUIText guiParts5:menuList) {
				guiParts5.update();
			}
			for(GUIText guiParts6:levelsList) {
				guiParts6.update();
			}
				
			//reflection texture
			buffers.bindReflectionFrameBuffer();
			float distance = 2*(camera.getPosition().y - waters.get(0).getHeight());
			camera.getPosition().y -= distance;
			camera.invertPitch();
			renderer.renderScene(entities, normalMapEntities, camera, lights, new Vector4f(0,1,0, -waters.get(0).getHeight()));
			camera.getPosition().y += distance;
			camera.invertPitch();
			
			//refraction texture
			buffers.bindRefractionFrameBuffer();
			renderer.renderScene(entities, normalMapEntities, camera, lights, new Vector4f(0, -1, 0, waters.get(0).getHeight()));
		
			//render to Screen
			GL11.glDisable(GL30.GL_CLIP_DISTANCE0);
			buffers.unbindCurrentFrameBuffer();
			renderer.renderScene(entities, normalMapEntities, camera, lights, new Vector4f(0,-1,0,100000));
			for(WaterTile waterTiles : waters){
				waterRenderer.render(waters,camera);
			}

			guiRenderer.render(guis);
			TextMaster.render();			
			
			DisplayManager.updateDisplay();
		}
		
		//gumbaAudio.delete();
		TextMaster.cleanUp();
		buffers.cleanUp();
		waterShader.cleanUp();
		guiRenderer.cleanUp();
		renderer.cleanUp();
		loader.cleanUp();
		AudioMaster.cleanUp();
		DisplayManager.closeDisplay();
	}

}
