package vmdv.control;

import java.util.LinkedList;
import java.util.Set;

import vmdv.model.AbstractGraph;
import vmdv.model.AbstractNode;
import vmdv.model.XYZ;

public class ForceAtlas2Layout extends GraphLayout {
	private float ka = 5f;
	private float kr = 60f;
//	private int maxRunningThread = 1;
//	private int currentRunningThread = 0;
	private boolean threadRunning = false;

	@Override
	public void updateLayout(AbstractGraph graph) {
		if (threadRunning) {
			return;
		} else {
			LayoutThread lt = new LayoutThread(graph);
			new Thread(lt).start();
		}
	}
	
	private class LayoutThread implements Runnable {
		private AbstractGraph graph;
		
		public LayoutThread(AbstractGraph graph) {
			this.graph = graph;
		}
		@Override
		public void run() {
			ForceAtlas2Layout.this.threadRunning = true;
			if (graph.getStart() == null) {
				return;
			}
			// resistance force
			Set<AbstractNode> tns = graph.getNodes();
			LinkedList<AbstractNode> tmp_tns = new LinkedList<AbstractNode>();
			// synchronized(tns) {
			for (AbstractNode tn : tns) {
				tmp_tns.addFirst(tn);
			}
			// }
			for (AbstractNode sn : tns) {
				for (AbstractNode n : tns) {
					if (!sn.id.equals(n.id)) {
						XYZ snp = sn.xyz;
						XYZ np = n.xyz;
						double d2 = Math.pow(snp.getX() - np.getX(), 2) + Math.pow(snp.getY() - np.getY(), 2)
								+ Math.pow(snp.getZ() - np.getZ(), 2);
						if (d2 == 0) {
							d2 = 1;
						}
						double dx = snp.getX() - np.getX();
						double dy = snp.getY() - np.getY();
						double dz = snp.getZ() - np.getZ();
						dx = dx == 0 ? 1 : dx;
						dy = dy == 0 ? 1 : dy;
						dz = dz == 0 ? 1 : dz;
						sn.addForce(kr * (dx) * Math.pow(d2, -1.5), kr * (dy) * Math.pow(d2, -1.5),
								kr * (dz) * Math.pow(d2, -1.5));
					}
				}
			}
			// attraction force
			for (AbstractNode sn : tmp_tns) {
				for (AbstractNode dn: graph.getSuccessors(sn)) {
					XYZ snp = sn.xyz;
					XYZ dnp = dn.xyz;
					sn.addForce(ka * (dnp.getX() - snp.getX()), ka * (dnp.getY() - snp.getY()),
							ka * (dnp.getZ() - snp.getZ()));
					dn.addForce(ka * (snp.getX() - dnp.getX()), ka * (snp.getY() - dnp.getY()),
							ka * (snp.getZ() - dnp.getZ()));
				}
			}

			// set move
			for (AbstractNode sn : tmp_tns) {
				XYZ force = sn.force;
				XYZ p = sn.xyz;
				sn.setXYZ(p.getX() + force.getX() * 0.02, p.getY() + force.getY() * 0.02, p.getZ() + force.getZ() * 0.02);
				sn.setForce(0, 0, 0);
			}
			graph.getStart().setXYZ(0, 0, 0);
			ForceAtlas2Layout.this.threadRunning = false;
		}
		
	}

}
