package vmdv.ui;

import java.awt.Font;
import java.awt.Point;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.LinkedList;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.gl2.GLUT;

import vmdv.model.Edge;
import vmdv.model.Graph;
import vmdv.model.Node;
import vmdv.model.NodeState;
import vmdv.model.RGBColor;
import vmdv.paint.treeViewer.ColorConfig;

public class GLEventHandler implements GLEventListener {
	
	private Graph graph;
	protected GLU glu;
	protected GLUT glut;

	private TextRenderer tr = new TextRenderer(new Font("SansSerif", Font.PLAIN, 30));
	
	private Viewer viewer;
	
	
	public void setGraph(Graph graph) {
		this.graph = graph;
	}
	
	public void setViewer(Viewer viewer) {
		this.viewer = viewer;
	}

	@Override
	public void display(GLAutoDrawable gld) {
//		gld.setAutoSwapBufferMode(true);
//		GL3 gl = gld.getGL().getGL3();
		GL2 gl = (gld).getGL().getGL2();
		
	
		
		gl.glLoadIdentity();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

		glu.gluLookAt(viewer.eyex, viewer.eyey, viewer.eyez, 0, 0, 0, 0, 1, 0);
		gl.glPushAttrib(GL2.GL_CURRENT_BIT);
		
		LinkedList<Node> painting = new LinkedList<Node>();
		painting.addLast(graph.getStart());
		int drawedNodes = 0;
		while (!painting.isEmpty()) {
//			System.out.println("painting node...");
			Node tn = painting.removeFirst();
			if(tn == null) {
				return;
			}
			if (tn.isVisible()) {
				RGBColor color = tn.getColor();
				gl.glPushMatrix();
				gl.glColor3f(color.getRed(), color.getGreen(), color.getBlue());
				gl.glTranslated(tn.getX(), tn.getY(), tn.getZ());
				glut.glutSolidSphere(tn.getSize(), 10, 10);
				drawedNodes ++;
				gl.glColor3f(1, 1, 1);
				
				if(tn.isLableVisible()) {
					tr.begin3DRendering();
					tr.draw3D(tn.getLabel(), 0, 0, 0, 0.005f);
					tr.flush();
					tr.end3DRendering();
				}
				if(tn.showChildLabel) {
					tr.begin3DRendering();
					tr.draw3D(tn.childLabel, 0, 0, 0, 0.01f);
					tr.flush();
					tr.end3DRendering();
				}
				gl.glPopMatrix();
				
				gl.glDisable(GL2.GL_LIGHTING);
				gl.glDisable(GL2.GL_LIGHT0);
				gl.glEnable(GL2.GL_LIGHTING);
				gl.glEnable(GL2.GL_LIGHT0);

				if(tn.isShowSubtree()) {
					for (Edge e : graph.getPostEdges(tn)) {
						Node ton = e.getTo();
						if (ton.isVisible()) {
							gl.glPushMatrix();
							RGBColor edgeColor = e.getColor();
							gl.glColor3f(edgeColor.getRed(), edgeColor.getGreen(), edgeColor.getBlue());
							gl.glLineWidth(e.getSize());
							gl.glBegin(GL2.GL_LINES);
							gl.glVertex3d(tn.getX(), tn.getY(), tn.getZ());
							gl.glVertex3d(ton.getX(), ton.getY(), ton.getZ());
							gl.glEnd();
							gl.glPopMatrix();

							painting.addLast(ton);
						}
					}
				}
			}
		}
		
		
//		tr.beginRendering(gld.getSurfaceWidth(), gld.getSurfaceHeight());
//		tr.setColor(0, 0, 0, 1);
//		tr.draw(String.valueOf(drawedNodes), 10, 10);
//		tr.endRendering();
		
//		gl.glColorMaterial(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE);
//		gl.glEnable(GL2.GL_COLOR_MATERIAL);
//		gl.glShadeModel(GL2.GL_SMOOTH);
//		gl.glEnable(GL2.GL_LIGHTING);
//		gl.glEnable(GL2.GL_LIGHT0);
//		gl.glEnable(GL2.GL_LINE_SMOOTH);
//		gl.glEnable(GL2.GL_POLYGON_SMOOTH);
//		gl.glHint(GL2.GL_LINE_SMOOTH_HINT, GL2.GL_NICEST);
//		gl.glHint(GL2.GL_POLYGON_SMOOTH_HINT, GL2.GL_NICEST);
//		gl.glEnable(GL2.GL_BLEND);
//		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
//		gl.glEnable(GL2.GL_DEPTH_TEST);
//
//		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, lightPosition, 0);
//		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, whiteLight, 0);
//		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, whiteLight, 0);


		if(!viewer.popupShowed) {
			Node sn = viewer.selectNode(gl, viewer.mousePosition);
			if (sn != null) {
				if (!sn.isPicked()) {
					Node oldHoverNode = viewer.hoverNode;
					if (oldHoverNode != null) {
						oldHoverNode.setColor(viewer.hoverNodeState.getColor());
						oldHoverNode.setSize(viewer.hoverNodeState.getSize());
					}
					viewer.hoverNode = sn;
					NodeState ns = new NodeState(sn.getColor(), sn.getSize());
					viewer.hoverNodeState = ns;
					ns.setColor(ColorConfig.hoverColor);
					ns.setSize(ns.getSize()*1.5);
				}
			} else {
				Node oldHoverNode = viewer.hoverNode;
				if (oldHoverNode != null) {
					oldHoverNode.setColor(viewer.hoverNodeState.getColor());
					oldHoverNode.setSize(viewer.hoverNodeState.getSize());
				}
				viewer.hoverNode = sn;
			}
		}

		// show color, label or else
		if (!viewer.affect.isEmpty()) {
			viewer.affect.removeFirst().affect(gld);
		}

//		gl.glLoadIdentity();
//		gl.glColor3f(0, 0, 0);
		gl.glFlush();
		
		setCamera(gld);
//		System.out.println("displayed...");

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
		// TODO Auto-generated method stub

	}

	@Override
	public void reshape(GLAutoDrawable gld, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
