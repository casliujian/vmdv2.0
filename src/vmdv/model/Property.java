package vmdv.model;

import java.util.Hashtable;

import exceptions.NoSuchProperty;

public class Property {
//	private RGBColor color;
//	private double size;
//	
//	public Property(RGBColor color, double size) {
//		this.color = color;
//		this.size = size;
//	}
//	
//	public RGBColor getColor() {
//		return color;
//	}
//	public void setColor(RGBColor color) {
//		this.color = color;
//	}
//	public double getSize() {
//		return size;
//	}
//	public void setSize(double size) {
//		this.size = size;
//	}
	
	private Hashtable<String, Object> properties = new Hashtable<String, Object>();
	
	public void set(String key, Object value) {
		if(key!=null && !key.equals("")) {
			properties.replace(key, value);
		}
	}
	
	public Object get(String key) throws NoSuchProperty {
		if (properties.containsKey(key))
			return properties.get(key);
		else
			throw new NoSuchProperty(key);
	}

}