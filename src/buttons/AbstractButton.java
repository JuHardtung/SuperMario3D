package buttons;

import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

import guis.GuiTexture;
import renderEngine.DisplayManager;
import renderEngine.Loader;

public abstract class AbstractButton implements IButton {
	
	private GuiTexture guiTexture;
	
	private Vector2f originalScale;
	
	private boolean isHidden = true, isHovering = false;

	public AbstractButton(Loader loader, String texture, Vector2f position, Vector2f scale) {
		guiTexture = new GuiTexture(loader.loadGameTextures(texture), position, scale);
		originalScale = scale;
	}
	
	public void update(){
		if(!isHidden) {
			Vector2f location = guiTexture.getPosition();
			Vector2f scale = guiTexture.getScale();
			
			Vector2f mouseCoordinates = DisplayManager.getNormalizedMouseCoordinates();
			
			if(location.y + scale.y > -mouseCoordinates.y 
					&& location.y - scale.y < -mouseCoordinates.y 
					&& location.x + scale.x > mouseCoordinates.x 
					&& location.x -scale.x < mouseCoordinates.x ){
				whileHovering(this);
				if(!isHovering) {
					isHovering = true;
					onStartHover(this);
				}
				while(Mouse.next())
					if(Mouse.isButtonDown(0))
						onClick(this);
			}else {
				if(isHovering) {
				isHovering = false;
				onStopHover(this);
				}
			}	
		}
	}
	
	public void show(List<GuiTexture> guiTextureList) {
		if(isHidden) {
			System.out.println(guiTextureList);
			guiTextureList.add(guiTexture);
			System.out.println(guiTextureList);
			isHidden = false;
		}
	}
	
	public void hide(List<GuiTexture> guiTextureList) {
		if(!isHidden) {
			guiTextureList.remove(guiTexture);
			isHidden = true;
		}
	}
	
	public void resetScale() {
		guiTexture.setScale(originalScale);
	}
	
	public void playHoverAnimation(float scaleFactor) {
		guiTexture.setScale(new Vector2f(originalScale.x + scaleFactor, originalScale.y + scaleFactor));
	}
	
	public boolean isHidden() {
		return isHidden;
	}
}
