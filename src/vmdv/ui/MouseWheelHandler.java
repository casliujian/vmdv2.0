package vmdv.ui;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseWheelHandler implements MouseWheelListener {
	private Viewer viewer;
	
	public void setViewer(Viewer viewer) {
		this.viewer = viewer;
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub

	}
}
