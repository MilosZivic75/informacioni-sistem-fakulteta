package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.BazaPredmeta;
import model.Ocena;
import model.Predmet;
import model.Student;

public class DodajPredmetStudentu extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DodajPredmetStudentu(JDialog parent, String title, boolean mod, Student student) {
		super(parent, title, mod);

		Dimension dim = parent.getSize();
		setSize(2 * dim.width / 3, 2 * dim.height / 3);
		setLocationRelativeTo(parent);

		JPanel panel = new JPanel(new BorderLayout());

		DefaultTableModel model = new DefaultTableModel();
		JTable tabela = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(tabela);
		model.addColumn("");

		for (Predmet pred : BazaPredmeta.getInstance().getPredmeti()) {
			if (predmetOdgovara(pred, student)) {
				model.addRow(new Object[] { pred.getSifra() + " - " + pred.getNaziv() });
			}
		}

		JButton dodaj = new JButton("Dodaj");

		dodaj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabela.getSelectedRow() != -1) {
					String s = (String) tabela.getModel().getValueAt(tabela.getSelectedRow(), 0);
					String sifra = s.split(" - ")[0];
					student.getNepolIspiti().add(getPredmetSifrom(sifra));
					dispose();
				} else {
					JOptionPane.showMessageDialog(parent, "Morate selektovati predmet");
				}

			}
		});

		JButton odustani = new JButton("Odustani");

		odustani.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JPanel akcije = new JPanel(new FlowLayout());
		akcije.add(dodaj);
		akcije.add(odustani);

		panel.add(scrollPane, BorderLayout.CENTER);
		panel.add(akcije, BorderLayout.SOUTH);

		add(panel);

	}

	public boolean predmetOdgovara(Predmet predmet, Student student) {
		for (Ocena pol : student.getPolIspiti()) {
			if (pol.getPredmet().getSifra().equals(predmet.getSifra()))
				return false;
		}

		for (Predmet nepol : student.getNepolIspiti()) {
			if (nepol.getSifra().equals(predmet.getSifra()))
				return false;
		}

		if (student.getTrenGod() < predmet.getGodStudija())
			return false;

		return true;
	}

	public Predmet getPredmetSifrom(String sifra) {
		for (Predmet p : BazaPredmeta.getInstance().getPredmeti()) {
			if (p.getSifra().equals(sifra))
				return p;
		}
		return null;
	}

}
