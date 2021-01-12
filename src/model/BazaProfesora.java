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
	private ArrayList<Profesor> filtriraniProfesori;
	private String filter;
	private boolean imeAsc;
	private boolean prezimeAsc;
	private boolean titulaAsc;
	private boolean zvanjeAsc;

	private BazaProfesora() {
		this.profesori = new ArrayList<Profesor>();
		this.filtriraniProfesori = new ArrayList<Profesor>();

		this.kolone = new ArrayList<String>();
		this.kolone.add("Ime");
		this.kolone.add("Prezime");
		this.kolone.add("Titula");
		this.kolone.add("Zvanje");

		this.filter = "";
		this.imeAsc = false;
		this.prezimeAsc = false;
		this.titulaAsc = false;
		this.zvanjeAsc = false;

	}

	public ArrayList<Profesor> getProfesori() {
		return this.profesori;
	}

	public void setProfesori(ArrayList<Profesor> profesori) {
		this.profesori = profesori;
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

	public int getRowCount() {
		int br = 0;
		for (Profesor p : profesori) {
			String[] filteri = filter.split(" ");
			if (filteri.length == 1) {
				if (p.getPrezime().toLowerCase().contains(filteri[0].toLowerCase())) {
					br++;
					filtriraniProfesori.add(p);
				}
			} else if (filteri.length == 2) {
				if (p.getPrezime().toLowerCase().contains(filteri[0].toLowerCase())
						&& p.getIme().toLowerCase().contains(filteri[1].toLowerCase())) {
					br++;
					filtriraniProfesori.add(p);
				}
			}

		}
		return br;
	}

	public String getValueAt(int row, int column) {

		Profesor profesor = this.filtriraniProfesori.get(row);
		String[] filteri = filter.split(" ");
		if (filteri.length == 1) {
			if (profesor.getPrezime().toLowerCase().contains(filteri[0].toLowerCase())) {
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
		} else if (filteri.length == 2) {
			if (profesor.getPrezime().toLowerCase().contains(filteri[0].toLowerCase())
					&& profesor.getIme().toLowerCase().contains(filteri[1].toLowerCase())) {
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
		}

		return null;
	}

	public void dodajProfesora(String prezime, String ime, Date datRodjenja, String adresa, String brTelefona,
			String email, String adresaKanc, String brLicne, Titule titula, Zvanja zvanje) {
		this.profesori.add(new Profesor(prezime, ime, datRodjenja, adresa, brTelefona, email, adresaKanc, brLicne,
				titula, zvanje));
	}

	public void izmeniProfesora(String prezime, String ime, Date datRodjenja, String adresa, String brTelefona,
			String email, String adresaKanc, String brLicne, Titule titula, Zvanja zvanje, int row) {
		this.filtriraniProfesori.get(row).setPrezime(prezime);
		this.filtriraniProfesori.get(row).setIme(ime);
		this.filtriraniProfesori.get(row).setDatRodjenja(datRodjenja);
		this.filtriraniProfesori.get(row).setAdresa(adresa);
		this.filtriraniProfesori.get(row).setBrTelefona(brTelefona);
		this.filtriraniProfesori.get(row).setEmail(email);
		this.filtriraniProfesori.get(row).setAdresaKanc(adresaKanc);
		this.filtriraniProfesori.get(row).setBrLicne(brLicne);
		this.filtriraniProfesori.get(row).setTitula(titula);
		this.filtriraniProfesori.get(row).setZvanje(zvanje);
	}

	public void obrisiProfesora(int row) {
		Profesor p = this.filtriraniProfesori.remove(row);
		this.profesori.remove(p);
	}

	public ArrayList<Profesor> getFiltriraniProfesori() {
		return filtriraniProfesori;
	}

	public void setFiltriraniProfesori(ArrayList<Profesor> filtriraniProfesori) {
		this.filtriraniProfesori = filtriraniProfesori;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public boolean isImeAsc() {
		return imeAsc;
	}

	public void setImeAsc(boolean imeAsc) {
		this.imeAsc = imeAsc;
	}

	public boolean isPrezimeAsc() {
		return prezimeAsc;
	}

	public void setPrezimeAsc(boolean prezimeAsc) {
		this.prezimeAsc = prezimeAsc;
	}

	public boolean isTitulaAsc() {
		return titulaAsc;
	}

	public void setTitulaAsc(boolean titulaAsc) {
		this.titulaAsc = titulaAsc;
	}

	public boolean isZvanjeAsc() {
		return zvanjeAsc;
	}

	public void setZvanjeAsc(boolean zvanjeAsc) {
		this.zvanjeAsc = zvanjeAsc;
	}

}
