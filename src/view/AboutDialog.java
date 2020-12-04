package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AboutDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AboutDialog(JFrame parent, String title, boolean mod) {
		super(parent, title, mod);
		
		Dimension dim = parent.getSize();
		setSize(dim.width/2, 2*dim.height/3);
		setLocationRelativeTo(parent);
		
		add(new JLabel("<html><pre> App version: 1.0.\n"
				+ " With this program you can manage complex relationships and \n"
				+ " operations in the student service.\n\n"
				+ " Authors:\n"
				+ " \tMilos Zivic - student of Applied computer science and \n"
				+ " \tinformatics, with grade point average of 9.47. Has few \n"
				+ " \tprojects in Android app development, and his last project\n"
				+ " \twas application of AI in app development.\n\n"
				+ " \tLazar Mijatovic - student of Applied computer science and \n"
				+ " \tinformatics. Double winner of the Dositej award. Two times \n"
				+ " \twinner of the republican competition in mathematics.\n"
				+ " \tHas huge passion for Ai development.\n</pre></html>"), BorderLayout.NORTH);
	}
}
