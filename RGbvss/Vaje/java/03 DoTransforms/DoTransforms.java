import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class DoTransforms extends ShowPyramid 
{

  float posX = 0, posY = 0, rotX = 0, rotY = 0, scale = 1;
  
	/**
	 * Initial setup of projection of the scene onto screen, lights etc.
	 */
  protected void setupView()
	{
    super.setupView();    
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
    GL11.glRotatef(30.0f, 1.0f, 0.0f, 0.0f);    
  }

  /**
   * Renders current frame
   */
  protected void renderFrame()
  {

    // model view stack 
    GL11.glMatrixMode(GL11.GL_MODELVIEW);
    
    // save camera matrix
    GL11.glPushMatrix();

    // TRANSLATE by X
    GL11.glTranslatef(posX, posY, 0.0f);

    // ROTATE around X - bring to center, rotate, bring back
    GL11.glTranslatef(0, 0, -3.5f);
    GL11.glRotatef(rotY, 0, 1, 0);
    GL11.glRotatef(rotX, 1, 0, 0);
    GL11.glScalef(scale, scale, scale);
    GL11.glTranslatef(0, 0, 3.5f);
    

    // draw pyramid
    super.renderFrame();

    // discard current matrix
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
    (new DoTransforms()).execute();
  }  
}
