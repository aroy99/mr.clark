/*
 * Game.java		Author-Aaron Roy
 * 
 * -Uses all of the objects from States to run the main gameplay
 * 
 */

package komorebi.clark.states;

import java.util.ArrayList;

import komorebi.clark.engine.AudioHandler;
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

public class Game extends State{
	
	protected static boolean started;
	protected static int startCount = 0;
	protected static boolean finishedMusic;
	
	public Game(){
		objects = new ArrayList<Entity>();
		play = new Penguin();
		b1 = new Background(0, true);
		b2 = new Background(256, false);
		s1 = new Snow(true);
		s2 = new Snow(false);
		ball = new Snowball();
		score = new Score();
				
		phy = new Physics(play);
		
		for(int i = 0; i<ground.length;i++) {
			ground[i] = new Ground(i*16);
		}
		gh = new GroundHandler(ground);
		
		objects.add(b1);
		objects.add(b2);
		objects.add(s1);
		objects.add(s2);
		objects.add(ball);
		objects.add(play);
		objects.add(score);
		for(int i = 0; i<ground.length;i++) objects.add(ground[i]);
	}
	
	@Override
	public void getInput() {
		play.getInput();
	}

	@Override
	public void update() {
		if(!started)startCount++;			//delay the beginning by half a second
		if(!started && startCount>30){
			started = true;
		}
		if(started){
			if(!finishedMusic)
				finishedMusic = AudioHandler.playMusic();
			else AudioHandler.resumeMusic();
			for(Entity e:objects){
				e.update();
				phy.update(e);
			}
			gh.update();
			if(play.hasDied()){
				switchState(States.DEATH);
			}
		}
	}
	
	@Override
	public void render() {
		for(Entity go:objects){
			go.render();
		}
	}
	
	public static void reset(){
		started = false;
		startCount = 0;
	}

}
