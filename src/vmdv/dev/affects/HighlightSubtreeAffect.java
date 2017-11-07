package vmdv.dev.affects;

import java.util.LinkedList;

import vmdv.control.Session;
import vmdv.dev.AssistAffect;
import vmdv.model.AbstractGraph;
import vmdv.model.AbstractNode;
import vmdv.model.Tree;

public class HighlightSubtreeAffect extends AssistAffect {
	private AbstractNode node;
	
	public HighlightSubtreeAffect(AbstractNode an) {
		this.node = an;
	}

	@Override
	public void affect(Session session) {
		if (node == null) 
			return;
		AbstractGraph graph = session.getGraph();
		if(graph instanceof Tree) {
//			Tree tree = (Tree) graph;
			LinkedList<AbstractNode> looked = new LinkedList<AbstractNode>();
			looked.addLast(node);
			while(!looked.isEmpty()) {
				AbstractNode current = looked.removeFirst();
				session.getViewer().affect.addLast(new HighlightNodeAffect(current));
				for(AbstractNode next : graph.getSuccessors(current)) {
					looked.addLast(next);
				}
			}
		}
	}

}
