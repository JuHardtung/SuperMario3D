package menuGeneration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import entities.Entity;
import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;
import fontRendering.TextMaster;
import renderEngine.DisplayManager;
import renderEngine.Loader;

public class MenuOptions {
	
	private Loader loader = new Loader();
	
	private FontType marioFont = new FontType(loader.loadFontTextureAtlas("/fonts/superMarioFont2"), new File("res/fonts/superMarioFont2.fnt"));
	private List<GUIText> allMenus = new ArrayList<GUIText>();
	private List<GUIText> allLevelMenus = new ArrayList<GUIText>();
	private int showGuiPart = 1;



	
	
	public List<GUIText> generateESCMenu() {
		
		
		GUIText optionsButton = new GUIText("Options", 1.5f, marioFont, new Vector2f(0.33f, 0.25f), 0.33f, true, new Vector2f(0.33f,0.04f)) {
			
			@Override
			public void whileHovering() {
			}
			
			@Override
			public void onStopHover() {
				setColour(0, 0, 0);
			}
			
			@Override
			public void onStartHover() {
				setColour(1, 0, 0);
			}
			
			@Override
			public void onClick() {
				setClicked(true);
			}
		};
		
		GUIText levelsButton = new GUIText("Levels", 1.5f, marioFont, new Vector2f(0.33f, 0.35f), 0.33f, true, new Vector2f(0.33f,0.04f)) {
			
			@Override
			public void whileHovering() {
			}
			
			@Override
			public void onStopHover() {
				setColour(0, 0, 0);
			}
			
			@Override
			public void onStartHover() {
				setColour(1, 0, 0);
			}
			
			@Override
			public void onClick() {
				setClicked(true);
			}
		};
		
		GUIText quitButton = new GUIText("Quit", 1.5f, marioFont, new Vector2f(0.33f, 0.45f), 0.33f, true, new Vector2f(0.33f,0.04f)) {
			
			@Override
			public void whileHovering() {

			}
			
			@Override
			public void onStopHover() {
				setColour(0, 0, 0);
			}
			
			@Override
			public void onStartHover() {
				setColour(1, 0, 0);
			}
			
			@Override
			public void onClick() {
				setClicked(true);
			}
		};
		
		allMenus.add(optionsButton);
		allMenus.add(levelsButton);
		allMenus.add(quitButton);

		
		return  allMenus;

	}
	
	
	public List<GUIText> generateLevelsGui() {
		
		
		GUIText levelsButton = new GUIText("Levels", 2.5f, marioFont, new Vector2f(0.33f, 0.2f), 0.33f, true, new Vector2f(0.33f,0.04f)) {
			
			@Override
			public void whileHovering() {
			}
			
			@Override
			public void onStopHover() {
			}
			
			@Override
			public void onStartHover() {
			}
			
			@Override
			public void onClick() {
			}
		};
		
		GUIText level1Button = new GUIText("Level 1", 1.5f, marioFont, new Vector2f(0.33f, 0.35f), 0.33f, true, new Vector2f(0.33f,0.04f)) {
			
			@Override
			public void whileHovering() {
			}
			
			@Override
			public void onStopHover() {
				setColour(0, 0, 0);
			}
			
			@Override
			public void onStartHover() {
				setColour(1, 0, 0);
			}
			
			@Override
			public void onClick() {
				setClicked(true);

			}
		};
		
		GUIText level2Button = new GUIText("Level 2", 1.5f, marioFont, new Vector2f(0.33f, 0.45f), 0.33f, true, new Vector2f(0.33f,0.04f)) {
			
			@Override
			public void whileHovering() {
			}
			
			@Override
			public void onStopHover() {
				setColour(0, 0, 0);
			}
			
			@Override
			public void onStartHover() {
				setColour(1, 0, 0);
			}
			
			@Override
			public void onClick() {
				setClicked(true);

			}
		};
		
		GUIText backButton = new GUIText("Back", 1.5f, marioFont, new Vector2f(0.33f, 0.55f), 0.33f, true, new Vector2f(0.33f,0.04f)) {
			
			@Override
			public void whileHovering() {
			}
			
			@Override
			public void onStopHover() {
				setColour(0, 0, 0);
			}
			
			@Override
			public void onStartHover() {
				setColour(1, 0, 0);
			}
			
			@Override
			public void onClick() {
				setClicked(true);
			}
		};
		
		allLevelMenus.add(levelsButton);
		allLevelMenus.add(level1Button);
		allLevelMenus.add(level2Button);
		allLevelMenus.add(backButton);

		
		return allLevelMenus;
	}
	/**
	 * 
	 * @return 1 = ESCMenu 2 = levelsMenu
	 */
	public int whichGuiParts() {
		
		return showGuiPart;
	}
	
}
