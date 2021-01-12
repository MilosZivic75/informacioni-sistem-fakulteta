package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.BazaPredmeta;
import model.BazaProfesora;
import model.Predmet;
import model.Profesor;
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

	public void dodajProfesora(JFrame parent) throws FileNotFoundException, ClassNotFoundException, IOException {
		DodajProfesora dodaj = new DodajProfesora(parent, "Dodavanje profesora", true);
		dodaj.setVisible(true);
		BazaProfesora.getInstance().setFiltriraniProfesori(new ArrayList<Profesor>());

		MainWindow.getInstance().azurirajPrikaz("DODAT", -1);
	}

	public void izmeniProfesora(JFrame parent) throws FileNotFoundException, ClassNotFoundException, IOException {
		int row = MainWindow.getInstance().getProfesorRow();
		if (row != -1) {
			IzmeniProfesora izmeni = new IzmeniProfesora(parent, "Izmena profesora", true, row);
			izmeni.setVisible(true);
			BazaProfesora.getInstance().setFiltriraniProfesori(new ArrayList<Profesor>());

			MainWindow.getInstance().azurirajPrikaz("IZMENJEN", -1);
		} else {
			JOptionPane.showMessageDialog(parent, "Morate selektovati profesora");
		}
	}

	public void obrisiProfesora(JFrame parent) throws FileNotFoundException, ClassNotFoundException, IOException {
		int row = MainWindow.getInstance().getProfesorRow();
		if (row != -1) {
			String[] options = new String[2];
			options[0] = new String("Da");
			options[1] = new String("Ne");
			int option = JOptionPane.showOptionDialog(parent,
					"Da li ste sigurni da želite da obrišete profesora?", "Brisanje profesora", 0,
					JOptionPane.QUESTION_MESSAGE, null, options, null);
			if (option == JOptionPane.YES_OPTION) {
				for (Predmet pred : BazaPredmeta.getInstance().getPredmeti()) {
					if (pred.getProfesor() != null && pred.getProfesor().getBrLicne()
							.equals(BazaProfesora.getInstance().getFiltriraniProfesori().get(row).getBrLicne()))
						pred.setProfesor(null);
				}

				BazaProfesora.getInstance().obrisiProfesora(row);
				BazaProfesora.getInstance().setFiltriraniProfesori(new ArrayList<Profesor>());
				MainWindow.getInstance().azurirajPrikaz("OBRISAN", -1);
			}

		} else {
			JOptionPane.showMessageDialog(parent, "Morate selektovati profesora");
		}
	}
}
