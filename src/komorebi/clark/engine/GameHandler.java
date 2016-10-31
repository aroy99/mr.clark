/*
 * GameHandler.java		Author-Aaron Roy
 * 
 * -Updates, renders and gets input depending on the current state
 * 
 */

package komorebi.clark.engine;

import komorebi.clark.states.Death;
import komorebi.clark.states.Game;
import komorebi.clark.states.Menu;

public class GameHandler {

    public static States state = States.MENU;
    private Menu menu = new Menu();
    private Game game = new Game();
    private Death death = new Death();


    public void update() {
        switch(state){
        case MENU:
            menu.update();
            break;
        case GAME:
            game.update();
            break;
        case DEATH:
            death.update();
            break;
        default:
            break;
        }
    }

    public void render() {
        switch(state){
        case MENU:
            menu.render();
            break;
        case GAME:
            game.render();
            break;
        case DEATH:
            death.render();
            break;
        default:
            break;
        }
    }

    public void getInput() {
        switch(state){
        case MENU:
            menu.getInput();
            break;
        case GAME:
            game.getInput();
            break;
        case DEATH:
            death.getInput();
            break;
        default:
            break;
        }		
    }
}
