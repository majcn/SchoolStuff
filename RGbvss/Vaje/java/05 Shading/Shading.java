import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;

public class Shading extends Refactored 
{

  TexturedCube m_Obj;
  IntBuffer m_Textures;
  
  protected void setupView()
  {
    super.setupView();
    // wireframe display
    // GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
   
    // smooth shading - Gouraud
    GL11.glShadeModel(GL11.GL_SMOOTH);
    
    // lights
    GL11.glEnable(GL11.GL_LIGHTING);
    GL11.glEnable(GL11.GL_LIGHT0);

    GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, allocFloats(new float[] { 0.2f, 0.2f, 0.2f, 0.0f}));
    GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE , allocFloats(new float[] { 1.0f, 1.0f, 1.0f, 0.0f}));
    GL11.glLightf(GL11.GL_LIGHT0, GL11.GL_LINEAR_ATTENUATION , 7f);
    GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, allocFloats(new float[] { 0f, 0f, 6.0f, 0f}));
    
    // fog
    GL11.glEnable(GL11.GL_FOG);
    GL11.glFog(GL11.GL_FOG_COLOR,allocFloats(new float[] { 0.8f,0.8f,0.8f,0.0f }));
    GL11.glFogi(GL11.GL_FOG_MODE,GL11.GL_EXP);
    GL11.glFogf(GL11.GL_FOG_DENSITY,0.1f);
    
    // textures
    // enable 2D textures 
    GL11.glEnable(GL11.GL_TEXTURE_2D);
 // select modulate to mix texture with color for shading; GL_REPLACE, GL_MODULATE ...
    GL11.glTexEnvf( GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE );    
 }

  protected void initializeModels()
  {
    m_Obj = new TexturedCube();
    m_Textures= Texture.loadTextures2D(new String[] { "checker2.jpg" });    
  } 
    
  /**
   * Renders current frame
   */
  protected void renderFrame()
  {  
    m_Obj.setPosition(0f, 2f, -0.5f);
    m_Obj.setRotation(rotX, 45, 0);
    m_Obj.setScaling(0.5f,0.5f,0.5f);
    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, allocFloats(new float[] { 1.0f, 1.0f, 1.0f, 0.0f}));
    // choose first texture
    GL11.glBindTexture(GL11.GL_TEXTURE_2D, m_Textures.get(0));    
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
    (new Shading()).execute();
  }  
}
