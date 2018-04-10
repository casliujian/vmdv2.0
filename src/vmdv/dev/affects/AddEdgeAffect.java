package vmdv.dev.affects;

import vmdv.control.Session;
import vmdv.dev.AssistAffect;
import vmdv.model.AbstractGraph;
import vmdv.model.Tree;

public class AddEdgeAffect extends AssistAffect {
	private String fromId;
	private String toId;
	private String label;
	
	public AddEdgeAffect(String fromId, String toId, String label) {
		this.fromId = fromId;
		this.toId = toId;
		this.label = label;
	}

	@Override
	public void affect(Session session) {
		AbstractGraph graph = session.getGraph();
		if(graph instanceof Tree) {
			((Tree) graph).addEdge(fromId, toId, label);
		} else {
			graph.addEdge(fromId, toId);
		}
	}

}
