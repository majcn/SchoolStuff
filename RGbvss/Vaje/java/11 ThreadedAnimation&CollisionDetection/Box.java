import org.lwjgl.opengl.GL11;


public class Box extends Model3D
{
  boolean wire = false;
  
  public void setWire(boolean wire)
  {
    this.wire = wire;
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
  
  private void renderModel()
  {
    GL11.glEnable(GL11.GL_BLEND);
    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    
    if (wire)
      GL11.glColor4f(0.1f, 0.2f, 0.3f, 1f);
    else
      GL11.glColor4f(0.3f, 0.6f, 0.8f, 0.5f);
    
    GL11.glBegin(GL11.GL_QUADS); // draw independent triangles
    
    // Back Face
    GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
    GL11.glVertex3f(-1.0f,  1.0f, -1.0f);
    GL11.glVertex3f( 1.0f,  1.0f, -1.0f);
    GL11.glVertex3f( 1.0f, -1.0f, -1.0f);

    GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
    GL11.glVertex3f( 1.0f, -1.0f, -1.0f);
    GL11.glVertex3f( 1.0f,  1.0f, -1.0f);
    GL11.glVertex3f(-1.0f,  1.0f, -1.0f);
    
    // Bottom Face
    GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
    GL11.glVertex3f( 1.0f, -1.0f, -1.0f);
    GL11.glVertex3f( 1.0f, -1.0f,  1.0f);
    GL11.glVertex3f(-1.0f, -1.0f,  1.0f);

    GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
    GL11.glVertex3f(-1.0f, -1.0f,  1.0f);
    GL11.glVertex3f( 1.0f, -1.0f,  1.0f);
    GL11.glVertex3f( 1.0f, -1.0f, -1.0f);
    
    // Right face
    GL11.glVertex3f( 1.0f, -1.0f, -1.0f);
    GL11.glVertex3f( 1.0f,  1.0f, -1.0f);
    GL11.glVertex3f( 1.0f,  1.0f,  1.0f);
    GL11.glVertex3f( 1.0f, -1.0f,  1.0f);

    GL11.glVertex3f( 1.0f, -1.0f, -1.0f);
    GL11.glVertex3f( 1.0f, -1.0f,  1.0f);
    GL11.glVertex3f( 1.0f,  1.0f,  1.0f);
    GL11.glVertex3f( 1.0f,  1.0f, -1.0f);
    
    // Left Face
    GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
    GL11.glVertex3f(-1.0f, -1.0f,  1.0f);
    GL11.glVertex3f(-1.0f,  1.0f,  1.0f);
    GL11.glVertex3f(-1.0f,  1.0f, -1.0f);
    
    GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
    GL11.glVertex3f(-1.0f,  1.0f, -1.0f);
    GL11.glVertex3f(-1.0f,  1.0f,  1.0f);
    GL11.glVertex3f(-1.0f, -1.0f,  1.0f);
    
    // Front Face
    GL11.glVertex3f(-1.0f, -1.0f,  1.0f);
    GL11.glVertex3f( 1.0f, -1.0f,  1.0f);
    GL11.glVertex3f( 1.0f,  1.0f,  1.0f);
    GL11.glVertex3f(-1.0f,  1.0f,  1.0f);
    
    GL11.glVertex3f(-1.0f, -1.0f,  1.0f);
    GL11.glVertex3f(-1.0f,  1.0f,  1.0f);
    GL11.glVertex3f( 1.0f,  1.0f,  1.0f);
    GL11.glVertex3f( 1.0f, -1.0f,  1.0f);
    
    // Top Face
    GL11.glVertex3f(-1.0f,  1.0f, -1.0f);
    GL11.glVertex3f(-1.0f,  1.0f,  1.0f);
    GL11.glVertex3f( 1.0f,  1.0f,  1.0f);
    GL11.glVertex3f( 1.0f,  1.0f, -1.0f);

    GL11.glVertex3f(-1.0f,  1.0f, -1.0f);
    GL11.glVertex3f( 1.0f,  1.0f, -1.0f);
    GL11.glVertex3f( 1.0f,  1.0f,  1.0f);
    GL11.glVertex3f(-1.0f,  1.0f,  1.0f);
    
    GL11.glEnd();

    GL11.glDisable(GL11.GL_BLEND);
  }
}
