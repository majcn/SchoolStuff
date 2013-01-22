import org.lwjgl.opengl.GL11;


public class TexturedCube extends Model3D
{
  
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
  
  private void renderModel()
  {
    GL11.glBegin(GL11.GL_QUADS); // draw independent triangles
    GL11.glTexCoord2f(0.0f, 0.0f); GL11.glVertex3f(-1.0f, -1.0f,  1.0f);  // Bottom Left Of The Texture and Quad
    GL11.glTexCoord2f(1.0f, 0.0f); GL11.glVertex3f( 1.0f, -1.0f,  1.0f);  // Bottom Right Of The Texture and Quad
    GL11.glTexCoord2f(1.0f, 1.0f); GL11.glVertex3f( 1.0f,  1.0f,  1.0f);  // Top Right Of The Texture and Quad
    GL11.glTexCoord2f(0.0f, 1.0f); GL11.glVertex3f(-1.0f,  1.0f,  1.0f);  // Top Left Of The Texture and Quad
    // Back Face
    GL11.glTexCoord2f(1.0f, 0.0f); GL11.glVertex3f(-1.0f, -1.0f, -1.0f);  // Bottom Right Of The Texture and Quad
    GL11.glTexCoord2f(1.0f, 1.0f); GL11.glVertex3f(-1.0f,  1.0f, -1.0f);  // Top Right Of The Texture and Quad
    GL11.glTexCoord2f(0.0f, 1.0f); GL11.glVertex3f( 1.0f,  1.0f, -1.0f);  // Top Left Of The Texture and Quad
    GL11.glTexCoord2f(0.0f, 0.0f); GL11.glVertex3f( 1.0f, -1.0f, -1.0f);  // Bottom Left Of The Texture and Quad
    // Top Face
    GL11.glTexCoord2f(0.0f, 1.0f); GL11.glVertex3f(-1.0f,  1.0f, -1.0f);  // Top Left Of The Texture and Quad
    GL11.glTexCoord2f(0.0f, 0.0f); GL11.glVertex3f(-1.0f,  1.0f,  1.0f);  // Bottom Left Of The Texture and Quad
    GL11.glTexCoord2f(1.0f, 0.0f); GL11.glVertex3f( 1.0f,  1.0f,  1.0f);  // Bottom Right Of The Texture and Quad
    GL11.glTexCoord2f(1.0f, 1.0f); GL11.glVertex3f( 1.0f,  1.0f, -1.0f);  // Top Right Of The Texture and Quad
    // Bottom Face
    GL11.glTexCoord2f(1.0f, 1.0f); GL11.glVertex3f(-1.0f, -1.0f, -1.0f);  // Top Right Of The Texture and Quad
    GL11.glTexCoord2f(0.0f, 1.0f); GL11.glVertex3f( 1.0f, -1.0f, -1.0f);  // Top Left Of The Texture and Quad
    GL11.glTexCoord2f(0.0f, 0.0f); GL11.glVertex3f( 1.0f, -1.0f,  1.0f);  // Bottom Left Of The Texture and Quad
    GL11.glTexCoord2f(1.0f, 0.0f); GL11.glVertex3f(-1.0f, -1.0f,  1.0f);  // Bottom Right Of The Texture and Quad
    // Right face
    GL11.glTexCoord2f(1.0f, 0.0f); GL11.glVertex3f( 1.0f, -1.0f, -1.0f);  // Bottom Right Of The Texture and Quad
    GL11.glTexCoord2f(1.0f, 1.0f); GL11.glVertex3f( 1.0f,  1.0f, -1.0f);  // Top Right Of The Texture and Quad
    GL11.glTexCoord2f(0.0f, 1.0f); GL11.glVertex3f( 1.0f,  1.0f,  1.0f);  // Top Left Of The Texture and Quad
    GL11.glTexCoord2f(0.0f, 0.0f); GL11.glVertex3f( 1.0f, -1.0f,  1.0f);  // Bottom Left Of The Texture and Quad
    // Left Face
    GL11.glTexCoord2f(0.0f, 0.0f); GL11.glVertex3f(-1.0f, -1.0f, -1.0f);  // Bottom Left Of The Texture and Quad
    GL11.glTexCoord2f(1.0f, 0.0f); GL11.glVertex3f(-1.0f, -1.0f,  1.0f);  // Bottom Right Of The Texture and Quad
    GL11.glTexCoord2f(1.0f, 1.0f); GL11.glVertex3f(-1.0f,  1.0f,  1.0f);  // Top Right Of The Texture and Quad
    GL11.glTexCoord2f(0.0f, 1.0f); GL11.glVertex3f(-1.0f,  1.0f, -1.0f);  // Top Left Of The Texture and Quad  
    GL11.glEnd();
  }
}
