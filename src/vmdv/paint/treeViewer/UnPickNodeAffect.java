package vmdv.paint.treeViewer;

import com.jogamp.opengl.GLAutoDrawable;

import vmdv.model.Node;

public class UnPickNodeAffect implements AssistAffect {
	private Node node;
	
	public UnPickNodeAffect(Node node) {
		this.node = node;
	}


	@Override
	public void affect(GLAutoDrawable gld) {
		node.showChildLabel = false;
		node.setPicked(false);
		node.clearColor();
	}

}
