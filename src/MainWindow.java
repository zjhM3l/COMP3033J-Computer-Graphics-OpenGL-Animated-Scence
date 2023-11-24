
import java.io.IOException;
import java.nio.FloatBuffer;

import objects3D.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import GraphicsObjects.Arcball;
import GraphicsObjects.Utils;

//Main windows class controls and creates the 3D virtual world , please do not change this class but edit the other classes to complete the assignment. 
// Main window is built upon the standard Helloworld LWJGL class which I have heavily modified to use as your standard openGL environment. 
// 

// Do not touch this class, I will be making a version of it for your 3rd Assignment 
public class MainWindow {

	private boolean MouseOnepressed = true;
	private boolean dragMode = false;
	private boolean BadAnimation = true;
	private boolean Earth = false;
	/** position of pointer */
	float x = 400, y = 300;
	/** angle of rotation */
	float rotation = 0;
	/** time at last frame */
	long lastFrame;
	/** frames per second */
	int fps;
	/** last fps time */
	long lastFPS;

	long myDelta = 0; // to use for animation
	float Alpha = 0; // to use for animation
	long StartTime; // beginAnimiation

	Arcball MyArcball = new Arcball();

	float playerX = 600;

	float playerY = 400;

	float playerZ = 300;

	float playerR = 90;

	boolean playerAnimation = true;

	int playerArcX = 400;

	int playerArcY = 300;

	float scale = 10; //10

	boolean gravity = false;

	float speed = 1;

	float scaleW = 1.0f;

	float scaleDelta = 0.01f;

	boolean DRAWGRID = false;
	boolean waitForKeyrelease = true;
	/** Mouse movement */
	int LastMouseX = -1;
	int LastMouseY = -1;

	float pullX = 0.0f; // arc ball X cord.
	float pullY = 0.0f; // arc ball Y cord.

	int OrthoNumber = 1200; // using this for screen size, making a window of 1200 x 800 so aspect ratio 3:2
							// // do not change this for assignment 3 but you can change everything for your
							// project

	// basic colours
	static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
	static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };

	static float grey[] = { 0.5f, 0.5f, 0.5f, 1.0f };
	static float spot[] = { 0.1f, 0.1f, 0.1f, 0.5f };

	// primary colours
	static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
	static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
	static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

	// secondary colours
	static float yellow[] = { 1.0f, 1.0f, 0.0f, 1.0f };
	static float magenta[] = { 1.0f, 0.0f, 1.0f, 1.0f };
	static float cyan[] = { 0.0f, 1.0f, 1.0f, 1.0f };

	// other colours
	static float orange[] = { 1.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float brown[] = { 0.5f, 0.25f, 0.0f, 1.0f, 1.0f };
	static float dkgreen[] = { 0.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float pink[] = { 1.0f, 0.6f, 0.6f, 1.0f, 1.0f };

	// static GLfloat light_position[] = {0.0, 100.0, 100.0, 0.0};

	// support method to aid in converting a java float array into a Floatbuffer
	// which is faster for the opengl layer to process

	public void start() {

		StartTime = getTime();
		try {
			Display.setDisplayMode(new DisplayMode(1200, 800));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		initGL(); // init OpenGL
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTime(); // call before loop to initialise fps timer

		while (!Display.isCloseRequested()) {
			int delta = getDelta();
			update(delta);
			renderGL();
			Display.update();
			Display.sync(120); // cap fps to 120fps
		}

		Display.destroy();
	}

	public void update(int delta) {
		// rotate quad
		// rotation += 0.01f * delta;

		int MouseX = Mouse.getX();
		int MouseY = Mouse.getY();
		int WheelPostion = Mouse.getDWheel();

		boolean MouseButonPressed = Mouse.isButtonDown(0);

		if(gravity){
			if(playerX > 730){
				playerX -= 1;
			}else {
				playerX += 1;
			}
			if(playerY > 356){
				playerY -= 1;
			} else{
				playerY += 1;
			}
			if(playerZ > 210){
				playerZ -=1;
			} else {
				playerZ +=1;
			}
		}



		if (MouseButonPressed && !MouseOnepressed) {
			MouseOnepressed = true;
			// System.out.println("Mouse drag mode");
			MyArcball.startBall(MouseX, MouseY, 1200, 800);
			dragMode = true;

		} else if (!MouseButonPressed) {
			// System.out.println("Mouse drag mode end ");
			MouseOnepressed = false;
			dragMode = false;
		}

		if (dragMode) {
			MyArcball.updateBall(MouseX, MouseY, 1200, 800);
		}

		if (WheelPostion > 0) {
			OrthoNumber += 10;
		}

		if (WheelPostion < 0) {
			OrthoNumber -= 10;
			if (OrthoNumber < 610) {
				OrthoNumber = 610;
			}

			// System.out.println("Orth nubmer = " + OrthoNumber);

		}

		if (Keyboard.isKeyDown(Keyboard.KEY_UP)){
			speed += 0.009;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
			speed -= 0.009;
		}

		if(speed == 0){
			if (Keyboard.isKeyDown(Keyboard.KEY_T)){
				speed = 1;
			}
		} else {
			if (Keyboard.isKeyDown(Keyboard.KEY_T)){
				speed = 0;
			}
		}


		/** rest key is R */
		if (Keyboard.isKeyDown(Keyboard.KEY_R))
			MyArcball.reset();

		/* bad animation can be turn on or off using A key) */

		if (Keyboard.isKeyDown(Keyboard.KEY_A))
			BadAnimation = !BadAnimation;
		if (Keyboard.isKeyDown(Keyboard.KEY_D))
			x += 0.35f * delta;

		if (Keyboard.isKeyDown(Keyboard.KEY_Q))
			rotation += 0.35f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			Earth = !Earth;
		}
		//up
		if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD8)) {
			playerY += 5;
			playerAnimation = true;
			if (playerArcY > 300){
				playerArcY -= 1;
			} else{
				if (playerY > 500) {
					playerArcY -= 1;
				}
			}
//			playerArcY -= 1;
			MyArcball.updateBall(playerArcX, playerArcY, 1200, 800);
			//stay
			System.out.println("player" + playerX + " " + playerY + " " + playerZ);
		}
		//down
		if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD2)) {
			playerY -= 5;
			playerAnimation = true;
			if (playerArcY < 300){
				playerArcY += 1;
			} else{
				if (playerY < 280) {
					playerArcY += 1;
				}
			}
//			playerArcY += 1;
			MyArcball.updateBall(playerArcX, playerArcY, 1200, 800);
			System.out.println("player" + playerX + " " + playerY + " " + playerZ);
		}
		//left
		if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD4)) {
			playerX -= 5;
			playerR = 0;
			playerAnimation = false;
			if (playerArcX < 400){
				playerArcX += 1;
			} else{
				if (playerX < 360) {
					playerArcX += 1;
				}
			}
//			playerArcX += 1;
			MyArcball.updateBall(playerArcX, playerArcY, 1200, 800);
			System.out.println("player" + playerX + " " + playerY + " " + playerZ);
			//run
		}
		//right
		if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD6)) {
			playerX += 5;
			playerR = 180;
			playerAnimation = false;
			if (playerArcX > 400){
				playerArcX -= 1;
			} else{
				if (playerX > 850) {
					playerArcX -= 1;
				}
			}
//			playerArcX -= 1;
			MyArcball.updateBall(playerArcX, playerArcY, 1200, 800);
			System.out.println("player" + playerX + " " + playerY + " " + playerZ);
		}
		//moveon
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			playerZ += 3;
			playerR = 90;
			scaleW -= scaleDelta;
			playerAnimation = false;
		}
		//moveback
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			playerZ -= 3;
			playerR = 270;
			playerAnimation = false;
			scaleW += scaleDelta;
		}
		
		if (gravity == false) // check done to see if key is released
		{
			if (Keyboard.isKeyDown(Keyboard.KEY_G)) {
				gravity = true;
			}
		} else {
			if (Keyboard.isKeyDown(Keyboard.KEY_G)) {
				gravity = false;
			}
		}

		/** to check if key is released */
		if (Keyboard.isKeyDown(Keyboard.KEY_G) == false) {
			waitForKeyrelease = true;
		} else {
			waitForKeyrelease = false;

		}

		// keep quad on the screen
		if (x < 0)
			x = 0;
		if (x > 1200)
			x = 1200;
		if (y < 0)
			y = 0;
		if (y > 800)
			y = 800;

		updateFPS(); // update FPS Counter

		LastMouseX = MouseX;
		LastMouseY = MouseY;
	}

	/**
	 * Calculate how many milliseconds have passed since last frame.
	 * 
	 * @return milliseconds passed since last frame
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	public void initGL() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		changeOrth();
		MyArcball.startBall(0, 0, 1200, 800);
		glMatrixMode(GL_MODELVIEW);
		FloatBuffer lightPos = BufferUtils.createFloatBuffer(4);
		lightPos.put(-100f).put(0f).put(0f).put(0f).flip();

		FloatBuffer lightPos2 = BufferUtils.createFloatBuffer(4);
		lightPos2.put(0f).put(1000f).put(0).put(-1000f).flip();

		FloatBuffer lightPos3 = BufferUtils.createFloatBuffer(4);
		lightPos3.put(-10000f).put(1000f).put(1000).put(0).flip();

		FloatBuffer lightPos4 = BufferUtils.createFloatBuffer(4);
		lightPos4.put(1000f).put(1000f).put(1000f).put(0).flip();

//		glLight(GL_LIGHT0, GL_POSITION, lightPos); // specify the
													// position
													// of the
													// light
//		 glEnable(GL_LIGHT0); // switch light #0 on // I've setup specific materials
		// so in real light it will look abit strange

		glLight(GL_LIGHT1, GL_POSITION, lightPos); // specify the
													// position
													// of the
													// light
//		glEnable(GL_LIGHT1); // switch light #0 on
		glLight(GL_LIGHT1, GL_DIFFUSE, Utils.ConvertForGL(spot));

		glLight(GL_LIGHT2, GL_POSITION, lightPos3); // specify
													// the
													// position
													// of the
													// light
//		glEnable(GL_LIGHT2); // switch light #0 on
		glLight(GL_LIGHT2, GL_DIFFUSE, Utils.ConvertForGL(grey));

		glLight(GL_LIGHT3, GL_POSITION, lightPos4); // specify
													// the
													// position
													// of the
													// light
//		glEnable(GL_LIGHT3); // switch light #0 on
		glLight(GL_LIGHT3, GL_DIFFUSE, Utils.ConvertForGL(grey));

		glEnable(GL_LIGHTING); // switch lighting on
		glEnable(GL_DEPTH_TEST); // make sure depth buffer is switched
									// on
		glEnable(GL_NORMALIZE); // normalize normal vectors for safety
		glEnable(GL_COLOR_MATERIAL);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // load in texture

	}

	public void changeOrth() {

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(1200 - OrthoNumber, OrthoNumber, (800 - (OrthoNumber * 0.66f)), (OrthoNumber * 0.66f), 100000, -100000);
		glMatrixMode(GL_MODELVIEW);

		FloatBuffer CurrentMatrix = BufferUtils.createFloatBuffer(16);
		glGetFloat(GL_MODELVIEW_MATRIX, CurrentMatrix);

		// if(MouseOnepressed)
		// {

		MyArcball.getMatrix(CurrentMatrix);
		// }

		glLoadMatrix(CurrentMatrix);

	}

	/*
	 * You can edit this method to add in your own objects / remember to load in
	 * textures in the INIT method as they take time to load
	 * 
	 */
	public void renderGL() {
		changeOrth();

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		glColor3f(0.5f, 0.5f, 1.0f);

		myDelta = getTime() - StartTime;
		float delta = ((float) myDelta) / 10000;

		// code to aid in animation
		float theta = (float) (delta * 2 * Math.PI);
		float thetaDeg = delta * 360;
		float posn_x = (float) Math.cos(theta / 20); // same as your circle code in your notes
		float posn_y = (float) Math.sin(theta / 20);

		/*
		 * This code draws a grid to help you view the human models movement You may
		 * change this code to move the grid around and change its starting angle as you
		 * please
		 */
		if (DRAWGRID) {
			glPushMatrix();
			Grid MyGrid = new Grid();
			glTranslatef(600, 400, 0);
			glScalef(200f, 200f, 200f);
			MyGrid.DrawGrid();
			glPopMatrix();
		}

		glPushMatrix();{
			Player myPlayer = new Player();
			glTranslatef(playerX, playerY, playerZ);
			glScalef(scale * scaleW, scale * scaleW, scale * scaleW);
			glRotatef(playerR, 0.0f, 1.0f, 0.0f);
			myPlayer.drawPlayer(delta * 2, playerAnimation);
		}
		glPopMatrix();

		//star wall below
		glPushMatrix();
		TexCube wallBelow = new TexCube();
		glTranslatef(650, -220, 300);
		glRotatef(-20.0f, 4.0f, -1.0f, 0.0f);
		glScalef(1100.0f, 0.1f, 1000.0f);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

		Color.white.bind();
		universe.bind();
		glEnable(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		wallBelow.drawTexCube(universe);
		glPopMatrix();

		//star wall up
		glPushMatrix();
		TexCube wallUp = new TexCube();
		glTranslatef(650, 1400, 300);
		glRotatef(-20.0f, 4.0f, -1.0f, 0.0f);
		glScalef(1100.0f, 0.1f, 1000.0f);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

		Color.white.bind();
		universe.bind();
		glEnable(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		wallUp.drawTexCube(universe);
		glPopMatrix();

		//star wall back
		glPushMatrix();
		TexCube wallBack = new TexCube();
		glTranslatef(800, 720, 1200);
		glRotatef(-20.0f, 4.0f, -1.0f, 0.0f);
		glScalef(1100.0f, 900.0f, 0.1f);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

		Color.white.bind();
		universe.bind();
		glEnable(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		wallBack.drawTexCube(universe);
		glPopMatrix();

		//star wall left
		glPushMatrix();
		TexCube wallLeft = new TexCube();
		glTranslatef(-300, 370, 300);
		glRotatef(-20.0f, 4.0f, -1.0f, 0.0f);
		glScalef(0.1f, 900.0f, 1700.0f);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

		Color.white.bind();
		universe.bind();
		glEnable(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		wallLeft.drawTexCube(universe);
		glPopMatrix();

		//star wall right
		glPushMatrix();
		TexCube wallRight = new TexCube();
		glTranslatef(1800, 370, 300);
		glRotatef(-20.0f, 4.0f, -1.0f, 0.0f);
		glScalef(0.1f, 900.0f, 1700.0f);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

		Color.white.bind();
		universe.bind();
		glEnable(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		wallRight.drawTexCube(universe);
		glPopMatrix();


		//主场景地铁线
		glPushMatrix();
		SubWayRailCircle MySubway = new SubWayRailCircle(brick, wall, rail, texture);
		glTranslatef(50, 350, 300);
		glRotatef(-20.0f, 4.0f, -1.0f, 0.0f);
		glScalef(20f, 20f, 20f);
		if (!BadAnimation) {
			// insert your animation code to correct the postion for the human rotating
			// Rotate the whole figure at the right angle
			MySubway.drawSubway(-thetaDeg/10, !BadAnimation);
		} else {
			// bad animation version
			MySubway.drawSubway(delta, !BadAnimation);
//			glTranslatef(posn_x * 3.0f, 0.0f, posn_y * 3.0f);
		}

//		MySubway.drawSubway(delta, !BadAnimation);
		glPopMatrix();
		//主场景地铁线结束

		//主场景地铁
		glPushMatrix();
		EmptyMetro MyTrain = new EmptyMetro(subwayFace, leather, valley);
		glTranslatef(50, 350, 300);
		glRotatef(-20.0f, 4.0f, -1.0f, 0.0f);
		glScalef(20f, 20f, 20f);
		if (!BadAnimation) {
			// insert your animation code to correct the postion for the human rotating
			// Rotate the whole figure at the right angle
//			glTranslatef(posn_x * 3.0f, 0.0f, posn_y * 3.0f);
//			glRotatef(thetaDeg, 0, 2, 0);
//			MyTrain.drawTrain(-thetaDeg, !BadAnimation);
			MyTrain.drawTrain(thetaDeg * speed , !BadAnimation);

		} else {
			MyTrain.drawTrain(delta, !BadAnimation);

			// bad animation version
//			glTranslatef(posn_x * 3.0f, 0.0f, posn_y * 3.0f);
		}

//		MyTrain.drawTrain(delta, !BadAnimation);
		glPopMatrix();
		//地铁结束


		//主场景画人
		glPushMatrix();
		Human MyHuman = new Human(satellite, satellite, textureGlass);
		glTranslatef(500, 550, 300);
		glScalef(10f, 10f, 10f);

		if (!BadAnimation) {
			// insert your animation code to correct the postion for the human rotating
			// Rotate the whole figure at the right angle
			glTranslatef(posn_x * 50.0f, -posn_y * 10.0f, -posn_y * 20.0f);
//			glRotatef(thetaDeg, 0, 1, 0);

		} else {

			// bad animation version
//			glTranslatef(posn_x * 3.0f, 0.0f, posn_y * 3.0f);
		}

		MyHuman.drawHuman(delta, !BadAnimation); // give a delta for the Human object ot be animated

		glPopMatrix();
		//主场景画人结束

		//主场景画人2
		glPushMatrix();
		Human MyHuman1 = new Human(satellite, satellite, textureGlass);
		glTranslatef(800, 650, 300);
		glScalef(10f, 10f, 10f);

		if (!BadAnimation) {
			// insert your animation code to correct the postion for the human rotating
			// Rotate the whole figure at the right angle
			glTranslatef(posn_x * -70.0f, -posn_y * 30.0f, -posn_y * 20.0f);
//			glRotatef(thetaDeg, 0, 1, 0);

		} else {

			// bad animation version
//			glTranslatef(posn_x * 3.0f, 0.0f, posn_y * 3.0f);
		}

		MyHuman1.drawHuman(delta * 2, !BadAnimation); // give a delta for the Human object ot be animated

		glPopMatrix();
		//主场景画人结束


		//主场景satellite
		glPushMatrix();
		Satellite MySatellite = new Satellite(valley, sateBody);
		glTranslatef(450, 350, 300);
		glRotatef(-60.0f, 4.0f, -1.0f, 0.0f);
		glScalef(10f, 10f, 10f);
		if (!BadAnimation) {
			// insert your animation code to correct the postion for the human rotating
			// Rotate the whole figure at the right angle
//			glTranslatef(posn_x * 3.0f, 0.0f, posn_y * 3.0f);
//			glRotatef(thetaDeg, 0, 2, 0);
//			MyTrain.drawTrain(-thetaDeg, !BadAnimation);
			MySatellite.drawSatellite(thetaDeg/3, !BadAnimation);

		} else {
			MySatellite.drawSatellite(delta/3, !BadAnimation);

			// bad animation version
//			glTranslatef(posn_x * 3.0f, 0.0f, posn_y * 3.0f);
		}

//		MyTrain.drawTrain(delta, !BadAnimation);
		glPopMatrix();
		//satellite结束

		//主场景satellite1
		glPushMatrix();
		Satellite MySatellite1 = new Satellite(valley, sateBody);
		glTranslatef(450, 600, 300);
		glRotatef(+120.0f, 4.0f, -1.0f, 0.0f);
		glScalef(10f, 10f, 10f);
		if (!BadAnimation) {
			// insert your animation code to correct the postion for the human rotating
			// Rotate the whole figure at the right angle
//			glTranslatef(posn_x * 3.0f, 0.0f, posn_y * 3.0f);
//			glRotatef(thetaDeg, 0, 2, 0);
//			MyTrain.drawTrain(-thetaDeg, !BadAnimation);
			MySatellite1.drawSatellite(thetaDeg, !BadAnimation);

		} else {
			MySatellite1.drawSatellite(delta/3, !BadAnimation);

			// bad animation version
//			glTranslatef(posn_x * 3.0f, 0.0f, posn_y * 3.0f);
		}

//		MyTrain.drawTrain(delta, !BadAnimation);
		glPopMatrix();
		//satellite1结束

		//主场景rocket
		glPushMatrix();
		Rocket MyRocket = new Rocket();
		glTranslatef(700, 350, 250);
//		glRotatef(-120.0f, 4.0f, -1.0f, 0.0f);
		glRotatef(-120.0f, 4.0f, -1.0f, 0.0f);
		glScalef(1f, 1f, 1f);
		if (!BadAnimation) {
			// insert your animation code to correct the postion for the human rotating
			// Rotate the whole figure at the right angle
//			glTranslatef(posn_x * 3.0f, 0.0f, posn_y * 3.0f);
//			glRotatef(thetaDeg, 0, 2, 0);
//			MyTrain.drawTrain(-thetaDeg, !BadAnimation);
			MyRocket.drawRocket(thetaDeg, !BadAnimation);

		} else {
			MyRocket.drawRocket(delta/3, !BadAnimation);

			// bad animation version
//			glTranslatef(posn_x * 3.0f, 0.0f, posn_y * 3.0f);
		}

//		MyTrain.drawTrain(delta, !BadAnimation);
		glPopMatrix();
		//rocket结束

		//主场景rocket
		glPushMatrix();
		Rocket MyRocket1 = new Rocket();
		glTranslatef(1800, -200, 250);
//		glRotatef(-120.0f, 4.0f, -1.0f, 0.0f);
		glRotatef(-100.0f, 4.0f, -1.0f, 0.0f);
		glScalef(5f, 5f, 5f);
		if (!BadAnimation) {
			// insert your animation code to correct the postion for the human rotating
			// Rotate the whole figure at the right angle
//			glTranslatef(posn_x * 3.0f, 0.0f, posn_y * 3.0f);
//			glRotatef(thetaDeg, 0, 2, 0);
//			MyTrain.drawTrain(-thetaDeg, !BadAnimation);
			MyRocket1.drawRocket(thetaDeg/25, !BadAnimation);

		} else {
			MyRocket1.drawRocket(delta, !BadAnimation);

			// bad animation version
//			glTranslatef(posn_x * 3.0f, 0.0f, posn_y * 3.0f);
		}

//		MyTrain.drawTrain(delta, !BadAnimation);
		glPopMatrix();
		//rocket结束


		/*
		 * This code puts the earth code in which is larger than the human so it appears
		 * to change the scene
		 */
		if (Earth) {
			// Globe in the centre of the scene
			// Cube with globe picture texture in the centre of the scene
			glPushMatrix();
//			TexSphere MyGlobe = new TexSphere();
			 TexCube MyGlobe = new TexCube();
			glTranslatef(500, 500, 500);
			glScalef(140f, 140f, 140f);

			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

			Color.white.bind();
			texture.bind();
			glEnable(GL_TEXTURE_2D);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//			glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

//			MyGlobe.DrawTexSphere(8f, 100, 100, texture);
			 MyGlobe.drawTexCube(texture);
			glPopMatrix();
		}

	}

	public static void main(String[] argv) {
		MainWindow hello = new MainWindow();
		hello.start();
	}

	Texture texture;
	Texture marbleTexture; // white marble texture
	Texture marbleBlackTexture; // golden texture

	Texture brick;

	Texture wall;

	Texture subwayFace;

	Texture leather;

	Texture rail;

	Texture catFur;

	Texture valley;

	Texture universe;

	Texture satellite;

	Texture sateBody;

	Texture textureGlass;

	/*
	 * Any additional textures for your assignment should be written in here. Make a
	 * new texture variable for each one so they can be loaded in at the beginning
	 */
	public void init() throws IOException {

		texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/earthspace.png"));
		// white marble texture init
		marbleTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Marble.png"));
		// golden texture init
		marbleBlackTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Marbleblack.png"));

		brick = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Bricks066_1K_Color.png"));

		wall = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Metal021_1K_Color.png"));

		subwayFace = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Metal003_1K_Color.png"));

		leather = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Leather034C_1K_Color.png"));

		rail = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/MetalPlates002_1K_Color.png"));

		catFur = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/cat.png"));

		valley = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/valley 2022.png"));

		universe = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/universe.png"));

		satellite = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/satellite.png"));

		sateBody = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/satebody.png"));

		textureGlass = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/glass.png"));

		System.out.println("Texture loaded okay ");

	}
}
