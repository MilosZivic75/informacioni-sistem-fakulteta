package controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.BazaPredmeta;
import model.BazaProfesora;
import model.Predmet;
import view.DodajProfesora;
import view.IzmeniProfesora;
import view.MainWindow;

public class ProfesoriController {
	private static ProfesoriController instance = null;

	public static ProfesoriController getInstance() {
		if (instance == null) {
			instance = new ProfesoriController();
		}
		return instance;
	}

	private ProfesoriController() {
	}

	public void dodajProfesora(JFrame parent) {
		DodajProfesora dodaj = new DodajProfesora(parent, "Dodavanje profesora", true);
		dodaj.setVisible(true);

		MainWindow.getInstance().azurirajPrikaz("DODAT", -1);
	}

	public void izmeniProfesora(JFrame parent) {
		int row = MainWindow.getInstance().getProfesorRow();
		if (row != -1) {
			IzmeniProfesora izmeni = new IzmeniProfesora(parent, "Izmena profesora", true, row);
			izmeni.setVisible(true);

			MainWindow.getInstance().azurirajPrikaz("IZMENJEN", -1);
		} else {
			JOptionPane.showMessageDialog(parent, "Morate selektovati profesora");
		}
	}

	public void obrisiProfesora(JFrame parent) {
		int row = MainWindow.getInstance().getProfesorRow();
		if (row != -1) {
			JFrame frame = new JFrame();
			String[] options = new String[2];
			options[0] = new String("Da");
			options[1] = new String("Ne");
			int option = JOptionPane.showOptionDialog(frame.getContentPane(),
					"Da li ste sigurni da želite da obrišete profesora?", "Brisanje profesora", 0,
					JOptionPane.QUESTION_MESSAGE, null, options, null);
			if (option == JOptionPane.YES_OPTION) {
				for (Predmet pred : BazaPredmeta.getInstance().getPredmeti()) {
					if (pred.getProfesor().getBrLicne()
							.equals(BazaProfesora.getInstance().getProfesori().get(row).getBrLicne()))
						pred.setProfesor(null);
				}

				BazaProfesora.getInstance().obrisiProfesora(row);
				MainWindow.getInstance().azurirajPrikaz("OBRISAN", -1);
			}

		} else {
			JOptionPane.showMessageDialog(parent, "Morate selektovati profesora");
		}
	}
}
