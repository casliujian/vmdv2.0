package vmdv.dev.affects;

import javax.swing.JOptionPane;

import vmdv.control.Session;
import vmdv.dev.AssistAffect;
import vmdv.model.Node;

public class ShowNodeInfoAffect extends AssistAffect {
	private Node node;

	public ShowNodeInfoAffect(Node node) {
		this.node = node;
	}

	@Override
	public void affect(Session session) {
		if (node == null) {
			JOptionPane.showMessageDialog(session.getViewer(), "No node selected!", "Warning",
					JOptionPane.WARNING_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(session.getViewer(), node.getLabel(), "Node " + node.getId(),
					JOptionPane.PLAIN_MESSAGE);
		}
	}
}
