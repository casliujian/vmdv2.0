package vmdv.dev.popup;

import vmdv.control.Session;
import vmdv.dev.PopupItem;
import vmdv.dev.affects.HighlightAncestorsAffect;

public class HighlightAncestorsPopup extends PopupItem {

	public HighlightAncestorsPopup(String l) {
		super(l);
	}

	@Override
	public void action(Session session) {
		session.getViewer().affect.addLast(new HighlightAncestorsAffect(session.getViewer().hoverNode));
	}

}
