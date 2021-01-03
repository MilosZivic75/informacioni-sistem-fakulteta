package view;

import java.text.SimpleDateFormat;

import javax.swing.table.AbstractTableModel;

import model.Ocena;
import model.Student;

public class AbstractTableModelPolozeni extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Student student;

	public AbstractTableModelPolozeni(Student student) {
		this.student = student;
	}

	@Override
	public int getRowCount() {
		return student.getPolIspiti().size();
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
			return "Ocena";
		case 4:
			return "Datum";
		}
		return null;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Ocena ocena = student.getPolIspiti().get(rowIndex);

		switch (columnIndex) {
		case 0:
			return ocena.getPredmet().getSifra();
		case 1:
			return ocena.getPredmet().getNaziv();
		case 2:
			return Integer.toString(ocena.getPredmet().getEspb());
		case 3:
			return Integer.toString(ocena.getOcena());
		case 4:
			return new SimpleDateFormat("dd/MM/yyyy").format(ocena.getDatumPol());
		}
		return null;
	}

}
