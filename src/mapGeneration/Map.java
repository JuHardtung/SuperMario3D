package mapGeneration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import textures.ModelTexture;

import static tools.Constants.BLOCK_SIZE;

public class Map {
	
	
	Loader loader = new Loader();
	
	
	/*################################################
	#######       SUPER MARIO 3D  HI-RES       #######
	##################################################*/
	
		
		//MARIO
		RawModel marioModel = OBJLoader.loadObjModel("models/char_mario", loader);
		TexturedModel marioTextured = new TexturedModel(marioModel, new ModelTexture(loader.loadGameTextures("models/char_mario")));
		
		//BLOCK_BRICK
		RawModel brickModel = OBJLoader.loadObjModel("models/block_brick", loader);
		TexturedModel brickTextured = new TexturedModel(brickModel, new ModelTexture(loader.loadGameTextures("models/block_brick")));
		
		//BLOCK_FLOOR
		RawModel floorModel = OBJLoader.loadObjModel("models/block_floor", loader);
		TexturedModel floorTextured = new TexturedModel(floorModel, new ModelTexture(loader.loadGameTextures("models/block_floor")));

		//BLOCK_QUESTION
		RawModel questionModel = OBJLoader.loadObjModel("models/block_question", loader);
		TexturedModel questionTextured = new TexturedModel(questionModel, new ModelTexture(loader.loadGameTextures("models/block_question")));

		//BLOCK_WALL
		RawModel wallModel = OBJLoader.loadObjModel("models/block_wall", loader);
		TexturedModel wallTextured = new TexturedModel(wallModel, new ModelTexture(loader.loadGameTextures("models/block_wall")));

		//BLOCK_PIPE
		RawModel pipeModel = OBJLoader.loadObjModel("models/block_pipe", loader);
		TexturedModel pipeTextured = new TexturedModel(pipeModel, new ModelTexture(loader.loadGameTextures("models/block_pipe")));
		
		//ITEM_MUSHROOM
		RawModel mushroomModel = OBJLoader.loadObjModel("models/item_mushroom", loader);
		TexturedModel mushroomTexturedRed = new TexturedModel(mushroomModel, new ModelTexture(loader.loadGameTextures("models/item_mushroomRed")));
		TexturedModel mushroomTexturedGreen = new TexturedModel(mushroomModel, new ModelTexture(loader.loadGameTextures("models/item_mushroomGreen")));
		
		//BG_MOUNTAIN
		RawModel mountainModel = OBJLoader.loadObjModel("models/bg_mountain", loader);
		TexturedModel mountainTextured = new TexturedModel(mountainModel, new ModelTexture(loader.loadGameTextures("models/bg_mountain")));
		
		//BG_BUSHES
		RawModel bushModel = OBJLoader.loadObjModel("models/bg_bush", loader);
		TexturedModel bushTextured = new TexturedModel(bushModel, new ModelTexture(loader.loadGameTextures("models/bg_bush")));
		TexturedModel bushDoubleTextured = new TexturedModel(bushModel, new ModelTexture(loader.loadGameTextures("models/bg_bush_double")));
		TexturedModel bushTripleTextured = new TexturedModel(bushModel, new ModelTexture(loader.loadGameTextures("models/bg_bush_triple")));

		//CHAR_GUMBA
		RawModel gumbaModel = OBJLoader.loadObjModel("models/char_gumba", loader);
		TexturedModel gumbaTextured = new TexturedModel(gumbaModel, new ModelTexture(loader.loadGameTextures("models/char_gumba")));
		
		/*################################################
		#######       SUPER MARIO 3D  LOW-RES      #######
		##################################################*/
		
		//BASIC CUBE
		RawModel basicCube = OBJLoader.loadObjModel("models/basic_cube", loader);
		
		
		TexturedModel brickCubeTextured = new TexturedModel(basicCube, new ModelTexture(loader.loadGameTextures("models/block_brickCube")));
		TexturedModel floorCubeTextured = new TexturedModel(basicCube, new ModelTexture(loader.loadGameTextures("models/block_floorCube")));
		TexturedModel questionCubeTextured = new TexturedModel(basicCube, new ModelTexture(loader.loadGameTextures("models/block_questionCube")));
		TexturedModel wallCubeTextured = new TexturedModel(basicCube, new ModelTexture(loader.loadGameTextures("models/block_wallCube")));

		
	
	/*######################
	####  LEVEL DESIGN  ####
	########################*/
	
	//FLOOR
		
	public List<Entity> generateLevelOne() {
		
		List<Entity> entitiesLevelOne = new ArrayList<Entity>();
		
		List<Entity> allFloorBlocks = generateFloor(floorTextured);
		List<Entity> bgBlocks = generateBG(mountainTextured, bushTextured, bushDoubleTextured, bushTripleTextured);
		List<Entity> questionBlocks = generateQuestionBlocks(questionTextured);
		List<Entity> brickBlocks = generateBrickBlocks(brickTextured);
		List<Entity> items = generateItems(mushroomTexturedGreen, mushroomTexturedRed);
		List<Entity> pipes = generatePipes(pipeTextured);
		List<Entity> enemys = generateEnemys(gumbaTextured);
		List<Entity> wallBlocks = generateWalls(wallTextured);
				
		for(Entity entity:allFloorBlocks){
			entitiesLevelOne.add(entity);
		}
		for(Entity entity:questionBlocks){
			entitiesLevelOne.add(entity);
		}
		for(Entity entity:brickBlocks){
			entitiesLevelOne.add(entity);
		}
		for(Entity entity:wallBlocks){
			entitiesLevelOne.add(entity);
		}
		for(Entity entity:items){
			entitiesLevelOne.add(entity);
		}
		for(Entity entity:pipes){
			entitiesLevelOne.add(entity);
		}
		for(Entity entity:enemys){
			entitiesLevelOne.add(entity);
		}
		for(Entity entity:bgBlocks){
			entitiesLevelOne.add(entity);
		}
		
		return entitiesLevelOne;
	}
	
	public List<Entity> generateLevelOneCube() {
		
		List<Entity> entitiesLevelOne = new ArrayList<Entity>();
		
		List<Entity> allFloorBlocks = generateFloor(floorCubeTextured);
		List<Entity> bgBlocks = generateBG(mountainTextured, bushTextured, bushDoubleTextured, bushTripleTextured);
		List<Entity> questionBlocks = generateQuestionBlocks(questionCubeTextured);
		List<Entity> brickBlocks = generateBrickBlocks(brickCubeTextured);
		List<Entity> items = generateItems(mushroomTexturedGreen, mushroomTexturedRed);
		List<Entity> pipes = generatePipes(pipeTextured);
		List<Entity> enemys = generateEnemys(gumbaTextured);
		List<Entity> wallBlocks = generateWalls(wallCubeTextured);
		
		for(Entity entity:allFloorBlocks){
			entitiesLevelOne.add(entity);
		}
		for(Entity entity:questionBlocks){
			entitiesLevelOne.add(entity);
		}
		for(Entity entity:brickBlocks){
			entitiesLevelOne.add(entity);
		}
		for(Entity entity:wallBlocks){
			entitiesLevelOne.add(entity);
		}
		for(Entity entity:items){
			entitiesLevelOne.add(entity);
		}
		for(Entity entity:pipes){
			entitiesLevelOne.add(entity);
		}
		for(Entity entity:enemys){
			entitiesLevelOne.add(entity);
		}
		for(Entity entity:bgBlocks){
			entitiesLevelOne.add(entity);
		}
		
		return entitiesLevelOne;
	}
		
	
	private List<Entity> generateQuestionBlocks(TexturedModel questionModel){
		
		List<Entity> allQuestionBlocks = new ArrayList<Entity>();		

		allQuestionBlocks.add(new Entity(questionModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*17), 0,0,0,1.0f));
		
		allQuestionBlocks.add(new Entity(questionModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*22), 0,0,0,1.0f));
		allQuestionBlocks.add(new Entity(questionModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*24), 0,0,0,1.0f));
		allQuestionBlocks.add(new Entity(questionModel, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*23), 0,0,0,1.0f));
		
		allQuestionBlocks.add(new Entity(questionModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*78), 0,0,0,1.0f));
		
		allQuestionBlocks.add(new Entity(questionModel, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*94), 0,0,0,1.0f));
		
		allQuestionBlocks.add(new Entity(questionModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*106), 0,0,0,1.0f));
		
		allQuestionBlocks.add(new Entity(questionModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*109), 0,0,0,1.0f));
		allQuestionBlocks.add(new Entity(questionModel, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*109), 0,0,0,1.0f));

		allQuestionBlocks.add(new Entity(questionModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*112), 0,0,0,1.0f));
		
		allQuestionBlocks.add(new Entity(questionModel, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*129), 0,0,0,1.0f));
		allQuestionBlocks.add(new Entity(questionModel, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*130), 0,0,0,1.0f));
		
		allQuestionBlocks.add(new Entity(questionModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*170), 0,0,0,1.0f));

		return allQuestionBlocks;
	}
	
	private List<Entity> generateBrickBlocks(TexturedModel brickModel){
		
		List<Entity> allBrickBlocks = new ArrayList<Entity>();
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*21), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*23), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*25), 0,0,0,1.0f));
		
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*65), 0,0,0,1.0f));
		
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*77), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*79), 0,0,0,1.0f));
		
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*80), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*81), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*82), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*83), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*84), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*85), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*86), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*87), 0,0,0,1.0f));
		
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*91), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*92), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*93), 0,0,0,1.0f));
		
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*94), 0,0,0,1.0f));
		
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*100), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*101), 0,0,0,1.0f));
		
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*118), 0,0,0,1.0f));
		
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*121), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*122), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*123), 0,0,0,1.0f));
		
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*128), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*129), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*130), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*131), 0,0,0,1.0f));
		
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*168), 0,0,0,1.0f));
		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*169), 0,0,0,1.0f));

		allBrickBlocks.add(new Entity(brickModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*171), 0,0,0,1.0f));

		return allBrickBlocks;
	}
	
	private List<Entity> generateItems(TexturedModel mushroomModelGreen, TexturedModel mushroomModelRed){
		
		List<Entity> allItems = new ArrayList<Entity>();
		

		allItems.add(new Entity(mushroomModelRed, new Vector3f(0,BLOCK_SIZE*4,BLOCK_SIZE*22), 0,0,0,1.0f));
		
		allItems.add(new Entity(mushroomModelGreen, new Vector3f(0,BLOCK_SIZE*4,BLOCK_SIZE*78), 0,0,0,1.0f));
		
		allItems.add(new Entity(mushroomModelRed, new Vector3f(0,BLOCK_SIZE*8,BLOCK_SIZE*109), 0,0,0,1.0f));

		return allItems;
	}
	
	private List<Entity> generatePipes(TexturedModel pipeModel){
		
		List<Entity> allPipes = new ArrayList<Entity>();
		

		allPipes.add(new Entity(pipeModel, new Vector3f(0,BLOCK_SIZE*-1,BLOCK_SIZE*29.5f), 0,180,0,1.0f));
		
		allPipes.add(new Entity(pipeModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*39.5f), 0,180,0,1.0f));
		
		allPipes.add(new Entity(pipeModel, new Vector3f(0,BLOCK_SIZE*0.5f,BLOCK_SIZE*47.5f), 0,180,0,1.0f));
		
		allPipes.add(new Entity(pipeModel, new Vector3f(0,BLOCK_SIZE*0.5f,BLOCK_SIZE*58.5f), 0,180,0,1.0f));
		
		allPipes.add(new Entity(pipeModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*163.5f), 0,180,0,1.0f));
		
		allPipes.add(new Entity(pipeModel, new Vector3f(0,BLOCK_SIZE*-1,BLOCK_SIZE*177.5f), 0,180,0,1.0f));




		return allPipes;
	}
	
	
	private List<Entity> generateEnemys(TexturedModel gumbaModel){
		
		List<Entity> allEnemys = new ArrayList<Entity>();
		
		allEnemys.add(new Entity(gumbaModel, new Vector3f(0,BLOCK_SIZE*8,BLOCK_SIZE*82), 0,-90,0,1.0f));
		
		allEnemys.add(new Entity(gumbaModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*95), 0,-90,0,1.0f));
		allEnemys.add(new Entity(gumbaModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*97.5f), 0,-90,0,1.0f));
		
		allEnemys.add(new Entity(gumbaModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*123.9f), 0,-90,0,1.0f));
		allEnemys.add(new Entity(gumbaModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*125.4f), 0,-90,0,1.0f));
		allEnemys.add(new Entity(gumbaModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*127.2f), 0,-90,0,1.0f));
		allEnemys.add(new Entity(gumbaModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*128.8f), 0,-90,0,1.0f));

		return allEnemys;
	}
	
	private List<Entity> generateWalls(TexturedModel wallModel){
		List<Entity> allWalls = new ArrayList<Entity>();
		
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*134),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*135),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*135),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*136),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*136),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*136),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*137),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*137),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*137),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*137),0,90,0,1.0f));

		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*140),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*140),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*140),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*140),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*141),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*141),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*141),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*142),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*142),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*143),0,90,0,1.0f));

		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*148),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*149),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*149),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*150),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*150),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*150),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*151),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*151),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*151),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*151),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*154),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*154),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*154),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*154),0,90,0,1.0f));
		
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*155),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*155),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*155),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*155),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*156),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*156),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*156),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*157),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*157),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*158),0,90,0,1.0f));
		
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*181),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*182),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*182),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*183),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*183),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*183),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*184),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*184),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*184),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*184),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*185),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*185),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*185),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*185),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*4,BLOCK_SIZE*185),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*186),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*186),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*186),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*186),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*4,BLOCK_SIZE*186),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*5,BLOCK_SIZE*186),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*187),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*187),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*187),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*187),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*4,BLOCK_SIZE*187),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*5,BLOCK_SIZE*187),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*6,BLOCK_SIZE*187),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*188),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*188),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*188),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*188),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*4,BLOCK_SIZE*188),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*5,BLOCK_SIZE*188),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*6,BLOCK_SIZE*188),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*188),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*189),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*1,BLOCK_SIZE*189),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*2,BLOCK_SIZE*189),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*3,BLOCK_SIZE*189),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*4,BLOCK_SIZE*189),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*5,BLOCK_SIZE*189),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*6,BLOCK_SIZE*189),0,90,0,1.0f));
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*7,BLOCK_SIZE*189),0,90,0,1.0f));
		
		allWalls.add(new Entity(wallModel, new Vector3f(0,BLOCK_SIZE*0,BLOCK_SIZE*198),0,90,0,1.0f));


		
		return allWalls;
	}
	
	private List<Entity> generateFloor(TexturedModel floorModel){
				
		List<Entity> allFloorBlocks = new ArrayList<Entity>();		

		
		for(int i=0;i<69; i++){
			float z = BLOCK_SIZE*i;
			for(int l=0;l<4; l++){
				float y = -BLOCK_SIZE-BLOCK_SIZE*l;
				for (int j=0;j<5; j++){
					float x = -BLOCK_SIZE *2 + BLOCK_SIZE * j;
					//brickTextured.getTexture().isHasTransparency();
					allFloorBlocks.add(new Entity(floorModel, new Vector3f(x,y,z), 0, 0,0,1.0f));
				}
			}
		}
		
		for(int i=71;i<86; i++){
			float z = BLOCK_SIZE*i;
			for(int l=0;l<4; l++){
				float y = -BLOCK_SIZE-BLOCK_SIZE*l;
				for (int j=0;j<5; j++){
					float x = -BLOCK_SIZE *2 + BLOCK_SIZE * j;
					allFloorBlocks.add(new Entity(floorModel, new Vector3f(x,y,z), 0, 0,0,1.0f));
				}
			}
		}
		
		for(int i=88;i<152; i++){
			float z = BLOCK_SIZE*i;
			for(int l=0;l<4; l++){
				float y = -BLOCK_SIZE-BLOCK_SIZE*l;
				for (int j=0;j<5; j++){
					float x = -BLOCK_SIZE *2 + BLOCK_SIZE * j;
					allFloorBlocks.add(new Entity(floorModel, new Vector3f(x,y,z), 0, 0,0,1.0f));
				}
			}
		}	
		
		for(int i=154;i<212; i++){
			float z = BLOCK_SIZE*i;
			for(int l=0;l<4; l++){
				float y = -BLOCK_SIZE-BLOCK_SIZE*l;
				for (int j=0;j<5; j++){
					float x = -BLOCK_SIZE *2 + BLOCK_SIZE * j;
					allFloorBlocks.add(new Entity(floorModel, new Vector3f(x,y,z), 0, 0,0,1.0f));
				}
			}
		}	
		

		return allFloorBlocks;
	}
	

	
	private List<Entity> generateBG(TexturedModel mountainModel, TexturedModel bushModel, TexturedModel bushDoubleModel, TexturedModel bushTripleModel){
		
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
				bgBlocks.add(new Entity(mountainModel, new Vector3f(x-20,y-20,z),0,-90,0,1));
				lastRandomBlock = 0;
				break;
			case 1:
				bgBlocks.add(new Entity(bushModel, new Vector3f(x,y-5,z),0,-90,0,0.5f));
				bushModel.getTexture().setHasTransparency(true);
				bushModel.getTexture().setUseFakeLighting(true);
				lastRandomBlock = 1;
				break;
			case 2:
				bgBlocks.add(new Entity(bushDoubleModel, new Vector3f(x,y+10,z),0,-90,0,0.75f));
				bushDoubleModel.getTexture().setHasTransparency(true);
				bushDoubleModel.getTexture().setUseFakeLighting(true);
				lastRandomBlock = 2;
				break;
			case 3:			
				bgBlocks.add(new Entity(bushTripleModel, new Vector3f(x,y+20,z),0,-90,0,1f));
				bushTripleModel.getTexture().setHasTransparency(true);
				bushTripleModel.getTexture().setUseFakeLighting(true);
				lastRandomBlock = 3;
				break;
			default: 				
				bgBlocks.add(new Entity(bushModel, new Vector3f(x,y,z),0,-90,0,0.5f));
				lastRandomBlock = 0;
				break;
			}
		}
		return bgBlocks;
	}
	
	private int isSameRdm(int lastRdm, int rdm){
		Random random = new Random();

		if (lastRdm == rdm){
			lastRdm = rdm;
			rdm = random.nextInt(4);
			isSameRdm(lastRdm, rdm);
		} 
		return rdm;
	}
	
	
}
