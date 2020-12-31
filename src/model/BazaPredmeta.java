package model;

import java.util.ArrayList;

import model.Predmet.Semestar;

public class BazaPredmeta {
	private static BazaPredmeta instance = null;

	public static BazaPredmeta getInstance() {
		if (instance == null)
			instance = new BazaPredmeta();
		return instance;
	}

	private ArrayList<Predmet> predmeti;
	private ArrayList<String> kolone;

	private BazaPredmeta() {
		this.predmeti = new ArrayList<Predmet>();

		this.kolone = new ArrayList<String>();
		this.kolone.add("Šifra predmeta");
		this.kolone.add("Naziv predmeta");
		this.kolone.add("Broj ESPB bodova");
		this.kolone.add("Godina na kojoj se predmet izvodi");
		this.kolone.add("Semestar u kome se predmet izvodi");
	}

	public ArrayList<Predmet> getPredmeti() {
		return this.predmeti;
	}

	public int getColumnCount() {
		return 5;
	}

	public String getColumnName(int idx) {
		return this.kolone.get(idx);
	}

	public Predmet getRow(int rowIdx) {
		return this.predmeti.get(rowIdx);
	}

	public String getValueAt(int row, int column) {
		Predmet predmet = this.predmeti.get(row);
		switch (column) {
		case 0:
			return predmet.getSifra();
		case 1:
			return predmet.getNaziv();
		case 2:
			return Integer.toString(predmet.getEspb());
		case 3:
			return Integer.toString(predmet.getGodStudija());
		case 4:
			return predmet.getSemestar().toString();
		default:
			return null;
		}
	}

	public void dodajPredmet(String sifra, String naziv, Semestar semestar, int godStudija, int espb) {
		this.predmeti.add(
				new Predmet(sifra, naziv, semestar, godStudija, espb));
	}

}
