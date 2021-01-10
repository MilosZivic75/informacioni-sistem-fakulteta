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
		
		add(new JLabel("<html><pre> Da vidite više o projektu idite u Help -> About (CTRL + A).\n\n"
				+ " Novi entitet: CTRL + N ili ALT + F -> ALT + N\n"
				+ " Zatvaranje programa: CTRL + C ili ALT + F -> ALT + C\n"
				+ " Izmena entiteta: CTRL + E ili ALT + E -> ALT + I\n"
				+ " Brisanje entiteta: CTRL + D ili ALT + E -> ALT + D\n"
				+ " Pomoć u radu sa programom: CTRL + H ili ALT + H -> ALT + L\n"
				+ " Nešto o projektu: CTRL + A ili ALT + H -> ALT + A\n\n"
				+ "< Svi datumi u programu su formata dd/MM/yyyy\n"
				+ " Pretraga studenata se vrši tako što napišete prezime(obavezno), ime i indeks(ili njihove delove).\n"
				+ " Pretraga predmeta se vrši tako što napišete šifru predmeta(ili njegov deo)(obavezno).</pre></html>"), BorderLayout.NORTH);
	}
}
