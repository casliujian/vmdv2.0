package vmdv.model;

import vmdv.config.ColorConfig;

public class TreeNode extends AbstractNode {
	public boolean subtreeVisible = true;
	public int depth;
	public String nodeState;
	
	public boolean showChildLabel = false;
	public String childLabel = "";

//	public TreeNode(String id, String label) {
//		super(id, label);
//	}
	
	public TreeNode(String id, String label, String nodeState) {
		super(id, label);
		this.nodeState = nodeState;
	}
	
	public RGBColor oriColorOfState(String state) {
		switch (state) {
		case "Not_proved":
			return ColorConfig.colorNotProved;
//			break;
		case "Admitted":
			return ColorConfig.colorAdmitted;
//			break;
		case "Chosen":
			return ColorConfig.colorChosen;
//			break;
		case "To_be_chosen":
			return ColorConfig.colorToBeChosen;
//			break;
		default:
			return new RGBColor(0,0,0);
		}
	}
}
