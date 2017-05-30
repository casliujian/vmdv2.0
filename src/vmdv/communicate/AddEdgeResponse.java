package vmdv.communicate;

import vmdv.control.Session;
import vmdv.dev.AssistAffect;
import vmdv.dev.affects.AddEdgeAffect;
import vmdv.model.Graph;

public class AddEdgeResponse extends ResponseMsg {
	private String from;
	private String to;
	
	public AddEdgeResponse(String from, String to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public AssistAffect parse() {
		return new AddEdgeAffect(from, to);
	}


}
