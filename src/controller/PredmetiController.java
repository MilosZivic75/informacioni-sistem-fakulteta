package controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.BazaPredmeta;
import model.BazaProfesora;
import model.BazaStudenata;
import model.Ocena;
import model.Predmet;
import model.Profesor;
import model.Student;
import view.DodajPredmet;
import view.IzmeniPredmet;
import view.MainWindow;

public class PredmetiController {
	private static PredmetiController instance = null;

	public static PredmetiController getInstance() {
		if (instance == null) {
			instance = new PredmetiController();
		}
		return instance;
	}

	private PredmetiController() {
	}

	public void dodajPredmet(JFrame parent) {
		DodajPredmet dodaj = new DodajPredmet(parent, "Dodavanje predmeta", true);
		dodaj.setVisible(true);

		MainWindow.getInstance().azurirajPrikaz("DODAT", -1);
	}

	public void izmeniPredmet(JFrame parent) {
		int row = MainWindow.getInstance().getPredmetRow();
		if (row != -1) {
			IzmeniPredmet izmeni = new IzmeniPredmet(parent, "Izmena predmeta", true, row);
			izmeni.setVisible(true);

			MainWindow.getInstance().azurirajPrikaz("IZMENJEN", -1);
		} else {
			JOptionPane.showMessageDialog(parent, "Morate selektovati predmet");
		}

	}

	public void obrisiPredmet(JFrame parent) {
		int row = MainWindow.getInstance().getPredmetRow();
		if (row != -1) {
			String[] options = new String[2];
			options[0] = new String("Da");
			options[1] = new String("Ne");
			int option = JOptionPane.showOptionDialog(parent,
					"Da li ste sigurni da želite da obrišete predmet?", "Brisanje predmeta", 0,
					JOptionPane.QUESTION_MESSAGE, null, options, null);
			if (option == JOptionPane.YES_OPTION) {
				for (Student s : BazaStudenata.getInstance().getStudenti()) {
					for (Predmet p : s.getNepolIspiti()) {
						if (p.getSifra().equals(BazaPredmeta.getInstance().getPredmeti().get(row).getSifra()))
							s.getNepolIspiti().remove(p);
					}

					for (Ocena o : s.getPolIspiti()) {
						if (o.getPredmet().getSifra()
								.equals(BazaPredmeta.getInstance().getPredmeti().get(row).getSifra()))
							s.getPolIspiti().remove(o);
					}
				}

				for (Profesor prof : BazaProfesora.getInstance().getProfesori()) {
					for (Predmet pred : prof.getPredmeti()) {
						if (pred.getSifra().equals(BazaPredmeta.getInstance().getPredmeti().get(row).getSifra()))
							prof.getPredmeti().remove(pred);
					}
				}

				BazaPredmeta.getInstance().obrisiPredmet(row);
				MainWindow.getInstance().azurirajPrikaz("OBRISAN", -1);
			}

		} else {
			JOptionPane.showMessageDialog(parent, "Morate selektovati predmet");
		}
	}

}
