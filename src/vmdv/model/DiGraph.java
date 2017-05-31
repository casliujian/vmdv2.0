package vmdv.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.gl2.GLUT;

public class DiGraph extends AbstractGraph {
	private HashMap<DiNode, DiEdges> struct = new HashMap<DiNode, DiEdges>();

	@Override
	public AbstractNode getNode(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNode(String id, String label) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeNode(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addEdge(String fromId, String toId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeEdge(String fromId, String toId) {
		// TODO Auto-generated method stub

	}

	@Override
	public AbstractNode getNearestNode(double x, double y, double z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearColor() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GL2 gl, GLUT glut, TextRenderer tr) {
		int drawedNodes = 0;
		for (DiNode sn : getDiNodes()) {
			if (!sn.visible) {
				continue;
			}
			gl.glPushMatrix();
			gl.glTranslated(sn.xyz.getX(), sn.xyz.getY(), sn.xyz.getZ());
			RGBColor color = sn.color;
			gl.glColor3f(color.getRed(), color.getGreen(), color.getBlue());
			glut.glutSolidSphere(sn.size, 10, 10);
			drawedNodes++;
			// if(sn.isLableVisible()) {
			// tr.begin3DRendering();
			// tr.draw3D(sn.getLabel(), 0, 0, 0, 0.005f);
			// tr.flush();
			// tr.end3DRendering();
			// }
			// if(sn.isPicked()) {
			// tr.begin3DRendering();
			// tr.draw3D(sn.getLabel(), 0, 0, 0, 0.005f);
			// tr.flush();
			// tr.end3DRendering();
			// }
			// if(sn.isPicked()) {
			// tr.beginRendering(gld.getSurfaceWidth(),
			// gld.getSurfaceHeight());
			// Point p = this.getScreenPoint(gld, sn.getX(), sn.getY(),
			// sn.getZ());
			// if(p != null) {
			// tr.setColor(0, 0, 0, 1);
			// tr.draw(sn.getLabel(), p.x, p.y);
			// System.out.println("Showing label of sn: "+sn.getLabel()+" in
			// position: "+String.valueOf(p.x)+","+String.valueOf(p.y));
			// }
			// tr.endRendering();
			// }

			gl.glPopMatrix();
			for (DiEdge se : struct.get(sn).posts) {
				DiNode psn = se.to;
				if (!psn.visible) {
					continue;
				}
				gl.glPushMatrix();
				// RGBColor snc = new
				// RGBColor(178.0f/255,178.0f/255,178.0f/255);
				RGBColor snc = new RGBColor(0, 0, 0);
				if (psn.picked) {
					snc = sn.color;
					se.size = 1.0f;
				}
				gl.glColor3f(snc.getRed(), snc.getGreen(), snc.getBlue());
				gl.glLineWidth(se.size);
				gl.glBegin(GL2.GL_LINES);
				gl.glVertex3d(sn.xyz.getX(), sn.xyz.getY(), sn.xyz.getZ());
				gl.glVertex3d(psn.xyz.getX(), psn.xyz.getY(), psn.xyz.getZ());
				gl.glColor3f(0, 0, 0);

				double dx = psn.xyz.getX() - sn.xyz.getX();
				double dy = psn.xyz.getY() - sn.xyz.getY();
				double dz = psn.xyz.getZ() - sn.xyz.getZ();
				double d = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2) + Math.pow(dz, 2));
				double x = sn.xyz.getX() + dx * ((d - 0.2) / d);
				double y = sn.xyz.getY() + dy * ((d - 0.2) / d);
				double z = sn.xyz.getZ() + dz * ((d - 0.2) / d);
				// gl.glTranslated(x, y, z);
				// gl.glColor3f(0, 1, 1);
				// glut.glutSolidSphere(0.03, 10, 10);
				// gl.glColor3f(1, 1, 1);
				double x1 = sn.xyz.getX() + dx * ((d - 0.3) / d);
				double y1 = sn.xyz.getY() + dy * ((d - 0.3) / d);
				double z1 = sn.xyz.getZ() + dz * ((d - 0.3) / d);

				// arrow
				gl.glVertex3d(x, y, z);
				gl.glVertex3d(x1 + 0.04, y1, z1);

				gl.glVertex3d(x, y, z);
				gl.glVertex3d(x1 - 0.04, y1, z1);

				gl.glVertex3d(x, y, z);
				gl.glVertex3d(x1, y1, z1 - 0.04);
				gl.glEnd();
				gl.glPopMatrix();
			}

		}

	}

	@Override
	public AbstractNode getStart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<AbstractNode> getSuccessors(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<AbstractNode> getPredecessors(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<DiNode> getDiNodes() {
		return struct.keySet();
	}
	
	@Override
	public Set<AbstractNode> getNodes() {
		Set<AbstractNode> nodes = new HashSet<AbstractNode>();
		for(DiNode tn: getDiNodes()) {
			nodes.add(tn);
		}
		return nodes;
	}

	@Override
	public Set<AbstractNode> getSuccessors(AbstractNode an) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<AbstractNode> getPredecessors(AbstractNode an) {
		// TODO Auto-generated method stub
		return null;
	}

}

class DiEdges {
	public Set<DiEdge> pres = new HashSet<DiEdge>();
	public Set<DiEdge> posts = new HashSet<DiEdge>();
	
}
