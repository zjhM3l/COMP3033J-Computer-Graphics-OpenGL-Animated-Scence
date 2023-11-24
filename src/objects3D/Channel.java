package objects3D;

import GraphicsObjects.Utils;
import org.lwjgl.BufferUtils;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;

public class Channel {
    private Texture textureBrick, textureWall, textureRail;
    static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
    static float yellow[] = { 1.0f, 1.0f, 0.0f, 1.0f };
    static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
    static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
    static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

    public Channel(Texture textureBrick, Texture textureWall, Texture textureRail){
        this.textureBrick = textureBrick;
        this.textureWall = textureWall;
        this.textureRail = textureRail;
    }
    public void drawChannel() {

        glPushMatrix();
        {
            //Wall
            glColor3f(blue[0], blue[1], blue[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
            glPushMatrix();
            {
                Wall testWall = new Wall(textureWall, textureRail);
                glTranslatef(4, 0.5f, 0);
                testWall.drawWall();
            }
            glPopMatrix();

            //rest stop
            glColor3f(green[0], green[1], green[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(green));
            glPushMatrix();
            {
                TexCube myStopCube = new TexCube();
                glTranslatef(7f-0.7f, -2.5f-0.5f, 2.2f);
                glScalef(1.0f, 1.0f, 2.8f);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

                Color.white.bind();
                textureBrick.bind();
                glEnable(GL_TEXTURE_2D);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                myStopCube.drawTexCube(textureBrick);
            }
            glPopMatrix();

            //light
            FloatBuffer lightPos = BufferUtils.createFloatBuffer(4);
            lightPos.put(-1f).put(0f).put(0f).put(0f).flip();
            glLight(GL_LIGHT0, GL_POSITION, lightPos);
            glEnable(GL_LIGHT0);

            glColor3f(yellow[0], yellow[1], yellow[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(yellow));
            glPushMatrix();
            {
                Cylinder cylinder = new Cylinder();
                glTranslatef(7.0f, 1.2f, 0.8f);
                cylinder.drawCylinder(0.4f, 1, 32);
            }
            glPopMatrix();

            glPushMatrix();
            {
                Cylinder cylinder = new Cylinder();
                glTranslatef(7.0f, 1.2f, 3.2f);
                cylinder.drawCylinder(0.4f, 1, 32);
            }
            glPopMatrix();

            //handler
            glColor3f(black[0], black[1], black[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
            glPushMatrix();
            {
                Cylinder cylinder = new Cylinder();
                glTranslatef(6.5f, -0.3f, 0.0f);
                cylinder.drawCylinder(0.2f, 5, 32);
            }
            glPopMatrix();

            glColor3f(black[0], black[1], black[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
            glPushMatrix();
            {
                Cylinder cylinder = new Cylinder();
                glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                glTranslatef(6.5f, 0.5f, 0.2f);
                cylinder.drawCylinder(0.2f, 2, 32);
            }
            glPopMatrix();

            glPushMatrix();
            {
                Cylinder cylinder = new Cylinder();
                glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                glTranslatef(6.5f, 1.5f, 0.2f);
                cylinder.drawCylinder(0.2f, 2, 32);
            }
            glPopMatrix();

            glPushMatrix();
            {
                Cylinder cylinder = new Cylinder();
                glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                glTranslatef(6.5f, 2.5f, 0.2f);
                cylinder.drawCylinder(0.2f, 2, 32);
            }
            glPopMatrix();

            glPushMatrix();
            {
                Cylinder cylinder = new Cylinder();
                glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                glTranslatef(6.5f, 3.5f, 0.2f);
                cylinder.drawCylinder(0.2f, 2, 32);
            }
            glPopMatrix();

            glPushMatrix();
            {
                Cylinder cylinder = new Cylinder();
                glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                glTranslatef(6.5f, 4.5f, 0.2f);
                cylinder.drawCylinder(0.2f, 2, 32);
            }
            glPopMatrix();
        }
        glPopMatrix();
    }
}
