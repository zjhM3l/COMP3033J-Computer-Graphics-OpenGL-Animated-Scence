package objects3D;

import GraphicsObjects.Utils;
import org.lwjgl.opengl.GL11;

import static objects3D.Human.white;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_AMBIENT_AND_DIFFUSE;

public class Rocket {
    static float blue[] = { 1.0f, 1.0f, 1.0f, 1.0f };

    public Rocket() {
    }

    public void drawRocket(float delta, boolean GoodAnimation) {
        glPushMatrix();
        {
            //center
            glColor3f(blue[0], blue[1], blue[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
            glPushMatrix();
            {
                Sphere centerSub = new Sphere();
                glTranslatef(35.0f, 0.0f, 0.0f);
                glRotatef(delta/10, 0.0f, 1.0f, 0.0f);
                centerSub.drawSphere(0.1f, 32, 32);

                glPushMatrix();
                {
                    RocketTop myRocket = new RocketTop();
                    glTranslatef(-400.0f, 0.0f, 0.0f);
                    glRotatef(0.0f, 1.0f, 0.0f, 0.0f);
                    glScalef(1.0f, 1.0f, 1.0f);
                    myRocket.drawRocketTop(3f, 2f, 15f, 100f, 7f, 32);
//                    myRocket.drawRocketTop(30f, 20f, 150f, 1000f, 70f, 32);

                    glPushMatrix();
                    {
                        RocketTop mySubRocket1 = new RocketTop();
                        glTranslatef(-5.0f, 0.0f, 0.0f);
                        glRotatef(0.0f, 1.0f, 0.0f, 0.0f);
                        glScalef(1.0f, 1.0f, 1.0f);
                        mySubRocket1.drawRocketTop(3f, 2f, 15f, 25f, 7f, 32);
                    }
                    glPopMatrix();

                    glPushMatrix();
                    {
                        RocketTop mySubRocket1 = new RocketTop();
                        glTranslatef(5.0f, 0.0f, 0.0f);
                        glRotatef(0.0f, 1.0f, 0.0f, 0.0f);
                        glScalef(1.0f, 1.0f, 1.0f);
                        mySubRocket1.drawRocketTop(3f, 2f, 15f, 25f, 7f, 32);
                    }
                    glPopMatrix();

                    glPushMatrix();
                    {
                        RocketTop mySubRocket1 = new RocketTop();
                        glTranslatef(0.0f, 5.0f, 0.0f);
                        glRotatef(0.0f, 1.0f, 0.0f, 0.0f);
                        glScalef(1.0f, 1.0f, 1.0f);
                        mySubRocket1.drawRocketTop(3f, 2f, 15f, 25f, 7f, 32);
                    }
                    glPopMatrix();

                    glPushMatrix();
                    {
                        RocketTop mySubRocket1 = new RocketTop();
                        glTranslatef(0.0f, -5.0f, 0.0f);
                        glRotatef(0.0f, 1.0f, 0.0f, 0.0f);
                        glScalef(1.0f, 1.0f, 1.0f);
                        mySubRocket1.drawRocketTop(3f, 2f, 15f, 25f, 7f, 32);
                    }
                    glPopMatrix();

                }
                glPopMatrix();
            }
            glPopMatrix();
        }
        glPopMatrix();
    }

    public static class RocketTop {
            public RocketTop(){

            }
            public void drawRocketTop(float radius1, float radius2, float height1, float height2, float height3, int nSegments){

                    GL11.glBegin(GL11.GL_TRIANGLES);

                    for (float i = 0.0f; i < nSegments; i ++) { // a loop around circumference of a tube
                            float angle = (float) (Math.PI * i * 2.0 / nSegments);
                            float nextAngle = (float) (Math.PI * (i + 1.0) * 2.0 / nSegments);

                            //compute sin & cosine
                            float x1 = (float) (radius1 * Math.sin(angle)), y1 = (float) (radius1 * Math.cos(angle));
                            float x2 = (float) (radius1 * Math.sin(nextAngle)), y2 = (float) (radius1 * Math.cos(nextAngle));

                            //底面半径
                            float x3 = (float) (radius2 * Math.sin(angle)), y3 = (float) (radius2 * Math.cos(angle));
                            float x4 = (float) (radius2 * Math.sin(nextAngle)), y4 = (float) (radius2 * Math.cos(nextAngle));

                            //锥形盖
                            GL11.glNormal3f(x1, y1, height2+height3);
                            GL11.glVertex3f(x1, y1, height2+height3);

                            GL11.glNormal3f(x1, y1, height2+height3);
                            GL11.glVertex3f(x2, y2, height2+height3);

                            GL11.glNormal3f(0, 0, height1+height2+height3);
                            GL11.glVertex3f(0, 0, height1+height2+height3);

                            //柱形身
                            // draw top triangle
                            GL11.glNormal3f(x1, y1, height3); GL11.glVertex3f(x1, y1, height3);
                            GL11.glNormal3f(x2, y2, height3); GL11.glVertex3f(x2, y2, height2+height3);
                            GL11.glNormal3f(x1, y1, height3); GL11.glVertex3f(x1, y1, height2+height3);

                            // draw bottom triangle
                            GL11.glNormal3f(x1, y1, height3); GL11.glVertex3f(x1, y1, height3);
                            GL11.glNormal3f(x2, y2, height3); GL11.glVertex3f(x2, y2, height3);
                            GL11.glNormal3f(x2, y2, height3); GL11.glVertex3f(x2, y2, height2+height3);

                            //台形底
                            // draw top triangle
                            GL11.glNormal3f(x1, y1, 0.0f); GL11.glVertex3f(x1, y1, height3);
                            GL11.glNormal3f(x2, y2, 0.0f); GL11.glVertex3f(x2, y2, height3);
                            GL11.glNormal3f(x1, y1, 0.0f); GL11.glVertex3f(x3, y3, 0.0f);

                            // draw bottom triangle
                            GL11.glNormal3f(x1, y1, 0.0f); GL11.glVertex3f(x3, y3, 0.0f);
                            GL11.glNormal3f(x2, y2, 0.0f); GL11.glVertex3f(x4, y4, 0.0f);
                            GL11.glNormal3f(x2, y2, 0.0f); GL11.glVertex3f(x2, y2, height3);

                            GL11.glNormal3f(x1, y1, 0.0f);GL11.glVertex3f(x3, y3, 0.0f);
                            GL11.glNormal3f(x1, y1, 0.0f);GL11.glVertex3f(x4, y4, 0.0f);
                            GL11.glNormal3f(0, 0, 0.0f);GL11.glVertex3f(0.0f, 0.0f, 0.0f);

                    }
                    GL11.glEnd();

            }
    }
}
