import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;


public class Ball extends Model3D
{
  private Sphere s = new Sphere();
  public float radius = 1;
  public float[] speed = {5f, 0.01f, 0.01f};
  public float[] color = {0, 0, 0};

  public Ball(float r) {
    super();
    radius = r;
  }
  
  public void setPos(float[] pos) {
    m_nX = pos[0];
    m_nY = pos[1];
    m_nZ = pos[2];
  }
  
  public void move(int timeMilis) {
    m_nX += speed[0]*timeMilis/1000;
    m_nY += speed[1]*timeMilis/1000;
    m_nZ += speed[2]*timeMilis/1000;
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
    GL11.glColor3f(color[0], color[1], color[2]);
    s.draw(radius, 64, 64);
  }
}
