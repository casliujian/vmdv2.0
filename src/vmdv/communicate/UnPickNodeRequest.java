package vmdv.communicate;

import org.json.JSONObject;

public class UnPickNodeRequest extends RequestMsg {
	private String sid;
	private String nid;
	
	public UnPickNodeRequest(String sid, String nid) {
		this.sid = sid;
		this.nid = nid;
	}

	@Override
	public JSONObject to_json() {
		JSONObject jo = new JSONObject();
		jo.accumulate("type", "unpick_node");
		jo.accumulate("session_id", this.sid);
		jo.accumulate("node_id", this.nid);
		return jo;
	}

}
