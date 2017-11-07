package vmdv.dev.popup;

import vmdv.control.Session;
import vmdv.dev.PopupItem;
import vmdv.dev.affects.HighlightSubtreeAffect;

public class HighlightSubtreePopup extends PopupItem {

	public HighlightSubtreePopup(String l) {
		super(l);
	}

	@Override
	public void action(Session session) {
		session.getViewer().affect.addLast(new HighlightSubtreeAffect(session.getViewer().hoverNode));
	}

}
