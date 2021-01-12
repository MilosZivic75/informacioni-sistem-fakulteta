package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.BazaPredmeta;
import model.BazaStudenata;
import model.Predmet;
import model.Student;
import view.DodajStudenta;
import view.IzmeniStudenta;
import view.MainWindow;

public class StudentiController {
	private static StudentiController instance = null;

	public static StudentiController getInstance() {
		if (instance == null) {
			instance = new StudentiController();
		}
		return instance;
	}

	private StudentiController() {
	}

	public void dodajStudenta(JFrame parent) throws FileNotFoundException, ClassNotFoundException, IOException {
		DodajStudenta dodaj = new DodajStudenta(parent, "Dodavanje studenta", true);
		dodaj.setVisible(true);
		BazaStudenata.getInstance().setFiltriraniStudenti(new ArrayList<Student>());

		MainWindow.getInstance().azurirajPrikaz("DODAT", -1);
	}

	public void izmeniStudenta(JFrame parent) throws FileNotFoundException, ClassNotFoundException, IOException {
		int row = MainWindow.getInstance().getStudentRow();
		if (row != -1) {
			IzmeniStudenta izmeni = new IzmeniStudenta(parent, "Izmena studenta", true, row);
			izmeni.setVisible(true);
			BazaStudenata.getInstance().setFiltriraniStudenti(new ArrayList<Student>());

			MainWindow.getInstance().azurirajPrikaz("IZMENJEN", -1);
		} else {
			JOptionPane.showMessageDialog(parent, "Morate selektovati studenta");
		}
	}

	public void obrisiStudenta(JFrame parent) throws FileNotFoundException, ClassNotFoundException, IOException {
		int row = MainWindow.getInstance().getStudentRow();
		if (row != -1) {
			String[] options = new String[2];
			options[0] = new String("Da");
			options[1] = new String("Ne");
			int option = JOptionPane.showOptionDialog(parent,
					"Da li ste sigurni da želite da obrišete studenta?", "Brisanje studenta", 0,
					JOptionPane.QUESTION_MESSAGE, null, options, null);
			if (option == JOptionPane.YES_OPTION) {
				for (Predmet p : BazaPredmeta.getInstance().getPredmeti()) {
					Iterator<Student> it = p.getNisuPolozili().iterator();
					while (it.hasNext()) {
						Student s = it.next();
						if (s.getIndeks().equals(BazaStudenata.getInstance().getFiltriraniStudenti().get(row).getIndeks())) {
							it.remove();
						}
					}
					it = p.getSuPolozili().iterator();
					while (it.hasNext()) {
						Student s = it.next();
						if (s.getIndeks().equals(BazaStudenata.getInstance().getFiltriraniStudenti().get(row).getIndeks())) {
							it.remove();
						}
					}
				}

				BazaStudenata.getInstance().obrisiStudenta(row);
				BazaStudenata.getInstance().setFiltriraniStudenti(new ArrayList<Student>());
				MainWindow.getInstance().azurirajPrikaz("OBRISAN", -1);
			}

		} else {
			JOptionPane.showMessageDialog(parent, "Morate selektovati studenta");
		}
	}
}
