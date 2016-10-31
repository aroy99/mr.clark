package komorebi.clark.states;

import komorebi.clark.engine.States;
import komorebi.clark.entities.Entity;

import org.lwjgl.input.Keyboard;

public class Death extends State{

	private boolean upPressed = true;
	private boolean wasUpPressed = true;
	private boolean downPressed = false;
	private boolean wasDownPressed = true;

	public void update() {

		
		if(upPressed && !wasUpPressed){
			for(Entity e:Game.objects){
				e.reset();
			}
			gh.reset();
			Game.reset();
			Menu.reset();
			switchState(States.GAME);

		}
		else if(play.getY()>-100 && !(downPressed && !wasDownPressed)){
			play.dUpdate();
			s1.dUpdate();
			s2.dUpdate();
			ball.dUpdate();
		}else{
			for(Entity e:Game.objects){
				e.reset();
			}
			gh.reset();
			Game.reset();
			Menu.reset();
			switchState(States.MENU);
		}
	}

	public void render() {
		for(Entity go:Game.objects){
			if(go.getType()==44)continue;
			go.render();
		}
		play.render();
	}

	public void getInput() {
		wasUpPressed = upPressed;
		upPressed = Keyboard.isKeyDown(Keyboard.KEY_UP);
		
		wasDownPressed = downPressed;
		downPressed = Keyboard.isKeyDown(Keyboard.KEY_DOWN);
	}

}
