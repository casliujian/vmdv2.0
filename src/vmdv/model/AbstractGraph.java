package vmdv.model;

import java.util.Set;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.gl2.GLUT;

public abstract class AbstractGraph {
	
	public abstract Set<AbstractNode> getNodes();
	public abstract void addNode(String id, String label);
	public abstract void removeNode(String id);
	public abstract void addEdge(String fromId, String toId);
	public abstract void removeEdge(String fromId, String toId);
	public abstract AbstractNode getNearestNode(double x, double y, double z);
	public abstract void clearColor();
	public abstract void render(GL2 gl, GLUT glut, TextRenderer tr);

}
