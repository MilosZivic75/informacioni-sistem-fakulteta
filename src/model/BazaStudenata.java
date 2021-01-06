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
	private ArrayList<Student> filtriraniStudenti;
	private ArrayList<String> kolone;
	private String filter;
	private boolean indeksAsc;
	private boolean imeAsc;
	private boolean prezimeAsc;
	private boolean godStudAsc;
	private boolean statusAsc;
	private boolean prosekAsc;

	private BazaStudenata() {
		this.studenti = new ArrayList<Student>();
		this.filtriraniStudenti = new ArrayList<Student>();

		this.kolone = new ArrayList<String>();
		this.kolone.add("Indeks");
		this.kolone.add("Ime");
		this.kolone.add("Prezime");
		this.kolone.add("Godina studija");
		this.kolone.add("Status");
		this.kolone.add("Prosek");

		this.filter = "";
		
		this.indeksAsc = false;
		this.imeAsc = false;
		this.prezimeAsc = false;
		this.godStudAsc = false;
		this.statusAsc = false;
		this.prosekAsc = false;
	}

	public ArrayList<Student> getStudenti() {
		return this.studenti;
	}

	public void setStudenti(ArrayList<Student> studenti) {
		this.studenti = studenti;
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

	public int getRowCount() {
		int br = 0;
		for (Student s : studenti) {
			String[] filteri = filter.split(" ");
			if (filteri.length == 1) {
				if (s.getPrezime().toLowerCase().contains(filteri[0].toLowerCase())) {
					br++;
					filtriraniStudenti.add(s);
				}
			} else if (filteri.length == 2) {
				if (s.getPrezime().toLowerCase().contains(filteri[0].toLowerCase())
						&& s.getIme().toLowerCase().contains(filteri[1].toLowerCase())) {
					br++;
					filtriraniStudenti.add(s);
				}
			} else if (filteri.length == 3) {
				if (s.getPrezime().toLowerCase().contains(filteri[0].toLowerCase())
						&& s.getIme().toLowerCase().contains(filteri[1].toLowerCase())
						&& s.getIndeks().toLowerCase().contains(filteri[2].toLowerCase())) {
					br++;
					filtriraniStudenti.add(s);
				}
			}

		}
		return br;
	}

	public String getValueAt(int row, int column) {
		Student student = this.filtriraniStudenti.get(row);
		String[] filteri = filter.split(" ");
		if (filteri.length == 1) {
			if (student.getPrezime().toLowerCase().contains(filteri[0].toLowerCase())) {
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
					return String.format("%.2f", student.getProsOcena());
				default:
					return null;
				}
			}
		} else if (filteri.length == 2) {
			if (student.getPrezime().toLowerCase().contains(filteri[0].toLowerCase())
					&& student.getIme().toLowerCase().contains(filteri[1].toLowerCase())) {
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
					return String.format("%.2f", student.getProsOcena());
				default:
					return null;
				}
			}
		} else if (filteri.length == 3) {
			if (student.getPrezime().toLowerCase().contains(filteri[0].toLowerCase())
					&& student.getIme().toLowerCase().contains(filteri[1].toLowerCase())
					&& student.getIndeks().toLowerCase().contains(filteri[2].toLowerCase())) {
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
					return String.format("%.2f", student.getProsOcena());
				default:
					return null;
				}

			}

		}

		return null;
	}

	public void dodajStudenta(String prezime, String ime, Date datRodjenja, String adresa, String brTelefona,
			String email, String indeks, int godUpisa, int trenGod, Status status) {
		this.studenti.add(
				new Student(prezime, ime, datRodjenja, adresa, brTelefona, email, indeks, godUpisa, trenGod, status));
	}

	public void izmeniStudenta(String prezime, String ime, Date datRodjenja, String adresa, String brTelefona,
			String email, String indeks, int godUpisa, int trenGod, Status status, int row) {
		this.filtriraniStudenti.get(row).setPrezime(prezime);
		this.filtriraniStudenti.get(row).setIme(ime);
		this.filtriraniStudenti.get(row).setDatRodjenja(datRodjenja);
		this.filtriraniStudenti.get(row).setAdresa(adresa);
		this.filtriraniStudenti.get(row).setBrTelefona(brTelefona);
		this.filtriraniStudenti.get(row).setEmail(email);
		this.filtriraniStudenti.get(row).setIndeks(indeks);
		this.filtriraniStudenti.get(row).setGodUpisa(godUpisa);
		this.filtriraniStudenti.get(row).setTrenGod(trenGod);
		this.filtriraniStudenti.get(row).setStatus(status);
	}

	public void obrisiStudenta(int row) {
		Student s = this.filtriraniStudenti.remove(row);
		this.studenti.remove(s);
	}

	public void setFilter(String s) {
		this.filter = s;
	}

	public ArrayList<Student> getFiltriraniStudenti() {
		return filtriraniStudenti;
	}

	public void setFiltriraniStudenti(ArrayList<Student> filtriraniStudenti) {
		this.filtriraniStudenti = filtriraniStudenti;
	}

	public boolean isIndeksAsc() {
		return indeksAsc;
	}

	public void setIndeksAsc(boolean indeksAsc) {
		this.indeksAsc = indeksAsc;
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

	public boolean isGodStudAsc() {
		return godStudAsc;
	}

	public void setGodStudAsc(boolean godStudAsc) {
		this.godStudAsc = godStudAsc;
	}

	public boolean isStatusAsc() {
		return statusAsc;
	}

	public void setStatusAsc(boolean statusAsc) {
		this.statusAsc = statusAsc;
	}

	public boolean isProsekAsc() {
		return prosekAsc;
	}

	public void setProsekAsc(boolean prosekAsc) {
		this.prosekAsc = prosekAsc;
	}

	

}
