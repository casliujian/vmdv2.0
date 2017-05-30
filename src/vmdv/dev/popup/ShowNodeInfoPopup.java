package vmdv.dev.popup;

import vmdv.control.Session;
import vmdv.dev.PopupItem;
import vmdv.dev.affects.ShowNodeInfoAffect;
import vmdv.model.Node;

public class ShowNodeInfoPopup extends PopupItem {

	public ShowNodeInfoPopup(String l) {
		super(l);
	}

	@Override
	public void action(Session session) {
		Node n = session.getViewer().getSelectedNode();
//		if(n == null) {
//			return;
//		}
		session.getViewer().affect.addLast(new ShowNodeInfoAffect(n));
	}

}
