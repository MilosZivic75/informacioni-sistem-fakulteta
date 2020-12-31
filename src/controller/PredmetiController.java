package controller;

import javax.swing.JFrame;

import view.DodajPredmet;
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
}
