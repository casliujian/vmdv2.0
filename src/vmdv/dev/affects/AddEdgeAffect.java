package vmdv.dev.affects;

import vmdv.control.Session;
import vmdv.dev.AssistAffect;
import vmdv.model.AbstractGraph;

public class AddEdgeAffect extends AssistAffect {
	private String fromId;
	private String toId;
	
	public AddEdgeAffect(String fromId, String toId) {
		this.fromId = fromId;
		this.toId = toId;
	}

	@Override
	public void affect(Session session) {
		AbstractGraph graph = session.getGraph();
		graph.addEdge(fromId, toId);
	}

}
