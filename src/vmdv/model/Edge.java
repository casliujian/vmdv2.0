package vmdv.model;

public class Edge {
	private Node pn;
	private Node cn;
	private RGBColor color;
	private float size;
	
	public Edge(Node pn, Node cn) {
		this.pn = pn;
		this.cn = cn;
		this.color = new RGBColor(0,0,0);
		this.size = 1.0f;
	}
	
	public void setColor(float red, float green, float blue) {
		color.setColor(red, green, blue);
	}
	
	public RGBColor getColor() {
		return color;
	}
	
	public void clearColor() {
		color = new RGBColor();
	}
	
	public void setSize(float s) {
		size = s;
	}
	
	public float getSize() {
		return size;
	}
	
	public Node getFrom() {
		return pn;
	}
	
	public Node getTo() {
		return cn;
	}
}
