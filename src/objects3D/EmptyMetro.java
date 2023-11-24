package objects3D;

import GraphicsObjects.Utils;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class EmptyMetro {
    private Texture textureFace, textureLeather, textValley;
    static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
    static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
    static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

    public EmptyMetro(Texture textureFace, Texture textureLeather, Texture textValley) {
        this.textureFace = textureFace;
        this.textureLeather = textureLeather;
        this.textValley = textValley;
    }

    public void drawTrain(float delta, boolean GoodAnimation) {
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
                centerSub.drawSphere(1.0f, 32, 32);

                glPushMatrix();
                {
                    glColor3f(blue[0], blue[1], blue[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                    glPushMatrix();
                    {
                        TrainHead trainHead = new TrainHead(textureFace, textureLeather, textValley);
                        glTranslatef(3.5f - 35.0f, 0.25f, 3.6f);
                        trainHead.drawHead();
                    }
                    glPopMatrix();

                    glPushMatrix();
                    {
                        TrainWall testWall = new TrainWall(textureFace);
                        glTranslatef(3.5f - 35.0f, 0.25f, 0);
                        glScalef(0.7f, 0.7f, 0.7f);
                        testWall.drawTrainWall();
                    }
                    glPopMatrix();

                    glColor3f(blue[0], blue[1], blue[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                    glPushMatrix();
                    {
                        TrainWallWithoutWindow testWall = new TrainWallWithoutWindow(textureFace);
                        glTranslatef(3.5f - 35.0f, 0.25f, -3.6f);
                        glScalef(0.7f, 0.7f, 0.7f);
                        testWall.drawTrainWall();
                    }
                    glPopMatrix();

                    glColor3f(blue[0], blue[1], blue[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                    glPushMatrix();
                    {
                        TrainWall testWall = new TrainWall(textureFace);
                        glTranslatef(3.5f - 35.0f, 0.25f, -7.2f);
                        glScalef(0.7f, 0.7f, 0.7f);
                        testWall.drawTrainWall();
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
