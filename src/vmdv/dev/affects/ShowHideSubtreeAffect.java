package vmdv.dev.affects;

import vmdv.control.Session;
import vmdv.dev.AssistAffect;
import vmdv.model.AbstractGraph;
import vmdv.model.AbstractNode;
import vmdv.model.TreeNode;

public class ShowHideSubtreeAffect extends AssistAffect {

	private AbstractNode node;
	
	public ShowHideSubtreeAffect(AbstractNode an) {
		this.node = an;
	}
	
	@Override
	public void affect(Session session) {
		// AbstractGraph graph = session.getGraph();
		// for(AbstractNode node : graph.getNodes()) {
		((TreeNode) node).subtreeVisible = !((TreeNode) node).subtreeVisible;
		// }
	}

}
