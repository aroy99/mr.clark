/*
 * State.java		Author-Aaron Roy
 * 
 * -Represents a state, with all of the objects in the game
 * 
 */

package komorebi.clark.states;

import java.util.ArrayList;

import komorebi.clark.engine.GameHandler;
import komorebi.clark.engine.GroundHandler;
import komorebi.clark.engine.Physics;
import komorebi.clark.engine.States;
import komorebi.clark.entities.Background;
import komorebi.clark.entities.Entity;
import komorebi.clark.entities.Ground;
import komorebi.clark.entities.Penguin;
import komorebi.clark.entities.Score;
import komorebi.clark.entities.Snow;
import komorebi.clark.entities.Snowball;

public abstract class State {

	protected static ArrayList<Entity> objects;
	protected static Penguin play;
	protected static Background b1, b2;
	protected static Ground[] ground = new Ground[17];
	protected static GroundHandler gh;
	protected static Snowball ball;
	protected static Score score;
	protected static Snow s1, s2;
	protected static Physics phy;
	
	public abstract void update();
	public abstract void render();
	public abstract void getInput();
	public static void switchState(States state){
		GameHandler.state = state;
	}
}
