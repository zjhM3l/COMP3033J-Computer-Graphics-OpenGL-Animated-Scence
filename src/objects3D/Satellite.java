package objects3D;

import GraphicsObjects.Utils;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glPopMatrix;

public class Satellite {
    static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
    static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
    static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };
    private Texture textureSatellite, textureStateBody;

    public Satellite(Texture textureSatellite, Texture textureStateBody) {
        this.textureSatellite = textureSatellite;
        this.textureStateBody = textureStateBody;
    }

    public void drawSatellite(float delta, boolean GoodAnimation) {
        float theta = (float) (delta * 2 * Math.PI) * 0.1f;
        float wingRotate;

        if(GoodAnimation) {
            wingRotate = Math.abs((float) Math.cos(theta) * 60);
        } else {
            wingRotate = 0.0f;
        }

        glPushMatrix();
        {
            //centerpoint
            glColor3f(blue[0], blue[1], blue[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
            glPushMatrix();
            {
                Sphere centerSub = new Sphere();
                glTranslatef(35.0f-0.7f, 0.5f-0.5f, 0.0f);
                glRotatef(delta, 0.0f, 1.0f, 0.0f);
                centerSub.drawSphere(0.01f, 32, 32);

                glPushMatrix();
                {
                    TexCube cube = new TexCube();
                    glTranslatef(3.5f - 25.0f, 0.25f, 3.6f);
                    glScalef(1.0f, 1.0f, 1.0f);


                    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

                    Color.white.bind();
                    textureStateBody.bind();
                    glEnable(GL_TEXTURE_2D);
                    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

                    cube.drawTexCube(textureStateBody);


                    glPushMatrix();
                    {
                        Sphere left = new Sphere();
                        glTranslatef(-1.0f, 0.0f, 0.0f);
                        glRotatef(wingRotate, 1.0f, 0.0f, 0.0f);
                        left.drawSphere(0.3f, 32, 32);

                        glPushMatrix();
                        {
                            TexCube cubeLeft = new TexCube();
                            glTranslatef(-2.0f, 0.0f, 0.0f);
                            glScalef(2.0f, 0.1f, 1.2f);

                            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

                            Color.white.bind();
                            textureSatellite.bind();
                            glEnable(GL_TEXTURE_2D);
                            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

                            cubeLeft.drawTexCube(textureSatellite);
                        }
                        glPopMatrix();
                    }
                    glPopMatrix();

                    glPushMatrix();
                    {
                        Sphere left = new Sphere();
                        glTranslatef(+1.0f, 0.0f, 0.0f);
                        glRotatef(-wingRotate + 90.0f, 1.0f, 0.0f, 0.0f);
                        left.drawSphere(0.3f, 32, 32);

                        glPushMatrix();
                        {
                            TexCube cubeLeft = new TexCube();
                            glTranslatef(+2.0f, 0.0f, 0.0f);
                            glScalef(2.0f, 0.1f, 1.2f);

                            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

                            Color.white.bind();
                            textureSatellite.bind();
                            glEnable(GL_TEXTURE_2D);
                            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

                            cubeLeft.drawTexCube(textureSatellite);
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
