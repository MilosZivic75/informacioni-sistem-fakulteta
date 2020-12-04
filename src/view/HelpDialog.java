package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HelpDialog extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HelpDialog(JFrame parent, String title, boolean mod) {
		super(parent, title, mod);
		
		Dimension dim = parent.getSize();
		setSize(2*dim.width/3, 2*dim.height/3);
		setLocationRelativeTo(parent);
		
		add(new JLabel("<html><pre> To see more about project go to Help -> About (CTRL + A).\n\n"
				+ " New = CTRL + N\n"
				+ " Close = CTRL + C\n"
				+ " Edit = CTRL + E\n"
				+ " Delete = CTRL + D\n"
				+ " Help = CTRL + H\n"
				+ " About = CTRL + A</pre></html>"), BorderLayout.NORTH);
	}
}
