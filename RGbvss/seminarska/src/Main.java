import org.lwjgl.*;
import org.lwjgl.opengl.*;
import org.lwjgl.input.*;

import java.nio.*;

import org.lwjgl.util.glu.GLU;
public class Main 
{
	protected static boolean isRunning = false;
	float posX = 0, posY = 0, posZ = 0.6f, posZ1 = 0,rotX = 0, rotY = 0, scale = 1;
	 boolean rotirajX = false, rotirajY=false;
	 float rotirajXKoliko = 0, rotirajYKoliko = 0;
	 boolean premakniZ;
	 static float[][] rotacijeX = new float[4][4];
	 static float[][] rotacijeY = new float[4][4];
	 static float[][] roticijeXKoliko = new float[4][4];
	 static float[][] roticijeYKoliko = new float[4][4];
	 float premakniZKoliko = 0;
	 float hitrostPremikanja = 0.1f;
	 float hitrostRotacije = 0.5f;
	float cursorX, cursorY;
	 boolean keyDown = true;
	 boolean keyDown1 = true;
	 int izbranI, izbranJ, pIzbranI, pIzbranJ;
	 boolean izbira = true;
	 float premikZ = 0.005f;
	 boolean premik = false;
	 int stevec = 0;
	 
	TexturedCube[][] m_Obj = new TexturedCube[4][4];
	IntBuffer m_Textures;
	
		public static void main(String[] args)
		{
			(new Main()).execute();
		}
	
	/**
	* Initializes display and enters main loop
	*/
	protected void execute()
	{
		try
		{
			initDisplay();
		}
		catch (LWJGLException e)
		{
			System.err.println("Can't open display.");
			System.exit(0);
		}
	
		Main.isRunning = true;
		mainLoop();
		Display.destroy();
	}
	
	
	/**
	* Main loop: renders and processes input events
	*/
	protected void mainLoop()
	{
		// setup camera and lights
		setupView();
		
		while (Main.isRunning)
		{
			//reset view
			resetView();
			
			// let subsystem paint
			renderFrame();
			
			// process input events
			processInput();
			
			// update window contents and process input messages
			Display.update();
		}
	}
	
	/**
	* Initial setup of projection of the scene onto screen, lights, etc.
	*/
	protected void setupView()
	{
		initializeModels();

		// enable depth buffer (off by default)
		GL11.glEnable(GL11.GL_DEPTH_TEST); 
		// enable culling of back sides of polygons
		GL11.glEnable(GL11.GL_CULL_FACE);

		// mapping from normalized to window coordinates
		GL11.glViewport(0, 0, 800, 600);

		// setup projection matrix stack
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		// perspective projection (45% FOV, 4/3 aspect, clipping near 0, far 30);
		GLU.gluPerspective(45, 800 / (float)600, 1.0f, 30.0f);

		setCameraMatrix();
		
		// smooth shading - Gouraud
		GL11.glShadeModel(GL11.GL_SMOOTH);
		
		// textures
		// enable 2D textures 
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	 // select modulate to mix texture with color for shading; GL_REPLACE, GL_MODULATE ...
		GL11.glTexEnvf( GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE );  
	}
	
	protected void setCameraMatrix()
	{
		// model view stack 
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		// setup view space; 
		// translate to 0,2,4 and rotate 30 degrees along x 
		GL11.glTranslatef(0.0f, -1.0f, -4.0f);
		GL11.glRotatef(-50.0f, 1.0f, 0.0f, 0.0f);
	}
	
	protected void initializeModels()
	{
		m_Textures = Texture.loadTextures2D(new String[] { "slika0.jpg", "slika1.jpg", "slika2.jpg", "slika3.jpg", "slika4.jpg", "slika5.jpg" });
		for(int i=0; i<4; i++)
		{
			for(int j=0; j<4; j++)
			{
				m_Obj[i][j] = new TexturedCube();
				rotacijeX[i][j] = m_Obj[i][j].getRotacijoX();
				rotacijeY[i][j] = m_Obj[i][j].getRotacijoY();
				roticijeXKoliko[i][j] = m_Obj[i][j].getRotacijoX();
				roticijeYKoliko[i][j] = m_Obj[i][j].getRotacijoY();
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
	}
	
	/**
	* Renders current frame
	*/

	

	
	protected void renderFrame()
	{
		
		for(int i=0; i<4; i++)
		{
			for(int j=0; j<4; j++)
			{
				m_Obj[i][j].setTexture(m_Textures, (float)i/4, (float)j/4);
				m_Obj[i][j].setPosition(-1+(float)i/2, 1+(float)j/2, -0.5f);
				m_Obj[i][j].setRotation(rotacijeX[i][j], rotacijeY[i][j], 0);
				m_Obj[i][j].setScaling(0.25f,0.25f,0.25f);
				
				//rotacijeX[i][j] = m_Obj[i][j].getRotacijoX();
				//rotacijeY[i][j] = m_Obj[i][j].getRotacijoX();
				//roticijeXKoliko[i][j] = m_Obj[i][j].getRotacijoX();
				//roticijeYKoliko[i][j] = m_Obj[i][j].getRotacijoX();
				//m_Obj[i][j].render3D();
			}
		}
	
		

		if(posZ<0.6 && premik){
			posZ+=premikZ;
			posZ1-=premikZ;
		}else{
			keyDown1 = true;
			premik = false;
		}
	
	
		
		m_Obj[pIzbranI][pIzbranJ].move(0, 0, posZ1);
		m_Obj[izbranI][izbranJ].move(0, 0, posZ);
		
			
		m_Obj[izbranI][izbranJ].setRotation(rotX,rotY,0);
		rotacijeX[izbranI][izbranJ] = rotX;
		rotacijeY[izbranI][izbranJ] = rotY;
		
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				m_Obj[i][j].render3D();
			}
		}
		
			
		//Za pocasno premikanje
		stevec++;
		if(stevec>100){
			izbira = true;
		}
		
		
		
		//Rotiranje po X
		if(rotirajX && rotX != rotirajXKoliko){
	    	rotX+=hitrostRotacije;
	    	if(rotX == rotirajXKoliko){
	    		keyDown = true;
	    		rotirajX = false;
	    	}
	    }
	    
	   if(rotX > 450 && rotirajXKoliko > 450){
	    	rotX-=360;
	    	rotirajXKoliko-=360;
	    }
	    //Rotiranje po Y
	    if(rotirajY && rotY != rotirajYKoliko){
	    	rotY+=hitrostRotacije;
	    	if(rotY == rotirajYKoliko){
	    		keyDown = true;
	    		rotirajY = false;
	    	}
	    }
	    
	    if(rotY > 450 && rotirajYKoliko > 450){
	    	rotY-=360;
	    	rotirajYKoliko-=360;
	    }
		   
	    
	    //START HUD
	    GL11.glMatrixMode(GL11.GL_PROJECTION);
	    GL11.glPushMatrix();
	    GL11.glLoadIdentity();
	    GLU.gluOrtho2D(0, 800, 0, 600);
	    GL11.glMatrixMode(GL11.GL_MODELVIEW);
	    GL11.glPushMatrix();
	    GL11.glLoadIdentity();
	    GL11.glTranslatef(1f, 600f, 0.0f);

	    GL11.glDisable(GL11.GL_DEPTH_TEST);
	    GL11.glDisable(GL11.GL_TEXTURE_2D);
	    GL11.glDisable(GL11.GL_CULL_FACE);
	 
	    
	    GL11.glBegin(GL11.GL_LINES);
		    for(int i=0; i<4; i++)
		    {
		    	for(int j=0; j<4; j++)
		    	{
		    	    GL11.glVertex2f(50*i+0.0f,-50*j+0.0f);
		    	    GL11.glVertex2f(50*i+0.0f,-50*j-50.0f);
		    	    GL11.glVertex2f(50*i+50.0f,-50*j+0.0f);
		    	    GL11.glVertex2f(50*i+50.0f,-50*j-50.0f);
		    	    GL11.glVertex2f(50*i+0.0f,-50*j+0.0f);
		    	    GL11.glVertex2f(50*i+50.0f,-50*j+0.0f);
		    	    GL11.glVertex2f(50*i+0.0f,-50*j-50.0f);
		    	    GL11.glVertex2f(50*i+50.0f,-50*j-50.0f);
		    	}
		    }
	    GL11.glEnd();
	    GL11.glBegin(GL11.GL_QUADS);
	    	GL11.glVertex2f(50*izbranI+50.0f,50*izbranJ-150.0f);
	    	GL11.glVertex2f(50*izbranI+0.0f,50*izbranJ-150.0f);
	    	GL11.glVertex2f(50*izbranI+0.0f,50*izbranJ-200.0f);
	    	GL11.glVertex2f(50*izbranI+50.0f,50*izbranJ-200.0f);
	    GL11.glEnd();

	    GL11.glEnable(GL11.GL_CULL_FACE);
	    GL11.glEnable(GL11.GL_TEXTURE_2D);
	    GL11.glEnable(GL11.GL_DEPTH_TEST);
	    GL11.glMatrixMode(GL11.GL_PROJECTION);
	    GL11.glPopMatrix();
	    GL11.glMatrixMode(GL11.GL_MODELVIEW);
	    GL11.glPopMatrix();
	    
	    //END HUD
	    
	    
	  //Miska
		 int mouseDX = Mouse.getDX();
	     int mouseDY = Mouse.getDY();
	     
	     if (mouseDX != 0 || mouseDY != 0 ) {
	            cursorX += mouseDX;
	            cursorY += mouseDY;
	            if (cursorX < 0) 
	                cursorX = 0;
	            
	            if (cursorY < 0) 
	                cursorY = 0;
	            
	            if(cursorY>600)
	            	cursorY = 600;
	            if(cursorX>800)
	            	cursorX = 800;
	        }
		while ( Mouse.next() ) {
        	if(Mouse.getEventButton() == 0 && Mouse.getEventButtonState() == true) {
        		if(cursorX > 0 && cursorX < 200 && cursorY > 400 && cursorY < 600 && keyDown1){
        			zapomniSi();
        			pIzbranI = izbranI;
        			pIzbranJ = izbranJ;
        			izbranI = (int)cursorX/50;
        			izbranJ = (int)(cursorY-400)/50;
        			dobiVrednosti();
        			premik = true;
        			posZ = 0;
        			posZ1 = 0.6f;
        			keyDown1 = false;
        		}
        	}
		}
	    
	}
	
	/**
	* Processes Keyboard and Mouse input and spawns actions
	*/
	
	//Zapomni si vrednosti trenutno izbrane kocke
	protected void zapomniSi(){
		rotacijeX[izbranI][izbranJ] = rotX;
		rotacijeY[izbranI][izbranJ] = rotY;
		roticijeXKoliko[izbranI][izbranJ] = rotirajXKoliko;
		roticijeYKoliko[izbranI][izbranJ] = rotirajYKoliko;
		
	}
	
	//Prebere vrednosti iz tabele oz. prebere vrednosti izbrane kocke
	protected void dobiVrednosti(){
		rotX = rotacijeX[izbranI][izbranJ];
		rotY = rotacijeY[izbranI][izbranJ];
		rotirajXKoliko = roticijeXKoliko[izbranI][izbranJ];
		rotirajYKoliko = roticijeYKoliko[izbranI][izbranJ];
	}
	
	protected void processInput()
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) && keyDown){
			 rotirajYKoliko -= 90;
		      rotirajY = true; 
		      keyDown = false;
		      if(hitrostRotacije>0)
		    	  hitrostRotacije*=-1;
			}
		
			
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)&& keyDown){
			rotirajYKoliko += 90;
		      rotirajY = true; 
		      keyDown = false;
		      	if(hitrostRotacije<0)
		      		hitrostRotacije*=-1;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_UP) && keyDown){
			rotirajXKoliko -= 90;
		      rotirajX = true; 
		      keyDown = false;
		      if(hitrostRotacije>0)
		      		hitrostRotacije*=-1;
		}
			
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN) && keyDown){
			rotirajXKoliko += 90;
			rotirajX = true; 
			keyDown = false;
	      	if(hitrostRotacije<0)
	      		hitrostRotacije*=-1;
		}
		
		if (Display.isCloseRequested() || Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) 
			Main.isRunning = false;
	}
	
	/**
	* Finds best 800x600 display mode and sets it
	* @throws LWJGLException
	*/
	protected void initDisplay() throws LWJGLException
	{
		DisplayMode bestMode = null;
		DisplayMode[] dm = Display.getAvailableDisplayModes();
		for ( int nI = 0; nI < dm.length; nI++ )
		{
			DisplayMode mode = dm[nI];
			if ( mode.getWidth() == 800 && mode.getHeight() == 600 && mode.getFrequency() <= 85 ) 
			{
				if ( bestMode == null || (mode.getBitsPerPixel() >= bestMode.getBitsPerPixel() && mode.getFrequency() > bestMode.getFrequency()) )
					bestMode = mode;
			}
		}
	
		Display.setDisplayMode(bestMode);
		Display.create(); 
		Display.setTitle(this.getClass().getName());
	} 
	
	/**
	* Utils for creating native buffers
	* @throws LWJGLException
	*/
	public static ByteBuffer allocBytes(int howmany)
	{
		return ByteBuffer.allocateDirect(howmany).order(ByteOrder.nativeOrder());
	}
	
	public static IntBuffer allocInts(int howmany)
	{
		return ByteBuffer.allocateDirect(howmany).order(ByteOrder.nativeOrder()).asIntBuffer();
	}
	
	public static FloatBuffer allocFloats(int howmany)
	{
		return ByteBuffer.allocateDirect(howmany).order(ByteOrder.nativeOrder()).asFloatBuffer();
	}
	
	public static ByteBuffer allocBytes(byte[] bytearray)
	{
		ByteBuffer bb = ByteBuffer.allocateDirect(bytearray.length* 1).order(ByteOrder.nativeOrder());
		bb.put(bytearray).flip();
		return bb;
	}
	
	public static IntBuffer allocInts(int[] intarray)
	{
		IntBuffer ib = ByteBuffer.allocateDirect(intarray.length* 4).order(ByteOrder.nativeOrder()).asIntBuffer();
		ib.put(intarray).flip();
		return ib;
	}
	
	public static FloatBuffer allocFloats(float[] floatarray)
	{
		FloatBuffer fb = ByteBuffer.allocateDirect(floatarray.length* 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
		fb.put(floatarray).flip();
		return fb;
	}
}