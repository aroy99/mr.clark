/*
 * Main.java		Author-Aaron Roy
 * 
 * -Initializes everything and uses the game handler to start the game
 * 
 */

package komorebi.clark.engine;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.openal.SoundStore;


public class Main {
	
	private static GameHandler gamehandler;
	private static int scale;				//TODO read from text
	private static BufferedReader read;
	
	
	public static void main(String[] args){
		try {
			read = new BufferedReader(new FileReader(new File("res/settings")));
			String s;
			
			while ((s = read.readLine()) != null) {
				if(s.charAt(0)=='#')continue;
				if(scale == 0)scale = Integer.parseInt(s);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
				
		initDisplay();
		initGL();
		
		initGame();
		gameLoop();
		cleanUp();
	}
	
	
    //----------------------------------------------------------------------
	//Initializes the Display, using the Display Class, properly Scaling it
    //----------------------------------------------------------------------
	public static void initDisplay(){
		//create display
				try {
					Display.setDisplayMode(new DisplayMode(256*scale,224*scale));
					Display.setTitle("Mr. Clark");
					Display.create();
					Display.setVSyncEnabled(true);
					
				} catch (LWJGLException e) {
					e.printStackTrace();
				}
	}

	
	private static void initGame(){
		gamehandler = new GameHandler();
		AudioHandler.init();
	}
	
	
	private static void getInput(){
		gamehandler.getInput();
	}
	
	private static void update(){
		gamehandler.update();
	}
	
	
	private static void render(){
		glClear(GL_COLOR_BUFFER_BIT);		//clears the matrix with black
		glLoadIdentity();
		
		gamehandler.render();
		
		Display.update();					//updates the display with the changes
		Display.sync(60);					//makes up for lost time
		
	}
	
	
    //----------------------------------------------------------------------
	//Goes through the game loop, starting the music once
    //----------------------------------------------------------------------
	private static void gameLoop(){
		
		while(!Display.isCloseRequested()){
			getInput();
			update();
			render();
			SoundStore.get().poll(0);

			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
				break;
			}
			
		}
	}
	
	private static void initGL(){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();										//resets the Matrix
		glOrtho(0,256,0,224,-1,1);								//creates a 3D space
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);								//enables Textures
		glEnable (GL_BLEND);
		glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);		//Enables transparency


		glClearColor(0,0,0,1);									//sets the clearing color to black
		
		glDisable(GL_DEPTH_TEST);								//kills off the third dimension
	}

    //----------------------------------------------------------------------
	//Destroys the display and keyboard, closing the window
    //----------------------------------------------------------------------
	private static void cleanUp(){
		Display.destroy();
		AL.destroy();
	}
	
	
}
