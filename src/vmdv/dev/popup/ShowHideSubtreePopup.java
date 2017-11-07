package vmdv.dev.popup;

import vmdv.control.Session;
import vmdv.dev.PopupItem;
import vmdv.dev.affects.ShowHideSubtreeAffect;
import vmdv.model.AbstractGraph;
import vmdv.model.AbstractNode;
import vmdv.model.Tree;

public class ShowHideSubtreePopup extends PopupItem {

	public ShowHideSubtreePopup(String l) {
		super(l);
	}

	@Override
	public void action(Session session) {
		AbstractGraph graph = session.getGraph();
		if (graph instanceof Tree) {
//			for (AbstractNode node : session.getViewer().getSelectedNode()) {
//				session.getViewer().affect.addLast(new ShowHideSubtreeAffect(node));
//			}
			session.getViewer().affect.addLast(new ShowHideSubtreeAffect(session.getViewer().hoverNode));
		}
	}

}
