import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class GLSL extends Refactored 
{
  Obj3D obj3d;

  protected void setupView()
  {
    super.setupView();   
    // smooth shading - Gouraud
    GL11.glShadeModel(GL11.GL_SMOOTH);

    int vertShader = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
    int fragShader = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);


//  Basic shaders
    String vertShaderSrc =
      "void main() {" +
      " gl_FrontColor = gl_Color;" +    // color from OpenGL definition of models
      " vec4 v = vec4(gl_Vertex);" + // get vertex coordinates
      " v.x = v.x*v.y; " +  // change vertex x coordinate 
      " gl_Position = gl_ModelViewProjectionMatrix * v;" +  // our own camera view transformation
      
//      " gl_Position = ftransform();" +  // default camera view transformation
      "}";

    String fragShaderSrc =
      "void main() {" +
      " gl_FragColor = gl_Color;" +     // color from OpenGL definition of model
//      " gl_FragColor = vec4(0.4,0.4,0.8,1.0);" + // our own color 
      "}";

    
    // Convert Unicode Java string shader sources to ASCII C char ByteBuffer
    ByteBuffer vertShaderSrcBuffer = null;
    ByteBuffer fragShaderSrcBuffer = null;
    try {
      byte[] vertShaderSrcBytes = vertShaderSrc.getBytes("ASCII");
      vertShaderSrcBuffer = (ByteBuffer) BufferUtils.createByteBuffer(
          vertShaderSrcBytes.length).put(vertShaderSrcBytes).flip();
      byte[] fragShaderSrcBytes = fragShaderSrc.getBytes("ASCII");
      fragShaderSrcBuffer = (ByteBuffer) BufferUtils.createByteBuffer(
          fragShaderSrcBytes.length).put(fragShaderSrcBytes).flip();
    } catch (UnsupportedEncodingException ex) {
      System.out.println(ex);
      System.exit(1);
    }

    // Vertex shader
    // Create
    vertShader = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
    // Upload source
    GL20.glShaderSource(vertShader, vertShaderSrcBuffer);
    // Compile
    GL20.glCompileShader(vertShader);
    // Check compilation status
    IntBuffer compileStatus = BufferUtils.createIntBuffer(1);
    GL20.glGetShader(vertShader, GL20.GL_COMPILE_STATUS, compileStatus);
    // Query shader info log on error
    if (compileStatus.get(0) != GL11.GL_TRUE) {
      IntBuffer infoLogLength = BufferUtils.createIntBuffer(1);
      ByteBuffer infoLog = BufferUtils.createByteBuffer(1024); // 1024 arbitrarily chosen
      GL20.glGetShaderInfoLog(vertShader, infoLogLength, infoLog);
      byte[] infoLogBytes = new byte[infoLogLength.get(0)];
      infoLog.get(infoLogBytes, 0, infoLogLength.get(0));
      try {
        System.out.print(new String(infoLogBytes, "ASCII"));
      } catch (UnsupportedEncodingException ex) {
        System.out.println(ex);
        System.exit(1);
      }
    }

    // Fragment shader
    // Create
    fragShader = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
    // Upload source
    GL20.glShaderSource(fragShader, fragShaderSrcBuffer);
    // Compile
    GL20.glCompileShader(fragShader);
    // Check compilation status
    compileStatus = BufferUtils.createIntBuffer(1);
    GL20.glGetShader(fragShader, GL20.GL_COMPILE_STATUS, compileStatus);
    // Query shader info log on error
    if (compileStatus.get(0) != GL11.GL_TRUE) {
      IntBuffer infoLogLength = BufferUtils.createIntBuffer(1);
      ByteBuffer infoLog = BufferUtils.createByteBuffer(1024); // 1024 arbitrarily chosen
      GL20.glGetShaderInfoLog(fragShader, infoLogLength, infoLog);
      byte[] infoLogBytes = new byte[infoLogLength.get(0)];
      infoLog.get(infoLogBytes, 0, infoLogLength.get(0));
      try {
        System.out.print(new String(infoLogBytes, "ASCII"));
      } catch (UnsupportedEncodingException ex) {
        System.out.println(ex);
        System.exit(1);
      }
    }

    // Shader program
    // Create
    int program = GL20.glCreateProgram();
    // Attach shaders
    GL20.glAttachShader(program, vertShader);
    GL20.glAttachShader(program, fragShader);
    // Link program
    GL20.glLinkProgram(program);
    // Check link status
    IntBuffer linkStatus = BufferUtils.createIntBuffer(1);
    GL20.glGetProgram(program, GL20.GL_LINK_STATUS, linkStatus);
    if (linkStatus.get(0) == GL11.GL_TRUE) {
      // Use program on success
      GL20.glUseProgram(program);
    } else {
      // Query program info log on error
      IntBuffer infoLogLength = BufferUtils.createIntBuffer(1);
      ByteBuffer infoLog = BufferUtils.createByteBuffer(1024); // 1024 arbitrarily chosen
      GL20.glGetProgramInfoLog(program, infoLogLength, infoLog);
      byte[] infoLogBytes = new byte[infoLogLength.get(0)];
      infoLog.get(infoLogBytes, 0, infoLogLength.get(0));
      try {
        System.out.print(new String(infoLogBytes, "ASCII"));
      } catch (UnsupportedEncodingException ex) {
        System.out.println(ex);
        System.exit(1);
      }
    }
  }

  /**
   * Renders current frame
   */

  /**
   * Processes Keyboard and Mouse input and spawns actions
   */  
  protected void processInput()
  {
    super.processInput();
  }

  public static void main(String[] args) {
    (new GLSL()).execute();
  }  
}
