package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

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

	public void dodajPredmet(JFrame parent) throws FileNotFoundException, ClassNotFoundException, IOException {
		DodajPredmet dodaj = new DodajPredmet(parent, "Dodavanje predmeta", true);
		dodaj.setVisible(true);
		BazaPredmeta.getInstance().setFiltriraniPredmeti(new ArrayList<Predmet>());

		MainWindow.getInstance().azurirajPrikaz("DODAT", -1);
	}

	public void izmeniPredmet(JFrame parent) throws FileNotFoundException, ClassNotFoundException, IOException {
		int row = MainWindow.getInstance().getPredmetRow();
		if (row != -1) {
			IzmeniPredmet izmeni = new IzmeniPredmet(parent, "Izmena predmeta", true, row);
			izmeni.setVisible(true);
			BazaPredmeta.getInstance().setFiltriraniPredmeti(new ArrayList<Predmet>());

			MainWindow.getInstance().azurirajPrikaz("IZMENJEN", -1);
		} else {
			JOptionPane.showMessageDialog(parent, "Morate selektovati predmet");
		}

	}

	public void obrisiPredmet(JFrame parent) throws FileNotFoundException, ClassNotFoundException, IOException {
		int row = MainWindow.getInstance().getPredmetRow();
		if (row != -1) {
			String[] options = new String[2];
			options[0] = new String("Da");
			options[1] = new String("Ne");
			int option = JOptionPane.showOptionDialog(parent, "Da li ste sigurni da želite da obrišete predmet?",
					"Brisanje predmeta", 0, JOptionPane.QUESTION_MESSAGE, null, options, null);
			if (option == JOptionPane.YES_OPTION) {
				for (Student s : BazaStudenata.getInstance().getStudenti()) {
					Iterator<Predmet> it = s.getNepolIspiti().iterator();
					while (it.hasNext()) {
						Predmet p = it.next();
						if (p.getSifra().equals(BazaPredmeta.getInstance().getFiltriraniPredmeti().get(row).getSifra()))
							it.remove();
					}
					Iterator<Ocena> it2 = s.getPolIspiti().iterator();
					while (it2.hasNext()) {
						Ocena o = it2.next();
						if (o.getPredmet().getSifra()
								.equals(BazaPredmeta.getInstance().getFiltriraniPredmeti().get(row).getSifra())) {
							it2.remove();
							int zbirOcena = 0;
							for (Ocena o1 : s.getPolIspiti()) {
								zbirOcena += o1.getOcena();
							}
							s.setProsOcena(
									s.getPolIspiti().size() != 0 ? (double) zbirOcena / s.getPolIspiti().size() : 0);
						}
							
					}
				}

				for (Profesor prof : BazaProfesora.getInstance().getProfesori()) {
					for (Predmet pred : prof.getPredmeti()) {
						if (pred.getSifra().equals(BazaPredmeta.getInstance().getFiltriraniPredmeti().get(row).getSifra()))
							prof.getPredmeti().remove(pred);
					}
				}

				BazaPredmeta.getInstance().obrisiPredmet(row);
				BazaPredmeta.getInstance().setFiltriraniPredmeti(new ArrayList<Predmet>());
				MainWindow.getInstance().azurirajPrikaz("OBRISAN", -1);
			}

		} else {
			JOptionPane.showMessageDialog(parent, "Morate selektovati predmet");
		}
	}

}
