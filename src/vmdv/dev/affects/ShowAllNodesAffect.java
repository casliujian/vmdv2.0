package vmdv.dev.affects;

import vmdv.control.Session;
import vmdv.dev.AssistAffect;
import vmdv.model.AbstractGraph;
import vmdv.model.AbstractNode;
import vmdv.model.Tree;
import vmdv.model.TreeNode;

public class ShowAllNodesAffect extends AssistAffect {

	@Override
	public void affect(Session session) {
		AbstractGraph graph = session.getGraph();
		for(AbstractNode node : graph.getNodes()) {
			node.visible = true;
			if(node instanceof TreeNode) {
				((TreeNode)node).subtreeVisible = true;
			}
		}
		if(graph instanceof Tree) {
			((Tree)graph).resetRoot();
		}
	}

}
