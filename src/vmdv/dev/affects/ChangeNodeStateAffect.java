package vmdv.dev.affects;

import vmdv.control.Session;
import vmdv.dev.AssistAffect;
import vmdv.model.AbstractNode;
import vmdv.model.Tree;
import vmdv.model.TreeNode;

public class ChangeNodeStateAffect extends AssistAffect {
	private String nodeId;
	private String newState;
	
	public ChangeNodeStateAffect(String nodeId, String newState) {
		this.nodeId = nodeId;
		this.newState = newState;
	}

	@Override
	public void affect(Session session) {
		Tree tree = (Tree) session.getViewer().getGraph();
		TreeNode tn = (TreeNode) tree.getNode(nodeId);
		tn.nodeState = newState;
//		for(AbstractNode an : tree.getPredecessors(tn)) {
//			tree.updateNodeState((TreeNode) an);
//		}
		tree.updateDepthColor();
	}

}
