package model;

import java.util.ArrayList;
import java.util.Date;

import model.Student.Status;

public class BazaStudenata {
	private static BazaStudenata instance = null;

	public static BazaStudenata getInstance() {
		if (instance == null)
			instance = new BazaStudenata();
		return instance;
	}

	private ArrayList<Student> studenti;
	private ArrayList<String> kolone;

	private BazaStudenata() {
		this.studenti = new ArrayList<Student>();

		this.kolone = new ArrayList<String>();
		this.kolone.add("Indeks");
		this.kolone.add("Ime");
		this.kolone.add("Prezime");
		this.kolone.add("Godina studija");
		this.kolone.add("Status");
		this.kolone.add("Prosek");
	}

	public ArrayList<Student> getStudenti() {
		return this.studenti;
	}

	public int getColumnCount() {
		return 6;
	}

	public String getColumnName(int idx) {
		return this.kolone.get(idx);
	}

	public Student getRow(int rowIdx) {
		return this.studenti.get(rowIdx);
	}

	public String getValueAt(int row, int column) {
		Student student = this.studenti.get(row);
		switch (column) {
		case 0:
			return student.getIndeks();
		case 1:
			return student.getIme();
		case 2:
			return student.getPrezime();
		case 3:
			return Integer.toString(student.getTrenGod());
		case 4:
			return student.getStatus().toString();
		case 5:
			return Double.toString(student.getProsOcena());
		default:
			return null;
		}
	}

	public void dodajStudenta(String prezime, String ime, Date datRodjenja, String adresa, String brTelefona,
			String email, String indeks, int godUpisa, int trenGod, Status status) {
		this.studenti.add(
				new Student(prezime, ime, datRodjenja, adresa, brTelefona, email, indeks, godUpisa, trenGod, status));
	}

}
