package vmdv.model;

public class TreeNode extends AbstractNode {
	public boolean subtreeVisible = true;
	public int depth;
	public String nodeState;
	
	public boolean showChildLabel = false;
	public String childLabel = "";

	public TreeNode(String id, String label) {
		super(id, label);
	}
	
	public TreeNode(String id, String label, String nodeState) {
		super(id, label);
		this.nodeState = nodeState;
	}
}
