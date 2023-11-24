package objects3D;

import static org.lwjgl.opengl.GL11.*;
import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import org.lwjgl.opengl.GL11;

import java.math.*;

public class Cylinder {

	
	public Cylinder() { 
	}
	
	// remember to use Math.PI isntead PI 
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8 
	public void drawCylinder(float radius, float height, int nSegments ) 
	{
		//draw a Cylinder

		GL11.glBegin(GL11.GL_TRIANGLES);
		for (float i = 0.0F; i < nSegments; i += 1.0){
			// a loop around circumference of a tube
			float angle = (float) (Math.PI * i * 2.0 / nSegments);
			float nextAngle = (float) (Math.PI * (i + 1.0) * 2.0 / nSegments);

			//compute sin & cos
			float y1 = radius * (float) Math.sin(angle), x1 = radius * (float) Math.cos(angle);
			float y2 = radius * (float) Math.sin(nextAngle), x2 = radius * (float) Math.cos(nextAngle);

			//draw top triangle
			GL11.glNormal3f(x1, y1, 0.0f); GL11.glVertex3f(x1, y1, 0.0f);
			GL11.glNormal3f(x2, y2, 0.0f); GL11.glVertex3f(x2, y2, height);
			GL11.glNormal3f(x1, y1, 0.0f); GL11.glVertex3f(x1, y1, height);

			//draw bottom triangle
			GL11.glNormal3f(x1, y1, 0.0f); GL11.glVertex3f(x1, y1, 0.0f);
			GL11.glNormal3f(x2, y2, 0.0f); GL11.glVertex3f(x2, y2, 0.0f);
			GL11.glNormal3f(x2, y2, 0.0f); GL11.glVertex3f(x2, y2, height);

			//draw bottom and top
			GL11.glVertex3f(x1, y1, 0.0f);
			GL11.glVertex3f(x2, y2, 0.0f);
			GL11.glVertex3f(0.0f, 0.0f, 0.0f);

			GL11.glVertex3f(x1, y1, height);
			GL11.glVertex3f(x2, y2, height);
			GL11.glVertex3f(0.0f, 0.0f, height);
		}
		GL11.glEnd();
	}
}
