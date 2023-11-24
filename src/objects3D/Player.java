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

public class Player {

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
    public Player() {
    }

    // Implement using notes in Animation lecture
    public void drawPlayer(float delta, boolean PlayerAnimation) {
        float theta = (float) (delta * 2 * Math.PI) * 0.5f;
        // Statement of joint movements
        float leftArmRotation, leftForearmRotation, rightArmRotation, rightForearmRotation, neckRotation,
                chestRotation, backRotation, leftHighLegRotation, leftLowLegRotation, rightHighLegRotation,
                rightLowLegRotation, leftFootRotation, rightFootRotation;
        //stay
        if (PlayerAnimation) {
            leftArmRotation = 180 - 180 +Math.abs((float) Math.cos(theta) * 20);//180
            leftForearmRotation = 0 +Math.abs((float) Math.cos(theta) * 10);//0
            rightArmRotation = 180 + 70 -Math.abs((float) Math.cos(theta) * 20);//180
            rightForearmRotation = 0 - 90 -Math.abs((float) Math.cos(theta) * 10);//0
            neckRotation = 0 +Math.abs((float) Math.cos(theta) * 10);//0
            chestRotation = 0;//0
            backRotation = -90 -Math.abs((float) Math.cos(theta) * 10);//-90
            leftHighLegRotation = 90  - 80 -Math.abs((float) Math.cos(theta) * 20);//90
            leftLowLegRotation = 0 + 130 -Math.abs((float) Math.cos(theta) * 10);;//0
            rightHighLegRotation = 90 +Math.abs((float) Math.cos(theta) * 20);//90
            rightLowLegRotation = 0 +Math.abs((float) Math.cos(theta) * 10);;//0
            leftFootRotation = 90;//90
            rightFootRotation = 90;//90

            //run
        } else {
            leftArmRotation = 180 - 10 -Math.abs((float) Math.cos(theta) * 40);//180
            leftForearmRotation = 0 - 10 -Math.abs((float) Math.cos(theta) * 30);//0
            rightArmRotation = 180 - 10 -Math.abs((float) Math.cos(theta) * 20);//180
            rightForearmRotation = 0 - 10 -Math.abs((float) Math.cos(theta) * 40);//0
            neckRotation = 10 +Math.abs((float) Math.cos(theta) * 10);//0
            chestRotation = 0 + 5 +Math.abs((float) Math.cos(theta) * 10);//0
            backRotation = -90 - 20 +Math.abs((float) Math.cos(theta) * 10);//-90

            leftHighLegRotation = 90 + 30 +Math.abs((float) Math.cos(theta/2) * 30);//90
            leftLowLegRotation = 0 - 100 +Math.abs((float) Math.cos(theta/2) * 20);//0
            rightHighLegRotation = 90 - 40 -Math.abs((float) Math.cos(theta/2) * 30);//90
            rightLowLegRotation = 0 - 20 +Math.abs((float) Math.cos(theta/2) * 20);//0
            leftFootRotation = 90 +Math.abs((float) Math.cos(theta/2) * 10);//90
            rightFootRotation = 90 +Math.abs((float) Math.cos(theta/2) * 10);//90
        }

        Sphere sphere = new Sphere();
        Cylinder cylinder = new Cylinder();

        glPushMatrix();

        {

            glRotatef(90.0f, 0.0f, 1.0f, 0.0f);

            //pelvis - gold sphere
            glColor3f(white[0], white[1], white[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
            glPushMatrix();
            {
                Sphere myGlobe = new Sphere();
                glTranslatef(0.0f, 0.5f, 0.0f);

                myGlobe.drawSphere(0.5f, 32, 32);

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
                    glColor3f(white[0], white[1], white[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                    glPushMatrix();
                    {
                        Sphere myMustle = new Sphere();
                        glTranslatef(0.2f, 0.1f, 0.6f);

                        myMustle.drawSphere(0.2f, 32, 32);
                    }
                    glPopMatrix();

                    //Abdominal muscle - marble sphere
                    glColor3f(white[0], white[1], white[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                    glPushMatrix();
                    {
                        Sphere myMustle = new Sphere();
                        glTranslatef(-0.2f, 0.1f, 0.6f);

                        myMustle.drawSphere(0.2f, 32, 32);
                    }
                    glPopMatrix();

                    //Abdominal muscle - marble sphere
                    glColor3f(white[0], white[1], white[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                    glPushMatrix();
                    {
                        Sphere myMustle = new Sphere();
                        glTranslatef(-0.2f, 0.1f, 1f);

                        myMustle.drawSphere(0.25f, 32, 32);
                    }
                    glPopMatrix();

                    //Abdominal muscle - marble sphere
                    glColor3f(white[0], white[1], white[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                    glPushMatrix();
                    {
                        Sphere myMustle = new Sphere();
                        glTranslatef(0.2f, 0.1f, 1f);

                        myMustle.drawSphere(0.25f, 32, 32);
                    }
                    glPopMatrix();

                    //Spinal node - gold sphere
                    glColor3f(white[0], white[1], white[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                    glPushMatrix();
                    {
                        Sphere MyGlobe = new Sphere();
                        glTranslatef(0.0f, 0.0f, 1.0f);

                        MyGlobe.drawSphere(0.2f, 32, 32);

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
                                Sphere myMustle = new Sphere();
                                glTranslatef(0.3f, 0.12f, 1.1f);

                                myMustle.drawSphere(0.4f, 32, 32);
                            }
                            glPopMatrix();

                            //Pectoral muscles - marble sphere
                            glColor3f(white[0], white[1], white[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                            glPushMatrix();
                            {
                                Sphere myMustle = new Sphere();
                                glTranslatef(-0.3f, 0.12f, 1.1f);

                                myMustle.drawSphere(0.4f, 32, 32);
                            }
                            glPopMatrix();

                            //Abdominal muscle - marble sphere
                            glColor3f(white[0], white[1], white[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                            glPushMatrix();
                            {
                                Sphere myMustle = new Sphere();
                                glTranslatef(0.23f, 0.1f, 0.36f);

                                myMustle.drawSphere(0.3f, 32, 32);
                            }
                            glPopMatrix();

                            //Abdominal muscle - marble sphere
                            glColor3f(white[0], white[1], white[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                            glPushMatrix();
                            {
                                Sphere myMustle = new Sphere();
                                glTranslatef(-0.23f, 0.1f, 0.36f);

                                myMustle.drawSphere(0.3f, 32, 32);
                            }
                            glPopMatrix();

                            //Abdominal muscle - marble sphere
                            glColor3f(white[0], white[1], white[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                            glPushMatrix();
                            {
                                Sphere myMustle = new Sphere();
                                glTranslatef(0.28f, 0.1f, 0.6f);

                                myMustle.drawSphere(0.35f, 32, 32);
                            }
                            glPopMatrix();

                            //Abdominal muscle - marble sphere
                            glColor3f(white[0], white[1], white[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                            glPushMatrix();
                            {
                                Sphere myMustle = new Sphere();
                                glTranslatef(-0.28f, 0.1f, 0.6f);

                                myMustle.drawSphere(0.35f, 32, 32);
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
                            glColor3f(white[0], white[1], white[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                            glPushMatrix();
                            {
                                Sphere myChest = new Sphere();
                                glTranslatef(0.0f, 0f, 1.2f);

                                myChest.drawSphere(0.5f, 32, 32);

                                // neck - brown cylinder
                                glColor3f(white[0], white[1], white[2]);
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                                glPushMatrix();
                                {
                                    glTranslatef(0.0f, 0.0f, 0.0f);
                                    glRotatef(neckRotation, 1.0f, 0.0f, 0.0f);
                                    cylinder.drawCylinder(0.17f, 0.7f, 32);

                                    // head - marble sphere(covered by cube head)
                                    glColor3f(white[0], white[1], white[2]);
                                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                                    glPushMatrix();
                                    {
                                        Sphere myHead = new Sphere();
                                        glTranslatef(0.0f, 0.0f, 1.0f);

                                        myHead.drawSphere(0.4f, 32, 32);
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
                                    glColor3f(white[0], white[1], white[2]);
                                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                                    glPushMatrix();
                                    {
                                        Sphere myShoulder = new Sphere();
                                        glTranslatef(0.7f, 0f, 0.0f);

                                        myShoulder.drawSphere(0.25f, 32, 32);

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
                                            glColor3f(white[0], white[1], white[2]);
                                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                                            glPushMatrix();
                                            {
                                                Sphere myElbow = new Sphere();
                                                glTranslatef(0.0f, 0.0f, 1.05f);

                                                myElbow.drawSphere(0.2f, 32, 32);

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
                                                    glColor3f(white[0], white[1], white[2]);
                                                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                                                    glPushMatrix();
                                                    {
                                                        Sphere myHand = new Sphere();
                                                        glTranslatef(0.0f, 0.0f, 0.75f);

                                                        myHand.drawSphere(0.2f, 32, 32);

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
                                    glColor3f(white[0], white[1], white[2]);
                                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                                    glPushMatrix();
                                    {
                                        Sphere myShoulder = new Sphere();
                                        glTranslatef(-0.7f, 0f, 0.0f);

                                        myShoulder.drawSphere(0.25f, 32, 32);

                                        // right arm
                                        glColor3f(white[0], white[1], white[2]);
                                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                                        glPushMatrix();
                                        {
                                            glTranslatef(0.0f, 0.0f, 0.0f);
                                            glRotatef(rightArmRotation, 1.0f, 0.0f, 0.0f);

                                            cylinder.drawCylinder(0.19f, 1f, 32);

                                            // right elbow
                                            glColor3f(white[0], white[1], white[2]);
                                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                                            glPushMatrix();
                                            {
                                                Sphere myElbow = new Sphere();
                                                glTranslatef(0.0f, 0.0f, 1.05f);

                                                myElbow.drawSphere(0.2f, 32, 32);

                                                // right forearm
                                                glColor3f(white[0], white[1], white[2]);
                                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                                                glPushMatrix();
                                                {
                                                    glTranslatef(0.0f, 0.0f, 0.0f);
                                                    glRotatef(rightForearmRotation, 1.0f, 0.0f, 0.0f);
                                                    cylinder.drawCylinder(0.13f, 0.7f, 32);

                                                    // right hand
                                                    glColor3f(white[0], white[1], white[2]);
                                                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                                                    glPushMatrix();
                                                    {
                                                        Sphere myHand = new Sphere();
                                                        glTranslatef(0.0f, 0.0f, 0.75f);

                                                        myHand.drawSphere(0.2f, 32, 32);

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
                glColor3f(white[0], white[1], white[2]);
                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                glPushMatrix();
                {
                    Sphere myHip = new Sphere();
                    glTranslatef(-0.6f, 0.2f, 0f);

                    myHip.drawSphere(0.25f, 32, 32);

                    // left high leg
                    glColor3f(white[0], white[1], white[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                    glPushMatrix();
                    {
                        glTranslatef(0.0f, 0.0f, 0.0f);
                        glRotatef(leftHighLegRotation, 1.0f, 0.0f, 0.0f);
                        cylinder.drawCylinder(0.23f, 1.4f, 32);

                        // left knee
                        glColor3f(white[0], white[1], white[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                        glPushMatrix();
                        {
                            Sphere myKnee = new Sphere();
                            glTranslatef(0.0f, 0.0f, 1.45f);

                            myKnee.drawSphere(0.25f, 32, 32);

                            // left low leg
                            glColor3f(white[0], white[1], white[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                            glPushMatrix();
                            {
                                glTranslatef(0.0f, 0.0f, 0.0f);
                                glRotatef(leftLowLegRotation,1.0f,0.0f,0.0f);
                                cylinder.drawCylinder(0.18f, 1.4f, 32);

                                // left foot
                                glColor3f(white[0], white[1], white[2]);
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                                glPushMatrix();
                                {
                                    Sphere myFoot = new Sphere();
                                    glTranslatef(0.0f, 0.0f, 1.45f);

                                    myFoot.drawSphere(0.3f, 32, 32);

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
                glColor3f(white[0], white[1], white[2]);
                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                glPushMatrix();
                {
                    Sphere myHip = new Sphere();
                    glTranslatef(0.6f, 0.2f, 0f);

                    myHip.drawSphere(0.25f, 32, 32);

                    // right high leg
                    glColor3f(white[0], white[1], white[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                    glPushMatrix();
                    {
                        glTranslatef(0.0f, 0.0f, 0.0f);
                        glRotatef(rightHighLegRotation, 1.0f, 0.0f, 0.0f);
                        cylinder.drawCylinder(0.23f, 1.4f, 32);

                        // right knee
                        glColor3f(white[0], white[1], white[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                        glPushMatrix();
                        {
                            Sphere myKnee = new Sphere();
                            glTranslatef(0.0f, 0.0f, 1.45f);

                            myKnee.drawSphere(0.25f, 32, 32);

                            // right low leg
                            glColor3f(white[0], white[1], white[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                            glPushMatrix();
                            {
                                glTranslatef(0.0f, 0.0f, 0.0f);
                                glRotatef(rightLowLegRotation,1.0f,0.0f,0.0f);
                                cylinder.drawCylinder(0.18f, 1.4f, 32);

                                // right foot
                                glColor3f(white[0], white[1], white[2]);
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                                glPushMatrix();
                                {
                                    Sphere myFoot = new Sphere();
                                    glTranslatef(0.0f, 0.0f, 1.45f);

                                    myFoot.drawSphere(0.3f, 32, 32);

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
