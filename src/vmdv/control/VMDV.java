package vmdv.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import vmdv.communicate.Messenger;
import vmdv.model.Graph;
import vmdv.model.Tree;
import vmdv.ui.GLEventHandler;
import vmdv.ui.KeyHandler;
import vmdv.ui.MouseHandler;
import vmdv.ui.MouseMotionHandler;
import vmdv.ui.MouseWheelHandler;
import vmdv.ui.Viewer;

public class VMDV {
	private Messenger messenger;
	private HashMap<String, Session> sessions = new HashMap<String, Session>();
	
	public VMDV() {
		try {
			ServerSocket ss = new ServerSocket(2222);
			Socket s = ss.accept();
			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter output = new PrintWriter(s.getOutputStream());
			this.messenger = new Messenger(input, output, sessions);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void startMessenger() {
		messenger.startSendingReceiving();
	}
	
	public void addSession(String vid, Session session) {
		sessions.put(vid, session);
		session.getViewer().setVisible(true);
//		session.setVisible(true);
	}
	
	public void removeSession(String vid) {
//		sessions.get(vid).dispose();
		sessions.remove(vid);
	}

	public static void main(String[] args) {
		VMDV vmdv = new VMDV();
		Viewer treeViewer = new Viewer("Proof Tree");
		GLEventHandler glh = new GLEventHandler();
		treeViewer.registerGLHandler(glh);
		KeyHandler kh = new KeyHandler();
		treeViewer.registerKeyHandler(kh);
		MouseHandler mh = new MouseHandler();
		treeViewer.registerMouseHandler(mh);
		MouseMotionHandler mmh = new MouseMotionHandler();
		treeViewer.registerMouseMotionHandler(mmh);
		MouseWheelHandler mwh = new MouseWheelHandler();
		treeViewer.registerMouseWheelHandler(mwh);
		Tree tree = new Tree();
//		treeViewer.setGraph(tree);
		Session session = new Session("s1", tree, treeViewer, new ForceAtlas2Layout());
//		Messenger messenger = new Messenger(null, null);
		vmdv.addSession("s1", session);
		session.start();
		
		Viewer stateViewer = new Viewer("State Graph");
		stateViewer.registerGLHandler(new GLEventHandler());
		stateViewer.registerKeyHandler(new KeyHandler());
		stateViewer.registerMouseHandler(new MouseHandler());
		stateViewer.registerMouseMotionHandler(new MouseMotionHandler());
		stateViewer.registerMouseWheelHandler(new MouseWheelHandler());
		Graph graph = new Graph();
		Session session2 = new Session("s2", graph, stateViewer, new ForceAtlas2Layout());
		vmdv.addSession("s2", session2);
		session2.start();
		
		vmdv.startMessenger();
		
	}

}
