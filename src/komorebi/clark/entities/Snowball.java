package komorebi.clark.entities;

import java.util.Random;

import komorebi.clark.engine.AudioHandler;

public class Snowball extends Entity {

	private float speed = 4;
	private Random rand = new Random();
	private boolean go = false;
	private float warnY;
	private boolean isWarning = false;
	private int wCounter = 0;
	private int blinkCounter = 0;
	private boolean wVisible = true;
	private int coolDown = 30;

	
	public Snowball() {
		sx=24;
		sy=24;
		y=rand.nextInt(2)*32+64;
		x=256;
		type = 7;
	}

	@Override
	public void update() {
		if(rand.nextInt(1001)==0 && !isWarning && coolDown == 0){
			isWarning=true;
			warnY = y+2;
			AudioHandler.play("warn");
			wCounter = 0;
			blinkCounter = 0;
		}
		if(isWarning) wCounter++;
		blinkCounter++;
		if(blinkCounter>20 && wVisible){
			wVisible = false;
			blinkCounter = 0;
		}
		if(blinkCounter>10 && !wVisible){
			wVisible = true;
			blinkCounter = 0;
		}
		
		if(!go && coolDown>0)coolDown--;
		
		if(wCounter>=60 && isWarning){
			isWarning = false;
			go = true;
			coolDown = 30;
		}
		if(go)x-=speed;
		if(x<=-16 && go){
			x+=288;
			go=false;
			y=rand.nextInt(2)*32+64;
		}

		if(speed<8)speed+=0.00125f;
	}
	
	public void dUpdate(){
		if(isWarning) wCounter++;
		blinkCounter++;
		if(blinkCounter>20 && wVisible){
			wVisible = false;
			blinkCounter = 0;
		}
		if(blinkCounter>10 && !wVisible){
			wVisible = true;
			blinkCounter = 0;
		}

		
		if(wCounter>=60 && isWarning){
			isWarning = false;
			go = true;
			coolDown = 30;
		}

		if(go)x-=speed;
	}

	public void render(){
		draw.rect(x,y,sx,sy,"sheet",0,64,24,88);
		if(isWarning && wVisible)draw.rect(232,warnY,12,12,"sheet",24,64,36,76);
	}

	@Override
	public void reset() {
		y=rand.nextInt(2)*32+64;
		x=256;
		speed = 4;
		isWarning = false;
		wCounter = 0;
		blinkCounter = 0;
		wVisible = true;
		coolDown = 30;
		go = false;
	}
}
