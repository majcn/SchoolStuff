import java.nio.IntBuffer;
import org.lwjgl.opengl.GL11;


public class TexturedCube
{
	protected float m_nX, m_nY, m_nZ;
	protected float m_rX, m_rY, m_rZ;
	protected float m_sX=1, m_sY=1, m_sZ=1;
	protected IntBuffer m_Textures;
	protected float textureX;
	protected float textureY;
	
	public TexturedCube(){
		mesaj();
	}
	
	public float getRotacijoX(){
		return this.m_rX;
	}
	
	public float getRotacijoY(){
		return this.m_rY;
	}

	public void setPosition(float p_X, float p_Y, float p_Z)
	{
		m_nX=p_X;
		m_nY=p_Y;
		m_nZ=p_Z;
	}
	public void setRotation(float p_X, float p_Y, float p_Z)
	{
		m_rX=p_X;
		m_rY=p_Y;
		m_rZ=p_Z;
	}
	public void setScaling(float p_X, float p_Y, float p_Z)
	{
		m_sX=p_X;
		m_sY=p_Y;
		m_sZ=p_Z;
	}
	public void changeScaling(float iks, float ips, float z){
		m_sX+=iks;
		m_sY+=ips;
		m_sZ+=z;
	}
	
	public void move(float iks, float ips, float z){
		m_nX+=iks;
		m_nY+=ips;
		m_nZ+=z;
	}
	public void setTexture(IntBuffer textures, float x, float y)
	{
		m_Textures = textures;
		textureX = x;
		textureY = y;
	}

	public void render3D()
	{
		// model view stack 
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		// save current matrix
		GL11.glPushMatrix();
		
		// TRANSLATE 
		GL11.glTranslatef(m_nX, m_nY, m_nZ);
		
		// ROTATE and SCALE
		if (m_rZ!=0)
			GL11.glRotatef(m_rZ, 0, 0, 1);
		if (m_rY!=0)
			GL11.glRotatef(m_rY, 0, 1, 0);
		if (m_rX!=0)
			GL11.glRotatef(m_rX, 1, 0, 0);
		if (m_sX!=1 || m_sY!=1 || m_sZ!=1)
			GL11.glScalef(m_sX, m_sY, m_sZ);
		renderModel();
		
		// discard current matrix
		GL11.glPopMatrix();
	}
	//generira random rotacijo
	private void mesaj(){
		int rx = (int) (Math.random()*4);
		int ry = (int) (Math.random()*4);
		this.setRotation(90*rx, 90*ry, 0);
	}
	
	private void renderModel()
	{
		GL11.glBegin(GL11.GL_LINES);
			GL11.glVertex3f(-1.0f, -1.0f,1.0f);
			GL11.glVertex3f( 1.0f, -1.0f,1.0f);
			GL11.glVertex3f( 1.0f,1.0f,1.0f);
			GL11.glVertex3f(-1.0f,1.0f,1.0f);
			GL11.glVertex3f(1.0f, 1.0f, -1.0f);
			GL11.glVertex3f(1.0f,-1.0f, -1.0f);
			GL11.glVertex3f(-1.0f,-1.0f, -1.0f);
			GL11.glVertex3f(-1.0f, 1.0f, -1.0f);
			GL11.glVertex3f(-1.0f,1.0f, -1.0f);
			GL11.glVertex3f(-1.0f,1.0f,1.0f);
			GL11.glVertex3f( 1.0f,1.0f,1.0f);
			GL11.glVertex3f( 1.0f,1.0f, -1.0f);
			GL11.glVertex3f(1.0f, -1.0f, 1.0f);
			GL11.glVertex3f(-1.0f, -1.0f, 1.0f);
			GL11.glVertex3f(-1.0f, -1.0f,-1.0f);
			GL11.glVertex3f(1.0f, -1.0f,-1.0f);
			GL11.glVertex3f( 1.0f, -1.0f, -1.0f);
			GL11.glVertex3f( 1.0f,1.0f, -1.0f);
			GL11.glVertex3f( 1.0f,1.0f,1.0f);
			GL11.glVertex3f( 1.0f, -1.0f,1.0f);
			GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
			GL11.glVertex3f(-1.0f, -1.0f,1.0f);
			GL11.glVertex3f(-1.0f,1.0f,1.0f);
			GL11.glVertex3f(-1.0f,1.0f, -1.0f);
		GL11.glEnd();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, m_Textures.get(0));
		GL11.glBegin(GL11.GL_QUADS); // draw independent triangles
			GL11.glTexCoord2f(textureX, textureY); GL11.glVertex3f(-1.0f, -1.0f,1.0f);// Bottom Left Of The Texture and Quad
			GL11.glTexCoord2f(textureX+0.25f, textureY); GL11.glVertex3f( 1.0f, -1.0f,1.0f);// Bottom Right Of The Texture and Quad
			GL11.glTexCoord2f(textureX+0.25f, textureY+0.25f); GL11.glVertex3f( 1.0f,1.0f,1.0f);// Top Right Of The Texture and Quad
			GL11.glTexCoord2f(textureX, textureY+0.25f); GL11.glVertex3f(-1.0f,1.0f,1.0f);// Top Left Of The Texture and Quad
		GL11.glEnd();
		// Back Face
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, m_Textures.get(1));
		GL11.glBegin(GL11.GL_QUADS); // draw independent triangles
			GL11.glTexCoord2f(textureX+0.25f, textureY); GL11.glVertex3f(1.0f, 1.0f, -1.0f);// Bottom Right Of The Texture and Quad
			GL11.glTexCoord2f(textureX+0.25f, textureY+0.25f); GL11.glVertex3f(1.0f,-1.0f, -1.0f);// Top Right Of The Texture and Quad
			GL11.glTexCoord2f(textureX, textureY+0.25f); GL11.glVertex3f(-1.0f,-1.0f, -1.0f);// Top Left Of The Texture and Quad
			GL11.glTexCoord2f(textureX, textureY); GL11.glVertex3f(-1.0f, 1.0f, -1.0f);// Bottom Left Of The Texture and Quad
		GL11.glEnd();
		// Top Face
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, m_Textures.get(2));
		GL11.glBegin(GL11.GL_QUADS); // draw independent triangles
			GL11.glTexCoord2f(textureX, textureY+0.25f); GL11.glVertex3f(-1.0f,1.0f, -1.0f);// Top Left Of The Texture and Quad
			GL11.glTexCoord2f(textureX, textureY); GL11.glVertex3f(-1.0f,1.0f,1.0f);// Bottom Left Of The Texture and Quad
			GL11.glTexCoord2f(textureX+0.25f, textureY); GL11.glVertex3f( 1.0f,1.0f,1.0f);// Bottom Right Of The Texture and Quad
			GL11.glTexCoord2f(textureX+0.25f, textureY+0.25f); GL11.glVertex3f( 1.0f,1.0f, -1.0f);// Top Right Of The Texture and Quad
		GL11.glEnd();
		// Bottom Face
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, m_Textures.get(3));
		GL11.glBegin(GL11.GL_QUADS); // draw independent triangles
			GL11.glTexCoord2f(textureX+0.25f, textureY+0.25f); GL11.glVertex3f(1.0f, -1.0f, 1.0f);// Top Right Of The Texture and Quad
			GL11.glTexCoord2f(textureX, textureY+0.25f); GL11.glVertex3f(-1.0f, -1.0f, 1.0f);// Top Left Of The Texture and Quad
			GL11.glTexCoord2f(textureX, textureY); GL11.glVertex3f(-1.0f, -1.0f,-1.0f);// Bottom Left Of The Texture and Quad
			GL11.glTexCoord2f(textureX+0.25f, textureY); GL11.glVertex3f(1.0f, -1.0f,-1.0f);// Bottom Right Of The Texture and Quad
		GL11.glEnd();
		// Right face
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, m_Textures.get(4));
		GL11.glBegin(GL11.GL_QUADS); // draw independent triangles
			GL11.glTexCoord2f(textureX+0.25f, textureY); GL11.glVertex3f( 1.0f, -1.0f, -1.0f);// Bottom Right Of The Texture and Quad
			GL11.glTexCoord2f(textureX+0.25f, textureY+0.25f); GL11.glVertex3f( 1.0f,1.0f, -1.0f);// Top Right Of The Texture and Quad
			GL11.glTexCoord2f(textureX, textureY+0.25f); GL11.glVertex3f( 1.0f,1.0f,1.0f);// Top Left Of The Texture and Quad
			GL11.glTexCoord2f(textureX, textureY); GL11.glVertex3f( 1.0f, -1.0f,1.0f);// Bottom Left Of The Texture and Quad
		GL11.glEnd();
		// Left Face
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, m_Textures.get(5));
		GL11.glBegin(GL11.GL_QUADS); // draw independent triangles
			GL11.glTexCoord2f(textureX, textureY); GL11.glVertex3f(-1.0f, -1.0f, -1.0f);// Bottom Left Of The Texture and Quad
			GL11.glTexCoord2f(textureX+0.25f, textureY); GL11.glVertex3f(-1.0f, -1.0f,1.0f);// Bottom Right Of The Texture and Quad
			GL11.glTexCoord2f(textureX+0.25f, textureY+0.25f); GL11.glVertex3f(-1.0f,1.0f,1.0f);// Top Right Of The Texture and Quad
			GL11.glTexCoord2f(textureX, textureY+0.25f); GL11.glVertex3f(-1.0f,1.0f, -1.0f);// Top Left Of The Texture and Quad
		GL11.glEnd();
	}
}