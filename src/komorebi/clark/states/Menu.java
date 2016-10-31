package komorebi.clark.states;

import komorebi.clark.engine.Draw;
import komorebi.clark.engine.States;
import komorebi.clark.entities.Entity;

import org.lwjgl.input.Keyboard;

public class Menu extends State{
	
	Draw draw = new Draw();
	private static boolean isStart;
	private static boolean wasStart = true;
	private static boolean credits = false;
	private static boolean wasCredits = true, isCredits;
	
	public void getInput() {
		
		wasCredits = isCredits;
		isCredits = Keyboard.isKeyDown(Keyboard.KEY_DOWN);
		
		wasStart = isStart;
		isStart = Keyboard.isKeyDown(Keyboard.KEY_UP);
	}

	public void update() {
		if(isCredits && !wasCredits)credits = !credits;
		if(isStart && !wasStart)switchState(States.GAME);
	}

	public void render() {
		for(Entity go:Game.objects){
			go.render();
		}
		if(credits) draw.rect(16, 80, 214, 45,"sheet", 0, 88, 214, 133);
		else {
			draw.rect(16, 80, 177, 27,"sheet", 51, 0, 228, 27);
			draw.rect(110, 50, 65, 27,"sheet", 146, 30, 211, 57);
		}
	}
	
	public static void reset(){
		wasCredits = isCredits;
		isCredits = Keyboard.isKeyDown(Keyboard.KEY_DOWN);
		
		wasStart = isStart;
		isStart = Keyboard.isKeyDown(Keyboard.KEY_UP);

		credits = false;
	}
}
