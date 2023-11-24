package objects3D;

import GraphicsObjects.Utils;
import org.lwjgl.BufferUtils;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_AMBIENT_AND_DIFFUSE;

public class TrainHead {
    private Texture textureFace, textureLeather, texValley;
    static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
    static float yellow[] = { 1.0f, 1.0f, 0.0f, 1.0f };
    static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
    static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
    static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

    public TrainHead(Texture textureFace, Texture textureLeather, Texture texValley){
        this.textureFace = textureFace;
        this.textureLeather = textureLeather;
        this.texValley = texValley;
    }

    public void drawHead(){
        glColor3f(green[0], green[1], green[2]);
        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(green));
        //down board
        glPushMatrix();
        {
            TexCube cube = new TexCube();
            glTranslatef(-0.5f, -2.4f, 0.5f);
            glRotatef(60.0f, 1.0f, 0.0f, 0.0f);
            glScalef(1.5f, 1.5f, 0.1f);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

            Color.white.bind();
            textureFace.bind();
            glEnable(GL_TEXTURE_2D);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            cube.drawTexCube(textureFace);
        }
        glPopMatrix();

        glPushMatrix();
        {
            TexCube cube = new TexCube();
            glTranslatef(1.3f, -2.1f, 0.3f);
            glRotatef(60.0f, 1.0f, 1.0f, 0.0f);
            glScalef(1.0f, 1.2f, 0.1f);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

            Color.white.bind();
            textureFace.bind();
            glEnable(GL_TEXTURE_2D);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            cube.drawTexCube(textureFace);
        }
        glPopMatrix();

        glPushMatrix();
        {
            TexCube cube = new TexCube();
            glTranslatef(-2.2f, -2.1f, 0.3f);
            glRotatef(60.0f, 1.0f, -1.0f, 0.0f);
            glScalef(1.0f, 1.2f, 0.1f);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

            Color.white.bind();
            textureFace.bind();
            glEnable(GL_TEXTURE_2D);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            cube.drawTexCube(textureFace);
        }
        glPopMatrix();

        //up board
        glPushMatrix();
        {
            TexCube cube = new TexCube();
            glTranslatef(-0.5f, 2.0f, 0.5f);
            glRotatef(120.0f, 1.0f, 0.0f, 0.0f);
            glScalef(1.5f, 1.2f, 0.1f);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

            Color.white.bind();
            textureFace.bind();
            glEnable(GL_TEXTURE_2D);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            cube.drawTexCube(textureFace);
        }
        glPopMatrix();

        glPushMatrix();
        {
            TexCube cube = new TexCube();
            glTranslatef(1.3f, 1.7f, 0.3f);
            glRotatef(-60.0f, 1.0f, -1.0f, 0.0f);
            glScalef(1.0f, 1.0f, 0.1f);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

            Color.white.bind();
            textureFace.bind();
            glEnable(GL_TEXTURE_2D);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            cube.drawTexCube(textureFace);
        }
        glPopMatrix();

        glPushMatrix();
        {
            TexCube cube = new TexCube();
            glTranslatef(-2.2f, 1.7f, 0.3f);
            glRotatef(-60.0f, 1.0f, 1.0f, 0.0f);
            glScalef(1.0f, 1.0f, 0.1f);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

            Color.white.bind();
            textureFace.bind();
            glEnable(GL_TEXTURE_2D);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            cube.drawTexCube(textureFace);
        }
        glPopMatrix();

        //handler
        glPushMatrix();
        {
            Cylinder handler = new Cylinder();
            glTranslatef(-1.6f, 1.6f, 0.0f);
            glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
            handler.drawCylinder(0.1f, 7.0f, 32);
        }
        glPopMatrix();

        //seat
        glColor3f(red[0], red[1], red[2]);
        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
        glPushMatrix();
        {
            TexCube cube = new TexCube();
            glTranslatef(1.6f, -1.7f, -5.5f);
            glScalef(0.7f, 0.2f, 4.0f);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

            Color.white.bind();
            textureLeather.bind();
            glEnable(GL_TEXTURE_2D);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            cube.drawTexCube(textureLeather);
        }
        glPopMatrix();

        glPushMatrix();
        {
            TexCube cube = new TexCube();
            glTranslatef(1.6f, -2.4f, -5.5f);
            glScalef(0.1f, 0.7f, 4.0f);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

            Color.white.bind();
            textureLeather.bind();
            glEnable(GL_TEXTURE_2D);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            cube.drawTexCube(textureLeather);
        }
        glPopMatrix();

        glColor3f(red[0], red[1], red[2]);
        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
        glPushMatrix();
        {
            TexCube cube = new TexCube();
            glTranslatef(-2.6f, -1.7f, -5.5f);
            glScalef(0.7f, 0.2f, 4.0f);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

            Color.white.bind();
            textureLeather.bind();
            glEnable(GL_TEXTURE_2D);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            cube.drawTexCube(textureLeather);
        }
        glPopMatrix();

        glPushMatrix();
        {
            TexCube cube = new TexCube();
            glTranslatef(-2.6f, -2.4f, -5.5f);
            glScalef(0.1f, 0.7f, 4.0f);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

            Color.white.bind();
            textureLeather.bind();
            glEnable(GL_TEXTURE_2D);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            cube.drawTexCube(textureLeather);
        }
        glPopMatrix();

        //screen

        glColor3f(red[0], red[1], red[2]);
        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
        glPushMatrix();
        {
            FloatBuffer lightPos = BufferUtils.createFloatBuffer(4);
            lightPos.put(1f).put(1f).put(1f).put(1f).flip();
            glLight(GL_LIGHT0, GL_POSITION, lightPos);
            glEnable(GL_LIGHT0);

            TexCube cube = new TexCube();
            glTranslatef(-3.1f, -0.1f, -2.2f);
            glScalef(0.05f, 1.2f, 0.6f);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

            Color.white.bind();
            texValley.bind();
            glEnable(GL_TEXTURE_2D);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            cube.drawTexCube(texValley);
        }
        glPopMatrix();

        glColor3f(red[0], red[1], red[2]);
        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
        glPushMatrix();
        {
            FloatBuffer lightPos = BufferUtils.createFloatBuffer(4);
            lightPos.put(-1f).put(-1f).put(-1f).put(-1f).flip();
            glLight(GL_LIGHT0, GL_POSITION, lightPos);
            glEnable(GL_LIGHT0);

            TexCube cube = new TexCube();
            glTranslatef(2.2f, -0.1f, -2.2f);
            glScalef(0.05f, 1.2f, 0.6f);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

            Color.white.bind();
            texValley.bind();
            glEnable(GL_TEXTURE_2D);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            cube.drawTexCube(texValley);
        }
        glPopMatrix();

    }
}
