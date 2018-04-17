package fontMeshCreator;
 
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import fontRendering.TextMaster;
 
/**
 * Represents a piece of text in the game.
 */
public abstract class GUIText implements FontButton{
 
    private String textString;
    private float fontSize;
 
    private int textMeshVao;
    private int vertexCount;
    private Vector3f colour = new Vector3f(0f, 0f, 0f);
 
    private Vector2f position;
    private float lineMaxSize;
    private int numberOfLines;
     
    private FontType font;
 
    private boolean centerText = false;
    private Vector2f scale;
    
	private boolean isHidden = true, isHovering = false;
	public boolean isClicked = false;


	/**
     * Creates a new text, loads the text's quads into a VAO, and adds the text
     * to the screen.
     * 
     * @param text
     *            - the text.
     * @param fontSize
     *            - the font size of the text, where a font size of 1 is the
     *            default size.
     * @param font
     *            - the font that this text should use.
     * @param position
     *            - the position on the screen where the top left corner of the
     *            text should be rendered. The top left corner of the screen is
     *            (0, 0) and the bottom right is (1, 1).
     * @param maxLineLength
     *            - basically the width of the virtual page in terms of screen
     *            width (1 is full screen width, 0.5 is half the width of the
     *            screen, etc.) Text cannot go off the edge of the page, so if
     *            the text is longer than this length it will go onto the next
     *            line. When text is centered it is centered into the middle of
     *            the line, based on this line length value.
     * @param centered
     *            - whether the text should be centered or not.
     */
    public GUIText(String text, float fontSize, FontType font, Vector2f position, float maxLineLength,
            boolean centered, Vector2f scale) {
        this.textString = text;
        this.fontSize = fontSize;
        this.font = font;
        this.position = position;
        this.lineMaxSize = maxLineLength;
        this.centerText = centered;
        this.scale = scale;
    }
    
    public void update() {
    	
    	if(!isHidden) {
   		
    		float mouseX = (float) Mouse.getX() / (float) Display.getWidth();
    		float mouseY = 1 - (float) Mouse.getY() / (float) Display.getHeight();
    		
//    		System.out.println("MAUSX: " + mouseX);
//    		System.out.println("MAUSY: " + mouseY);
//    		
//    		System.out.println("links: " + (position.x));
//    		System.out.println("rechts: " + (position.x +scale.x));
//    		System.out.println("oben: " + (position.y));
//    		System.out.println("unten: " + (position.y + scale.y));
//    		System.out.println("__________________________________");
//    		System.out.println("scaleX: " + scale.x);
//    		System.out.println("scaleY: " + scale.y);
//    		System.out.println("__________________________________");

			if(position.x < mouseX 						//top border
				&& position.x + scale.x > mouseX 		//bottom border
				&& position.y < mouseY  				//left border
				&& position.y + scale.y > mouseY ){ 	//right border
				
				whileHovering();
				if(!isHovering) {
					isHovering = true;
					onStartHover();
				}
				while(Mouse.next())
					if(Mouse.isButtonDown(0)) {
						onClick();

					}
			
			}else {
				if(isHovering) {
				isHovering = false;
				onStopHover();
				}
			}    		
    	}
    }
    
	public void show() {
		if(isHidden) {
	        TextMaster.loadText(this);
			isHidden = false;
		}
	}
	
	public void hide() {
		if(!isHidden) {
			remove();
			isHidden = true;
		}
	}
    
	public boolean isHidden() {
		return isHidden;
	}
	
	public void setIsHidden(boolean hidden) {
		isHidden = hidden;
	}

    public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean clicked) {
		isClicked = clicked;
	}
	
    public void setTextString(String text) {
		this.textString = text;
		TextMaster.loadText(this);
	}
    
	/**
     * Remove the text from the screen.
     */
    public void remove() {
    	TextMaster.removeText(this);    
    }
 
    /**
     * @return The font used by this text.
     */
    public FontType getFont() {
        return font;
    }
    
 
    /**
     * Set the colour of the text.
     * 
     * @param r
     *            - red value, between 0 and 1.
     * @param g
     *            - green value, between 0 and 1.
     * @param b
     *            - blue value, between 0 and 1.
     */
    public void setColour(float r, float g, float b) {
        colour.set(r, g, b);
    }
 
    /**
     * @return the colour of the text.
     */
    public Vector3f getColour() {
        return colour;
    }
 
    /**
     * @return The number of lines of text. This is determined when the text is
     *         loaded, based on the length of the text and the max line length
     *         that is set.
     */
    public int getNumberOfLines() {
        return numberOfLines;
    }
 
    /**
     * @return The position of the top-left corner of the text in screen-space.
     *         (0, 0) is the top left corner of the screen, (1, 1) is the bottom
     *         right.
     */
    public Vector2f getPosition() {
        return position;
    }
 
    /**
     * @return the ID of the text's VAO, which contains all the vertex data for
     *         the quads on which the text will be rendered.
     */
    public int getMesh() {
        return textMeshVao;
    }
 
    /**
     * Set the VAO and vertex count for this text.
     * 
     * @param vao
     *            - the VAO containing all the vertex data for the quads on
     *            which the text will be rendered.
     * @param verticesCount
     *            - the total number of vertices in all of the quads.
     */
    public void setMeshInfo(int vao, int verticesCount) {
        this.textMeshVao = vao;
        this.vertexCount = verticesCount;
    }
 
    /**
     * @return The total number of vertices of all the text's quads.
     */
    public int getVertexCount() {
        return this.vertexCount;
    }
 
    /**
     * @return the font size of the text (a font size of 1 is normal).
     */
    protected float getFontSize() {
        return fontSize;
    }
    
    /**
     * 
     * @param size
     * 				- the desired new fontSize - updated through update() each frame
     */
    public void setFontSize(float size) {
    	fontSize = fontSize + size;
        //TextMaster.loadText(this);
    }
 
    /**
     * Sets the number of lines that this text covers (method used only in
     * loading).
     * 
     * @param number
     */
    protected void setNumberOfLines(int number) {
        this.numberOfLines = number;
    }
 
    /**
     * @return {@code true} if the text should be centered.
     */
    protected boolean isCentered() {
        return centerText;
    }
 
    /**
     * @return The maximum length of a line of this text.
     */
    protected float getMaxLineSize() {
        return lineMaxSize;
    }
 
    /**
     * @return The string of text.
     */
    protected String getTextString() {
        return textString;
    }
 
}