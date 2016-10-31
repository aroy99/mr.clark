package komorebi.clark.entities;


public class Snow extends Entity{
	
	private float[]yBig = new float[7];
	private float[]ySmall = new float[7];
	private float xBig;
	private float xSmall;
	private float speed = 0.875f;
	private float fallSpeed = 1;
	private int counter = 0;
	private boolean left;
	
	/**
	 * 
	 * @param l - whether the snow is on the left or right.
	 * True is left, false is right
	 */
	public Snow(boolean l) {
		left = l;
		if(left)x=0;
		else x=256;
		xBig=x+7;
		xSmall=x;
		for(int i = 0; i<yBig.length;i++){
			yBig[i]  = 448-i*32;
		}
		for(int i = 0; i<ySmall.length;i++){
			ySmall[i]  = 448-i*32+17;
		}
		type = 22;
	}

	@Override
	public void update() {
		
		for(int i = 0; i<yBig.length;i++){
			yBig[i]-=fallSpeed;
			if(yBig[i]<0)yBig[i]+=224;
		}
		for(int i = 0; i<ySmall.length;i++){
			ySmall[i]-=fallSpeed;
			if(ySmall[i]<0)ySmall[i]+=224;
		}

		xBig-=speed;
		if(xBig<-256)xBig+=512;
		xSmall-=speed;
		if(xSmall<-256)xSmall+=512;

		if(speed<6.125f)speed+=0.00102083333333f;
		if(fallSpeed < 3)fallSpeed+= 0.0005;
		
		if(counter  >= 60){
			counter = 0;
		}
		counter++;

	}
	
	public void dUpdate(){
		for(int i = 0; i<yBig.length;i++){
			yBig[i]-=fallSpeed;
			if(yBig[i]<0)yBig[i]+=224;
		}
		for(int i = 0; i<ySmall.length;i++){
			ySmall[i]-=fallSpeed;
			if(ySmall[i]<0)ySmall[i]+=224;
		}

	}

	@Override
	public void render() {
		for(int i = 0; i<yBig.length;i++){
			draw.rect(xBig, yBig[i], 243, 4, "sheet", 0, 135, 243, 139);
		}
		for(int i = 0; i<ySmall.length;i++){
			draw.rect(xSmall, ySmall[i], 242, 2, "sheet", 0, 133, 242, 135);
		}			
	}

	@Override
	public void reset() {
		if(left)x=0;
		else x=256;

		xBig=x+7;
		xSmall=x;
		for(int i = 0; i<yBig.length;i++){
			yBig[i]  = 448-i*32;
		}
		for(int i = 0; i<ySmall.length;i++){
			ySmall[i]  = 448-i*32+17;
		}
		speed = 0.875f;
		fallSpeed = 1;
	}

}
