package objects3D;

import GraphicsObjects.Utils;
import org.lwjgl.BufferUtils;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glPopMatrix;

public class Station {
    private Texture textureBrick, textureWall, textureRail;

    static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
    static float yellow[] = { 1.0f, 1.0f, 0.0f, 1.0f };
    static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
    static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
    static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

    public Station(Texture textureBrick, Texture textureWall, Texture textureRail){
        this.textureBrick = textureBrick;
        this.textureWall = textureWall;
        this.textureRail = textureRail;
    }

    public void drawStation(){
        glPushMatrix();
        {
            //Wall with door
            glColor3f(blue[0], blue[1], blue[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
            glPushMatrix();
            {
                WallDoor testWall = new WallDoor(textureWall, textureRail);
                glTranslatef(4, 0.5f, 0);
                testWall.drawWall();
            }
            glPopMatrix();

            //rest
            glColor3f(green[0], green[1], green[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(green));
            glPushMatrix();
            {
                TexCube myStopCube = new TexCube();
                glTranslatef(7f-0.7f, -3.0f-0.5f, 2.2f);
                glScalef(1.0f, 1.0f, 2.8f);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

                Color.white.bind();
                textureBrick.bind();
                glEnable(GL_TEXTURE_2D);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                myStopCube.drawTexCube(textureBrick);

            }
            glPopMatrix();


            //light board
            FloatBuffer lightPos = BufferUtils.createFloatBuffer(4);
            lightPos.put(1f).put(0f).put(0f).put(0f).flip();
            glLight(GL_LIGHT0, GL_POSITION, lightPos);
            glEnable(GL_LIGHT0);

            glColor3f(yellow[0], yellow[1], yellow[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(yellow));
            glPushMatrix();
            {
                Cylinder cylinder = new Cylinder();
                glTranslatef(-0.5f, 1.0f, 1.0f);
                glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                cylinder.drawCylinder(0.5f, 2, 32);
            }
            glPopMatrix();

            glPushMatrix();
            {
                Cylinder cylinder = new Cylinder();
                glTranslatef(-0.5f, 1.0f, 4.0f);
                glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                cylinder.drawCylinder(0.5f, 2, 32);
            }
            glPopMatrix();
        }
        glPopMatrix();
    }
}
