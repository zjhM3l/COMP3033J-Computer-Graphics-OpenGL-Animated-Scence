package objects3D;

import GraphicsObjects.Utils;
import org.lwjgl.BufferUtils;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_AMBIENT_AND_DIFFUSE;

public class SubWayRailCircle {
    private Texture textureBrick, textureWall, textureRail, texEarth;
    static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

    public SubWayRailCircle(Texture textureBrick, Texture textureWall, Texture textureRail, Texture textureEarth){
        this.textureBrick = textureBrick;
        this.textureWall = textureWall;
        this.textureRail = textureRail;
        this.texEarth = textureEarth;
    }

    public void drawSubway(float delta, boolean GoodAnimation){

        glPushMatrix();
        {
            //center point
            glColor3f(blue[0], blue[1], blue[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
            glPushMatrix();
            {
                TexSphere centerSub = new TexSphere();
                glTranslatef(35.0f-0.7f, 0.5f-0.5f, 0.0f);
                glRotatef(delta, 0.0f, 1.0f, 0.0f);

                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

                Color.white.bind();
                texEarth.bind();
                glEnable(GL_TEXTURE_2D);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

                centerSub.DrawTexSphere(4.0f, 32, 32, texEarth);

                for (float angle = 0.0f; angle <= 360.0f; angle += 20.0f) {
                        if (angle <= 360f & angle >= 0f) {
                            glPushMatrix();
                            {
                                Station station = new Station(textureBrick, textureWall, textureRail);
                                glScalef(1.0f, 1.0f, 1.0f);
                                glRotatef(angle, 0.0f, 1.0f, 0.0f);
                                glTranslatef(-35.0f, 0.0f, 0.0f);
                                station.drawStation();
                            }
                            glPopMatrix();
                        } else {
                            glPushMatrix();
                            {
                                Channel station = new Channel(textureBrick, textureWall, textureRail);
                                glScalef(1.0f, 1.0f, 1.0f);
                                glRotatef(angle, 0.0f, 1.0f, 0.0f);
                                glTranslatef(-35.0f, 0.0f, 0.0f);
                                station.drawChannel();
                            }
                            glPopMatrix();
                        }
                    }
            }
            glPopMatrix();
        }
        glPopMatrix();
    }
}
