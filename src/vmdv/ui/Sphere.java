package vmdv.ui;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

import vmdv.model.RGBColor;

public class Sphere {

	
	private FloatBuffer verticesBuffer;
	private IntBuffer indicesBuffer;

	private int[] m_vao = new int[1];
	private int[] m_vboVertex = new int[1];
	private int[] m_vboIndex = new int[1];
	
	private int numsToDraw;
	private int vertexCount;
	
	GLUT glut = new GLUT();
	
	public Sphere(int lats, int longs) {
				
		ArrayList<Float> vertices = new ArrayList<Float>();
		ArrayList<Integer> indices = new ArrayList<Integer>();

		int indicator = 0;

		for (int i = 0; i <= lats; i++) {
			float lat0 = (float) (Math.PI * (-0.5 + (float) (i - 1) / lats));
			float z0 = (float) Math.sin(lat0);
			float zr0 = (float) Math.cos(lat0);

			float lat1 = (float) (Math.PI * (-0.5 + (float) i / lats));
			float z1 = (float) Math.sin(lat1);
			float zr1 = (float) Math.cos(lat1);

			for (int j = 0; j <= longs; j++) {
				float lng = (float) (2 * Math.PI * (float) (j - 1) / longs);
				float x = (float) Math.cos(lng);
				float y = (float) Math.sin(lng);

				vertices.add(x * zr0);
				vertices.add(y * zr0);
				vertices.add(z0);
				indices.add(indicator);
				indicator++;

				vertices.add(x * zr1);
				vertices.add(y * zr1);
				vertices.add(z1);
				indices.add(indicator);
				indicator++;
			}
			indices.add(GL2.GL_PRIMITIVE_RESTART_FIXED_INDEX);
		}
		// sizes
		numsToDraw = indices.size();
		vertexCount = vertices.size();
		
		// to array
		int[] indexArray = new int[indices.size()];
		for (int i = 0; i < indices.size(); i++)
			indexArray[i] = indices.get(i);
		
		float[] vertexArray = new float[vertices.size()];
		for (int i = 0; i < vertices.size(); i++)
			vertexArray[i] = vertices.get(i);
		
		// to Buffer
		verticesBuffer = Buffers.newDirectFloatBuffer(vertexArray);
		indicesBuffer = Buffers.newDirectIntBuffer(indexArray);
		
		
	
	}

	public void initGL(GL2 gl) {
	    gl.glGenVertexArrays(1, m_vao, 0);
	    gl.glBindVertexArray(m_vao[0]);

	    gl.glGenBuffers(1, m_vboVertex, 0);
	    gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, m_vboVertex[0]);
	    gl.glBufferData(GL2.GL_ARRAY_BUFFER, vertexCount * 4, verticesBuffer, GL2.GL_STATIC_DRAW);
	    
	    gl.glVertexAttribPointer(0, 3, GL2.GL_FLOAT, false, 0, 0);
	    gl.glEnableVertexAttribArray (0);

	    gl.glGenBuffers(1, m_vboIndex, 0);
	    gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, m_vboIndex[0]);
	    gl.glBufferData(GL2.GL_ELEMENT_ARRAY_BUFFER, vertexCount * 4, indicesBuffer, GL2.GL_STATIC_DRAW);
	    
	    // bind normal
	    gl.glEnableVertexAttribArray(2);
	    gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, m_vboVertex[0]);
	    gl.glVertexAttribPointer(2, 3, GL2.GL_FLOAT, false, 0, 0);
//	    gl.glScalef(0.1f, 0.1f, 0.1f);
	}

	public void render(GL2 gl, RGBColor color) {
		
		gl.glEnable(GL2.GL_COLOR_MATERIAL);
		gl.glColor3f(color.getRed(), color.getGreen(), color.getBlue());
	    // draw sphere
	    gl.glBindVertexArray(m_vao[0]);
	    gl.glEnable(GL2.GL_PRIMITIVE_RESTART);
	    gl.glPrimitiveRestartIndex(GL2.GL_PRIMITIVE_RESTART_FIXED_INDEX);
	    gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, m_vboIndex[0]);
	    gl.glDrawElements(GL2.GL_QUAD_STRIP, numsToDraw, GL2.GL_UNSIGNED_INT, 0);

	    //glut.glutSolidSphere(1.0, 10, 10);
	}

}
