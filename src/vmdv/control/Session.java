package vmdv.control;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import vmdv.communicate.RequestMsg;
import vmdv.communicate.ResponseMsg;
import vmdv.dev.AssistAffect;
import vmdv.model.Graph;
import vmdv.ui.Viewer;

public class Session {
	private String sid;
	private Viewer viewer;
	private Graph graph;
	private GraphLayout layout;
	private BlockingQueue<RequestMsg> requests = new LinkedBlockingQueue<RequestMsg>();
//	private Queue<ResponseMsg> responses = new ConcurrentLinkedQueue<ResponseMsg>();

	public Session(String sid, Graph graph, Viewer viewer, GraphLayout layout) {
		this.sid = sid;
		this.graph = graph;
		this.viewer = viewer;
		this.viewer.setGraph(graph);
		this.viewer.setSession(this);
		this.layout = layout;
		this.viewer.setGraphLayout(layout);
//		new Thread(new LayoutThread()).start();
	}
	
	public void start() {
		viewer.showView();
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public Viewer getViewer() {
		return viewer;
	}

	public void setViewer(Viewer viewer) {
		this.viewer = viewer;
	}

	public Graph getGraph() {
		return graph;
	}

//	private class LayoutThread implements Runnable {
//		private boolean running = true;
//
//		@Override
//		public void run() {
//			while (running) {
//				while (!responses.isEmpty()) {
//					ResponseMsg rmsg = responses.poll();
//					if (rmsg != null) {
//						AssistAffect affect = rmsg.parse();
//						if (affect != null) {
//							viewer.affect.addLast(affect);
//						}
//					}
//				}
//				layout.updateLayout(graph);
//			}
//		}
//
//	}

	public void parseResponseMsg(ResponseMsg rmsg) {
//		if (rmsg != null) {
//			responses.add(rmsg);
//		}
		AssistAffect affect = rmsg.parse();
		if (affect != null) {
			viewer.affect.addLast(affect);
		}
	}

	public RequestMsg takeRequestMsg() {
		try {
			return requests.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void addRequestMsg(RequestMsg rmsg) {
		if (rmsg != null) {
			requests.add(rmsg);
		}
	}

}
