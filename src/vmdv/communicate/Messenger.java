package vmdv.communicate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import org.json.JSONArray;

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
					RequestMsg rmsg = sessions.get(sid).takeRequestMsg();
					if (rmsg != null) {
						output.println(rmsg.to_json().toString());
						output.flush();
					}
				}
				;
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
						JSONArray json = new JSONArray(str);
						ResponseMsg rmsg = to_msg(json);
						if (rmsg != null) {
							sessions.get(sid).addResponseMsg(rmsg);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		private ResponseMsg to_msg(JSONArray json) {

			return null;
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
