package vmdv.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import vmdv.model.Node;
import vmdv.paint.treeViewer.PickNodeAffect;
import vmdv.paint.treeViewer.UnPickNodeAffect;

public class MouseHandler implements MouseListener {

	private Viewer viewer;
	
	public void setViewer(Viewer viewer) {
		this.viewer = viewer;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		viewer.mousePosition = e.getPoint();
		Node selected = viewer.hoverNode;
		if (e.isMetaDown()) {//right click the mouse
			viewer.popupShowed = true;
			if(selected != null) {//showing node popup menu
				viewer.nodePop.show(e.getComponent(), e.getX(), e.getY());
			} else {//showing background popup menu
				viewer.backPop.show(e.getComponent(), e.getX(), e.getY());
			}
		} else {//left click the mouse
			viewer.popupShowed = false;
			if(selected != null) {
				if(selected.isPicked()) {
					viewer.affect.addLast(new UnPickNodeAffect(selected));
				} else {
					viewer.affect.addLast(new PickNodeAffect(viewer.getGraph(), selected));
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}



}
