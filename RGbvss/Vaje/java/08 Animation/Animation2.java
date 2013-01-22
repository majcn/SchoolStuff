import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class Animation2 extends ShowPyramid 
{

  float posX = 0, posY = 0, rotX = 0, rotY = 0, scale = 1;
  int nPyramidList;
	/**
	 * Initial setup of projection of the scene onto screen, lights etc.
	 */
  protected void setupView()
	{
    super.setupView();    
    setCameraMatrix();    
    nPyramidList = makeList();
	}
  
  protected void setCameraMatrix()
  {
    // model view stack 
    GL11.glMatrixMode(GL11.GL_MODELVIEW);
    GL11.glLoadIdentity();
    // setup view space; 
    // translate to 0,2,4 and rotate 30 degrees along x 
    GL11.glTranslatef(0, -2f, -8.0f);
    GL11.glRotatef(30.0f, 1.0f, 0.0f, 0.0f);    
  }

  protected int makeList()
  {
    // make id for one list
    int listID = GL11.glGenLists(1);
    
    // next we start producing the contents of the list
    GL11.glNewList(listID, GL11.GL_COMPILE);    

    GL11.glBegin(GL11.GL_TRIANGLES); // draw independent triangles
    GL11.glColor3f(0, 0, 1);
    GL11.glVertex3f(-1.0f, -0.5f, -0.5f);    // lower left vertex
    GL11.glVertex3f( 0.0f,  0.5f, -0.5f);    // upper vertex
    GL11.glVertex3f( 1.0f, -0.5f, -0.5f);    // lower right vertex

    GL11.glColor3f(1, 0, 0);
    GL11.glVertex3f(-1.0f, -0.5f, -0.5f);    // lower left vertex
    GL11.glVertex3f( 1.0f, -0.5f, -0.5f);    // lower right vertex
    GL11.glVertex3f( 0.0f,  -0.5f, 0.5f);    // lower front vertex

    GL11.glColor3f(1, 1, 0);
    GL11.glVertex3f(-1.0f, -0.5f, -0.5f);    // lower left vertex
    GL11.glVertex3f( 0.0f,  -0.5f, 0.5f);    // lower front vertex
    GL11.glVertex3f( 0.0f,  0.5f, -0.5f);    // upper vertex

    GL11.glColor3f(0, 1, 1);
    GL11.glVertex3f( 1.0f, -0.5f, -0.5f);    // lower right vertex
    GL11.glVertex3f( 0.0f,  0.5f, -0.5f);    // upper vertex
    GL11.glVertex3f( 0.0f,  -0.5f, 0.5f);    // lower front vertex    
    GL11.glEnd();
    
    // end list
    GL11.glEndList();
    
    return listID;
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
    GL11.glRotatef(rotY, 0, 1, 0);
    GL11.glRotatef(rotX, 1, 0, 0);
    GL11.glScalef(scale, scale, scale);   

    // draw character
    drawCharacter();
    
    // discard current matrix
    GL11.glPopMatrix();
    
  }
  
  float nHandsAngle=35, nHandsDelta = 0.4f;
  float nLegsAngle=25, nLegsDelta = 0.4f;
  protected void drawCharacter()
  {
    // telo
    GL11.glPushMatrix();
    GL11.glScalef(0.3f, 1, 0.3f);   
    GL11.glCallList(nPyramidList);
    GL11.glPopMatrix();

    // glava
    GL11.glPushMatrix();
    GL11.glTranslatef(0, 0.75f, 0);   
    GL11.glRotatef(180,0, 0, 1);   
    GL11.glScalef(0.3f, 0.5f, 0.3f);   
    GL11.glCallList(nPyramidList);
    GL11.glPopMatrix();

    // noge
    GL11.glPushMatrix();
    GL11.glTranslatef(0.3f, -1.25f, 0);   
    GL11.glTranslatef(0f, 0.75f, 0);   
//    GL11.glRotatef(23,0, 0, 1f);   
    GL11.glRotatef(nLegsAngle,1, 0, 0);   
    GL11.glScalef(0.2f, 1.5f, 0.2f);   
    GL11.glTranslatef(0f, -0.5f, 0);   
    GL11.glCallList(nPyramidList);
    GL11.glPopMatrix();

    GL11.glPushMatrix();
    GL11.glTranslatef(-0.3f, -1.25f, 0);
    GL11.glTranslatef(0f, 0.75f, 0);
//    GL11.glRotatef(-23,0, 0, 1f);   
    GL11.glRotatef(-nLegsAngle,1f, 0, 0);
    GL11.glScalef(0.2f, 1.5f, 0.2f);
    GL11.glTranslatef(0f, -0.5f, 0);
    GL11.glCallList(nPyramidList);
    GL11.glPopMatrix();
    
    // roke
    GL11.glPushMatrix();
    GL11.glTranslatef(0.1f, 0f, 0);   
    GL11.glTranslatef(0f, 0.5f, 0);   
    GL11.glRotatef(nHandsAngle,0, 0, 1f);   
    GL11.glTranslatef(0f, -0.5f, 0);   
    GL11.glScalef(0.2f, 1f, 0.2f);   
    GL11.glCallList(nPyramidList);
    GL11.glPopMatrix();

    GL11.glPushMatrix();
    GL11.glTranslatef(-0.1f, 0f, 0);   
    GL11.glTranslatef(0f, 0.5f, 0);   
    GL11.glRotatef(-nHandsAngle,0, 0, 1f);   
    GL11.glTranslatef(0f, -0.5f, 0);   
    GL11.glScalef(0.2f, 1f, 0.2f);   
    GL11.glCallList(nPyramidList);
    GL11.glPopMatrix();    

    if (nHandsAngle>=135)
      nHandsDelta=-0.4f;      
    else if (nHandsAngle<=35)
      nHandsDelta=0.4f;    
    nHandsAngle+=nHandsDelta;

    if (nLegsAngle>=40)
      nLegsDelta=-0.4f;      
    else if (nLegsAngle<=-40)
      nLegsDelta=0.4f;    
    nLegsAngle+=nLegsDelta;
  
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
    (new Animation2()).execute();
  }  
}
