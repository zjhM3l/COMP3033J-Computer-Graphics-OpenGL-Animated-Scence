package objects3D;

import GraphicsObjects.Utils;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.w3c.dom.Text;

import static org.lwjgl.opengl.GL11.*;

public class Cat {
    Texture textCatFur;
    static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
    static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
    static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

    public Cat(Texture textCatFur) {
        this.textCatFur = textCatFur;
    }

    public void drawCat(float delta, boolean GoodAnimation) {
        float theta = (float) (delta * 2 * Math.PI) * 4;
        float tailRotation;

        if (GoodAnimation) {
            tailRotation = 160 + (float) Math.cos(theta * 4) * 60;
        } else {
            tailRotation = 160;
        }

        glPushMatrix();
        {
            //pelvis
            glColor3f(blue[0], blue[1], blue[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
            glPushMatrix();
            {
                TexSphere catPelvis = new TexSphere();
                glTranslatef(4.0f, 0.5f, 0.0f);

                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

                Color.white.bind();
                textCatFur.bind();
                glEnable(GL_TEXTURE_2D);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                catPelvis.DrawTexSphere(0.5f, 32, 32, textCatFur);

                //tail
                glColor3f(red[0], red[1], red[2]);
                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                glPushMatrix();
                {
                    Cylinder myTail = new Cylinder();
                    glTranslatef(0.0f, 0.0f, -0.4f);
                    glRotatef(tailRotation, 1.0f, 1.0f, 0.0f);
                    myTail.drawCylinder(0.14f, 0.8f, 32);
                }
                glPopMatrix();

                //back high leg(left)
                glColor3f(red[0], red[1], red[2]);
                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                glPushMatrix();
                {
                    Cylinder myTail = new Cylinder();
                    glTranslatef(-0.4f, 0.0f, 0.0f);
                    glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                    myTail.drawCylinder(0.2f, 0.8f, 32);

                    //front leg knee(right)
                    glColor3f(blue[0], blue[1], blue[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                    glPushMatrix();
                    {
                        TexSphere catFrontKneeRight = new TexSphere();
                        glTranslatef(0.0f, 0.0f, 0.8f);

                        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

                        Color.white.bind();
                        textCatFur.bind();
                        glEnable(GL_TEXTURE_2D);
                        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                        catFrontKneeRight.DrawTexSphere(0.25f, 32, 32, textCatFur);

                        //front low leg(right)
                        glColor3f(red[0], red[1], red[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                        glPushMatrix();
                        {
                            Cylinder myLowLegRight = new Cylinder();
                            glTranslatef(0.0f, 0.0f, 0.0f);
                            glRotatef(0.0f, 1.0f, 0.0f, 0.0f);
                            myLowLegRight.drawCylinder(0.2f, 0.6f, 32);
                        }
                        glPopMatrix();
                    }
                    glPopMatrix();
                }
                glPopMatrix();

                //back high leg(right)
                glColor3f(red[0], red[1], red[2]);
                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                glPushMatrix();
                {
                    Cylinder myTail = new Cylinder();
                    glTranslatef(0.4f, 0.0f, 0.0f);
                    glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                    myTail.drawCylinder(0.2f, 0.8f, 32);

                    //front leg knee(right)
                    glColor3f(blue[0], blue[1], blue[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                    glPushMatrix();
                    {
                        TexSphere catFrontKneeRight = new TexSphere();
                        glTranslatef(0.0f, 0.0f, 0.8f);

                        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

                        Color.white.bind();
                        textCatFur.bind();
                        glEnable(GL_TEXTURE_2D);
                        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                        catFrontKneeRight.DrawTexSphere(0.25f, 32, 32, textCatFur);

                        //front low leg(right)
                        glColor3f(red[0], red[1], red[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                        glPushMatrix();
                        {
                            Cylinder myLowLegRight = new Cylinder();
                            glTranslatef(0.0f, 0.0f, 0.0f);
                            glRotatef(0.0f, 1.0f, 0.0f, 0.0f);
                            myLowLegRight.drawCylinder(0.2f, 0.6f, 32);
                        }
                        glPopMatrix();
                    }
                    glPopMatrix();
                }
                glPopMatrix();

                //back bone
                glColor3f(red[0], red[1], red[2]);
                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                glPushMatrix();
                {
                    Cylinder myTail = new Cylinder();
                    glTranslatef(0.0f, 0.0f, 0.0f);
                    glRotatef(0.0f, 1.0f, 0.0f, 0.0f);
                    myTail.drawCylinder(0.2f, 1.6f, 32);

                    //chest
                    glColor3f(blue[0], blue[1], blue[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                    glPushMatrix();
                    {
                        TexSphere catChest = new TexSphere();
                        glTranslatef(0.0f, 0.0f, 1.6f);

                        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

                        Color.white.bind();
                        textCatFur.bind();
                        glEnable(GL_TEXTURE_2D);
                        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                        catChest.DrawTexSphere(0.5f, 32, 32, textCatFur);

                        //front high leg(left)
                        glColor3f(red[0], red[1], red[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                        glPushMatrix();
                        {
                            Cylinder myLeg = new Cylinder();
                            glTranslatef(-0.4f, 0.0f, 0.0f);
                            glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                            myLeg.drawCylinder(0.2f, 0.8f, 32);

                            //front leg knee(right)
                            glColor3f(blue[0], blue[1], blue[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                            glPushMatrix();
                            {
                                TexSphere catFrontKneeRight = new TexSphere();
                                glTranslatef(0.0f, 0.0f, 0.8f);

                                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

                                Color.white.bind();
                                textCatFur.bind();
                                glEnable(GL_TEXTURE_2D);
                                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                                catFrontKneeRight.DrawTexSphere(0.25f, 32, 32, textCatFur);

                                //front low leg(right)
                                glColor3f(red[0], red[1], red[2]);
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                                glPushMatrix();
                                {
                                    Cylinder myLowLegRight = new Cylinder();
                                    glTranslatef(0.0f, 0.0f, 0.0f);
                                    glRotatef(0.0f, 1.0f, 0.0f, 0.0f);
                                    myLowLegRight.drawCylinder(0.2f, 0.6f, 32);
                                }
                                glPopMatrix();
                            }
                            glPopMatrix();
                        }
                        glPopMatrix();

                        //front high leg(right)
                        glColor3f(red[0], red[1], red[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                        glPushMatrix();
                        {
                            Cylinder myLeg = new Cylinder();
                            glTranslatef(0.4f, 0.0f, 0.0f);
                            glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                            myLeg.drawCylinder(0.2f, 0.8f, 32);

                            //front leg knee(right)
                            glColor3f(blue[0], blue[1], blue[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                            glPushMatrix();
                            {
                                TexSphere catFrontKneeRight = new TexSphere();
                                glTranslatef(0.0f, 0.0f, 0.8f);

                                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

                                Color.white.bind();
                                textCatFur.bind();
                                glEnable(GL_TEXTURE_2D);
                                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                                catFrontKneeRight.DrawTexSphere(0.25f, 32, 32, textCatFur);

                                //front low leg(right)
                                glColor3f(red[0], red[1], red[2]);
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                                glPushMatrix();
                                {
                                    Cylinder myLowLegRight = new Cylinder();
                                    glTranslatef(0.0f, 0.0f, 0.0f);
                                    glRotatef(0.0f, 1.0f, 0.0f, 0.0f);
                                    myLowLegRight.drawCylinder(0.2f, 0.6f, 32);
                                }
                                glPopMatrix();
                            }
                            glPopMatrix();
                        }
                        glPopMatrix();

                        //neck
                        glColor3f(red[0], red[1], red[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                        glPushMatrix();
                        {
                            Cylinder myLeg = new Cylinder();
                            glTranslatef(0.0f, 0.0f, 0.0f);
                            glRotatef(-40.0f, 1.0f, 0.0f, 0.0f);
                            myLeg.drawCylinder(0.2f, 1.0f, 32);

                            glColor3f(blue[0], blue[1], blue[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                            glPushMatrix();
                            {
                                TexSphere catHead = new TexSphere();
                                glTranslatef(0.0f, 0.0f, 1.2f);

                                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

                                Color.white.bind();
                                textCatFur.bind();
                                glEnable(GL_TEXTURE_2D);
                                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                                catHead.DrawTexSphere(0.45f, 32, 32, textCatFur);
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
