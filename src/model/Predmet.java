package model;

import java.util.ArrayList;

public class Predmet {

	public enum Semestar {
		letnji, zimski
	};

	private String sifra;
	private String naziv;
	private Semestar semestar;
	private int godStudija;
	private Profesor profesor;
	private int espb;
	private ArrayList<Student> suPolozili;
	private ArrayList<Student> nisuPolozili;

	public Predmet(String sifra, String naziv, Semestar semestar, int godStudija, int espb) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.semestar = semestar;
		this.godStudija = godStudija;
		this.espb = espb;
		
		this.suPolozili = new ArrayList<Student>();
		this.nisuPolozili = new ArrayList<Student>();
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Semestar getSemestar() {
		return semestar;
	}

	public void setSemestar(Semestar semestar) {
		this.semestar = semestar;
	}

	public int getGodStudija() {
		return godStudija;
	}

	public void setGodStudija(int godStudija) {
		this.godStudija = godStudija;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public int getEspb() {
		return espb;
	}

	public void setEspb(int espb) {
		this.espb = espb;
	}

	public ArrayList<Student> getSuPolozili() {
		return suPolozili;
	}

	public void setSuPolozili(ArrayList<Student> suPolozili) {
		this.suPolozili = suPolozili;
	}

	public ArrayList<Student> getNisuPolozili() {
		return nisuPolozili;
	}

	public void setNisuPolozili(ArrayList<Student> nisuPolozili) {
		this.nisuPolozili = nisuPolozili;
	}

}
