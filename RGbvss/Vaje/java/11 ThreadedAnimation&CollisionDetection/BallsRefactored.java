import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class BallsRefactored extends BaseWindow 
{
  BitmapText text;

  boolean running = true;
  
  float posX = 0, posY = 0, posZ = 0, rotX = 0, rotY = 0, scale = 1;
  int fps = 0; 
  
  Ball[] balls = new Ball[10];
  Box box;

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
    GL11.glTranslatef(0, 0f, -10.0f);
    GL11.glRotatef(15.0f, 1.0f, 0.0f, 0.0f);    
  }

  /** 
   * can be used for 3D model initialization
   */
  protected void initializeModels()
  {
    text = new BitmapText();
    for (int i=0; i<balls.length; i++) {
       balls[i] = new Ball((float)Math.random()*0.2f+0.1f);
       balls[i].color = new float[]{(float)Math.random(), (float)Math.random(), (float)Math.random()};
       balls[i].speed = new float[]{(float)Math.random()*4-2, (float)Math.random()*4-2, (float)Math.random()*4-2};
       boolean flag = true; 
       while (flag & i>0) {
         balls[i].setPos(new float[] {(float)Math.random()*(4-1.99f*balls[i].radius)-(1.99f-balls[i].radius),(float)Math.random()*(4-1.99f*balls[i].radius)-(1.99f-balls[i].radius), (float)Math.random()*(4-1.99f*balls[i].radius)-(1.99f-balls[i].radius)});
         for (int j=0; j<i; j++) {
           if ( (balls[i].m_nX - balls[j].m_nX)*(balls[i].m_nX - balls[j].m_nX) + (balls[i].m_nY - balls[j].m_nY)*(balls[i].m_nY - balls[j].m_nY) + (balls[i].m_nZ - balls[j].m_nZ)*(balls[i].m_nZ - balls[j].m_nZ) > (balls[i].radius + balls[j].radius)*(balls[i].radius + balls[j].radius) ) {
             flag = false;
           }
         }
       }
    }
    box = new Box();
    
    Thread t = new Thread(new Runnable()
    {
      
      @Override
      public void run()
      {
        while (running) {
          try {
            for (Ball b : balls)
              b.move(20);
            checkCollisions();
            Thread.sleep(20);
          } catch (Exception e) {
          }
        }
      }
    });
    t.start();
  }
  /**
   * Checks collisions between balls and the box,
   * but not between balls themselves
   */
  protected void checkCollisions() {
    for (int i=0; i<balls.length; i++) {
      if (balls[i].m_nX - balls[i].radius < -1.99) {
        balls[i].speed[0] = -balls[i].speed[0];
      }
      if (balls[i].m_nY - balls[i].radius < -1.99) {
        balls[i].speed[1] = -balls[i].speed[1];
      }
      if (balls[i].m_nZ - balls[i].radius < -1.99) {
        balls[i].speed[2] = -balls[i].speed[2];
      }
      
      if (balls[i].m_nX + balls[i].radius > 1.99) {
        balls[i].speed[0] = -balls[i].speed[0];
      }
      if (balls[i].m_nY + balls[i].radius > 1.99) {
        balls[i].speed[1] = -balls[i].speed[1];
      }
      if (balls[i].m_nZ + balls[i].radius > 1.99) {
        balls[i].speed[2] = -balls[i].speed[2];
      }
    }
  }
  /**
   * Resets the view of current frame
   */
  protected void resetView()
  {
    // clear color and depth buffer
    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    GL11.glClearColor(1, 1, 1, 1);
  }
  
  /**
   * Renders current frame
   */
  protected void renderFrame()
  {
    long tic = System.currentTimeMillis();
    
//    m_pUser.setPosition(posX, posY, posZ);
//    ball1.setRotation(rotX, rotY, 0);
//    ball1.setScaling(scale, scale, scale);
    
    GL11.glRotatef(rotX, 1, 0, 0);
    GL11.glRotatef(rotY, 0, 1, 0);

    for (Ball b: balls)
      b.render3D();
    
    
    GL11.glPushMatrix();
    GL11.glScalef(2f, 2f, 2f);

    GL11.glPolygonOffset(-1.0f, -1.0f);
    GL11.glEnable(GL11.GL_POLYGON_OFFSET_LINE);
//    GL11.glEnable(GL11.GL_LINE_SMOOTH);
    GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
    GL11.glLineWidth(1f);
    box.setWire(true);
    box.render3D();
    box.setWire(false);
    GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
    GL11.glDisable(GL11.GL_POLYGON_OFFSET_LINE);
    GL11.glLineWidth(1.0f);
//    GL11.glDisable(GL11.GL_LINE_SMOOTH);
    
    box.render3D();

    GL11.glPolygonOffset(-1.0f, -1.0f);
    GL11.glEnable(GL11.GL_POLYGON_OFFSET_LINE);
    GL11.glEnable(GL11.GL_LINE_SMOOTH);
    GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
    GL11.glLineWidth(1f);
    box.setWire(true);
    box.render3D();
    box.setWire(false);
    GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
    GL11.glDisable(GL11.GL_POLYGON_OFFSET_LINE);
    GL11.glLineWidth(1.0f);
    GL11.glDisable(GL11.GL_LINE_SMOOTH);
    
    GL11.glPopMatrix();
    
    
//   HUD & Text render
    startHUD();
    
    GL11.glColor4f(0, 0, 0, 1);
    float w = text.textWidth(""+fps, 15);
    GL11.glTranslatef(1024-w, 768-20, 0);
    text.renderString(""+fps, 15);
    
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
    if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
      running = false;
      System.exit(0);
    }
            
    super.processInput();
  }
  
  public static void main(String[] args) {
    (new BallsRefactored()).execute();
  }  
}
