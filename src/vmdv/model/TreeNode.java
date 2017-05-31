package vmdv.model;

public class TreeNode extends AbstractNode {
	public boolean showSubtree = true;
	public int depth;
	public int proofState;
	
	public boolean showChildLabel = false;
	public String childLabel = "";

	public TreeNode(String id, String label) {
		super(id, label);
	}

//	public int getDepth() {
//		return depth;
//	}
//
//	public void setDepth(int depth) {
//		this.depth = depth;
//	}

}
