package komorebi.clark.entities;

public class Background extends Entity{

	private boolean left;
	private float bSpeed = 0.75f;
	
	
	/**
	 * Creates a new background
	 * 
	 * @param x - The starting x
	 * @param l - Whether the background uses the left or right image, true is left, false is right
	 */
	public Background (int x, boolean l){
		left = l;
		this.x=x;
		sx=256;
		sy=224;
		type = 75;
	}
	
	public void update() {
		x-=bSpeed;
		if(x<=-256)x+=512;
		if(bSpeed<=5.25f)bSpeed+=0.000875f;
	}
	
	public void render(){
		if(left)draw.rect(x, y, sx, sy, "mountain-sky", 0, 0, 256, 224);
		else draw.rect(x, y, sx, sy, "mountain-sky", 256, 0, 512, 224);
	}

	@Override
	public void reset() {
		if (left)x=0;
		else x = 256;
		bSpeed = 0.75f;
		
	}

}
