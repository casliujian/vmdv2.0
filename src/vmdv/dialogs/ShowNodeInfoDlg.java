package vmdv.dialogs;

import javax.swing.JDialog;
import javax.swing.JFrame;

import vmdv.model.Node;

public class ShowNodeInfoDlg extends JDialog {
	
	public ShowNodeInfoDlg(JFrame frame, boolean modal) {
		super(frame, modal);
		this.setTitle("Node Info");
	}
	
	public void showNodeInfo(Node node) {
		this.setTitle("Node "+node.getId());
		
	}

	public static void main(String[] args) {
		try {
			ShowNodeInfoDlg dialog = new ShowNodeInfoDlg(null, false);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
}
