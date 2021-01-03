package model;

import java.util.ArrayList;
import java.util.Date;

public class Student {

	public enum Status {
		B, S
	};

	private String prezime;
	private String ime;
	private Date datRodjenja;
	private String adresa;
	private String brTelefona;
	private String email;
	private String indeks;
	private int godUpisa;
	private int trenGod;
	private Status status;
	private double prosOcena;
	private ArrayList<Ocena> polIspiti;
	private ArrayList<Predmet> nepolIspiti;

	public Student(String prezime, String ime, Date datRodjenja, String adresa, String brTelefona, String email,
			String indeks, int godUpisa, int trenGod, Status status) {
		super();
		this.prezime = prezime;
		this.ime = ime;
		this.datRodjenja = datRodjenja;
		this.adresa = adresa;
		this.brTelefona = brTelefona;
		this.email = email;
		this.indeks = indeks;
		this.godUpisa = godUpisa;
		this.trenGod = trenGod;
		this.status = status;
		
		this.polIspiti = new ArrayList<Ocena>();
		this.nepolIspiti = new ArrayList<Predmet>();
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public Date getDatRodjenja() {
		return datRodjenja;
	}

	public void setDatRodjenja(Date datRodjenja) {
		this.datRodjenja = datRodjenja;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getBrTelefona() {
		return brTelefona;
	}

	public void setBrTelefona(String brTelefona) {
		this.brTelefona = brTelefona;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndeks() {
		return indeks;
	}

	public void setIndeks(String indeks) {
		this.indeks = indeks;
	}

	public int getGodUpisa() {
		return godUpisa;
	}

	public void setGodUpisa(int godUpisa) {
		this.godUpisa = godUpisa;
	}

	public int getTrenGod() {
		return trenGod;
	}

	public void setTrenGod(int trenGod) {
		this.trenGod = trenGod;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public double getProsOcena() {
		return prosOcena;
	}

	public void setProsOcena(double prosOcena) {
		this.prosOcena = prosOcena;
	}

	public ArrayList<Ocena> getPolIspiti() {
		return polIspiti;
	}

	public void setPolIspiti(ArrayList<Ocena> polIspiti) {
		this.polIspiti = polIspiti;
	}

	public ArrayList<Predmet> getNepolIspiti() {
		return nepolIspiti;
	}

	public void setNepolIspiti(ArrayList<Predmet> nepolIspiti) {
		this.nepolIspiti = nepolIspiti;
	}

}
