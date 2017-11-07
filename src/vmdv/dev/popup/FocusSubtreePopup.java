package vmdv.dev.popup;

import vmdv.control.Session;
import vmdv.dev.PopupItem;
import vmdv.dev.affects.FocusSubtreeAffect;
import vmdv.model.TreeNode;

public class FocusSubtreePopup extends PopupItem {

	public FocusSubtreePopup(String l) {
		super(l);
	}

	@Override
	public void action(Session session) {
//		Set<AbstractNode> selected = session.getViewer().getSelectedNode();
//		for (AbstractNode node : selected) {
//			if (node instanceof TreeNode) {
//				session.getViewer().affect.addLast(new FocusSubtreeAffect((TreeNode) node));
//			}
//			break;
//		}
		session.getViewer().affect.addLast(new FocusSubtreeAffect((TreeNode)session.getViewer().hoverNode));
	}

}
