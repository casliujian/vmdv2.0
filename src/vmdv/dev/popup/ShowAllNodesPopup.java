package vmdv.dev.popup;

import vmdv.control.Session;
import vmdv.dev.PopupItem;
import vmdv.dev.affects.ShowAllNodesAffect;

public class ShowAllNodesPopup extends PopupItem {

	public ShowAllNodesPopup(String l) {
		super(l);
	}

	@Override
	public void action(Session session) {
		session.getViewer().affect.addLast(new ShowAllNodesAffect());
	}

}
