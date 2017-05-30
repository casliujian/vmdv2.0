package vmdv.dev.affects;

import vmdv.control.Session;
import vmdv.dev.AssistAffect;
import vmdv.model.Graph;

public class AddNodeAffect extends AssistAffect {
	
	private String nid;
	private String label;
	
	public AddNodeAffect(String nid, String label) {
		this.nid = nid;
		this.label = label;
	}

	@Override
	public void affect(Session session) {
		Graph graph = session.getGraph();
		graph.addNode(nid, label);
	}

}
