package vmdv.model;

import vmdv.config.ColorConfig;

public abstract class AbstractNode {
	protected Property defaultProperty = new Property();
	
	public RGBColor oriColor = new RGBColor(0,0,0);
	public double oriSize;

	public String id;
	public double size;
	public XYZ xyz;
	public RGBColor color;
	public boolean visible;
	public String label;

	public boolean showLabel;
	public boolean picked;
	public XYZ force;
	
	private boolean stay = false;
	private RGBColor colorBeforeStay = null;
	private double sizeBeforeStay = 0;
	
	// public int depth;

	public AbstractNode(String id, String label) {
		this.defaultProperty.set("label", label);
		
		this.xyz = new XYZ(0,0,0);
		this.color = new RGBColor(0, 0, 0);
		this.visible = true;
		this.id = id;
		this.oriSize = 0.2;
		this.size = 0.2;
		this.label = label;
		this.showLabel = false;
		this.picked = false;
		this.force = new XYZ(0, 0, 0);
	}
	
	public boolean isStayingOn() {
		return stay;
	}
	
	public void stayOn() {
		if(!stay) {
			this.colorBeforeStay = this.color;
			this.sizeBeforeStay = this.size;
			if (!this.picked) {
				this.color = ColorConfig.hoverColor;
			}
			this.size = this.size*1.2;
			this.stay = true;
		}
	}
	
	public void outOfStay() {
		System.out.println("stay out");
		if(!picked) {
			this.color = this.colorBeforeStay;
		}
		this.size = this.sizeBeforeStay;
		this.stay = false;
	}
	
	public void clearColor() {		
		this.color = new RGBColor(oriColor.getRed(), oriColor.getGreen(), oriColor.getBlue());
	}
	
	
	public void resetSize() {
		this.size = oriSize;
	}

	public void addForce(double d, double e, double f) {
		force.addXyz(d, e, f);
	}
	
	public void setForce(double x, double y, double z) {
//		this.defaultProperty.set("force", new XYZ(x,y,z));
		force = new XYZ(x, y, z);
	}
	

	public void setXYZ(double d, double e, double f) {
		xyz.setX(d);
		xyz.setY(e);
		xyz.setZ(f);	
	}
}
