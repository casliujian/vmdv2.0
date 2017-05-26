package vmdv.control;

import java.util.HashMap;

import vmdv.model.Graph;
import vmdv.ui.Viewer;

public class VMDV {
	private HashMap<String, Viewer> viewers = new HashMap<String, Viewer>();
	
	public void addViewer(String vid, Viewer v, Graph graph) {
		v.setGraph(graph);
		viewers.put(vid, v);
		v.setVisible(true);
	}
	
	public void removeViewer(String vid) {
		viewers.get(vid).dispose();
		viewers.remove(vid);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
