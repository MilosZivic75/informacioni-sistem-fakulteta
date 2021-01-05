package view;

import javax.swing.table.AbstractTableModel;

import model.BazaStudenata;

public class AbstractTableModelStudent extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int getColumnCount() {
		return BazaStudenata.getInstance().getColumnCount();
	}

	@Override
	public int getRowCount() {
		return BazaStudenata.getInstance().getRowCount();
	}

	@Override
	public String getColumnName(int column) {
		return BazaStudenata.getInstance().getColumnName(column);
	}

	@Override
	public Object getValueAt(int row, int column) {
		return BazaStudenata.getInstance().getValueAt(row, column);
	}

}
