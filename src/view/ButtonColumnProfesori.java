package view;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import model.BazaProfesora;
import model.Profesor;

public class ButtonColumnProfesori implements TableCellRenderer {
	private JTable table = null;
	private MouseEventReposter reporter = null;
	private JComponent editor;

	public ButtonColumnProfesori(JComponent editor) {
		this.editor = editor;
		this.editor.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int col) {
		if (table != null && this.table != table) {
			this.table = table;
			final JTableHeader header = table.getTableHeader();
			if (header != null) {
				this.editor.setForeground(header.getForeground());
				this.editor.setBackground(header.getBackground());
				this.editor.setFont(header.getFont());
				reporter = new MouseEventReposter(header, col, this.editor);
				header.addMouseListener((MouseListener) reporter);
			}
		}

		if (reporter != null)
			reporter.setColumn(col);

		return this.editor;
	}

	static public class MouseEventReposter extends MouseAdapter {

		private Component dispatchComponent;
		private JTableHeader header;
		private int column = -1;
		private Component editor;

		public MouseEventReposter(JTableHeader header, int column, Component editor) {
			this.header = header;
			this.column = column;
			this.editor = editor;
		}

		public void setColumn(int column) {
			this.column = column;
		}

		private void setDispatchComponent(MouseEvent e) {
			int col = header.getTable().columnAtPoint(e.getPoint());
			if (col != column || col == -1)
				return;

			Point p = e.getPoint();
			Point p2 = SwingUtilities.convertPoint(header, p, editor);
			dispatchComponent = SwingUtilities.getDeepestComponentAt(editor, p2.x, p2.y);
		}

		private boolean repostEvent(MouseEvent e) {
			if (dispatchComponent == null) {
				return false;
			}
			MouseEvent e2 = SwingUtilities.convertMouseEvent(header, e, dispatchComponent);
			dispatchComponent.dispatchEvent(e2);
			return true;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (header.getResizingColumn() == null) {
				Point p = e.getPoint();

				int col = header.getTable().columnAtPoint(p);
				if (col != column || col == -1)
					return;

				int index = header.getColumnModel().getColumnIndexAtX(p.x);
				if (index == -1)
					return;

				editor.setBounds(header.getHeaderRect(index));
				header.add(editor);
				editor.validate();
				setDispatchComponent(e);
				repostEvent(e);

				switch (column) {
				case 0:
					if (!BazaProfesora.getInstance().isImeAsc()) {
						Collections.sort(BazaProfesora.getInstance().getProfesori(), new Comparator<Profesor>() {

							@Override
							public int compare(Profesor o1, Profesor o2) {
								return o1.getIme().compareTo(o2.getIme());
							}

						});
						BazaProfesora.getInstance().setImeAsc(true);
					} else {
						Collections.sort(BazaProfesora.getInstance().getProfesori(), new Comparator<Profesor>() {

							@Override
							public int compare(Profesor o1, Profesor o2) {
								return o2.getIme().compareTo(o1.getIme());
							}

						});
						BazaProfesora.getInstance().setImeAsc(false);
					}
					break;
				case 1:
					if (!BazaProfesora.getInstance().isPrezimeAsc()) {
						Collections.sort(BazaProfesora.getInstance().getProfesori(), new Comparator<Profesor>() {

							@Override
							public int compare(Profesor o1, Profesor o2) {
								return o1.getPrezime().compareTo(o2.getPrezime());
							}

						});
						BazaProfesora.getInstance().setPrezimeAsc(true);
					} else {
						Collections.sort(BazaProfesora.getInstance().getProfesori(), new Comparator<Profesor>() {

							@Override
							public int compare(Profesor o1, Profesor o2) {
								return o2.getPrezime().compareTo(o1.getPrezime());
							}

						});
						BazaProfesora.getInstance().setPrezimeAsc(false);
					}
					break;
				case 2:
					if (!BazaProfesora.getInstance().isTitulaAsc()) {
						Collections.sort(BazaProfesora.getInstance().getProfesori(), new Comparator<Profesor>() {

							@Override
							public int compare(Profesor o1, Profesor o2) {
								return o1.getTitula().compareTo(o2.getTitula());
							}

						});
						BazaProfesora.getInstance().setTitulaAsc(true);
					} else {
						Collections.sort(BazaProfesora.getInstance().getProfesori(), new Comparator<Profesor>() {

							@Override
							public int compare(Profesor o1, Profesor o2) {
								return o2.getTitula().compareTo(o1.getTitula());
							}

						});
						BazaProfesora.getInstance().setTitulaAsc(false);
					}
					break;
				case 3:
					if (!BazaProfesora.getInstance().isZvanjeAsc()) {
						Collections.sort(BazaProfesora.getInstance().getProfesori(), new Comparator<Profesor>() {

							@Override
							public int compare(Profesor o1, Profesor o2) {
								return o1.getZvanje().compareTo(o2.getZvanje());
							}

						});
						BazaProfesora.getInstance().setZvanjeAsc(true);
					} else {
						Collections.sort(BazaProfesora.getInstance().getProfesori(), new Comparator<Profesor>() {

							@Override
							public int compare(Profesor o1, Profesor o2) {
								return o2.getZvanje().compareTo(o1.getZvanje());
							}

						});
						BazaProfesora.getInstance().setZvanjeAsc(false);
					}
					break;
				}

				BazaProfesora.getInstance().setFiltriraniProfesori(new ArrayList<Profesor>());
				try {
					MainWindow.getInstance().azurirajPrikaz("SORT", -1);
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			repostEvent(e);
			dispatchComponent = null;
			header.remove(editor);
		}
	}
}
