package view;

import javax.swing.table.AbstractTableModel;

import model.Predmet;
import model.Student;

public class AbstractTableModelNepolozeni extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Student student;

	public AbstractTableModelNepolozeni(Student student) {
		this.student = student;
	}

	@Override
	public int getRowCount() {
		return student.getNepolIspiti().size();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Šifra predmeta";
		case 1:
			return "Naziv predmeta";
		case 2:
			return "ESPB";
		case 3:
			return "Godina studija";
		case 4:
			return "Semestar";
		}
		return null;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Predmet predmet = student.getNepolIspiti().get(rowIndex);

		switch (columnIndex) {
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

}
