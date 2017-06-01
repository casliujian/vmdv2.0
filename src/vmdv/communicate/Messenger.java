package vmdv.communicate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import org.json.JSONObject;

import vmdv.config.GraphConfig;
import vmdv.config.GraphConfig.GraphType;
import vmdv.control.Session;

public class Messenger {
	private BufferedReader input;
	private PrintWriter output;
	private HashMap<String, Session> sessions;

	public Messenger(BufferedReader br, PrintWriter pw, HashMap<String, Session> sessions) {
		this.input = br;
		this.output = pw;
		this.sessions = sessions;
	}

	public void startSendingReceiving() {
		new Thread(new SendingThread()).start();
		new Thread(new ReceivingThread()).start();
	}

	private class SendingThread implements Runnable {
		private boolean running = true;

		@Override
		public void run() {
			while (running) {
				for (String sid : sessions.keySet()) {
					JSONObject json = sessions.get(sid).takeRequestMsg();
					if (json != null) {
						output.println(json.toString());
						output.flush();
					}
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
					running = false;
				}
			}
		}

	}

	private class ReceivingThread implements Runnable {
		private boolean running = true;

		@Override
		public void run() {
			while (running) {
				// for (String sid : sessions.keySet()) {
				try {
					String str = input.readLine();
					JSONObject json = new JSONObject(str);
					String sid = json.getString("session_id");
					Session session = sessions.get(sid);
					// ResponseMsg rmsg = to_msg(session, json);
					// if (rmsg != null) {
					session.parseResponseMsg(json);
					// }
				} catch (IOException e) {
					e.printStackTrace();
				}
				// }
			}
		}

		// private ResponseMsg to_msg(Session session, JSONObject json) {
		// if (json != null) {
		// switch (json.get("type").toString()) {
		// case "add_node":
		// switch(session.getGraph().getType().ordinal()) {
		// case GraphConfig.GraphType.TREE.ordinal():
		// AddTreeNodeResponse
		// break;
		// }
		// if(json.isNull("node_state")) {
		// AddNodeResponse anr = new AddNodeResponse(json.getString("node_id"),
		// json.getString("node_label"));
		// }
		// AddNodeResponse anr = new AddNodeResponse();
		// break;
		// }
		// }
		//
		// return null;
		// }

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
