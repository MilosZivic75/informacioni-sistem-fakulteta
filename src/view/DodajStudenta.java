package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.BazaStudenata;
import model.Student.Status;

public class DodajStudenta extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DodajStudenta(JFrame parent, String title, boolean mod) {
		super(parent, title, mod);

		Dimension dim = parent.getSize();
		setSize(dim.width / 2, 95 * dim.height / 100);
		setLocationRelativeTo(parent);

		JPanel panel = new JPanel(new GridBagLayout());

		JLabel lblIme = new JLabel("Ime*");
		JTextField tfIme = new JTextField(15);

		JLabel lblPrezime = new JLabel("Prezime*");
		JTextField tfPrezime = new JTextField(15);

		JLabel lblDatumRodj = new JLabel("Datum rođenja*");
		JTextField tfDatumRodj = new JTextField(15);

		JLabel lblAdresa = new JLabel("Adresa stanovanja*");
		JTextField tfAdresa = new JTextField(15);

		JLabel lblBrTel = new JLabel("Broj telefona*");
		JTextField tfBrTel = new JTextField(15);

		JLabel lblEmail = new JLabel("E-mail adresa*");
		JTextField tfEmail = new JTextField(15);

		JLabel lblIndeks = new JLabel("Broj indeksa*");
		JTextField tfIndeks = new JTextField(15);

		JLabel lblGodUpisa = new JLabel("Godina upisa*");
		JTextField tfGodUpisa = new JTextField(15);

		JLabel lblGodStud = new JLabel("Trenutna godina studija*");
		String[] godineStudija = { "I(prva)", "II(druga)", "III(treća)", "IV(četvrta)" };
		JComboBox<String> comboStudije = new JComboBox<String>(godineStudija);
		comboStudije.setSelectedIndex(0);

		JLabel lblStatus = new JLabel("Nacin finansiranja*");
		String[] statusi = { "Budžet", "Samofinansiranje" };
		JComboBox<String> comboStatusi = new JComboBox<String>(statusi);
		comboStatusi.setSelectedIndex(0);

		JButton potvrdi = new JButton("Potvrdi");
		potvrdi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tfIme.getText().trim().isEmpty() || !tfIme.getText().matches("[A-Za-zŠšŽžĐđĆćČč ]+")) {
					tfIme.setText("Neispravan unos!");
				} else if (tfPrezime.getText().trim().isEmpty() || !tfPrezime.getText().matches("[A-Za-zŠšŽžĐđĆćČč ]+")) {
					tfPrezime.setText("Neispravan unos!");
				} else if (tfDatumRodj.getText().trim().isEmpty()
						|| !tfDatumRodj.getText().matches("[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}")) {
					tfDatumRodj.setText("Neispravan unos! (dd/MM/yyyy)");
				} else if (tfAdresa.getText().trim().isEmpty() || !tfAdresa.getText().matches("[A-Za-z0-9ŠšŽžĐđĆćČč ]+")) {
					tfAdresa.setText("Neispravan unos!");
				} else if (tfBrTel.getText().trim().isEmpty() || !tfBrTel.getText().matches("[+]?[0-9]+")) {
					tfBrTel.setText("Neispravan unos!");
				} else if (tfEmail.getText().trim().isEmpty()) {
					tfEmail.setText("Neispravan unos!");
				} else if (tfIndeks.getText().trim().isEmpty()) {
					tfIndeks.setText("Neispravan unos!");
				} else if (tfGodUpisa.getText().trim().isEmpty() || !tfGodUpisa.getText().matches("[0-9]{4}")) {
					tfGodUpisa.setText("Neispravan unos! (yyyy)");
				} else {
					BazaStudenata.getInstance().dodajStudenta(tfPrezime.getText().trim(), tfIme.getText().trim(),
							getDate(tfDatumRodj.getText().trim()), tfAdresa.getText().trim(), tfBrTel.getText().trim(),
							tfEmail.getText().trim(), tfIndeks.getText().trim(),
							Integer.parseInt(tfGodUpisa.getText().trim()),
							getGodStud((String) comboStudije.getSelectedItem()),
							getStatus((String) comboStatusi.getSelectedItem()));
					dispose();
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

		panel.add(lblIme, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		panel.add(tfIme, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panel.add(lblPrezime, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		panel.add(tfPrezime, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panel.add(lblDatumRodj, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 50), 100, 15));
		panel.add(tfDatumRodj, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panel.add(lblAdresa, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		panel.add(tfAdresa, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panel.add(lblBrTel, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		panel.add(tfBrTel, new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panel.add(lblEmail, new GridBagConstraints(0, 5, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		panel.add(tfEmail, new GridBagConstraints(1, 5, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panel.add(lblIndeks, new GridBagConstraints(0, 6, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		panel.add(tfIndeks, new GridBagConstraints(1, 6, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panel.add(lblGodUpisa, new GridBagConstraints(0, 7, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 50), 100, 15));
		panel.add(tfGodUpisa, new GridBagConstraints(1, 7, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panel.add(lblGodStud, new GridBagConstraints(0, 8, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		panel.add(comboStudije, new GridBagConstraints(1, 8, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panel.add(lblStatus, new GridBagConstraints(0, 9, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		panel.add(comboStatusi, new GridBagConstraints(1, 9, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panel.add(potvrdi, new GridBagConstraints(0, 10, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 150, 5, 5), 0, 0));
		panel.add(odustani, new GridBagConstraints(1, 10, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));

		add(panel, BorderLayout.CENTER);

	}

	public int getGodStud(String s) {
		switch (s) {
		case "I(prva)":
			return 1;
		case "II(druga)":
			return 2;
		case "III(treća)":
			return 3;
		case "IV(četvrta)":
			return 4;
		default:
			return -1;
		}
	}

	public Status getStatus(String s) {
		switch (s) {
		case "Budžet":
			return Status.B;
		case "Samofinansiranje":
			return Status.S;
		default:
			return Status.S;
		}
	}

	public Date getDate(String s) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;

	}

}
