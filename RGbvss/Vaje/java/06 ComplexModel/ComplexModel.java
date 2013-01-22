import org.lwjgl.opengl.*;

public class ComplexModel extends Refactored 
{

  Obj3D m_Obj;
  
  protected void initializeModels()
  {
//	GL11.glShadeModel(GL11.GL_SMOOTH);
	    
	// lights
	GL11.glEnable(GL11.GL_LIGHTING);
	GL11.glEnable(GL11.GL_LIGHT0);
		
	GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, allocFloats(new float[] { 0f, 0f, 2.0f, 0f}));
	GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, allocFloats(new float[] { 0.2f, 0.2f, 0.2f, 0.0f}));
	GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE , allocFloats(new float[] { 1.0f, 1.0f, 1.0f, 0.0f}));
	GL11.glLightf(GL11.GL_LIGHT0, GL11.GL_LINEAR_ATTENUATION , 7f);

    // textures
    // enable 2D textures 
    GL11.glEnable(GL11.GL_TEXTURE_2D);
 // select modulate to mix texture with color for shading; GL_REPLACE, GL_MODULATE ...
    GL11.glTexEnvf( GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE );

    m_Obj = new Obj3D("models/JetFire/JetFire.obj");
//    m_Obj = new Obj3D("car.obj");
//    m_Obj = new Obj3D("ship.obj");
  }
 
  /**
   * Renders current frame
   */
  protected void renderFrame()
  {
    m_Obj.setPosition(posX, posY+2, posZ);
    m_Obj.setRotation(rotX, rotY, 0);
    m_Obj.setScaling(1f,1f,1f);   //   JetFireSize
//    m_Obj.setScaling(0.01f,0.01f,0.01f);    //  car & ship size
    
    m_Obj.render3D();    
  }
  
  /**
   * Processes Keyboard and Mouse input and spawns actions
   */  
  protected void processInput()
  {
    super.processInput();
  }
  
  public static void main(String[] args) {
    (new ComplexModel()).execute();
  }  
}
