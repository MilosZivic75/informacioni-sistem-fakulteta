package controller;

import javax.swing.JFrame;

import view.DodajProfesora;
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
}
