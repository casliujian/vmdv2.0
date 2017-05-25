package vmdv.ui;

import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;

import com.jogamp.newt.event.KeyListener;
import com.jogamp.newt.event.MouseListener;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;

public class ViewFrame extends JFrame {
	private GLJPanel renderPanel;
	private JPopupMenu backPop, nodePop;
	private GLEventListener glistener;
	private KeyListener keyListener;
	private MouseListener mouseListener;
	private MouseWheelListener mwListener;
	private MouseMotionListener mmListener;
	
	
	public ViewFrame(String title) {
		this.setTitle(title);
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities glcaps = new GLCapabilities(glp);
		glcaps.setDoubleBuffered(true);
		this.renderPanel = new GLJPanel(glcaps);
//		glistener.
//		this.renderPanel.addm
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
