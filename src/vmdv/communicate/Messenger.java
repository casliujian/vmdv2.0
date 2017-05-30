package vmdv.communicate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

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
				for (String sid : sessions.keySet()) {
					try {
						String str = input.readLine();
						JSONObject json = new JSONObject(str);
						ResponseMsg rmsg = to_msg(json);
						if (rmsg != null) {
							sessions.get(sid).parseResponseMsg(rmsg);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		private ResponseMsg to_msg(JSONObject json) {
			if(json != null) {
				switch(json.get("type").toString()) {
				case "add_node": 
					
					break;
				}
			}

			return null;
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
