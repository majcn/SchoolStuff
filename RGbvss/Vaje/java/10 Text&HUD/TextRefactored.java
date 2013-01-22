import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class TextRefactored extends BaseWindow 
{
  BitmapText text;

  float posX = 0, posY = 0, posZ = 0, rotX = 0, rotY = 0, scale = 1;
  int fps = 0; 
  
  Pyramid m_pUser, m_pUser2;
  Text m_txt;

	/**
	 * Initial setup of projection of the scene onto screen, lights etc.
	 */
  protected void setupView()
	{    
    // textures
 // select modulate to mix texture with color for shading; GL_REPLACE, GL_MODULATE ...
    GL11.glTexEnvf( GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE );
    
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
    text = new BitmapText();
    m_pUser = new Pyramid();
    m_pUser2 = new  Pyramid();
    m_txt = new Text();
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
    long tic = System.currentTimeMillis();
    
    m_pUser.setPosition(posX, posY, posZ);
    m_pUser.setRotation(rotX, rotY, 0);
    m_pUser.setScaling(scale, scale, scale);

    m_pUser.render3D();
    m_pUser2.setPosition(posX+2, posY, posZ);
    m_pUser2.setRotation(rotX, rotY, 0);
    m_pUser2.setScaling(scale, scale, scale);

    m_pUser2.render3D();
    
//   Text render
    m_txt.setPosition(posX-3, posY, posZ);
    m_txt.setRotation(rotX, rotY, 0);
    m_txt.setScaling(scale/100, scale/100, scale/100);

    m_txt.render3D();
    
    
//   HUD & Text render
    startHUD();
    
    GL11.glColor4f(1, 1, 1, 1);
    float w = text.textWidth(""+fps, 15);
    GL11.glTranslatef(1024-w, 768-20, 0);
    text.renderString(""+fps, 15);
    
    GL11.glLoadIdentity();
    GL11.glColor4f(0, 1, 0, 0.25f);
    w = text.textWidth("HUD - Demo", 100);
    GL11.glTranslatef(1024/2-w/2, 768/2-50, 0);
    text.renderString("HUD - Demo", 100);
    
    GL11.glLoadIdentity();
    GL11.glColor4f(0.3f, 0.3f, 0.3f, 1f);
    GL11.glTranslatef(10, 70, 0);
    text.renderString("Pozdravljen svet!", 25);
    GL11.glTranslatef(0, -30, 0);
    text.renderString("Prikazan je primer Head Up Display-a.", 25);
    GL11.glTranslatef(0, -30, 0);
    text.renderString("Besedilo miruje kljub transformiranju objektov!", 25);
    text.renderString("lsdfh92z\\rH)(Z#)HF)", 25);
    
    endHUD();
    
    long tac = System.currentTimeMillis();
    if ((tac - tic) > 0)
      fps  = (int)(1000 / (tac - tic));
  }
  
  protected void startHUD() {
    GL11.glMatrixMode(GL11.GL_PROJECTION);
    GL11.glPushMatrix();
    GL11.glLoadIdentity();
    GL11.glOrtho(0, 1024, 0, 768, -1, 1);
    GL11.glMatrixMode(GL11.GL_MODELVIEW);
    GL11.glPushMatrix();
    GL11.glLoadIdentity();
  }
  
  protected void endHUD() {
    GL11.glMatrixMode(GL11.GL_PROJECTION);
    GL11.glPopMatrix();
    GL11.glMatrixMode(GL11.GL_MODELVIEW);
    GL11.glPopMatrix();
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
    (new TextRefactored()).execute();
  }  
}
