package vmdv.dev.affects;

import vmdv.control.Session;
import vmdv.dev.AssistAffect;
import vmdv.model.Node;

public class UnPickNodeAffect extends AssistAffect {
	private Node node;

	public UnPickNodeAffect(Node node) {
		this.node = node;
	}

	@Override
	public void affect(Session session) {
		node.showChildLabel = false;
		node.setPicked(false);
		node.clearColor();

	}

}
