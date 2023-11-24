package objects3D;

import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public class Sphere {

	public Sphere() {

	}

	// Implement using notes and examine Tetrahedron to aid in the coding look at
	// lecture 7 , 7b and 8
	// 7b should be your primary source, we will cover more about circles in later
	// lectures to understand why the code works
	public void drawSphere(float radius, float nSlices, float nSegments) {
		//draw a Sphere

		double inctheta = ((2.0f * Math.PI) / (nSlices));
		double incphi = (Math.PI / (nSegments));

		GL11.glBegin(GL11.GL_QUADS);

		//rendering sphere
		//r(phi) = r·cos(phi)
		//z = r·sin(phi)
		//x = r(phi)·cos(theta) = r·cos(phi)·cos(theta)
		//y = r(phi)·sin(theta) = r·cos(phi)·sin(theta)
		for (double theta = -Math.PI; theta < Math.PI; theta += inctheta){
			for (double phi = -(Math.PI/2.0f); phi < (Math.PI/2.0f); phi+=incphi){
				double x = (radius * Math.cos(phi) * Math.cos(theta));
				double y = (radius * Math.cos(phi) * Math.sin(theta));
				double z = (radius * Math.sin(phi));

				double x1 = (radius * Math.cos(phi) * Math.cos(theta + inctheta));
				double y1 = (radius * Math.cos(phi) * Math.sin(theta + inctheta));
				double z1 = (radius * Math.sin(phi));

				double x2 = (radius * Math.cos(phi + incphi) * Math.cos(theta + inctheta));
				double y2 = (radius * Math.cos(phi + incphi) * Math.sin(theta + inctheta));
				double z2 = (radius * Math.sin(phi + incphi));

				double x3 = (radius * Math.cos(phi + incphi) * Math.cos(theta));
				double y3 = (radius * Math.cos(phi + incphi) * Math.sin(theta));
				double z3 = (radius * Math.sin(phi + incphi));

				//draw Square
				GL11.glNormal3f((float) x, (float) y, (float) z);
				GL11.glVertex3f((float) x, (float) y, (float) z);

				GL11.glNormal3f((float) x1, (float) y1, (float) z1);
				GL11.glVertex3f((float) x1, (float) y1, (float) z1);

				GL11.glNormal3f((float) x2, (float) y2, (float) z2);
				GL11.glVertex3f((float) x2, (float) y2, (float) z2);

				GL11.glNormal3f((float) x3, (float) y3, (float) z3);
				GL11.glVertex3f((float) x3, (float) y3, (float) z3);
			}
		}
		GL11.glEnd();
	}
}
