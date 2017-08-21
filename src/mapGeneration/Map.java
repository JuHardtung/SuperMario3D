package mapGeneration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.util.vector.Vector3f;

import audio.AudioMaster;
import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import textures.ModelTexture;

public class Map {
	
	private static final float BLOCK_SIZE = 17.25f;

	
	Loader loader = new Loader();
	

	
	/*################################################
	#######           SUPER MARIO 3D           #######
	##################################################*/
	
	//MARIO
	RawModel marioModel = OBJLoader.loadObjModel("models/char_mario", loader);
	TexturedModel marioTextured = new TexturedModel(marioModel, new ModelTexture(loader.loadGameTextures("models/char_mario")));
	
	
	//BG_MOUNTAIN
	RawModel mountainModel = OBJLoader.loadObjModel("models/bg_mountain", loader);
	TexturedModel mountainTextured = new TexturedModel(mountainModel, new ModelTexture(loader.loadGameTextures("models/bg_mountain")));
		
	//BUSHES
	RawModel bushModel = OBJLoader.loadObjModel("models/bg_bush", loader);
	TexturedModel bushTextured = new TexturedModel(bushModel, new ModelTexture(loader.loadGameTextures("models/bg_bush")));
	TexturedModel bushDoubleTextured = new TexturedModel(bushModel, new ModelTexture(loader.loadGameTextures("models/bg_bush_double")));
	TexturedModel bushTripleTextured = new TexturedModel(bushModel, new ModelTexture(loader.loadGameTextures("models/bg_bush_triple")));
	
	//BLOCK_BRICK
	RawModel brickModel = OBJLoader.loadObjModel("models/block_brick", loader);
	TexturedModel brickTextured = new TexturedModel(brickModel, new ModelTexture(loader.loadGameTextures("models/block_brick")));
	
	//BLOCK_FLOOR
	RawModel floorModel = OBJLoader.loadObjModel("models/block_floor", loader);
	TexturedModel floorTextured = new TexturedModel(floorModel, new ModelTexture(loader.loadGameTextures("models/block_floor")));
	
	//BLOCK_PIPE
	RawModel pipeModel = OBJLoader.loadObjModel("models/block_pipe", loader);
	TexturedModel pipeTextured = new TexturedModel(pipeModel, new ModelTexture(loader.loadGameTextures("models/block_pipe")));

	//BLOCK_QUESTION
	RawModel questionModel = OBJLoader.loadObjModel("models/block_question", loader);
	TexturedModel questionTextured = new TexturedModel(questionModel, new ModelTexture(loader.loadGameTextures("models/block_question")));
	
	//BLOCK_WALL
	RawModel wallModel = OBJLoader.loadObjModel("models/block_wall", loader);
	TexturedModel wallTextured = new TexturedModel(wallModel, new ModelTexture(loader.loadGameTextures("models/block_wall")));

	//ITEM_MUSHROOM
	RawModel mushroomModel = OBJLoader.loadObjModel("models/item_mushroom", loader);
	TexturedModel mushroomTexturedRed = new TexturedModel(mushroomModel, new ModelTexture(loader.loadGameTextures("models/item_mushroomRed")));
	TexturedModel mushroomTexturedGreen = new TexturedModel(mushroomModel, new ModelTexture(loader.loadGameTextures("models/item_mushroomGreen")));

			
	//CHAR_GUMBA
	RawModel gumbaModel = OBJLoader.loadObjModel("models/char_gumba", loader);
	TexturedModel gumbaTextured = new TexturedModel(gumbaModel, new ModelTexture(loader.loadGameTextures("models/char_gumba")));

	
	
	/*######################
	####  LEVEL DESIGN  ####
	########################*/
	
	//FLOOR
	
	public List<Entity> generateQuestionBlocks(){
		
		List<Entity> allQuestionBlocks = new ArrayList<Entity>();		

		allQuestionBlocks.add(new Entity(questionTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*17), 0,0,0,1.0f));
		
		allQuestionBlocks.add(new Entity(questionTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*22), 0,0,0,1.0f));
		allQuestionBlocks.add(new Entity(questionTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*24), 0,0,0,1.0f));
		allQuestionBlocks.add(new Entity(questionTextured, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*23), 0,0,0,1.0f));
		
		allQuestionBlocks.add(new Entity(questionTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*78), 0,0,0,1.0f));
		
		allQuestionBlocks.add(new Entity(questionTextured, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*94), 0,0,0,1.0f));
		
		allQuestionBlocks.add(new Entity(questionTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*106), 0,0,0,1.0f));
		
		allQuestionBlocks.add(new Entity(questionTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*109), 0,0,0,1.0f));
		allQuestionBlocks.add(new Entity(questionTextured, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*109), 0,0,0,1.0f));

		allQuestionBlocks.add(new Entity(questionTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*112), 0,0,0,1.0f));
		
		allQuestionBlocks.add(new Entity(questionTextured, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*129), 0,0,0,1.0f));
		allQuestionBlocks.add(new Entity(questionTextured, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*130), 0,0,0,1.0f));
		
		allQuestionBlocks.add(new Entity(questionTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*170), 0,0,0,1.0f));

		return allQuestionBlocks;
	}
	
	public List<Entity> generateBrickBlocks(){
		
		List<Entity> allBrickBlocks = new ArrayList<Entity>();
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*21), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*23), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*25), 0,0,0,1.0f));
		
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*65), 0,0,0,1.0f));
		
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*77), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*79), 0,0,0,1.0f));
		
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*80), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*81), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*82), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*83), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*84), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*85), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*86), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*87), 0,0,0,1.0f));
		
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*91), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*92), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*93), 0,0,0,1.0f));
		
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*94), 0,0,0,1.0f));
		
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*100), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*101), 0,0,0,1.0f));
		
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*118), 0,0,0,1.0f));
		
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*121), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*122), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*123), 0,0,0,1.0f));
		
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*128), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*129), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*130), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*131), 0,0,0,1.0f));
		
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*168), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*169), 0,0,0,1.0f));

		allBrickBlocks.add(new Entity(brickTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*171), 0,0,0,1.0f));

		return allBrickBlocks;
	}
	
	public List<Entity> generateItems(){
		
		List<Entity> allItems = new ArrayList<Entity>();
		

		allItems.add(new Entity(mushroomTexturedRed, new Vector3f(0,BLOCK_SIZE*4,BLOCK_SIZE*22), 0,0,0,1.0f));
		
		allItems.add(new Entity(mushroomTexturedGreen, new Vector3f(0,BLOCK_SIZE*4,BLOCK_SIZE*78), 0,0,0,1.0f));
		
		allItems.add(new Entity(mushroomTexturedRed, new Vector3f(0,BLOCK_SIZE*8,BLOCK_SIZE*109), 0,0,0,1.0f));

		return allItems;
	}
	
	public List<Entity> generatePipes(){
		
		List<Entity> allPipes = new ArrayList<Entity>();
		

		allPipes.add(new Entity(pipeTextured, new Vector3f(0,BLOCK_SIZE*-1,BLOCK_SIZE*29.5f), 0,180,0,1.0f));
		
		allPipes.add(new Entity(pipeTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*39.5f), 0,180,0,1.0f));
		
		allPipes.add(new Entity(pipeTextured, new Vector3f(0,BLOCK_SIZE*0.5f,BLOCK_SIZE*47.5f), 0,180,0,1.0f));
		
		allPipes.add(new Entity(pipeTextured, new Vector3f(0,BLOCK_SIZE*0.5f,BLOCK_SIZE*58.5f), 0,180,0,1.0f));
		
		allPipes.add(new Entity(pipeTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*163.5f), 0,180,0,1.0f));
		
		allPipes.add(new Entity(pipeTextured, new Vector3f(0,BLOCK_SIZE*-1,BLOCK_SIZE*177.5f), 0,180,0,1.0f));




		return allPipes;
	}
	
	
	public List<Entity> generateEnemys(){
		
		List<Entity> allEnemys = new ArrayList<Entity>();
		
		allEnemys.add(new Entity(gumbaTextured, new Vector3f(0,BLOCK_SIZE*8,BLOCK_SIZE*82), 0,-90,0,1.0f));
		
		allEnemys.add(new Entity(gumbaTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*95), 0,-90,0,1.0f));
		allEnemys.add(new Entity(gumbaTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*97.5f), 0,-90,0,1.0f));
		
		allEnemys.add(new Entity(gumbaTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*123.9f), 0,-90,0,1.0f));
		allEnemys.add(new Entity(gumbaTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*125.4f), 0,-90,0,1.0f));
		allEnemys.add(new Entity(gumbaTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*127.2f), 0,-90,0,1.0f));
		allEnemys.add(new Entity(gumbaTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*128.8f), 0,-90,0,1.0f));

		return allEnemys;
	}
	
	public List<Entity> generateWalls(){
		List<Entity> allWalls = new ArrayList<Entity>();
		
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*134),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*135),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*135),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*136),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*136),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*136),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*137),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*137),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*137),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*137),0,90,0,1.0f));

		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*140),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*140),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*140),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*140),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*141),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*141),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*141),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*142),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*142),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*143),0,90,0,1.0f));

		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*148),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*149),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*149),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*150),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*150),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*150),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*151),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*151),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*151),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*151),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*154),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*154),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*154),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*154),0,90,0,1.0f));
		
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*155),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*155),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*155),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*155),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*156),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*156),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*156),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*157),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*157),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*158),0,90,0,1.0f));
		
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*181),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*182),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*182),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*183),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*183),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*183),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*184),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*184),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*184),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*184),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*185),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*185),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*185),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*185),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*4,BLOCK_SIZE*185),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*186),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*186),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*186),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*186),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*4,BLOCK_SIZE*186),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*5,BLOCK_SIZE*186),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*187),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*187),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*187),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*187),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*4,BLOCK_SIZE*187),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*5,BLOCK_SIZE*187),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*6,BLOCK_SIZE*187),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*188),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*188),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*188),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*188),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*4,BLOCK_SIZE*188),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*5,BLOCK_SIZE*188),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*6,BLOCK_SIZE*188),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*188),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*189),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*189),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*189),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*189),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*4,BLOCK_SIZE*189),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*5,BLOCK_SIZE*189),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*6,BLOCK_SIZE*189),0,90,0,1.0f));
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*189),0,90,0,1.0f));
		
		allWalls.add(new Entity(wallTextured, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*198),0,90,0,1.0f));


		
		return allWalls;
	}
	
	public List<Entity> generateFloor(){
				
		List<Entity> allFloorBlocks = new ArrayList<Entity>();		

		
		for(int i=0;i<69; i++){
			float z = BLOCK_SIZE*i;
			for(int l=0;l<4; l++){
				float y = -BLOCK_SIZE-BLOCK_SIZE*l;
				for (int j=0;j<5; j++){
					float x = -BLOCK_SIZE *2 + BLOCK_SIZE * j;
					brickTextured.getTexture().isHasTransparency();
					allFloorBlocks.add(new Entity(floorTextured, new Vector3f(x,y,z), 0, 0,0,1.0f));
				}
			}
		}
		
		for(int i=71;i<86; i++){
			float z = BLOCK_SIZE*i;
			for(int l=0;l<4; l++){
				float y = -BLOCK_SIZE-BLOCK_SIZE*l;
				for (int j=0;j<5; j++){
					float x = -BLOCK_SIZE *2 + BLOCK_SIZE * j;
					allFloorBlocks.add(new Entity(floorTextured, new Vector3f(x,y,z), 0, 0,0,1.0f));
				}
			}
		}
		
		for(int i=88;i<152; i++){
			float z = BLOCK_SIZE*i;
			for(int l=0;l<4; l++){
				float y = -BLOCK_SIZE-BLOCK_SIZE*l;
				for (int j=0;j<5; j++){
					float x = -BLOCK_SIZE *2 + BLOCK_SIZE * j;
					allFloorBlocks.add(new Entity(floorTextured, new Vector3f(x,y,z), 0, 0,0,1.0f));
				}
			}
		}	
		
		for(int i=154;i<212; i++){
			float z = BLOCK_SIZE*i;
			for(int l=0;l<4; l++){
				float y = -BLOCK_SIZE-BLOCK_SIZE*l;
				for (int j=0;j<5; j++){
					float x = -BLOCK_SIZE *2 + BLOCK_SIZE * j;
					allFloorBlocks.add(new Entity(floorTextured, new Vector3f(x,y,z), 0, 0,0,1.0f));
				}
			}
		}	
		

		return allFloorBlocks;
	}
	

	
	public List<Entity> generateBG(){
		
		List<Entity> bgBlocks = new ArrayList<Entity>();
		int lastRandomBlock = 0;
		Random random = new Random();

		
		for(int k=0; k<20;k++){
			
			float x = 150 + random.nextFloat()*-40;
			float y = random.nextFloat() * 25;
			float z = random.nextFloat()*80+(k*120);
			
			int randomBlock = random.nextInt(4);
			int hmm = isSameRdm(lastRandomBlock, randomBlock);
			
			randomBlock = hmm;
		
			switch(randomBlock) {
			case 0:
				bgBlocks.add(new Entity(mountainTextured, new Vector3f(x-20,y-20,z),0,-90,0,1));
				lastRandomBlock = 0;
				break;
			case 1:
				bgBlocks.add(new Entity(bushTextured, new Vector3f(x,y-5,z),0,-90,0,0.5f));
				bushTextured.getTexture().setHasTransparency(true);
				bushTextured.getTexture().setUseFakeLighting(true);
				lastRandomBlock = 1;
				break;
			case 2:
				bgBlocks.add(new Entity(bushDoubleTextured, new Vector3f(x,y+10,z),0,-90,0,0.75f));
				bushDoubleTextured.getTexture().setHasTransparency(true);
				bushDoubleTextured.getTexture().setUseFakeLighting(true);
				lastRandomBlock = 2;
				break;
			case 3:			
				bgBlocks.add(new Entity(bushTripleTextured, new Vector3f(x,y+20,z),0,-90,0,1f));
				bushTripleTextured.getTexture().setHasTransparency(true);
				bushTripleTextured.getTexture().setUseFakeLighting(true);
				lastRandomBlock = 3;
				break;
			default: 				
				bgBlocks.add(new Entity(bushTextured, new Vector3f(x,y,z),0,-90,0,0.5f));
				lastRandomBlock = 0;
				break;
			}
		}
		return bgBlocks;
	}
	
	private int isSameRdm(int lastRdm, int rdm){
		Random random = new Random();

		//System.out.println("lastrdm: " + lastRdm);
		//System.out.println("rdm: " + rdm);

		if (lastRdm == rdm){
			lastRdm = rdm;
			rdm = random.nextInt(4);
			isSameRdm(lastRdm, rdm);
		} 
		return rdm;
	}
	
	
}
