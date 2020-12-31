package model;

import java.util.ArrayList;
import java.util.Date;

public class Profesor {
	public enum Titule {
		BSc, MSc, PhD, mr, dr, profDr
	};

	public enum Zvanja {
		saradnikNastava, asistent, asistentDr, docent, vanrProf, redProf, profEmeritus
	};

	private String prezime;
	private String ime;
	private Date datRodjenja;
	private String adresa;
	private String brTelefona;
	private String email;
	private String adresaKanc;
	private String brLicne;
	private Titule titula;
	private Zvanja zvanje;
	private ArrayList<Predmet> predmeti;

	public Profesor(String prezime, String ime, Date datRodjenja, String adresa, String brTelefona, String email,
			String adresaKanc, String brLicne, Titule titula, Zvanja zvanje) {
		super();
		this.prezime = prezime;
		this.ime = ime;
		this.datRodjenja = datRodjenja;
		this.adresa = adresa;
		this.brTelefona = brTelefona;
		this.email = email;
		this.adresaKanc = adresaKanc;
		this.brLicne = brLicne;
		this.titula = titula;
		this.zvanje = zvanje;
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

	public String getAdresaKanc() {
		return adresaKanc;
	}

	public void setAdresaKanc(String adresaKanc) {
		this.adresaKanc = adresaKanc;
	}

	public String getBrLicne() {
		return brLicne;
	}

	public void setBrLicne(String brLicne) {
		this.brLicne = brLicne;
	}

	public Titule getTitula() {
		return titula;
	}

	public void setTitula(Titule titula) {
		this.titula = titula;
	}

	public Zvanja getZvanje() {
		return zvanje;
	}

	public void setZvanje(Zvanja zvanje) {
		this.zvanje = zvanje;
	}

	public ArrayList<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(ArrayList<Predmet> predmeti) {
		this.predmeti = predmeti;
	}
	
	public String getTitulaString(Titule titula) {
		switch(titula) {
		case BSc:
			return "BSc";
		case MSc:
			return "MSc";
		case PhD:
			return "PhD";
		case dr:
			return "Dr";
		case mr:
			return "Mr";
		case profDr:
			return "prof. Dr";
		default:
			return null;
		}
	}
	
	public String getZvanjeString(Zvanja zvanje) {
		switch(zvanje) {
		case asistent:
			return "Asistent";
		case asistentDr:
			return "Asistent sa doktoratom";
		case docent:
			return "Docent";
		case profEmeritus:
			return "Profesor emeritus";
		case redProf:
			return "Redovni profesor";
		case saradnikNastava:
			return "Saradnik u nastavi";
		case vanrProf:
			return "Vanredni profesor";
		default:
			return null;
		
		}
	}

}
