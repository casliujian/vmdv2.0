package vmdv.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Graph {
	protected HashMap<Node, Edges> struct = new HashMap<Node, Edges>();
	protected Node start;
	
	public Set<Node> getNodes() {
		return struct.keySet();
	}
	
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
		if(struct.isEmpty()) {
			this.setStart(n);
		}
		struct.put(n, es);
	}
	
	public Set<Edge> getPostEdges(String id) {
		Node n = getNode(id);
		assert(n != null);
		return struct.get(n).getPostEdges();
	}
	
	public Set<Edge> getPostEdges(Node n) {
		assert(n != null);
		return struct.get(n).getPostEdges();
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
	
	public void removeNode(Node n) {
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
	
	public HashSet<Node> getPreNodes(String id) {
		Node n = getNode(id);
		assert(n != null);
		HashSet<Edge> preEdges = struct.get(n).getPreEdges();
		HashSet<Node> pns = new HashSet<Node>();
		for (Edge e: preEdges) {
			pns.add(e.getFrom());
		}
		return pns;
	}
	
	public HashSet<Node> getPostNodes(String id) {
		Node n = getNode(id);
		assert(n != null);
		HashSet<Edge> postEdges = struct.get(n).getPostEdges();
		HashSet<Node> pns = new HashSet<Node>();
		for (Edge e: postEdges) {
			pns.add(e.getTo());
		}
		return pns;
	}	
	
	public Node getNearestNode(double x, double y, double z) {
		double dist = 0.25;
		Node rn = null;
		
		for (Node n: struct.keySet()) {
			double tmp_dist = Math.sqrt(Math.pow(n.getX()-x, 2)+Math.pow(n.getY()-y, 2)+Math.pow(n.getZ()-z, 2));
			if(tmp_dist <= dist) {
				rn = n;
				break;
			}
		}
		
		return rn;		
	}
	
	public void clearColor() {
		for (Node n: struct.keySet()) {
			n.clearColor();
		}
	}
	
	
}
