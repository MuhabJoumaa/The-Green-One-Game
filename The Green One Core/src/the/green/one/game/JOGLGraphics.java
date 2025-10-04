package the.green.one.game;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import java.io.File;
import java.io.IOException;

public class JOGLGraphics implements GLEventListener {

    private final float[] vertices = {
        -0.8f, -0.4f, -0.05f,
        0.8f, -0.4f, -0.05f,
        0.8f, 0.4f, -0.05f,
        -0.8f, 0.4f, -0.05f,
        -0.8f, -0.4f, 0.05f,
        0.8f, -0.4f, 0.05f,
        0.8f, 0.4f, 0.05f,
        -0.8f, 0.4f, 0.05f
    }, textureCoords = {
        0.0f, 0.0f,
        1.0f, 0.0f,
        1.0f, 1.0f,
        0.0f, 1.0f
    };
    private final float lightY = 0.0f;
    private float lightX = 0.0f, lightZ = 1.0f, angle = 0.0f, flagRotation = 0.0f;
    private final GLU glu = new GLU();
    private Texture texture;

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_TEXTURE_2D);
        try {
            File textureFile = new File(new File("pics/SF.png").getAbsolutePath());
            this.texture = TextureIO.newTexture(textureFile, true);
        } catch (IOException e) {
        }
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        this.glu.gluLookAt(0, 0, 5, 0, 0, 0, 0, 1, 0);
        float[] lightPosition = {this.lightX, this.lightY, this.lightZ, 1.0f};
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, lightPosition, 0);
        gl.glRotatef(this.flagRotation, 1.0f, 1.0f, 1.0f);
        if (this.texture != null) {
            this.texture.enable(gl);
            this.texture.bind(gl);
        }
        gl.glBegin(GL2.GL_QUADS);
        // Front face
        gl.glNormal3f(0.0f, 0.0f, 1.0f);
        gl.glTexCoord2f(this.textureCoords[0], this.textureCoords[1]);
        gl.glVertex3fv(this.vertices, 12);
        gl.glTexCoord2f(this.textureCoords[2], this.textureCoords[3]);
        gl.glVertex3fv(this.vertices, 15);
        gl.glTexCoord2f(this.textureCoords[4], this.textureCoords[5]);
        gl.glVertex3fv(this.vertices, 18);
        gl.glTexCoord2f(this.textureCoords[6], this.textureCoords[7]);
        gl.glVertex3fv(this.vertices, 21);
        // Back face
        gl.glNormal3f(0.0f, 0.0f, -1.0f);
        gl.glTexCoord2f(this.textureCoords[0], this.textureCoords[1]);
        gl.glVertex3fv(this.vertices, 0);
        gl.glTexCoord2f(this.textureCoords[2], this.textureCoords[3]);
        gl.glVertex3fv(this.vertices, 3);
        gl.glTexCoord2f(this.textureCoords[4], this.textureCoords[5]);
        gl.glVertex3fv(this.vertices, 6);
        gl.glTexCoord2f(this.textureCoords[6], this.textureCoords[7]);
        gl.glVertex3fv(this.vertices, 9);
        // Left face
        gl.glNormal3f(-1.0f, 0.0f, 0.0f);
        gl.glTexCoord2f(this.textureCoords[0], this.textureCoords[1]);
        gl.glVertex3fv(this.vertices, 0);
        gl.glTexCoord2f(this.textureCoords[2], this.textureCoords[3]);
        gl.glVertex3fv(this.vertices, 12);
        gl.glTexCoord2f(this.textureCoords[4], this.textureCoords[5]);
        gl.glVertex3fv(this.vertices, 21);
        gl.glTexCoord2f(this.textureCoords[6], this.textureCoords[7]);
        gl.glVertex3fv(this.vertices, 9);
        // Right face
        gl.glNormal3f(1.0f, 0.0f, 0.0f);
        gl.glTexCoord2f(this.textureCoords[0], this.textureCoords[1]);
        gl.glVertex3fv(this.vertices, 3);
        gl.glTexCoord2f(this.textureCoords[2], this.textureCoords[3]);
        gl.glVertex3fv(this.vertices, 6);
        gl.glTexCoord2f(this.textureCoords[4], this.textureCoords[5]);
        gl.glVertex3fv(this.vertices, 18);
        gl.glTexCoord2f(this.textureCoords[6], this.textureCoords[7]);
        gl.glVertex3fv(this.vertices, 15);
        // Top face
        gl.glNormal3f(0.0f, 1.0f, 0.0f);
        gl.glTexCoord2f(this.textureCoords[0], this.textureCoords[1]);
        gl.glVertex3fv(this.vertices, 9);
        gl.glTexCoord2f(this.textureCoords[2], this.textureCoords[3]);
        gl.glVertex3fv(this.vertices, 21);
        gl.glTexCoord2f(this.textureCoords[4], this.textureCoords[5]);
        gl.glVertex3fv(this.vertices, 18);
        gl.glTexCoord2f(this.textureCoords[6], this.textureCoords[7]);
        gl.glVertex3fv(this.vertices, 6);
        // Bottom face
        gl.glNormal3f(0.0f, -1.0f, 0.0f);
        gl.glTexCoord2f(this.textureCoords[0], this.textureCoords[1]);
        gl.glVertex3fv(this.vertices, 0);
        gl.glTexCoord2f(this.textureCoords[2], this.textureCoords[3]);
        gl.glVertex3fv(this.vertices, 3);
        gl.glTexCoord2f(this.textureCoords[4], this.textureCoords[5]);
        gl.glVertex3fv(this.vertices, 15);
        gl.glTexCoord2f(this.textureCoords[6], this.textureCoords[7]);
        gl.glVertex3fv(this.vertices, 12);
        gl.glEnd();
        if (this.texture != null) {
            this.texture.disable(gl);
        }
        this.angle += 0.03f;
        this.lightX = 2.0f * (float) Math.cos(this.angle);
        this.lightZ = 2.0f * (float) Math.sin(this.angle);
        this.flagRotation += 0.3f;
        if (this.flagRotation > 360.0f) {
            this.flagRotation -= 360.0f;
        }
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        float aspect = (float) width / height;
        this.glu.gluPerspective(45.0, aspect, 0.1, 100.0);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        if (this.texture != null) {
            this.texture.destroy(drawable.getGL().getGL2());
        }
    }
}
