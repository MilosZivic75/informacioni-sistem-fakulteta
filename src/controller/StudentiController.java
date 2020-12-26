package controller;

import javax.swing.JFrame;

import view.DodajStudenta;
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

	public void dodajStudenta(JFrame parent) {
		DodajStudenta dodaj = new DodajStudenta(parent, "Dodavanje studenta", true);
		dodaj.setVisible(true);
		
		MainWindow.getInstance().azurirajPrikaz("DODAT", -1);
	}
}
