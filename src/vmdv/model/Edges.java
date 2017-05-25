package vmdv.model;

import java.util.HashSet;

public class Edges {
	protected HashSet<Edge> preEdges = new HashSet<Edge>();
	protected HashSet<Edge> postEdges = new HashSet<Edge>();
	
	public void addPreEdge(Edge e) {
		preEdges.add(e);
	}
	public void addPostEdge(Edge e) {
		postEdges.add(e);
	}
	
	public void removePreEdge(Edge e) {
		preEdges.remove(e);
	}
	public void removePostEdge(Edge e) {
		postEdges.remove(e);
	}
	
	public HashSet<Edge> getPreEdges() {
		return preEdges;
	}
	public HashSet<Edge> getPostEdges() {
		return postEdges;
	}
}
