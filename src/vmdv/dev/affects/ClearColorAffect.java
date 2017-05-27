package vmdv.dev.affects;

import java.util.LinkedList;

import vmdv.control.Session;
import vmdv.dev.AssistAffect;
import vmdv.model.Edge;
import vmdv.model.Node;
import vmdv.ui.Viewer;

public class ClearColorAffect extends AssistAffect {

	public ClearColorAffect(Session session) {
		super(session);
	}

	@Override
	public void affect() {
		Viewer viewer = session.getViewer();
		LinkedList<Node> looked = new LinkedList<Node>();
		looked.addLast(viewer.getGraph().getStart());
		while(!looked.isEmpty()) {
			Node n = looked.removeFirst();
			n.showChildLabel = false;
			n.clearColor();
//			if(n.isPicked()) {
				n.setPicked(false);
				tv.operateListener.unHightLightState(n.getId());
//			}
			for(Edge e : viewer.getGraph().getPostEdges(n)) {
				Node tn = e.getTo();
				looked.addLast(tn);
			}
		}
	}

}
