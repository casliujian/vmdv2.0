package vmdv.communicate;

import vmdv.control.Session;
import vmdv.model.Graph;

public class AddNodeResponse extends ResponseMsg {
	private String id;
	private String label;
	public AddNodeResponse(String id, String label) {
		this.id = id;
		this.label = label;
	}

	@Override
	public void parse(Session session) {
		Graph graph = session.getGraph();
		graph.addNode(id, label);
	}
}
