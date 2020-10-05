package cena;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

/**
 *
 * @author Fernanda P. Umberto
 */
public class Scene implements GLEventListener {

    private float xMin, xMax, yMin, yMax, zMin, zMax;
    GLU glu;

    @Override
    public void init(GLAutoDrawable drawable) {
        glu = new GLU();
        xMin = yMin = zMin = -1;
        xMax = yMax = zMax = 1;
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity(); 

        // Draw sky, grass, entry path and wall.
        drawSky(gl);
        drawGrass(gl);
        drawEntryPath(gl);
        drawWall(gl);

        // Draw the roof and apply a 0.3 (x) and 0.1 (y) translation.
        gl.glPushMatrix();
        gl.glTranslatef(0.3f, 0.1f, 0);
        drawRoof(gl);
        gl.glPopMatrix();
        
        // Draw the door and apply a 0.5 (y) scale.
        gl.glPushMatrix();
        gl.glScalef(1, 0.5f, 1);
        drawDoor(gl);
        gl.glPopMatrix();

        
        // Draw the window and rotate it 20º (z).
        gl.glPushMatrix();
        gl.glRotatef(20, 0, 0, 1);
        drawWindow(gl);
        gl.glPopMatrix();

        gl.glFlush();

    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();

        if (height == 0) {
            height = 1;
        }
        float aspect = (float) width / height;

        gl.glViewport(0, 0, width, height);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        if (width >= height) {
            gl.glOrtho(xMin * aspect, xMax * aspect, yMin, yMax, zMin, zMax);
        } else {
            gl.glOrtho(xMin, xMax, yMin / aspect, yMax / aspect, zMin, zMax);
        }

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        System.out.println("Reshape: " + width + ", " + height);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    //Draw sky
    public void drawSky(GL2 gl) {
        gl.glColor3f(0, 0, 0.6f);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(-1.0f, 0);
        gl.glVertex2f(-1.0f, 1.0f);
        gl.glVertex2f(1.0f, 1.0f);
        gl.glVertex2f(1.0f, 0);

        gl.glEnd();
    }

    //Draw door
    public void drawDoor(GL2 gl) {
        gl.glColor3f(0, 255, 0);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(-0.1f, -0.5f);
        gl.glVertex2f(-0.3f, -0.5f);
        gl.glVertex2f(-0.3f, 0.2f);
        gl.glVertex2f(-0.1f, 0.2f);

        gl.glEnd();
        
        //Draw door handle
        gl.glColor3f(0, 0, 0);
        gl.glPointSize(3f);
        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex2f(-0.25f, -0.1f);
        gl.glEnd();
    }

    //Draw roof
    public void drawRoof(GL2 gl) {
        gl.glColor3f(0, 0, 255);
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex2f(-0.5f, 0.4f);
        gl.glVertex2f(0, 0.7f);
        gl.glVertex2f(0.5f, 0.4f);

        gl.glEnd();
    }

    //Draw entry path
    public void drawEntryPath(GL2 gl) {
        gl.glColor3f(255, 255, 0);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(-0.7f, -1.0f);
        gl.glVertex2f(0.2f, -1.0f);
        gl.glVertex2f(-0.1f, -0.5f);
        gl.glVertex2f(-0.3f, -0.5f);

        gl.glEnd();
    }

    //Draw window
    public void drawWindow(GL2 gl) {
        gl.glColor3f(0, 0, 0);
        gl.glLineWidth(2f);
        gl.glBegin(GL2.GL_LINE_LOOP);
        gl.glVertex2f(0.2f, 0.1f);
        gl.glVertex2f(0.0f, 0.1f);
        gl.glVertex2f(0.0f, 0.2f);
        gl.glVertex2f(0.2f, 0.2f);
        gl.glBegin(GL2.GL_LINE_LOOP);
        gl.glVertex2f(0.2f, 0.1f);
        gl.glVertex2f(0.4f, 0.1f);
        gl.glVertex2f(0.4f, 0.2f);
        gl.glVertex2f(0.2f, 0.2f);

        gl.glBegin(GL2.GL_LINE_LOOP);
        gl.glVertex2f(0.2f, 0.1f);
        gl.glVertex2f(0.4f, 0.1f);
        gl.glVertex2f(0.4f, 0);
        gl.glVertex2f(0.2f, 0f);

        gl.glBegin(GL2.GL_LINE_LOOP);
        gl.glVertex2f(0.2f, 0.1f);
        gl.glVertex2f(0, 0.1f);
        gl.glVertex2f(0, 0);
        gl.glVertex2f(0.2f, 0f);

        gl.glEnd();
    }

    //Draw wall
    public void drawWall(GL2 gl) {
        gl.glColor3f(255, 0, 0);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(-0.5f, -0.5f);
        gl.glVertex2f(-0.5f, 0.4f);
        gl.glVertex2f(0.5f, 0.4f);
        gl.glVertex2f(0.5f, -0.5f);

        gl.glEnd();
    }

    //Draw grass
    public void drawGrass(GL2 gl) {
        gl.glColor3f(0, 0.8f, 0);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(-1.0f, 0);
        gl.glVertex2f(1.0f, 0f);
        gl.glVertex2f(1.0f, -1.0f);
        gl.glVertex2f(-1.0f, -1.0f);

        gl.glEnd();
    }
}
