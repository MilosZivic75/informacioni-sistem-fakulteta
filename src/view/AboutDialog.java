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
		
		add(new JLabel("<html><pre> Verzija programa: 1.0\n"
				+ " Ovaj program vam omogućava rukovanje raznim operacijama\n"
				+ " nad studentskom službom.\n\n"
				+ " Autori:\n"
				+ " \tMiloš Živić - student Primenjenih računarskih nauka i \n"
				+ " \tinformatike, sa prosekom 9.47. Radio je na nekoliko \n"
				+ " \tprojekata u razvoju Android aplikacija, a poslednji je\n"
				+ " \tbio primena veštačke inteligencije u razvoju aplikacija.\n\n"
				+ " \tLazar Mijatović - student Primenjenih računarskih nauka i \n"
				+ " \tinformatike. Dvostruki dobitnik Dositejeve nagrade. Dva puta \n"
				+ " \tpobednik republičkog takmičenja iz matematike.\n"
				+ " \tIma veliku strast prema veštačkoj inteligenciji.\n</pre></html>"), BorderLayout.NORTH);
	}
}
