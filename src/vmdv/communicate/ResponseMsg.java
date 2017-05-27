package vmdv.communicate;

import vmdv.control.Session;

public abstract class ResponseMsg {
	public abstract void parse(Session session);
}
