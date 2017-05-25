package vmdv.communicate;

import vmdv.communicate.ComConfig.EnumMsg;
import vmdv.paint.graph.Tree;

public abstract class Msg {
	protected EnumMsg type;
	protected Tree tree;
	
	public Msg (Tree tree) {
		this.tree = tree;
	}
	
	public abstract void parse();
}
