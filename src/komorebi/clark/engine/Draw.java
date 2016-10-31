package komorebi.clark.engine;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glTexParameteri;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Draw {
	
	private Texture tex;
	private boolean texLoaded = false;
	private int imgX, imgY;

	public void rect(float x, float y, float sx, float sy){
		glPushMatrix();
		{
			glTranslatef((int)x,(int)y,0);
		
			glBegin(GL_QUADS);
			{
				glColor3f(1,0.5f,1);
				glVertex2f(0,0);
				glVertex2f(0,(int)sy);
				glVertex2f((int)sx,(int)sy);
				glVertex2f((int)sx,0);
				glColor3f(1,1,1);
			}
			glEnd();
		}
		glPopMatrix();
	}
	
	public void rect(float x, float y, float sx, float sy,String key, int texX, int texY, int texSX, int texSY){
		glPushMatrix();
		{
			
			if(!texLoaded){
				tex = loadTexture(key);
				texLoaded = true;
				
				}

			glTranslatef(Math.round(x),Math.round(y),0);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

			tex.bind();

			glBegin(GL_QUADS);
			{
				glTexCoord2f((float)texX/imgX,(float)texSY/imgY);
					glVertex2f(0,0);
				glTexCoord2f((float)texX/imgX,(float)texY/imgY);
					glVertex2f(0,(int)sy);
				glTexCoord2f((float)texSX/imgX,(float)texY/imgY);
					glVertex2f((int)sx,(int)sy);
				glTexCoord2f((float)texSX/imgX,(float)texSY/imgY);
					glVertex2f((int)sx,0);
			}
			glEnd();
		}
		glPopMatrix();
	}
	
	private Texture loadTexture(String key){
		try {
			Texture t = TextureLoader.getTexture("PNG",new FileInputStream(new File("res/"+key+".png")));
			imgX = t.getImageWidth();
			imgY = t.getImageHeight();
			return t;
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
