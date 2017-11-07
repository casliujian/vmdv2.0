package vmdv.dev.affects;

import vmdv.control.Session;
import vmdv.dev.AssistAffect;
import vmdv.model.AbstractGraph;
import vmdv.model.AbstractNode;
import vmdv.model.Tree;
import vmdv.model.TreeNode;

public class HighlightAncestorsAffect extends AssistAffect {
	
	private AbstractNode node;
	
	public HighlightAncestorsAffect(AbstractNode node) {
		this.node = node;
	}

	@Override
	public void affect(Session session) {
		AbstractGraph graph = session.getGraph();
		if(graph instanceof Tree) {
			Tree tree = (Tree)graph;
			TreeNode pnode = (TreeNode)tree.parent((TreeNode)node);
			while(pnode != null && pnode != tree.getRoot()) {
				session.getViewer().affect.addLast(new HighlightNodeAffect(pnode));
				pnode = tree.parent(pnode);
			}
			if(pnode!=null && pnode==tree.getRoot()) {
				session.getViewer().affect.addLast(new HighlightNodeAffect(pnode));
			}
		}
	}

}
