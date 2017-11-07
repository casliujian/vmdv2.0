package vmdv.dev.affects;

import vmdv.control.Session;
import vmdv.dev.AssistAffect;
import vmdv.model.Tree;
import vmdv.model.TreeNode;

public class FocusSubtreeAffect extends AssistAffect {
	
	private TreeNode node;
	
	public FocusSubtreeAffect(TreeNode node) {
		this.node = node;
	}

	@Override
	public void affect(Session session) {
		Tree tree = (Tree)session.getGraph();
		tree.setNewRoot(node);
	}

}
