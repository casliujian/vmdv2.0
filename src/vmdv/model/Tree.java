package vmdv.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import vmdv.paint.graph.RGBColor;
import vmdv.paint.treeViewer.ColorConfig;

public class Tree extends Graph {
	private int height = 0;
	public HashMap<String, ArrayList<Node>> depthMap = new HashMap<String, ArrayList<Node>>();


	public void removeSubtree(String id) {
		Node n = getNode(id);
		LinkedList<Node> tmp_nodes = new LinkedList<Node>();
		tmp_nodes.addLast(n);
		while(!tmp_nodes.isEmpty()) {
			Node tmp_n = tmp_nodes.removeFirst();
			for(Edge e: struct.get(tmp_n).getPostEdges()) {
				tmp_nodes.addLast(e.getTo());
			}
			this.removeNode(tmp_n);
		}
	}

	@Override
	public void addEdge(String fromId, String toId) {
		super.addEdge(fromId, toId);
		Node fn = getNode(fromId);
		Node tn = getNode(toId);
		int fnDepth = fn.getDepth();
		tn.setDepth(fnDepth+1);
		if(height < fnDepth + 1) {
			height = fnDepth + 1;
		}
		updateDepthColor();
	}



	public void updateHeight() {
		Node r = this.start;
		if(start == null) {
			return;
		}
		r.setDepth(0);
		height = 0;
		LinkedList<Node> heighted = new LinkedList<Node>();
		heighted.addLast(r);
		while(!heighted.isEmpty()) {
			Node tn = heighted.removeFirst();
			int fromDepth = tn.getDepth();
			ArrayList<Node> depthList = depthMap.get(String.valueOf(fromDepth));
			if(depthList == null) {
				ArrayList<Node> tmp_depthList = new ArrayList<Node>();
				tmp_depthList.add(tn);
				depthMap.put(String.valueOf(fromDepth), tmp_depthList);
			} else {
				depthList.add(tn);
			}
			
			for(Edge te : struct.get(tn).getPostEdges()) {
				
				te.getTo().setDepth(fromDepth+1);
				if(height < fromDepth+1) {
					height = fromDepth+1;
				}
				heighted.addLast(te.getTo());
			}
		}
	}
	
	public void updateDepthColor() {
		RGBColor fromColor = ColorConfig.fromColor;
		RGBColor toColor = ColorConfig.toColor;
		
		float dr = toColor.getRed() - fromColor.getRed();
		float dg = toColor.getGreen() - fromColor.getGreen();
		float db = toColor.getBlue() - fromColor.getBlue();
		
		for(Node tn : this.struct.keySet()) {
			tn.setOriColor(new RGBColor(fromColor.getRed()+dr*tn.getDepth()/height, fromColor.getGreen()+dg*tn.getDepth()/height, fromColor.getBlue()+db*tn.getDepth()/height));
		}
		if(start != null) {
			start.setOriColor(ColorConfig.rootColor);
		}
	}
}
