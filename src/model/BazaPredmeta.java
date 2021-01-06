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
	private ArrayList<Predmet> filtriraniPredmeti;
	private String filter;
	private boolean sifraAsc;
	private boolean nazivAsc;
	private boolean espbAsc;
	private boolean godinaAsc;
	private boolean semestarAsc;

	private BazaPredmeta() {
		this.predmeti = new ArrayList<Predmet>();
		this.filtriraniPredmeti = new ArrayList<Predmet>();

		this.kolone = new ArrayList<String>();
		this.kolone.add("Šifra predmeta");
		this.kolone.add("Naziv predmeta");
		this.kolone.add("Broj ESPB bodova");
		this.kolone.add("Godina izvođenja predmeta");
		this.kolone.add("Semestar izvođenja predmeta");

		this.filter = "";
		
		this.sifraAsc = false;
		this.nazivAsc = false;
		this.espbAsc = false;
		this.godinaAsc = false;
		this.semestarAsc = false;
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

	public int getRowCount() {
		int br = 0;
		for (Predmet p : predmeti) {
			if (p.getNaziv().toLowerCase().contains(filter.toLowerCase())) {
				br++;
				filtriraniPredmeti.add(p);
			}

		}
		return br;
	}

	public String getValueAt(int row, int column) {
		Predmet predmet = this.filtriraniPredmeti.get(row);
		if (predmet.getNaziv().toLowerCase().contains(filter.toLowerCase())) {
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
		return null;
	}

	public void dodajPredmet(String sifra, String naziv, Semestar semestar, int godStudija, int espb) {
		this.predmeti.add(new Predmet(sifra, naziv, semestar, godStudija, espb));
	}

	public void izmeniPredmet(String sifra, String naziv, Semestar semestar, int godStudija, int espb, int row) {
		this.filtriraniPredmeti.get(row).setSifra(sifra);
		this.filtriraniPredmeti.get(row).setNaziv(naziv);
		this.filtriraniPredmeti.get(row).setSemestar(semestar);
		this.filtriraniPredmeti.get(row).setGodStudija(godStudija);
		this.filtriraniPredmeti.get(row).setEspb(espb);

	}

	public void obrisiPredmet(int row) {
		Predmet p = this.filtriraniPredmeti.remove(row);
		this.predmeti.remove(p);
	}

	public ArrayList<Predmet> getFiltriraniPredmeti() {
		return filtriraniPredmeti;
	}

	public void setFiltriraniPredmeti(ArrayList<Predmet> filtriraniPredmeti) {
		this.filtriraniPredmeti = filtriraniPredmeti;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public boolean isSifraAsc() {
		return sifraAsc;
	}

	public void setSifraAsc(boolean sifraAsc) {
		this.sifraAsc = sifraAsc;
	}

	public boolean isNazivAsc() {
		return nazivAsc;
	}

	public void setNazivAsc(boolean nazivAsc) {
		this.nazivAsc = nazivAsc;
	}

	public boolean isEspbAsc() {
		return espbAsc;
	}

	public void setEspbAsc(boolean espbAsc) {
		this.espbAsc = espbAsc;
	}

	public boolean isGodinaAsc() {
		return godinaAsc;
	}

	public void setGodinaAsc(boolean godinaAsc) {
		this.godinaAsc = godinaAsc;
	}

	public boolean isSemestarAsc() {
		return semestarAsc;
	}

	public void setSemestarAsc(boolean semestarAsc) {
		this.semestarAsc = semestarAsc;
	}
	
}
