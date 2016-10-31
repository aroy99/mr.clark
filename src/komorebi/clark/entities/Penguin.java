package komorebi.clark.entities;

import komorebi.clark.engine.AudioHandler;

import org.lwjgl.input.Keyboard;

public class Penguin extends Entity {

	private boolean isJumping, isSliding, wasSliding, wasJumping;
	private boolean isMoving = false;
	private int frame = 0;
	private int slideCounter = 0, aniCounter = 0, slideJumpCounter = 0;
	private float aniSpeed = 15;
	private float dy=-.5f;
	private float dx = 0;
	public static boolean isTouchingGround = true;
	private boolean currSliding = false;
	private boolean stoppedJumping;
	private float slideTime = 30;
	private boolean hasDied = false;
	
	
	public Penguin(){
		x= 48;
		y= 48;
		sx = 16;
		sy = 24;
		type = 44;
	}
	
	public void getInput() {
		wasJumping = isJumping;
		isJumping = Keyboard.isKeyDown(Keyboard.KEY_UP);
		
		wasSliding = isSliding;
		isSliding = !isJumping && Keyboard.isKeyDown(Keyboard.KEY_DOWN);
	}

	public void update() {
		if(!isMoving)isMoving = true;
		if(isJumping && isTouchingGround && !currSliding && !wasJumping){
			dy = 8;
			AudioHandler.play("jump");
			isTouchingGround = false;
		}
		
		if(isSliding && isTouchingGround && !wasSliding && !currSliding){
			currSliding = true;
			sx = 24;
			sy = 16;
			AudioHandler.play("slide");
			slideCounter = (int)slideTime;
		}
		if(slideTime>20)slideTime -= 0.0033333f;

		
		if(!isJumping&&!stoppedJumping && !isTouchingGround && dy>0){
			dy= -.1f*dy;
			stoppedJumping = true;
		}
		
		if(y<48)die();
		
		y+=dy;
		
		if(!isTouchingGround)dy-=.5;
		if(isTouchingGround && dy != -.5f) dy = -.5f;

		if(currSliding) slideCounter--;
		
		if(slideCounter <= 0){
			currSliding = false;
		}
		
		if(!currSliding) {
			sx = 16;
			sy = 24;
		}
		aniCounter++;
		if(aniCounter>=aniSpeed){
			aniCounter = 0;
			if(frame<3)frame++;
			else {
				frame = 0;
				if(aniSpeed>2)aniSpeed -= 0.05f;
			}
		}
	}
	
	public void dUpdate(){
		y+=dy;
		dy-=.5f;
		x+=dx;
		//dx-=.5f;
	}
	
	public void render(){
		if(currSliding)
			draw.rect(x, y, sx, sy,"sheet", 16,  0, 40, 16);
		else if (!isTouchingGround)
			draw.rect(x, y, sx, sy,"sheet", 32, 24, 48, 48);
		else if (isMoving && frame==0 && isTouchingGround)
			draw.rect(x, y, sx, sy,"sheet",  0, 24, 16, 48);
		else if (isMoving && frame==2 && isTouchingGround)
			draw.rect(x, y, sx, sy,"sheet", 16, 24, 32, 48);
		else
			draw.rect(x, y, sx, sy,"sheet",  0,  0, 16, 24);

	}
	
	public void hasCollided(int type){
		switch(type){
			case 0:case 1:case 2:case 3:
				isTouchingGround = true;
				stoppedJumping = false;
				y=48;
				break;
			case 5: case 6:case 7:
				dy=8;
				dx=1;
				System.out.println("Did stuff");
				die();
				break;
			case 4:
				isTouchingGround = false;
				break;
		}
	}

	private void die() {
		if(!hasDied)System.out.println("You ded!");
		currSliding = false;
		sx = 16;
		sy = 24;
		AudioHandler.stop();
		AudioHandler.play("death");
		hasDied = true;
	}
	
	public boolean hasDied(){
		return hasDied;
	}

	@Override
	public void reset() {
		x= 64;
		y= 48;
		isMoving = false;
		aniSpeed = 15;
		slideTime = 30;
		dy = -.5f;
		isTouchingGround = true;
		currSliding = false;
		hasDied = false;
	}
}
