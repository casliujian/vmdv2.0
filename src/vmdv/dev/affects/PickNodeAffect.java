package vmdv.dev.affects;

import vmdv.config.ColorConfig;
import vmdv.control.Session;
import vmdv.dev.AssistAffect;
import vmdv.model.Edge;
import vmdv.model.Graph;
import vmdv.model.Node;
import vmdv.model.RGBColor;

public class PickNodeAffect extends AssistAffect {
	private Node node;
	
	public PickNodeAffect(Node node) {
		this.node = node;
	}
	

	@Override
	public void affect(Session session) {
		Graph graph = session.getGraph();
		node.setPicked(true);
		RGBColor color = ColorConfig.red;
		node.setColor(color.getRed(), color.getGreen(), color.getBlue());
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<h3>"+node.getLabel()+"</h3><br>");
		int index = 1;
		for(Edge e : graph.getPostEdges(node)) {
			Node tn = e.getTo();
			tn.showChildLabel = true;
			tn.setColor(color);
			tn.childLabel = "     Child "+index;
			sb.append("<h3>Child "+index+": "+tn.getLabel()+"</h3><br>");
			index++;
		}
		sb.append("</html>");

	}

}
