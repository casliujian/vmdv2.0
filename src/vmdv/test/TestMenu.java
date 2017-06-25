package vmdv.test;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

public class TestMenu extends JFrame {
	JMenuBar menubar = new JMenuBar();
	JMenu m1 = new JMenu("M1");
	
	public TestMenu() {
		this.setJMenuBar(menubar);
		menubar.add(new JMenu("menu1"));
		menubar.add(new JMenu("menu2"));
		menubar.add(m1);
		m1.add(new JMenuItem("menuitem"));
		
		JToolBar jtb = new JToolBar();
		jtb.add(new JLabel("label 1"));
		this.getContentPane().add(jtb, BorderLayout.SOUTH);
		
		this.setSize(300, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public static void main(String[] args) {
		TestMenu tm = new TestMenu();
	}

}
