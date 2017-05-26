package vmdv.model;

public class NodeState {
	private RGBColor color;
	private double size;
	
	public NodeState(RGBColor color, double size) {
		this.color = color;
		this.size = size;
	}
	
	public RGBColor getColor() {
		return color;
	}
	public void setColor(RGBColor color) {
		this.color = color;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	
	
}
