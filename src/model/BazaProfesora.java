package model;

import java.util.ArrayList;
import java.util.Date;

import model.Profesor.Titule;
import model.Profesor.Zvanja;

public class BazaProfesora {

	private static BazaProfesora instance = null;

	public static BazaProfesora getInstance() {
		if (instance == null)
			instance = new BazaProfesora();
		return instance;
	}

	private ArrayList<Profesor> profesori;
	private ArrayList<String> kolone;

	private BazaProfesora() {
		this.profesori = new ArrayList<Profesor>();

		this.kolone = new ArrayList<String>();
		this.kolone.add("Ime");
		this.kolone.add("Prezime");
		this.kolone.add("Titula");
		this.kolone.add("Zvanje");
	}

	public ArrayList<Profesor> getProfesori() {
		return this.profesori;
	}

	public int getColumnCount() {
		return 4;
	}

	public String getColumnName(int idx) {
		return this.kolone.get(idx);
	}

	public Profesor getRow(int rowIdx) {
		return this.profesori.get(rowIdx);
	}

	public String getValueAt(int row, int column) {
		Profesor profesor = this.profesori.get(row);
		switch (column) {
		case 0:
			return profesor.getIme();
		case 1:
			return profesor.getPrezime();
		case 2:
			return profesor.getTitulaString(profesor.getTitula());
		case 3:
			return profesor.getZvanjeString(profesor.getZvanje());
		default:
			return null;
		}
	}

	public void dodajProfesora(String prezime, String ime, Date datRodjenja, String adresa, String brTelefona,
			String email, String adresaKanc, String brLicne, Titule titula, Zvanja zvanje) {
		this.profesori.add(new Profesor(prezime, ime, datRodjenja, adresa, brTelefona, email, adresaKanc, brLicne,
				titula, zvanje));
	}
}
