import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class Animation1 extends BaseWindow
{
  /**
   * Initial setup of projection of the scene onto screen, lights etc.
   */
  protected void setupView()
  {
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
      // perspective projection (45% FOV, 4/3 aspect, clipping near 1, far 30);
      GLU.gluPerspective(60, 1024 / (float)768, 0.30f, 500.0f);
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
  static float xvc = 5.0f, yvc = 2.0f, zvc = 5.0f;
  static float xat = 0.0f, yat = 0.0f, zat = zvc - zvc - 10;
  static float Vx = 0.0f, Vy = 1.0f, Vz = 0.0f;
  static float day_in_year = 315.0f, hour = 8.0f;
  static float AnimationSpeed = 1.0f;
  static float distEarth = 8.0f;
  static float distMoon = 1.3f;
  static int spin = GL11.GL_TRUE;
  
  protected void drawCircleXY(double radius){
      double x, y, t;
      
      for(t=0; t<2*Math.PI; t += 0.002) {
        x = radius * Math.cos(t);
        y = radius * Math.sin(t);
        GL11.glBegin(GL11.GL_POINTS);
          GL11.glVertex2d(x, y);
        GL11.glEnd();
      }
  };
    
  protected void renderFrame()
  {
    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    if (spin == 1) {
      hour += AnimationSpeed;
      day_in_year += AnimationSpeed / 24.0;
      if (hour >= 24.0) hour -= 24.0;
      if (day_in_year >= 365.0) day_in_year -= 365.0;
    }
    
    GL11.glMatrixMode(GL11.GL_MODELVIEW);
    GL11.glLoadIdentity();
    GLU.gluLookAt(xvc, yvc, zvc, xat, yat, zat, Vx, Vy, Vz);
    
    GL11.glColor3f(1.0f, 1.0f, 1.0f);
    GL11.glBegin(GL11.GL_LINES);
      GL11.glVertex3f(0.0f, 0.0f, -1.0f);
      GL11.glVertex3f(0.0f, 0.0f, +1.0f);
    GL11.glEnd();
    
    GL11.glColor3f(1.0f, 0.0f, 1.0f);
    GL11.glBegin(GL11.GL_LINES);
      GL11.glVertex3f(0.0f, -1.0f, 0.0f);
      GL11.glVertex3f(0.0f, +1.0f, 0.0f);
    GL11.glEnd();
    
    GL11.glColor3f(0.0f, 1.0f, 0.0f);
    GL11.glBegin(GL11.GL_LINES);
      GL11.glVertex3f(-1.0f, 0.0f, 0.0f);
      GL11.glVertex3f(+1.0f, 0.0f, 0.0f);
    GL11.glEnd();
    
    GL11.glTranslatef(0.0f, 0.0f, -9.0f);
    
    GL11.glPushMatrix();
    GL11.glColor3f(1.0f, 1.0f, 0.0f);
    (new org.lwjgl.util.glu.Sphere()).draw(1.5f, 30, 30);
    
    GL11.glColor3f(1.0f, 0.0f, 0.0f);
    GL11.glBegin(GL11.GL_LINES);
      GL11.glVertex3f(0.0f, 0.0f, -distEarth-1.0f);
      GL11.glVertex3f(0.0f, 0.0f, distEarth+1.0f);
      GL11.glVertex3f(0.0f, -3.0f, 0.0f);
      GL11.glVertex3f(0.0f, +3.0f, 0.0f);
      GL11.glVertex3f(-distEarth-1.0f, 0.0f, 0.0f);
      GL11.glVertex3f(distEarth+1.0f, 0.0f, 0.0f);
    GL11.glEnd();
    GL11.glPopMatrix();
    
    GL11.glPushMatrix();
    GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
    GL11.glColor3f(0.0f, 0.4f, 1.0f);
    drawCircleXY(distEarth);
    GL11.glPopMatrix();
    
    GL11.glRotatef(360.0f*day_in_year/365.0f, 0.0f, 1.0f, 0.0f);
    GL11.glTranslatef(distEarth, 0.0f, 0.0f);
    GL11.glRotatef(-360.0f*day_in_year/365.0f, 0.0f, 1.0f, 0.0f);
    GL11.glPushMatrix();
    
    GL11.glRotatef(-23.5f, 0.0f, 0.0f, 1.0f);
    GL11.glRotatef(360.0f*hour/24.0f, 0.0f, 1.0f, 0.0f);
    GL11.glColor3f(0.0f, 0.4f, 1.0f);
    (new org.lwjgl.util.glu.Sphere()).draw(0.5f, 25, 25);
    
    GL11.glBegin(GL11.GL_LINES);
      GL11.glVertex3f(0.0f, 0.0f, -1.0f);
      GL11.glVertex3f(0.0f, 0.0f, 1.0f);
      GL11.glVertex3f(0.0f, -1.0f, 0.0f);
      GL11.glVertex3f(0.0f, 1.0f, 0.0f);
      GL11.glVertex3f(-1.0f, 0.0f, 0.0f);
      GL11.glVertex3f(1.0f, 0.0f, 0.0f);
    GL11.glEnd();
    

    GL11.glPushMatrix();
    GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
    GL11.glColor3f(1.0f, 1.0f, 0.0f);
    drawCircleXY(0.5);
    GL11.glPopMatrix();
    
    GL11.glPopMatrix();
    
    GL11.glPushMatrix();
    GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
    GL11.glColor3f(0.5f, 0.5f, 0.5f);
    drawCircleXY(distMoon);
    GL11.glPopMatrix();
    
    GL11.glRotatef(360.0f*12.0f*day_in_year/365.0f, 0.0f, 1.0f, 0.0f);
    GL11.glTranslatef(distMoon, 0.0f, 0.0f);
    GL11.glColor3f(0.5f, 0.5f, 0.5f);
    (new org.lwjgl.util.glu.Sphere()).draw(0.25f, 18, 18);
    GL11.glBegin(GL11.GL_LINES);
      GL11.glVertex3f(0.0f, 0.0f, -0.5f);
      GL11.glVertex3f(0.0f, 0.0f, 0.5f);
      GL11.glVertex3f(0.0f, -0.5f, 0.0f);
      GL11.glVertex3f(0.0f, 0.5f, 0.0f);
      GL11.glVertex3f(-0.5f, 0.0f, 0.0f);
      GL11.glVertex3f(0.5f, 0.0f, 0.0f);
    GL11.glEnd();
    
    GL11.glFlush();
  }
  
  public static void main(String[] args) {
    (new Animation1()).execute();
  }  
}
