package vmdv.communicate;

import org.json.JSONArray;

public class UnPickNodeRequest extends RequestMsg {
	private String sid;
	private String nid;
	
	public UnPickNodeRequest(String sid, String nid) {
		this.sid = sid;
		this.nid = nid;
	}

	@Override
	public JSONArray to_json() {
		// TODO Auto-generated method stub
		return null;
	}

}
