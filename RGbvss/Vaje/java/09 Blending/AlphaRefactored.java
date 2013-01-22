import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class AlphaRefactored extends BaseWindow 
{

  float posX = 0, posY = 0, posZ = 0, rotX = 0, rotY = 0, scale = 1;
  
  AlphaPyramid m_pUser, m_pUser2;

	/**
	 * Initial setup of projection of the scene onto screen, lights etc.
	 */
  protected void setupView()
	{    
    initializeModels();
    
    // enable depth buffer (off by default)
    GL11.glEnable(GL11.GL_DEPTH_TEST); 
    // enable culling of back sides of polygons
    GL11.glEnable(GL11.GL_CULL_FACE);

    // mapping from normalized to window coordinates
    GL11.glViewport(0, 0, 1024, 768);

    // setup projection matrix stack
    GL11.glMatrixMode(GL11.GL_PROJECTION);
    GL11.glLoadIdentity();
    // orthographic projection 
    //GL11.glOrtho(-5,5,-5,5,1,30);
    // perspective projection (45% FOV, 4/3 aspect, clipping near 0, far 30);
    GLU.gluPerspective(45, 1024 / (float)768, 1.0f, 30.0f);

    setCameraMatrix();    
	}
    
  protected void setCameraMatrix()
  {
    // model view stack 
    GL11.glMatrixMode(GL11.GL_MODELVIEW);
    GL11.glLoadIdentity();
    // setup view space; 
    // translate to 0,2,4 and rotate 30 degrees along x 
    GL11.glTranslatef(0, -2f, -4.0f);
    GL11.glRotatef(15.0f, 1.0f, 0.0f, 0.0f);    
  }

  /** 
   * can be used for 3D model initialization
   */
  protected void initializeModels()
  {
    m_pUser = new AlphaPyramid();
    m_pUser2 = new  AlphaPyramid();
  }
  /**
   * Resets the view of current frame
   */
  protected void resetView()
  {
    // clear color and depth buffer
    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
  }
  
  /**
   * Renders current frame
   */
  protected void renderFrame()
  {
    GL11.glBegin(GL11.GL_QUADS);
    GL11.glColor4f(0.5f, 0.6f, 0.7f, 1.0f);
    GL11.glVertex3f(-3.0f, -3.0f, -4.5f);
    GL11.glVertex3f(3.0f, -3.0f, -4.5f);
    GL11.glVertex3f(3.0f, 3.0f, -4.5f);
    GL11.glVertex3f(-3.0f, 3.0f, -4.5f);
  GL11.glEnd();
    GL11.glEnable(GL11.GL_BLEND);
    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    m_pUser.setPosition(posX, posY, posZ);
    m_pUser.setRotation(rotX, rotY, 0);
    m_pUser.setScaling(scale, scale, scale);

    m_pUser.render3D();
    m_pUser2.setPosition(posX+2, posY, posZ);
    m_pUser2.setRotation(rotX, rotY, 0);
    m_pUser2.setScaling(scale, scale, scale);

    m_pUser2.render3D();
    GL11.glDisable(GL11.GL_BLEND);
  }
  
  /**
   * Processes Keyboard and Mouse input and spawns actions
   */  
  protected void processInput()
  {
    if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
      posX-=0.01;
    if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
      posX+=0.01;
    if (Keyboard.isKeyDown(Keyboard.KEY_UP))
      posY+=0.01;
    if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
      posY-=0.01;
    if (Keyboard.isKeyDown(Keyboard.KEY_HOME))
      posZ-=0.01;
    if (Keyboard.isKeyDown(Keyboard.KEY_END))
      posZ+=0.01;
    if (Keyboard.isKeyDown(Keyboard.KEY_Q))
      rotX++;
    if (Keyboard.isKeyDown(Keyboard.KEY_A))
      rotX--;    
    if (Keyboard.isKeyDown(Keyboard.KEY_E))
      rotY++;
    if (Keyboard.isKeyDown(Keyboard.KEY_D))
      rotY--;    
    if (Keyboard.isKeyDown(Keyboard.KEY_W))
      scale+=0.01;    
    if (Keyboard.isKeyDown(Keyboard.KEY_S))
      scale-=0.01;
            
    super.processInput();
  }
  
  public static void main(String[] args) {
    (new AlphaRefactored()).execute();
  }  
}
