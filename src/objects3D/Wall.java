package objects3D;

import GraphicsObjects.Utils;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_AMBIENT_AND_DIFFUSE;

public class Wall {
    private Texture textureWall, textureRail;
    static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
    static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
    static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };
    static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };

    public Wall(Texture textureWall, Texture textureRail) {
        this.textureWall = textureWall;
        this.textureRail = textureRail;
    }

    public void drawWall(){
        glPushMatrix();
        {
            //center point
            glColor3f(blue[0], blue[1], blue[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
            glPushMatrix();
            {
                Sphere center = new Sphere();
                glTranslatef(-0.7f, -0.5f, 0.0f);
                center.drawSphere(0.1f, 32, 32);

                //right wall
                glColor3f(red[0], red[1], red[2]);
                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                glPushMatrix();
                {
                    TexCube cube = new TexCube();
                    glTranslatef(4.0f, 0.0f, 2.3f);
                    glScalef(0.2f, 2.0f, 2.5f);

                    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

                    Color.white.bind();
                    textureWall.bind();
                    glEnable(GL_TEXTURE_2D);
                    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                    cube.drawTexCube(textureWall);
                }
                glPopMatrix();

                //left wall
                glColor3f(red[0], red[1], red[2]);
                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                glPushMatrix();
                {
                    TexCube cube = new TexCube();
                    glTranslatef(-4.0f, 0.0f, 2.3f);
                    glScalef(0.2f, 2.0f, 2.5f);

                    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

                    Color.white.bind();
                    textureWall.bind();
                    glEnable(GL_TEXTURE_2D);
                    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                    cube.drawTexCube(textureWall);
                }
                glPopMatrix();

                //up wall
                glColor3f(red[0], red[1], red[2]);
                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                glPushMatrix();
                {
                    TexCube cube = new TexCube();
                    glTranslatef(0.0f, 4.0f, 2.3f);
                    glScalef(2.0f, 0.2f, 2.5f);

                    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

                    Color.white.bind();
                    textureWall.bind();
                    glEnable(GL_TEXTURE_2D);
                    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                    cube.drawTexCube(textureWall);
                }
                glPopMatrix();

                //down wall
                glColor3f(red[0], red[1], red[2]);
                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                glPushMatrix();
                {
                    TexCube cube = new TexCube();
                    glTranslatef(0.0f, -4.0f, 2.3f);
                    glScalef(2.0f, 0.2f, 2.5f);

                    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

                    Color.white.bind();
                    textureWall.bind();
                    glEnable(GL_TEXTURE_2D);
                    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                    cube.drawTexCube(textureWall);
                }
                glPopMatrix();

                //electric line
                glColor3f(black[0], black[1], black[2]);
                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
                glPushMatrix();
                {
                    Cube cube = new Cube();
                    glTranslatef(-3.6f, 1.4f, 2.3f);
                    glScalef(0.2f, 0.2f, 2.5f);
                    cube.drawCube();
                }
                glPopMatrix();

                glColor3f(black[0], black[1], black[2]);
                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
                glPushMatrix();
                {
                    Cube cube = new Cube();
                    glTranslatef(-3.6f, -1.4f, 2.3f);
                    glScalef(0.2f, 0.2f, 2.5f);
                    cube.drawCube();
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cube cube = new Cube();
                    glTranslatef(1.0f, 3.4f, 2.3f);
                    glScalef(0.2f, 0.6f, 2.5f);
                    cube.drawCube();
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cube cube = new Cube();
                    glTranslatef(-1.0f, 3.6f, 2.3f);
                    glScalef(0.2f, 0.4f, 2.5f);
                    cube.drawCube();
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cube cube = new Cube();
                    glTranslatef(-0.6f, 3.4f, 2.3f);
                    glScalef(0.2f, 0.2f, 2.5f);
                    cube.drawCube();
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cube cube = new Cube();
                    glTranslatef(-0.1f, 3.3f, 2.3f);
                    glScalef(0.1f, 0.1f, 2.5f);
                    cube.drawCube();
                }
                glPopMatrix();

                //right-up
                glColor3f(blue[0], blue[1], blue[2]);
                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                glPushMatrix();
                {
                    Cylinder cylinder = new Cylinder();
                    glTranslatef(2.0f, 3.9f, -0.2f);
                    cylinder.drawCylinder(0.3f, 5, 32);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cylinder cylinder = new Cylinder();
                    glTranslatef(2.5f, 3.8f, -0.2f);
                    cylinder.drawCylinder(0.3f, 5, 32);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cylinder cylinder = new Cylinder();
                    glTranslatef(3.0f, 3.5f, -0.2f);
                    cylinder.drawCylinder(0.3f, 5, 32);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cylinder cylinder = new Cylinder();
                    glTranslatef(3.4f, 3.1f, -0.2f);
                    cylinder.drawCylinder(0.3f, 5, 32);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cylinder cylinder = new Cylinder();
                    glTranslatef(3.7f, 2.6f, -0.2f);
                    cylinder.drawCylinder(0.3f, 5, 32);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cylinder cylinder = new Cylinder();
                    glTranslatef(3.9f, 2.1f, -0.2f);
                    cylinder.drawCylinder(0.3f, 5, 32);
                }
                glPopMatrix();

                //left up
                glPushMatrix();
                {
                    Cylinder cylinder = new Cylinder();
                    glTranslatef(-2.0f, 3.9f, -0.2f);
                    cylinder.drawCylinder(0.3f, 5, 32);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cylinder cylinder = new Cylinder();
                    glTranslatef(-2.5f, 3.8f, -0.2f);
                    cylinder.drawCylinder(0.3f, 5, 32);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cylinder cylinder = new Cylinder();
                    glTranslatef(-3.0f, 3.5f, -0.2f);
                    cylinder.drawCylinder(0.3f, 5, 32);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cylinder cylinder = new Cylinder();
                    glTranslatef(-3.4f, 3.1f, -0.2f);
                    cylinder.drawCylinder(0.3f, 5, 32);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cylinder cylinder = new Cylinder();
                    glTranslatef(-3.7f, 2.6f, -0.2f);
                    cylinder.drawCylinder(0.3f, 5, 32);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cylinder cylinder = new Cylinder();
                    glTranslatef(-3.9f, 2.1f, -0.2f);
                    cylinder.drawCylinder(0.3f, 5, 32);
                }
                glPopMatrix();

                //right low
                glPushMatrix();
                {
                    Cylinder cylinder = new Cylinder();
                    glTranslatef(2.0f, -3.9f, -0.2f);
                    cylinder.drawCylinder(0.3f, 5, 32);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cylinder cylinder = new Cylinder();
                    glTranslatef(2.5f, -3.8f, -0.2f);
                    cylinder.drawCylinder(0.3f, 5, 32);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cylinder cylinder = new Cylinder();
                    glTranslatef(3.0f, -3.5f, -0.2f);
                    cylinder.drawCylinder(0.3f, 5, 32);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cylinder cylinder = new Cylinder();
                    glTranslatef(3.4f, -3.1f, -0.2f);
                    cylinder.drawCylinder(0.3f, 5, 32);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cylinder cylinder = new Cylinder();
                    glTranslatef(3.7f, -2.6f, -0.2f);
                    cylinder.drawCylinder(0.3f, 5, 32);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cylinder cylinder = new Cylinder();
                    glTranslatef(3.9f, -2.1f, -0.2f);
                    cylinder.drawCylinder(0.3f, 5, 32);
                }
                glPopMatrix();

                //left low
                glPushMatrix();
                {
                    Cylinder cylinder = new Cylinder();
                    glTranslatef(-2.0f, -3.9f, -0.2f);
                    cylinder.drawCylinder(0.3f, 5, 32);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cylinder cylinder = new Cylinder();
                    glTranslatef(-2.5f, -3.8f, -0.2f);
                    cylinder.drawCylinder(0.3f, 5, 32);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cylinder cylinder = new Cylinder();
                    glTranslatef(-3.0f, -3.5f, -0.2f);
                    cylinder.drawCylinder(0.3f, 5, 32);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cylinder cylinder = new Cylinder();
                    glTranslatef(-3.4f, -3.1f, -0.2f);
                    cylinder.drawCylinder(0.3f, 5, 32);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cylinder cylinder = new Cylinder();
                    glTranslatef(-3.7f, -2.6f, -0.2f);
                    cylinder.drawCylinder(0.3f, 5, 32);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cylinder cylinder = new Cylinder();
                    glTranslatef(-3.9f, -2.1f, -0.2f);
                    cylinder.drawCylinder(0.3f, 5, 32);
                }
                glPopMatrix();

                glColor3f(black[0], black[1], black[2]);
                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
                glPushMatrix();
                {
                    Cylinder rail = new Cylinder();
                    glTranslatef(-1.6f, -3.6f, -0.3f);
                    rail.drawCylinder(0.25f, 5.2f, 32);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cylinder rail = new Cylinder();
                    glTranslatef(1.6f, -3.6f, -0.3f);
                    rail.drawCylinder(0.25f, 5.2f, 32);
                }
                glPopMatrix();

                //rail
                glPushMatrix();
                {
                    Cylinder rail = new Cylinder();
                    glTranslatef(-1.6f, -3.6f, 0.5f);
                    glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                    rail.drawCylinder(0.15f, 3.2f, 32);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cylinder rail = new Cylinder();
                    glTranslatef(-1.6f, -3.6f, 1.5f);
                    glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                    rail.drawCylinder(0.15f, 3.2f, 32);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cylinder rail = new Cylinder();
                    glTranslatef(-1.6f, -3.6f, 2.5f);
                    glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                    rail.drawCylinder(0.15f, 3.2f, 32);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cylinder rail = new Cylinder();
                    glTranslatef(-1.6f, -3.6f, 3.5f);
                    glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                    rail.drawCylinder(0.15f, 3.2f, 32);
                }
                glPopMatrix();

                glPushMatrix();
                {
                    Cylinder rail = new Cylinder();
                    glTranslatef(-1.6f, -3.6f, 4.5f);
                    glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                    rail.drawCylinder(0.15f, 3.2f, 32);
                }
                glPopMatrix();
            }
            glPopMatrix();
        }
        glPopMatrix();
    }
}
