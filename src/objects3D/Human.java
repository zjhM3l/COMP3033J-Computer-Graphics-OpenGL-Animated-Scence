package objects3D;

import static org.lwjgl.opengl.GL11.*;
import GraphicsObjects.Point4f;
import GraphicsObjects.Utils;
import GraphicsObjects.Vector4f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;


//JIAHE ZHANG 20205722

public class Human {
	private Texture texture, textureBlack, textureGlass;

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

	// Pass the texture as a parameter to
	public Human(Texture texture, Texture textureBlack, Texture textureGlass) {
		this.texture = texture;
		this.textureBlack = textureBlack;
		this.textureGlass = textureGlass;
	}

	// Implement using notes in Animation lecture
	public void drawHuman(float delta, boolean GoodAnimation) {
		float theta = (float) (delta * 2 * Math.PI) * 4;
		// Statement of joint movements
		float leftArmRotation, leftForearmRotation, rightArmRotation, rightForearmRotation, neckRotation,
				chestRotation, backRotation, leftHighLegRotation, leftLowLegRotation, rightHighLegRotation,
				rightLowLegRotation, leftFootRotation, rightFootRotation;
		if (GoodAnimation) {
			// Slight forward tilt of the neck with running
			neckRotation = 0 - 10 -Math.abs((float) Math.cos(theta / 3) * 20);
			// With running, the chest leans forward
			chestRotation =  - 35 + Math.abs(- ((float) Math.cos(theta /3) * 15));
			// Slight swaying of the back with running
			backRotation = -90 - 10 - Math.abs((float) Math.cos(theta / 3) * 15);
			// Left large arm swing
			leftArmRotation = 180 + 60 + (float) Math.cos(theta/3) * 10;
			// Right large arm swing
			rightArmRotation = 180 + 60 + (float) Math.cos(theta/3) * 10;
			// Left large leg swing
			leftHighLegRotation = 90 + 135 + (float) Math.cos(theta/3) * 10;
			// Right large leg swing
			rightHighLegRotation = 90 + 135 + (float) Math.cos(theta/3) * 10;
			// Left foot swing
			leftFootRotation = 90 + (float) Math.cos(theta /4 ) * 25;
			// Right foot swing
			rightFootRotation = 90 + (float) Math.cos(theta / 4) * 25;
			// Thighs crossed forward, calves tucked back,
			// thighs back, calves in stirrups.
//			if(leftHighLegRotation < 90) {
//				leftLowLegRotation = ((float) Math.cos(theta) * 30);
//			} else {
//				leftLowLegRotation = - ((float) Math.cos(theta) * 60);
//			}
//			if (rightHighLegRotation < 90) {
//				rightLowLegRotation = - ((float) Math.cos(theta) * 30);
//			} else {
//				rightLowLegRotation = (float) Math.cos(theta) * 60;
//			}
			// Large arm swing forward, small arm lift,
			// large arm swing back, small arm rock back in small increments.
//			if(leftArmRotation < 180) {
//				leftForearmRotation = - ((float) Math.cos(theta) * 60);
//			} else {
//				leftForearmRotation = ((float) Math.cos(theta) * 110);
//			}
//			if(rightArmRotation < -180) {
//				rightForearmRotation = ((float) Math.cos(theta) * 60);
//			} else {
//				rightForearmRotation = -((float) Math.cos(theta) * 110);
//			}
			leftForearmRotation = 0 + 30;//0
			rightForearmRotation = 0 + 30;//0
			leftLowLegRotation = 0 - 140;//0
			rightLowLegRotation = 0 - 140;//0

		} else {
			leftArmRotation = 180 + 60;//180
			leftForearmRotation = 0 + 30;//0
			rightArmRotation = 180 + 60;//180
			rightForearmRotation = 0 + 30;//0
			neckRotation = 0 - 10;//0
			chestRotation = 0 - 35;//0
			backRotation = -90 - 10;//-90
			leftHighLegRotation = 90 + 135;//90
			leftLowLegRotation = 0 - 140;//0
			rightHighLegRotation = 90 + 135;//90
			rightLowLegRotation = 0 - 140;//0
			leftFootRotation = 90;//90
			rightFootRotation = 90;//90
		}

		Sphere sphere = new Sphere();
		Cylinder cylinder = new Cylinder();

		glPushMatrix();

		glRotatef(90.0f, 0.0f, 1.0f, 0.0f);

		{
			//pelvis - gold sphere
			glRotatef(-delta * 60, 1.0f, 0.0f, 0.0f);
			glColor3f(blue[0], blue[1], blue[2]);
			glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
			glPushMatrix();
			{
				TexSphere myGlobe = new TexSphere();
				glTranslatef(0.0f, 0.5f, 0.0f);
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

				Color.white.bind();
				textureBlack.bind();
				glEnable(GL_TEXTURE_2D);
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//				glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
				myGlobe.DrawTexSphere(0.5f, 32, 32, textureBlack);

				//Second spinal segment - brown cylinder
				glColor3f(white[0], white[1], white[2]);
				glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
				glPushMatrix();
				{
					glTranslatef(0.0f, 0f, 0.0f);
					glRotatef(backRotation, 1.0f, 0.0f, 0.0f);
					cylinder.drawCylinder(0.1f, 1f, 32);

					//Lower latissimus dorsi (right) - brown cylinder
					glColor3f(white[0], white[1], white[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
					glPushMatrix();
					{
						glTranslatef(0.2f, -0.1f, 0.35f);
						cylinder.drawCylinder(0.2f, 0.9f, 32);
					}
					glPopMatrix();

					//Lower latissimus dorsi (left) - brown cylinder
					glColor3f(white[0], white[1], white[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
					glPushMatrix();
					{
						glTranslatef(-0.2f, -0.1f, 0.35f);
						cylinder.drawCylinder(0.2f, 0.9f, 32);
					}
					glPopMatrix();

					//Abdominal muscle - marble sphere
					glColor3f(orange[0], orange[1], orange[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
					glPushMatrix();
					{
						TexSphere myMustle = new TexSphere();
						glTranslatef(0.2f, 0.1f, 0.6f);
						glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

						Color.white.bind();
						texture.bind();
						glEnable(GL_TEXTURE_2D);
						glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//						glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
						myMustle.DrawTexSphere(0.2f, 32, 32, texture);
					}
					glPopMatrix();

					//Abdominal muscle - marble sphere
					glColor3f(orange[0], orange[1], orange[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
					glPushMatrix();
					{
						TexSphere myMustle = new TexSphere();
						glTranslatef(-0.2f, 0.1f, 0.6f);
						glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

						Color.white.bind();
						texture.bind();
						glEnable(GL_TEXTURE_2D);
						glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//						glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
						myMustle.DrawTexSphere(0.2f, 32, 32, texture);
					}
					glPopMatrix();

					//Abdominal muscle - marble sphere
					glColor3f(orange[0], orange[1], orange[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
					glPushMatrix();
					{
						TexSphere myMustle = new TexSphere();
						glTranslatef(-0.2f, 0.1f, 1f);
						glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

						Color.white.bind();
						texture.bind();
						glEnable(GL_TEXTURE_2D);
						glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//						glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
						myMustle.DrawTexSphere(0.25f, 32, 32, texture);
					}
					glPopMatrix();

					//Abdominal muscle - marble sphere
					glColor3f(orange[0], orange[1], orange[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
					glPushMatrix();
					{
						TexSphere myMustle = new TexSphere();
						glTranslatef(0.2f, 0.1f, 1f);
						glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

						Color.white.bind();
						texture.bind();
						glEnable(GL_TEXTURE_2D);
						glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//						glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
						myMustle.DrawTexSphere(0.25f, 32, 32, texture);
					}
					glPopMatrix();

					//Spinal node - gold sphere
					glColor3f(blue[0], blue[1], blue[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
					glPushMatrix();
					{
						TexSphere MyGlobe = new TexSphere();
						glTranslatef(0.0f, 0.0f, 1.0f);
						glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

						Color.white.bind();
						textureBlack.bind();
						glEnable(GL_TEXTURE_2D);
						glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//						glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
						MyGlobe.DrawTexSphere(0.2f, 32, 32, textureBlack);

						//Second spinal segment - brown cylinder
						glColor3f(white[0], white[1], white[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
						glPushMatrix();
						{
							glTranslatef(0.0f, 0f, 0.0f);
							glRotatef(chestRotation, 1.0f, 0.0f, 0.0f);
							cylinder.drawCylinder(0.1f, 1f, 32);

							//Pectoral muscles - marble sphere
							glColor3f(white[0], white[1], white[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
							glPushMatrix();
							{
								TexSphere myMustle = new TexSphere();
								glTranslatef(0.3f, 0.12f, 1.1f);
								glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

								Color.white.bind();
								texture.bind();
								glEnable(GL_TEXTURE_2D);
								glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//								glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
								myMustle.DrawTexSphere(0.4f, 32, 32, texture);
							}
							glPopMatrix();

							//Pectoral muscles - marble sphere
							glColor3f(white[0], white[1], white[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
							glPushMatrix();
							{
								TexSphere myMustle = new TexSphere();
								glTranslatef(-0.3f, 0.12f, 1.1f);
								glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

								Color.white.bind();
								texture.bind();
								glEnable(GL_TEXTURE_2D);
								glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//								glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
								myMustle.DrawTexSphere(0.4f, 32, 32, texture);
							}
							glPopMatrix();

							//Abdominal muscle - marble sphere
							glColor3f(white[0], white[1], white[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
							glPushMatrix();
							{
								TexSphere myMustle = new TexSphere();
								glTranslatef(0.23f, 0.1f, 0.36f);
								glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

								Color.white.bind();
								texture.bind();
								glEnable(GL_TEXTURE_2D);
								glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//								glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
								myMustle.DrawTexSphere(0.3f, 32, 32, texture);
							}
							glPopMatrix();

							//Abdominal muscle - marble sphere
							glColor3f(white[0], white[1], white[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
							glPushMatrix();
							{
								TexSphere myMustle = new TexSphere();
								glTranslatef(-0.23f, 0.1f, 0.36f);
								glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

								Color.white.bind();
								texture.bind();
								glEnable(GL_TEXTURE_2D);
								glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//								glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
								myMustle.DrawTexSphere(0.3f, 32, 32, texture);
							}
							glPopMatrix();

							//Abdominal muscle - marble sphere
							glColor3f(white[0], white[1], white[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
							glPushMatrix();
							{
								TexSphere myMustle = new TexSphere();
								glTranslatef(0.28f, 0.1f, 0.6f);
								glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

								Color.white.bind();
								texture.bind();
								glEnable(GL_TEXTURE_2D);
								glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//								glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
								myMustle.DrawTexSphere(0.35f, 32, 32, texture);
							}
							glPopMatrix();

							//Abdominal muscle - marble sphere
							glColor3f(white[0], white[1], white[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
							glPushMatrix();
							{
								TexSphere myMustle = new TexSphere();
								glTranslatef(-0.28f, 0.1f, 0.6f);
								glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

								Color.white.bind();
								texture.bind();
								glEnable(GL_TEXTURE_2D);
								glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//								glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
								myMustle.DrawTexSphere(0.35f, 32, 32, texture);
							}
							glPopMatrix();

							//Upper latissimus dorsi (right) - brown cylinder
							glColor3f(white[0], white[1], white[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
							glPushMatrix();
							{
								glTranslatef(0.2f, -0.1f, 0.16f);
								cylinder.drawCylinder(0.3f, 1.1f, 32);
							}
							glPopMatrix();

							//Upper latissimus dorsi (left) - brown cylinder
							glColor3f(white[0], white[1], white[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
							glPushMatrix();
							{
								glTranslatef(-0.2f, -0.1f, 0.16f);
								cylinder.drawCylinder(0.3f, 1.1f, 32);
							}
							glPopMatrix();

							// Thorax - gold sphere
							glColor3f(green[0], green[1], green[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(green));
							glPushMatrix();
							{
								TexSphere myChest = new TexSphere();
								glTranslatef(0.0f, 0f, 1.2f);
								glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

								Color.white.bind();
								textureBlack.bind();
								glEnable(GL_TEXTURE_2D);
								glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//								glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
								myChest.DrawTexSphere(0.5f, 32, 32, textureBlack);

								// neck - brown cylinder
								glColor3f(white[0], white[1], white[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
								glPushMatrix();
								{
									glTranslatef(0.0f, 0.0f, 0.0f);
									glRotatef(neckRotation, 1.0f, 0.0f, 0.0f);
									cylinder.drawCylinder(0.17f, 0.7f, 32);

									// head - marble sphere(covered by cube head)
									glColor3f(red[0], red[1], red[2]);
									glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
									glPushMatrix();
									{
										TexSphere myHead = new TexSphere();
										glTranslatef(0.0f, 0.0f, 1.0f);
										glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

										Color.white.bind();
										texture.bind();
										glEnable(GL_TEXTURE_2D);
										glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//										glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

										myHead.DrawTexSphere(0.4f, 32, 32, texture);
										glPopMatrix();

										//head - marble cube(as a sign)
										glColor3f(white[0], white[1], white[2]);
										glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
										glPushMatrix();
										{
											Sphere mySign = new Sphere();
											glTranslatef(0.0f, 0.0f, 1.0f);
											glScalef(0.4f, 0.4f, 0.4f);
//											glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
//
//											Color.white.bind();
//											textureGlass.bind();
//											glEnable(GL_TEXTURE_2D);
//											glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//											glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
											mySign.drawSphere(2.0f, 32, 32);
										}
										glPopMatrix();

										//right eye ball - black sphere
										glColor3f(black[0], black[1], black[2]);
										glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
										glPushMatrix();
										{
											glTranslatef(0.2f, 0.33f, 1.0f);
											sphere.drawSphere(0.1f, 32, 32);
										}
										glPopMatrix();

										//left eye ball - black sphere
										glColor3f(black[0], black[1], black[2]);
										glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
										glPushMatrix();
										{
											glTranslatef(-0.2f, 0.33f, 1.0f);
											sphere.drawSphere(0.1f, 32, 32);
										}
										glPopMatrix();
									}
									glPopMatrix();

									// left shoulder - gold sphere
									glColor3f(blue[0], blue[1], blue[2]);
									glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
									glPushMatrix();
									{
										TexSphere myShoulder = new TexSphere();
										glTranslatef(0.7f, 0f, 0.0f);
										glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

										Color.white.bind();
										textureBlack.bind();
										glEnable(GL_TEXTURE_2D);
										glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//										glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
										myShoulder.DrawTexSphere(0.25f, 32, 32, textureBlack);

										// left arm - orange cylinder
										glColor3f(white[0], white[1], white[2]);
										glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
										glPushMatrix();
										{
											glTranslatef(0.0f, 0.0f, 0.0f);
											glRotatef(leftArmRotation, 1.0f, 0.0f, 0.0f);
											// glRotatef(27.5f,0.0f,1.0f,0.0f);
											cylinder.drawCylinder(0.19f, 1f, 32);

											// left elbow - gold sphere
											glColor3f(blue[0], blue[1], blue[2]);
											glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
											glPushMatrix();
											{
												TexSphere myElbow = new TexSphere();
												glTranslatef(0.0f, 0.0f, 1.05f);
												glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

												Color.white.bind();
												textureBlack.bind();
												glEnable(GL_TEXTURE_2D);
												glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//												glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
												myElbow.DrawTexSphere(0.2f, 32, 32, textureBlack);

												// left forearm - orange cylinder
												glColor3f(white[0], white[1], white[2]);
												glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
												glPushMatrix();
												{
													glTranslatef(0.0f, 0.0f, 0.0f);
													glRotatef(leftForearmRotation, 1.0f, 0.0f, 0.0f);
													// glRotatef(90.0f,0.0f,1.0f,0.0f);
													cylinder.drawCylinder(0.13f, 0.7f, 32);

													// left hand - gold sphere
													glColor3f(blue[0], blue[1], blue[2]);
													glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
													glPushMatrix();
													{
														TexSphere myHand = new TexSphere();
														glTranslatef(0.0f, 0.0f, 0.75f);
														glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

														Color.white.bind();
														textureBlack.bind();
														glEnable(GL_TEXTURE_2D);
														glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//														glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
														myHand.DrawTexSphere(0.2f, 32, 32, textureBlack);

													}
													glPopMatrix();
												}
												glPopMatrix();
											}
											glPopMatrix();
										}
										glPopMatrix();
									}
									glPopMatrix();

									// right shoulder
									glColor3f(blue[0], blue[1], blue[2]);
									glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
									glPushMatrix();
									{
										TexSphere myShoulder = new TexSphere();
										glTranslatef(-0.7f, 0f, 0.0f);
										glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

										Color.white.bind();
										textureBlack.bind();
										glEnable(GL_TEXTURE_2D);
										glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//										glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
										myShoulder.DrawTexSphere(0.25f, 32, 32, textureBlack);

										// right arm
										glColor3f(white[0], white[1], white[2]);
										glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
										glPushMatrix();
										{
											glTranslatef(0.0f, 0.0f, 0.0f);
											glRotatef(rightArmRotation, 1.0f, 0.0f, 0.0f);
											glRotatef(-15.0f, 1.0f, 0.0f, 0.0f);

											cylinder.drawCylinder(0.19f, 1f, 32);

											// right elbow
											glColor3f(blue[0], blue[1], blue[2]);
											glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
											glPushMatrix();
											{
												TexSphere myElbow = new TexSphere();
												glTranslatef(0.0f, 0.0f, 1.05f);
												glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

												Color.white.bind();
												textureBlack.bind();
												glEnable(GL_TEXTURE_2D);
												glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//												glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
												myElbow.DrawTexSphere(0.2f, 32, 32, textureBlack);

												// right forearm
												glColor3f(white[0], white[1], white[2]);
												glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
												glPushMatrix();
												{
													glTranslatef(0.0f, 0.0f, 0.0f);
													glRotatef(rightForearmRotation, 1.0f, 0.0f, 0.0f);
													cylinder.drawCylinder(0.13f, 0.7f, 32);

													// right hand
													glColor3f(blue[0], blue[1], blue[2]);
													glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
													glPushMatrix();
													{
														TexSphere myHand = new TexSphere();
														glTranslatef(0.0f, 0.0f, 0.75f);
														glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

														Color.white.bind();
														textureBlack.bind();
														glEnable(GL_TEXTURE_2D);
														glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//														glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
														myHand.DrawTexSphere(0.2f, 32, 32, textureBlack);

													}
													glPopMatrix();
												}
												glPopMatrix();
											}
											glPopMatrix();
										}
										glPopMatrix();
									}
									glPopMatrix();
								}
								glPopMatrix();
							}
							glPopMatrix();

						}
						glPopMatrix();
					}
					glPopMatrix();
				}
				glPopMatrix();

				// left hip
				glColor3f(blue[0], blue[1], blue[2]);
				glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
				glPushMatrix();
				{
					TexSphere myHip = new TexSphere();
					glTranslatef(-0.6f, 0.2f, 0f);
					glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

					Color.white.bind();
					textureBlack.bind();
					glEnable(GL_TEXTURE_2D);
					glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//					glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
					myHip.DrawTexSphere(0.25f, 32, 32, textureBlack);

					// left high leg
					glColor3f(white[0], white[1], white[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
					glPushMatrix();
					{
						glTranslatef(0.0f, 0.0f, 0.0f);
						glRotatef(leftHighLegRotation, 1.0f, 0.0f, 0.0f);
//									glRotatef((-LimbRotation / 2) + 90, 1.0f, 0.0f, 0.0f);
						// glRotatef(90.0f,1.0f,0.0f,0.0f);
						cylinder.drawCylinder(0.23f, 1.4f, 32);

						// left knee
						glColor3f(blue[0], blue[1], blue[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
						glPushMatrix();
						{
							TexSphere myKnee = new TexSphere();
							glTranslatef(0.0f, 0.0f, 1.45f);
							glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

							Color.white.bind();
							textureBlack.bind();
							glEnable(GL_TEXTURE_2D);
							glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//							glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
							myKnee.DrawTexSphere(0.25f, 32, 32, textureBlack);

							// left low leg
							glColor3f(white[0], white[1], white[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
							glPushMatrix();
							{
								glTranslatef(0.0f, 0.0f, 0.0f);
								glRotatef(leftLowLegRotation,1.0f,0.0f,0.0f);
								cylinder.drawCylinder(0.18f, 1.4f, 32);

								// left foot
								glColor3f(blue[0], blue[1], blue[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
								glPushMatrix();
								{
									TexSphere myFoot = new TexSphere();
									glTranslatef(0.0f, 0.0f, 1.45f);
									glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

									Color.white.bind();
									textureBlack.bind();
									glEnable(GL_TEXTURE_2D);
									glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//									glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
									myFoot.DrawTexSphere(0.3f, 32, 32, textureBlack);

									glColor3f(white[0], white[1], white[2]);
									glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
									glPushMatrix();
									{
										glTranslatef(0.0f, 0f, 0.15f);
										glRotatef(leftFootRotation, 1, 0, 0);
										cylinder.drawCylinder(0.15f, 0.5f, 32);

									}
									glPopMatrix();
								}
								glPopMatrix();
							}
							glPopMatrix();
						}
						glPopMatrix();
					}
					glPopMatrix();
				}
				glPopMatrix();


				// right hip
				glColor3f(blue[0], blue[1], blue[2]);
				glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
				glPushMatrix();
				{
					TexSphere myHip = new TexSphere();
					glTranslatef(0.6f, 0.2f, 0f);
					glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

					Color.white.bind();
					textureBlack.bind();
					glEnable(GL_TEXTURE_2D);
					glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//					glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

					myHip.DrawTexSphere(0.25f, 32, 32, textureBlack);

					// right high leg
					glColor3f(white[0], white[1], white[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
					glPushMatrix();
					{
						glTranslatef(0.0f, 0.0f, 0.0f);
						glRotatef(rightHighLegRotation, 1.0f, 0.0f, 0.0f);

//									glRotatef((-LimbRotation / 2) + 90, 1.0f, 0.0f, 0.0f);
						// glRotatef(90.0f,1.0f,0.0f,0.0f);
						cylinder.drawCylinder(0.23f, 1.4f, 32);

						// right knee
						glColor3f(blue[0], blue[1], blue[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
						glPushMatrix();
						{
							TexSphere myKnee = new TexSphere();
							glTranslatef(0.0f, 0.0f, 1.45f);
							glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

							Color.white.bind();
							textureBlack.bind();
							glEnable(GL_TEXTURE_2D);
							glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//							glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
							myKnee.DrawTexSphere(0.25f, 32, 32, textureBlack);

							// right low leg
							glColor3f(white[0], white[1], white[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
							glPushMatrix();
							{
								glTranslatef(0.0f, 0.0f, 0.0f);
								glRotatef(rightLowLegRotation,1.0f,0.0f,0.0f);
								cylinder.drawCylinder(0.18f, 1.4f, 32);

								// right foot
								glColor3f(blue[0], blue[1], blue[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
								glPushMatrix();
								{
									TexSphere myFoot = new TexSphere();
									glTranslatef(0.0f, 0.0f, 1.45f);
									glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

									Color.white.bind();
									textureBlack.bind();
									glEnable(GL_TEXTURE_2D);
									glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//									glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
									myFoot.DrawTexSphere(0.3f, 32, 32, textureBlack);

									glColor3f(white[0], white[1], white[2]);
									glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
									glPushMatrix();
									{
										glTranslatef(0.0f, 0f, 0.15f);
										glRotatef(rightFootRotation, 1, 0, 0);
										cylinder.drawCylinder(0.15f, 0.5f, 32);

									}
									glPopMatrix();
								}
								glPopMatrix();
							}
							glPopMatrix();
						}
						glPopMatrix();
					}
					glPopMatrix();
				}
				glPopMatrix();
			}
			glPopMatrix();
		}
	}
}

/*
 * 
 * 
 * }
 * 
 */
