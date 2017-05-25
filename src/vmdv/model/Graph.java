package vmdv.model;

import java.util.HashMap;

public class Graph {
	protected HashMap<Node, Edges> struct = new HashMap<Node, Edges>();
	protected Node start;
	
	public Node getStart() {
		return start;
	}
	
	public void setStart(Node n) {
		start = n;
	}
	
	public Node getNode(String id) {
		for (Node n: struct.keySet()) {
			if (n.id.equals(id)) {
				return n;
			}
		}
		return null;
	}
	
	public void addNode(String id, String label) {
		Node tmp_n = getNode(id);
		assert(tmp_n == null);
		Node n = new Node(id, label);
		Edges es = new Edges();
		struct.put(n, es);
	}
	
	public void addEdge(String fromId, String toId) {
		Node fn = this.getNode(fromId);
		Node tn = this.getNode(toId);
		assert(fn != null && tn != null);
		Edge e = new Edge(fn, tn);
		Edges fes = struct.get(fn);
		fes.addPostEdge(e);
		Edges tes = struct.get(tn);
		tes.addPreEdge(e);
	}
	
	public void removeNode(String id) {
		Node n = getNode(id);
		if (n == null) {
			return;
		}
		Edges es = struct.get(n);
		for(Edge e: es.getPostEdges()) {
			Node tn = e.getTo();
			struct.get(tn).removePreEdge(e);
		}
		for(Edge e: es.getPreEdges()) {
			Node fn = e.getFrom();
			struct.get(fn).removePostEdge(e);
		}
		struct.remove(n);
	}
	
	
}
