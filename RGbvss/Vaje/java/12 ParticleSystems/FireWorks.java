import org.lwjgl.opengl.GL11;


public class FireWorks extends Model3D
{
  private int time = 0, dTime;
  private float[] color = {0, 0, 0, 1}, originalColor = {0, 0, 0, 1};
  private float pointSize = 6f;

  public FireWorks(float[] c, int dt) {
    super();
    dTime = dt;
    color = c;
    originalColor[0] = c[0];
    originalColor[1] = c[1];
    originalColor[2] = c[2];
    originalColor[3] = c[3];
  }
  
  public void setDTime(int dt) {
    dTime = dt;
  }
  
  public void step() {
    if (time +dTime >= 1000) {
      color[0] = originalColor[0];
      color[1] = originalColor[1];
      color[2] = originalColor[2];
      color[3] = originalColor[3];
      setRotation((float)Math.random()*90f, (float)Math.random()*90f, 0f);
    }
    time = (time + dTime)%1000;
    
    color[3] -= (float)dTime/1000f;
  }
  
  public void setPos(float[] pos) {
    m_nX = pos[0];
    m_nY = pos[1];
    m_nZ = pos[2];
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
//    GL11.glTranslatef(0, 0, -5f);
    if (m_rZ!=0)
      GL11.glRotatef(m_rZ, 0, 0, 1);
    if (m_rY!=0)
      GL11.glRotatef(m_rY, 0, 1, 0);
    if (m_rX!=0)
      GL11.glRotatef(m_rX, 1, 0, 0);
    if (m_sX!=1 || m_sY!=1 || m_sZ!=1)
      GL11.glScalef(m_sX, m_sY, m_sZ);
    GL11.glTranslatef(0, 0, 0f);    

    renderModel();

    // discard current matrix
    GL11.glPopMatrix();
  }

  private void renderModel()
  {
    GL11.glEnable(GL11.GL_BLEND);
    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    GL11.glPointSize(pointSize/2 +pointSize*(float)time/2000f);
    GL11.glColor4f(color[0], color[1], color[2], color[3]);
    GL11.glBegin(GL11.GL_POINTS);
      for (int i=0; i<360; i+=10)
        GL11.glVertex3f((float)Math.sin(Math.toRadians(i))*3f*((float)time/1000f), (float)Math.cos(Math.toRadians(i))*3f*((float)time/1000f), 0);
    GL11.glEnd();
    GL11.glDisable(GL11.GL_BLEND);
  }
}
