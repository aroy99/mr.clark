package komorebi.clark.engine;

import java.util.Random;

import komorebi.clark.entities.Ground;

public class GroundHandler {

	private final static int MAX_ROCKS = 1, MAX_PITS = 3;
	private static int rocks = 0, pits = 0;
	private static int meters = 0;
	private Random rand = new Random();
	private static int currGround;
	Ground[] ground;
	
	public GroundHandler(Ground[] ground) {
		this.ground = ground;
	}
	
	public void update(){
		
		for(int i=0;i<ground.length;i++){
			if(ground[i].getX()<=-16){
				ground[i].resetX();
				if(ground[i].getType()==5||ground[i].getType()==6)rocks--;
				if(ground[i].getType()==4)pits--;
				meters++;
				
				if(ground[i].getLock()==-1){
					int next = rand.nextInt(21);
					
					if(next==5 && rocks<MAX_ROCKS){
						ground[i].setBig();
						ground[i].setType(currGround+5);
						ground[wrapArray(i+1)].setLock(currGround == 0 ? 1:0);
						rocks++;
					}
					else if(next == 2 && pits<MAX_PITS){
						ground[i].setType(2);
						next = rand.nextInt(2)+2;
						switch (next) {
						case 2:
							ground[wrapArray(i+1)].setLock(4);
							ground[wrapArray(i+2)].setLock(4);
							ground[wrapArray(i+3)].setLock(3);
							pits+=2;
							break;
						case 3:
							ground[wrapArray(i+1)].setLock(4);
							ground[wrapArray(i+2)].setLock(4);
							ground[wrapArray(i+3)].setLock(4);
							ground[wrapArray(i+4)].setLock(3);
							pits+=3;
							break;
						}
					}
					else {
						ground[i].setSmall();
						ground[i].setType(currGround);
					}
				}else {
					if(ground[i].getLock()==5||ground[i].getLock()==6){
						ground[i].setBig();
					}else ground[i].setSmall();
					ground[i].setType();
					ground[i].setLock(-1);
				}
				
				if(currGround==0)currGround=1;
				else currGround=0;
				break;
			}
		}
	}

	public static void setMeters(int m) {
		meters=m;
	}

	public static int getMeters() {
		return meters;
	}
	
	public int wrapArray(int index){
		if (index>=ground.length)
			return index-ground.length;
		else return index;
	}
	
	public void reset(){
		for(int i = 0; i<ground.length;i++) {
			ground[i].setX(i*16);
		}
		rocks = 0;
		pits = 0;
		meters = 0;
	}

}
