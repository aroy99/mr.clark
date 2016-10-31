package komorebi.clark.entities;

import komorebi.clark.engine.GroundHandler;

public class Score extends Entity {

	public static int[] places= new int[5];
	public static int[] highScore = new int[5];
	private boolean isVisible = false;
	private int hx;

	public Score() {
		x = 16;
		y = 200;
		sx = 6;
		sy = 11;
		hx = 200;
		for(int i=0;i<places.length;i++){
			places[i]=0;
			highScore[i] = 0;
		}
	}

	public void update() {
		if(!isVisible)isVisible = true;
		if(toScore(places)<99999){
			places[4] = GroundHandler.getMeters();
			for(int i = places.length-1; i>0;i--){
				if(places[i]==10 && i>0){
					places[i]=0;
					places[i-1]++;
				}else break;
			}
			GroundHandler.setMeters(places[4]);
		}
		if(toScore(places) > toScore(highScore)){
			for(int i=0;i<places.length;i++)
				highScore[i] = places[i];
		}
	}

	public void drawArray(int[] array, int x){
		for(int i = array.length-1; i>=0;i--){
			switch(array[i]){
				case 1:
					draw.rect(x+i*7, y, sx, sy, "sheet", 0,139,6,150);
					break;
				case 2:
					draw.rect(x+i*7, y, sx, sy, "sheet", 6,139,12,150);
					break;
				case 3:
					draw.rect(x+i*7, y, sx, sy, "sheet", 12,139,18,150);
					break;
				case 4:
					draw.rect(x+i*7, y, sx, sy, "sheet", 18,139,24,150);
					break;
				case 5:
					draw.rect(x+i*7, y, sx, sy, "sheet", 24,139,30,150);
					break;
				case 6:
					draw.rect(x+i*7, y, sx, sy, "sheet", 30,139,36,150);
					break;
				case 7:
					draw.rect(x+i*7, y, sx, sy, "sheet", 36,139,42,150);
					break;
				case 8:
					draw.rect(x+i*7, y, sx, sy, "sheet", 42,139,48,150);
					break;
				case 0:
					draw.rect(x+i*7, y, sx, sy, "sheet", 54,139,60,150);
					break;
				default:
					draw.rect(x+i*7, y, sx, sy, "sheet", 48,139,54,150);
					break;
			}
			draw.rect(x+i*7+6, y, 1, 2, "sheet", 60,148,61,150);
		}
	}
	
	public void render(){
		drawArray(places, (int) this.x);
		drawArray(highScore, hx);
	}
	
	public int toScore(int[] array){
		return array[4] + array[3]*10 + array[2]*100 + array[1]*1000 + array[0]*10000;
		
	}

	@Override
	public void reset() {
		System.out.println("Score: "+ toScore(places));
		for(int i=0;i<places.length;i++)
			places[i]=0;
	}

}
