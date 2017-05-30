package vmdv.communicate;

import vmdv.dev.AssistAffect;
import vmdv.dev.affects.AddNodeAffect;

public class AddNodeResponse extends ResponseMsg {
	private String nid;
	private String label;
	public AddNodeResponse(String nid, String label) {
		this.nid = nid;
		this.label = label;
	}
	@Override
	public AssistAffect parse() {
		
		assert(nid != null && label != null);
		
		AddNodeAffect ana = new AddNodeAffect(nid, label);
		return ana;
	}
}
