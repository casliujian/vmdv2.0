package vmdv.ui;

import java.awt.Point;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

import vmdv.model.Graph;
import vmdv.model.Node;
import vmdv.model.NodeState;
import vmdv.paint.treeViewer.AssistAffect;

public class Viewer extends JFrame {
	private GLU glu = new GLU();
	private GLUT glut = new GLUT();
	
	private GLJPanel renderPanel;
	protected JPopupMenu backPop, nodePop;
	private GLEventListener glistener;
	private KeyListener keyListener;
	private MouseListener mouseListener;
	private MouseWheelListener mwListener;
	private MouseMotionListener mmListener;
	
	private Graph graph;
	
	protected double eyex = 0;
	protected double eyey = 0;
	protected double eyez = 5;
	protected double phi = 0; // 0--360
	protected double theta = 90; // 0--180
	protected int dragStartX = 0;
	protected int dragStartY = 0;
	protected LinkedList<AssistAffect> affect = new LinkedList<AssistAffect>();
	protected boolean popupShowed = false;
	protected Point mousePosition = null;
	protected boolean singleSelection = true;
	
	
	protected Node hoverNode = null;
	protected NodeState hoverNodeState = null;
	protected Node nodeSelected = null;
	protected NodeState selectNodeState = null;
	protected LinkedList<Node> multiNodesSelected = new LinkedList<Node>();
	protected LinkedList<NodeState> multiNodesSelectedState = new LinkedList<NodeState>();
	

	
	
	public Viewer(String title) {
		this.setTitle(title);
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities glcaps = new GLCapabilities(glp);
		glcaps.setDoubleBuffered(true);
		this.renderPanel = new GLJPanel(glcaps);
//		glistener.
//		this.renderPanel.addm
	}
	
	public void setEye(double x, double y, double z) {
		eyex = x;
		eyey = y;
		eyez = z;
	}
	
	public Graph getGraph() {
		return graph;
	}
	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public void refreshGLPanel() {
		renderPanel.repaint();
	}
	
	public void registerGLHandler(GLEventHandler glistener) {
		glistener.setGraph(this.getGraph());
		glistener.glu = glu;
		glistener.glut = glut;
		glistener.setViewer(this);
		this.glistener = glistener;
		renderPanel.addGLEventListener(glistener);
	}
	
	public void registerKeyHandler(KeyHandler keyHandler) {
		keyHandler.setViewer(this);
		this.keyListener = keyHandler;
		renderPanel.addKeyListener(keyHandler);
	}
	
	public void registerMouseHandler(MouseHandler mouseHandler) {
		mouseHandler.setViewer(this);
		this.mouseListener = mouseHandler;
		renderPanel.addMouseListener(mouseHandler);
	}
	
	public void registerMouseMotionHandler(MouseMotionHandler mmHandler) {
		mmHandler.setViewer(this);
		this.mmListener = mmHandler;
		renderPanel.addMouseMotionListener(mmHandler);
	}
	
	public void registerMouseWheelHandler(MouseWheelHandler mwHandler) {
		mwHandler.setViewer(this);
		this.mwListener = mwHandler;
		renderPanel.addMouseWheelListener(mwHandler);
	}
	
	protected Node selectNode(GL2 gl, Point p) {
		if (p == null) {
			return null;
		}
		FloatBuffer projection = FloatBuffer.allocate(16);
		FloatBuffer modelview = FloatBuffer.allocate(16);
		IntBuffer viewport = IntBuffer.allocate(4);
		FloatBuffer bz = FloatBuffer.allocate(1);
		FloatBuffer objxyz = FloatBuffer.allocate(3);
		
		gl.glGetFloatv(GL2.GL_MODELVIEW_MATRIX, modelview);
		gl.glGetFloatv(GL2.GL_PROJECTION_MATRIX, projection);
		gl.glGetIntegerv(GL2.GL_VIEWPORT, viewport);
		// float x = visibleEvent.getX();
		int x = (int) p.getX();
		int y = (int) (viewport.get(3) - p.getY());
		gl.glReadPixels(x, y, 1, 1, GL2.GL_DEPTH_COMPONENT, GL2.GL_FLOAT, bz);
		float z = bz.get(0);
		glu.gluUnProject(x, y, z, modelview, projection, viewport, objxyz);
		// System.out.println("x, y, z:"+objxyz.get(0)+", "+objxyz.get(1)+",
		// "+objxyz.get(2));
		Node n = graph.getNearestNode(objxyz.get(0), objxyz.get(1), objxyz.get(2));
		return n;
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
