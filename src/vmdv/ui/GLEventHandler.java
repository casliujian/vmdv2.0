package vmdv.ui;

import java.awt.Font;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.gl2.GLUT;

import vmdv.config.ColorConfig;
import vmdv.model.AbstractNode;
//import vmdv.model.Property;
import vmdv.model.Property;

public class GLEventHandler implements GLEventListener {

	protected GLU glu;
	protected GLUT glut;

	private TextRenderer tr = new TextRenderer(new Font("SansSerif", Font.PLAIN, 30));

	private Viewer viewer;

	private float frames = 0;
	private long time;

	private float[] lightPosition = new float[4];
	private float[] whiteLight = new float[4];
	
	private Sphere sphere;
	

	public void setViewer(Viewer viewer) {
		this.viewer = viewer;
	}

	@Override
	public void display(GLAutoDrawable gld) {
		// gld.setAutoSwapBufferMode(true);
		// GL3 gl = gld.getGL().getGL3();
		GL2 gl = (gld).getGL().getGL2();

		
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		
		glu.gluLookAt(viewer.eyex, viewer.eyey, viewer.eyez, 0, 0, 0, 0, 1, 0);
		gl.glPushAttrib(GL2.GL_CURRENT_BIT);

		viewer.graph.render(gl, glut, tr, sphere);

		if (!viewer.popupShowed) {
			AbstractNode sn = viewer.selectNode(gl, viewer.mousePosition);
			if (sn != viewer.hoverNode && viewer.hoverNode != null) {
				viewer.hoverNode.outOfStay();
			}
			if (sn != null) {
				sn.stayOn();
//				viewer.hoverNode = sn;
			}
			viewer.hoverNode = sn;
			
//			if (sn != null) {
//				if (!sn.picked) {
//					AbstractNode oldHoverNode = viewer.hoverNode;
//					if (oldHoverNode != null) {
//						oldHoverNode.color = viewer.hoverNodeState.getColor();
//						oldHoverNode.size = viewer.hoverNodeState.getSize();
//					}
//					viewer.hoverNode = sn;
//					Property ns = new Property(sn.color, sn.size);
//					viewer.hoverNodeState = ns;
//					sn.color = (ColorConfig.hoverColor);
//					sn.size = (ns.getSize() * 1.2);
//				}
//			} else {
//				AbstractNode oldHoverNode = viewer.hoverNode;
//				if (oldHoverNode != null && !oldHoverNode.picked) {
//					oldHoverNode.color = viewer.hoverNodeState.getColor();
//					oldHoverNode.size = viewer.hoverNodeState.getSize();
//				}
//				viewer.hoverNode = sn;
//			}
		}

		// show color, label or else
		if (!viewer.affect.isEmpty()) {
			viewer.affect.removeFirst().affect(viewer.session);
		}

		viewer.layout.updateLayout(viewer.graph);

		// gl.glLoadIdentity();
		// gl.glColor3f(0, 0, 0);
		gl.glFlush();

		setCamera(gld);
		// System.out.println("displayed...");

		long currentTime = System.currentTimeMillis();
		long timeDiff = currentTime - time;
		if (timeDiff > 1000) {
			float fps = frames * 1000 / timeDiff;
//			System.out.println("FPS of " + viewer.session.getSid() + ": " + fps);
			this.viewer.setStatusStrIfEmpty("FPS: "+fps);
			time = currentTime;
			frames = 0;
		} else {
			frames = frames + 1.0f;
		}

	}

	public void setCamera(GLAutoDrawable gld) {
		GL2 gl = gld.getGL().getGL2();
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		glu.gluLookAt(viewer.eyex, viewer.eyey, viewer.eyez, 0, 0, 0, 0, 1, 0);
		viewer.refreshGLPanel();
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(GLAutoDrawable gld) {
		tr.setColor(0, 0, 0, 1);
		lightPosition[0] = 10.0f;
		lightPosition[1] = 10.0f;
		lightPosition[2] = 10.0f;
		lightPosition[3] = 0.0f;
		whiteLight[0] = 0.8f;
		whiteLight[1] = 0.8f;
		whiteLight[2] = 0.8f;
		whiteLight[3] = 1.0f;
		GL2 gl = gld.getGL().getGL2();

		// GLUT glut = new GLUT();
		gld.setAutoSwapBufferMode(true);
		glu.gluLookAt(viewer.eyex, viewer.eyey, viewer.eyez, 0, 0, 0, 0, 1, 0);
		gl.glColorMaterial(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE);
		gl.glEnable(GL2.GL_COLOR_MATERIAL);
		gl.glClearColor(1, 1, 1, 0);
		gl.glClearDepth(1.0);
		gl.glEnable(GL2.GL_LINE_SMOOTH);
		gl.glEnable(GL2.GL_POLYGON_SMOOTH);
		gl.glHint(GL2.GL_LINE_SMOOTH_HINT, GL2.GL_NICEST);
		gl.glHint(GL2.GL_POLYGON_SMOOTH_HINT, GL2.GL_NICEST);
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		
//		gl.glShadeModel(GL2.GL_SMOOTH);
//		gl.glClearColor(1f, 1f, 1f, 0f);
//		gl.glClearDepth(1.0f);
//		gl.glEnable(GL2.GL_DEPTH_TEST);
//		gl.glDepthFunc(GL2.GL_LEQUAL);
//		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
//		gl.glEnable(GL2.GL_LIGHTING);
//		gl.glEnable(GL2.GL_LIGHT0);

		gl.glViewport(0, 0, gld.getSurfaceWidth(), gld.getSurfaceHeight());
		gl.glMatrixMode(GL2.GL_PROJECTION);
		int width = gld.getSurfaceWidth();
		int height = gld.getSurfaceHeight();
		if (width == 0 || height == 0) {
			glu.gluPerspective(60.0f, 1, 1.0f, 10000.0f);
		} else {
			glu.gluPerspective(60.0f, width / height, 1.0f, 10000.0f);
		}
		// glu.gluPerspective(60.0f, gld.getSurfaceWidth() /
		// gld.getSurfaceHeight(), 1.0f, 10000.0f);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		// gl.glLightfv(arg0, arg1, arg2, arg3);
		// gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, lightPosition, 0);
		// gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, whiteLight, 0);
		// gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, whiteLight, 0);
		// glu.gluOrtho2D(0.0, 500.0, 0.0, 300.0);
		gld.swapBuffers();
//		try {
//			Thread.sleep(10);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		this.setCamera(gld);
		
		this.sphere = new Sphere(50, 50);
		sphere.initGL(gl);

		this.time = System.currentTimeMillis();
	}

	@Override
	public void reshape(GLAutoDrawable gld, int x, int y, int width, int height) {

		if (height == 0) {
			height = 1;
		}
		GL2 gl2 = gld.getGL().getGL2();
		gl2.glViewport(0, 0, width, height);

		gl2.glMatrixMode(GL2.GL_PROJECTION);
		gl2.glLoadIdentity();

		// coordinate system origin at lower left with width and height same as
		// the window
		if (glu == null) {
			glu = new GLU();
		}
		glu.gluPerspective(45.0f, (float) ((float) width / (float) height), 0.1f, 10000.0f);

		gl2.glMatrixMode(GL2.GL_MODELVIEW);
		gl2.glLoadIdentity();
		
//		final GL2 gl = gld.getGL().getGL2();
//		if (height <= 0)
//			height = 1;
//		final float h = (float) width / (float) height;
//		gl.glViewport(0, 0, width, height);
//		gl.glMatrixMode(GL2.GL_PROJECTION);
//		gl.glLoadIdentity();
//		glu.gluPerspective(45.0f, h, 1.0, 20.0);
//		gl.glMatrixMode(GL2.GL_MODELVIEW);
//		gl.glLoadIdentity();

	}
}
