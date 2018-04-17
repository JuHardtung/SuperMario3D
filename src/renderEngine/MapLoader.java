package renderEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import normalMappingObjConverter.NormalMappedObjLoader;
import textures.ModelTexture;

import static tools.Constants.BLOCK_SIZE;
import static tools.Constants.RES_LOC;

public class MapLoader {
	
	private static final String brick = 	"B";
	private static final String floor = 	"F";
	private static final String question =  "Q";
	private static final String wall = 		"W";
	private static final String itemR = 	"IR";
	private static final String itemG = 	"IG";
	private static final String pipe = 		"P";
	private static final String gumba = 	"G";
	
	private static final String darkGumba =	"DG";
	private static final String darkBrick =	"DB";
	private static final String darkFloor =	"DF";
	private static final String darkQuestion =	"DQ";
	private static final String darkWall =	"DW";
	
	Loader loader = new Loader();
	
	//BASIC_CUBE
	RawModel basicCubeModel = OBJLoader.loadObjModel("models/basic_cube", loader);
	RawModel basicCubeNormalModel = NormalMappedObjLoader.loadOBJ("models/basic_cube", loader);

	
	TexturedModel basicBrickTextured = new TexturedModel(basicCubeModel, new ModelTexture(loader.loadGameTextures("block_brick")));
	TexturedModel basicFloorTextured = new TexturedModel(basicCubeModel, new ModelTexture(loader.loadGameTextures("block_floor")));
	TexturedModel basicQuestionTextured = new TexturedModel(basicCubeModel, new ModelTexture(loader.loadGameTextures("block_question")));
	
	TexturedModel basicDarkBrickTextured = new TexturedModel(basicCubeModel, new ModelTexture(loader.loadGameTextures("block_brick_dark")));
	TexturedModel basicDarkFloorTextured = new TexturedModel(basicCubeModel, new ModelTexture(loader.loadGameTextures("block_floor_dark")));
	TexturedModel basicDarkQuestionTextured = new TexturedModel(basicCubeModel, new ModelTexture(loader.loadGameTextures("block_question_dark")));



	
	//BLOCK_BRICK
	RawModel brickModel = OBJLoader.loadObjModel("models/block_brick", loader);
	TexturedModel brickTextured = new TexturedModel(brickModel, new ModelTexture(loader.loadGameTextures("block_brick")));
	
	//BLOCK_FLOOR
	RawModel floorModel = OBJLoader.loadObjModel("models/block_floor", loader);
	TexturedModel floorTextured = new TexturedModel(floorModel, new ModelTexture(loader.loadGameTextures("block_floor")));
	
	//BLOCK_QUESTION
	RawModel questionModel = OBJLoader.loadObjModel("models/block_question", loader);
	TexturedModel questionTextured = new TexturedModel(questionModel, new ModelTexture(loader.loadGameTextures("block_question")));
	
	//BLOCK_PIPE
	RawModel pipeModel = OBJLoader.loadObjModel("models/block_pipeFixed", loader);
	TexturedModel pipeTextured = new TexturedModel(pipeModel, new ModelTexture(loader.loadGameTextures("block_pipe")));
	
	//BLOCK_WALL
	RawModel wallModel = OBJLoader.loadObjModel("models/block_wall", loader);
	TexturedModel wallTextured = new TexturedModel(wallModel, new ModelTexture(loader.loadGameTextures("block_wall")));

	//ITEM_MUSHROOM
	RawModel mushroomModel = OBJLoader.loadObjModel("models/item_mushroom", loader);
	TexturedModel mushroomTexturedRed = new TexturedModel(mushroomModel, new ModelTexture(loader.loadGameTextures("item_mushroomRed")));
	TexturedModel mushroomTexturedGreen = new TexturedModel(mushroomModel, new ModelTexture(loader.loadGameTextures("item_mushroomGreen")));

	//CHAR_GUMBA
	RawModel gumbaModel = OBJLoader.loadObjModel("models/char_gumba", loader);
	TexturedModel gumbaTextured = new TexturedModel(gumbaModel, new ModelTexture(loader.loadGameTextures("char_gumba")));
	
	//CHAR_DARK_GUMBA
	TexturedModel darkGumbaTextured = new TexturedModel(gumbaModel, new ModelTexture(loader.loadGameTextures("char_gumba_dark")));
	
	//BG_MOUNTAIN
	RawModel mountainModel = OBJLoader.loadObjModel("models/bg_mountain", loader);
	TexturedModel mountainTextured = new TexturedModel(mountainModel, new ModelTexture(loader.loadGameTextures("bg_mountain")));
	
	//BG_BUSHES
	RawModel bushModel = OBJLoader.loadObjModel("models/bg_bush", loader);
	TexturedModel bushSingleTextured = new TexturedModel(bushModel, new ModelTexture(loader.loadGameTextures("bg_bush")));
	TexturedModel bushDoubleTextured = new TexturedModel(bushModel, new ModelTexture(loader.loadGameTextures("bg_bush_double")));
	TexturedModel bushTripleTextured = new TexturedModel(bushModel, new ModelTexture(loader.loadGameTextures("bg_bush_triple")));
	
	

	public List<Entity> loadMap(String mapFileName) {
		
		InputStreamReader isr = new InputStreamReader(Class.class.getResourceAsStream(RES_LOC + mapFileName + ".txt"));
		BufferedReader reader = new BufferedReader(isr);
		
//		basicBrickTextured.getTexture().setNormalMap(loader.loadGameTextures("models/block_brick_final_n"));
//		basicFloorTextured.getTexture().setNormalMap(loader.loadGameTextures("models/block_floor_final_n"));
//		basicQuestionTextured.getTexture().setNormalMap(loader.loadGameTextures("models/block_question_final_n"));


		List<Entity> entities = new ArrayList<Entity>();
		String line;

		try {
			while (true) {
				line = reader.readLine();
				String[] currentLine = line.split("\t");
				
				if(line.startsWith("X ")) {
					break;
				} else {
					
					for(int i = 0; i < currentLine.length; i++)
					{
						float heightMultiplier = Float.valueOf(currentLine[0]);	
						
						if(floor.equals(currentLine[i])) 
						{
							for(int j=-2; j<=2; j++) {
								for(int k=0; k<5; k++) {

									if ((k==0 || k==5) || ((j==-2) || (j==2)) ){
										float x =  BLOCK_SIZE*j;
										float y = -BLOCK_SIZE + BLOCK_SIZE*-k;
										entities.add(new Entity(basicFloorTextured, new Vector3f(x, y, BLOCK_SIZE*i), 0,0,0,1.0f, 8,8,8));
									}
								}
							}
						} else if(darkFloor.equals(currentLine[i])) 
						{
							for(int j=-2; j<=2; j++) {
								for(int k=0; k<5; k++) {

									if ((k==0 || k==5) || ((j==-2) || (j==2)) ){
										float x =  BLOCK_SIZE*j;
										float y = -BLOCK_SIZE + BLOCK_SIZE*-k;
										entities.add(new Entity(basicFloorTextured, new Vector3f(x, y, BLOCK_SIZE*i), 0,0,0,1.0f, 8,8,8));
									} 
								}
							}
						} else if(brick.equals(currentLine[i])) 
						{
							entities.add(new Entity(basicBrickTextured, new Vector3f(0, -BLOCK_SIZE+ BLOCK_SIZE*heightMultiplier, BLOCK_SIZE*i), 0,0,0,1.0f, 8,8,8));
						} else if(darkBrick.equals(currentLine[i])) 
						{
							entities.add(new Entity(basicDarkBrickTextured, new Vector3f(0, -BLOCK_SIZE+ BLOCK_SIZE*heightMultiplier, BLOCK_SIZE*i), 0,0,0,1.0f, 8,8,8));
						} else if(wall.equals(currentLine[i])) 
						{
							entities.add(new Entity(wallTextured, new Vector3f(0, -BLOCK_SIZE+ BLOCK_SIZE*heightMultiplier, BLOCK_SIZE*i), 0,90,0,1.0f, 8,8,8));
						} else if(darkWall.equals(currentLine[i])) 
						{
							//TODO: REPLACE WITH DARK WALL
							entities.add(new Entity(wallTextured, new Vector3f(0, -BLOCK_SIZE+ BLOCK_SIZE*heightMultiplier, BLOCK_SIZE*i), 0,90,0,1.0f, 8,8,8));
						} else if(question.equals(currentLine[i])) 
						{
							entities.add(new Entity(basicQuestionTextured, new Vector3f(0,-BLOCK_SIZE+ BLOCK_SIZE*heightMultiplier, BLOCK_SIZE*i), 0,0,0,1.0f, 8,8,8));
						} else if(darkQuestion.equals(currentLine[i])) 
						{
							entities.add(new Entity(basicDarkQuestionTextured, new Vector3f(0,-BLOCK_SIZE+ BLOCK_SIZE*heightMultiplier, BLOCK_SIZE*i), 0,0,0,1.0f, 8,8,8));
						} else if(pipe.equals(currentLine[i])) 
						{
							entities.add(new Entity(pipeTextured, new Vector3f(0, -BLOCK_SIZE+ BLOCK_SIZE*heightMultiplier, BLOCK_SIZE*(i+0.5f)), 0,0,0,1.0f, 16,32,16));	
						} else if(itemR.equals(currentLine[i])) 
						{
							entities.add(new Entity(mushroomTexturedRed, new Vector3f(0, -BLOCK_SIZE+ BLOCK_SIZE*heightMultiplier, BLOCK_SIZE*i), 0,0,0,1.0f, 8,8,8));
						} else if(itemG.equals(currentLine[i])) 
						{
							entities.add(new Entity(mushroomTexturedGreen, new Vector3f(0, -BLOCK_SIZE+ BLOCK_SIZE*heightMultiplier, BLOCK_SIZE*i), 0,0,0,1.0f, 8,8,8));
						} else if(gumba.equals(currentLine[i])) 
						{
							entities.add(new Entity(gumbaTextured, new Vector3f(0, -BLOCK_SIZE+ BLOCK_SIZE*heightMultiplier, BLOCK_SIZE*i), 0,-90,0,1.0f, 8,8,8));
						} else if(darkGumba.equals(currentLine[i])) 
						{
							entities.add(new Entity(darkGumbaTextured, new Vector3f(0, -BLOCK_SIZE+ BLOCK_SIZE*heightMultiplier, BLOCK_SIZE*i), 0,-90,0,1.0f, 8,8,8));
						}
					}
				}
			}
			reader.close();

		} catch (IOException e) {
			System.err.println("Error reading the file");
		}
		
//		List<Entity> entitiesBG = generateBG();
//		
//		for(Entity entity:entitiesBG){
//			entities.add(entity);
//		}
		
		return  entities;
	}
	
	
	private List<Entity> generateBG(){
		
		List<Entity> bgBlocks = new ArrayList<Entity>();
		int lastRandomBlock = 0;
		Random random = new Random();		
		
		for(int k=0; k<30;k++){
		
		float x = 150 + random.nextFloat()*-40;
		float y = random.nextFloat() * 25;
		float z = random.nextFloat()*80+(k*120);
		
		int randomBlock = random.nextInt(4);
		
		randomBlock = isSameRdm(lastRandomBlock, randomBlock);
	
		switch(randomBlock) {
		case 0:
			bgBlocks.add(new Entity(mountainTextured, new Vector3f(x-20 ,y-20, z), 0, -90, 0, 1.0f, 8,8,8));
			lastRandomBlock = 0;
			break;
		case 1:
			bgBlocks.add(new Entity(bushSingleTextured, new Vector3f(x, y-5, z), 0, -90, 0, 0.5f, 8,8,8));
			bushSingleTextured.getTexture().setHasTransparency(true);
			bushSingleTextured.getTexture().setUseFakeLighting(true);
			lastRandomBlock = 1;
			break;
		case 2:
			bgBlocks.add(new Entity(bushDoubleTextured, new Vector3f(x, y+10, z), 0, -90, 0, 0.75f, 8,8,8));
			bushDoubleTextured.getTexture().setHasTransparency(true);
			bushDoubleTextured.getTexture().setUseFakeLighting(true);
			lastRandomBlock = 2;
			break;
		case 3:			
			bgBlocks.add(new Entity(bushTripleTextured, new Vector3f(x, y+20, z), 0, -90, 0, 1.0f, 8,8,8));
			bushTripleTextured.getTexture().setHasTransparency(true);
			bushTripleTextured.getTexture().setUseFakeLighting(true);
			lastRandomBlock = 3;
			break;
		default: 				
			//bgBlocks.add(new Entity(bushTextured, new Vector3f(x, y, z), 0, -90, 0, 0.5f));
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
