package vmdv.dev.affects;

import vmdv.communicate.HighlightNodeRequest;
import vmdv.config.ColorConfig;
import vmdv.control.Session;
import vmdv.dev.AssistAffect;
import vmdv.model.AbstractGraph;
import vmdv.model.AbstractNode;
import vmdv.model.DiGraph;
import vmdv.model.RGBColor;
import vmdv.model.Tree;
import vmdv.ui.Viewer;

public class HighlightNodeAffect extends AssistAffect {
	private AbstractNode node;
	
	public HighlightNodeAffect(AbstractNode node) {
		this.node = node;
	}
	

	@Override
	public void affect(Session session) {
		AbstractGraph graph = session.getGraph();
		if(graph instanceof Tree) {
			Tree tree = (Tree)graph;
			node.picked = true;
			RGBColor color = ColorConfig.red;
			node.color = new RGBColor(color.getRed(), color.getGreen(), color.getBlue());
			session.addRequestMsg(new HighlightNodeRequest(node.id));
		} else if(graph instanceof DiGraph) {
			node.picked = true;
			RGBColor color = ColorConfig.red;
			node.color = new RGBColor(color.getRed(), color.getGreen(), color.getBlue());
		}
		Viewer viewer = session.getViewer();
		viewer.setStatusStr(node.label);
	}

}
