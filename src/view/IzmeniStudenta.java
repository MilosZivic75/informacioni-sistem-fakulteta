package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.BazaPredmeta;
import model.BazaStudenata;
import model.Ocena;
import model.Predmet;
import model.Student;
import model.Student.Status;

public class IzmeniStudenta extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Student student;
	JTable tabelaPolozenih;
	JTable tabelaNepolozenih;
	JLabel ukEspb;
	JLabel prosekOcena;

	public IzmeniStudenta(JFrame parent, String title, boolean mod, int row) {
		super(parent, title, mod);

		Dimension dim = parent.getSize();
		setSize(2 * dim.width / 3, 95 * dim.height / 100);
		setLocationRelativeTo(parent);

		JDialog instance = this;

		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel info = new JPanel(new GridBagLayout());
		JPanel polozeni = new JPanel(new BorderLayout());
		JPanel nepolozeni = new JPanel(new BorderLayout());

		add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("Informacije", info);
		tabbedPane.addTab("Položeni", polozeni);
		tabbedPane.addTab("Nepoloženi", nepolozeni);

		student = BazaStudenata.getInstance().getFiltriraniStudenti().get(row);

		JButton potvrdi = new JButton("Potvrdi");

		JLabel lblIme = new JLabel("Ime*");
		JTextField tfIme = new JTextField(student.getIme(), 15);

		JLabel lblPrezime = new JLabel("Prezime*");
		JTextField tfPrezime = new JTextField(student.getPrezime(), 15);

		JLabel lblDatumRodj = new JLabel("Datum rođenja*");
		JTextField tfDatumRodj = new JTextField(new SimpleDateFormat("dd/MM/yyyy").format(student.getDatRodjenja()),
				15);

		JLabel lblAdresa = new JLabel("Adresa stanovanja*");
		JTextField tfAdresa = new JTextField(student.getAdresa(), 15);

		JLabel lblBrTel = new JLabel("Broj telefona*");
		JTextField tfBrTel = new JTextField(student.getBrTelefona(), 15);

		JLabel lblEmail = new JLabel("E-mail adresa*");
		JTextField tfEmail = new JTextField(student.getEmail(), 15);

		JLabel lblIndeks = new JLabel("Broj indeksa*");
		JTextField tfIndeks = new JTextField(student.getIndeks(), 15);

		JLabel lblGodUpisa = new JLabel("Godina upisa*");
		JTextField tfGodUpisa = new JTextField(Integer.toString(student.getGodUpisa()), 15);

		JLabel lblGodStud = new JLabel("Trenutna godina studija*");
		String[] godineStudija = { "I(prva)", "II(druga)", "III(treća)", "IV(četvrta)" };
		JComboBox<String> comboStudije = new JComboBox<String>(godineStudija);
		comboStudije.setSelectedIndex(student.getTrenGod() - 1);

		JLabel lblStatus = new JLabel("Nacin finansiranja*");
		String[] statusi = { "Budžet", "Samofinansiranje" };
		JComboBox<String> comboStatusi = new JComboBox<String>(statusi);
		comboStatusi.setSelectedItem((student.getStatus() == Status.B) ? "Budžet" : "Samofinansiranje");

		tfIme.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (tfIme.getText().trim().isEmpty() || !tfIme.getText().matches("[A-Za-zŠšŽžĐđĆćČč ]+")) {
					potvrdi.setEnabled(false);
					tfIme.setText("Neispravan unos!");
				} else if (tfPrezime.getText().trim().isEmpty()
						|| !tfPrezime.getText().matches("[A-Za-zŠšŽžĐđĆćČč ]+")) {
					potvrdi.setEnabled(false);
					tfPrezime.setText("Neispravan unos!");
				} else if (tfDatumRodj.getText().trim().isEmpty()
						|| !tfDatumRodj.getText().matches("[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}")) {
					potvrdi.setEnabled(false);
					tfDatumRodj.setText("Neispravan unos! (dd/MM/yyyy)");
				} else if (tfAdresa.getText().trim().isEmpty()
						|| !tfAdresa.getText().matches("[A-Za-z0-9ŠšŽžĐđĆćČč, ]+")) {
					potvrdi.setEnabled(false);
					tfAdresa.setText("Neispravan unos!");
				} else if (tfBrTel.getText().trim().isEmpty() || !tfBrTel.getText().matches("[+]?[0-9/-]+")) {
					potvrdi.setEnabled(false);
					tfBrTel.setText("Neispravan unos!");
				} else if (tfEmail.getText().trim().isEmpty()) {
					potvrdi.setEnabled(false);
					tfEmail.setText("Neispravan unos!");
				} else if (tfIndeks.getText().trim().isEmpty()) {
					potvrdi.setEnabled(false);
					tfIndeks.setText("Neispravan unos!");
				} else if (existsIndeks(tfIndeks.getText().trim(), student)) {
					potvrdi.setEnabled(false);
					tfIndeks.setText("Indeks mora biti jedinstven!");
				} else if (tfGodUpisa.getText().trim().isEmpty() || !tfGodUpisa.getText().matches("[0-9]{4}")) {
					potvrdi.setEnabled(false);
					tfGodUpisa.setText("Neispravan unos! (yyyy)");
				} else {
					potvrdi.setEnabled(true);
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (tfIme.getText().equals("Neispravan unos!"))
					tfIme.setText("");

			}
		});

		tfPrezime.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (tfIme.getText().trim().isEmpty() || !tfIme.getText().matches("[A-Za-zŠšŽžĐđĆćČč ]+")) {
					potvrdi.setEnabled(false);
					tfIme.setText("Neispravan unos!");
				} else if (tfPrezime.getText().trim().isEmpty()
						|| !tfPrezime.getText().matches("[A-Za-zŠšŽžĐđĆćČč ]+")) {
					potvrdi.setEnabled(false);
					tfPrezime.setText("Neispravan unos!");
				} else if (tfDatumRodj.getText().trim().isEmpty()
						|| !tfDatumRodj.getText().matches("[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}")) {
					potvrdi.setEnabled(false);
					tfDatumRodj.setText("Neispravan unos! (dd/MM/yyyy)");
				} else if (tfAdresa.getText().trim().isEmpty()
						|| !tfAdresa.getText().matches("[A-Za-z0-9ŠšŽžĐđĆćČč, ]+")) {
					potvrdi.setEnabled(false);
					tfAdresa.setText("Neispravan unos!");
				} else if (tfBrTel.getText().trim().isEmpty() || !tfBrTel.getText().matches("[+]?[0-9/-]+")) {
					potvrdi.setEnabled(false);
					tfBrTel.setText("Neispravan unos!");
				} else if (tfEmail.getText().trim().isEmpty()) {
					potvrdi.setEnabled(false);
					tfEmail.setText("Neispravan unos!");
				} else if (tfIndeks.getText().trim().isEmpty()) {
					potvrdi.setEnabled(false);
					tfIndeks.setText("Neispravan unos!");
				} else if (existsIndeks(tfIndeks.getText().trim(), student)) {
					potvrdi.setEnabled(false);
					tfIndeks.setText("Indeks mora biti jedinstven!");
				} else if (tfGodUpisa.getText().trim().isEmpty() || !tfGodUpisa.getText().matches("[0-9]{4}")) {
					potvrdi.setEnabled(false);
					tfGodUpisa.setText("Neispravan unos! (yyyy)");
				} else {
					potvrdi.setEnabled(true);
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (tfPrezime.getText().equals("Neispravan unos!"))
					tfPrezime.setText("");

			}
		});

		tfDatumRodj.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (tfIme.getText().trim().isEmpty() || !tfIme.getText().matches("[A-Za-zŠšŽžĐđĆćČč ]+")) {
					potvrdi.setEnabled(false);
					tfIme.setText("Neispravan unos!");
				} else if (tfPrezime.getText().trim().isEmpty()
						|| !tfPrezime.getText().matches("[A-Za-zŠšŽžĐđĆćČč ]+")) {
					potvrdi.setEnabled(false);
					tfPrezime.setText("Neispravan unos!");
				} else if (tfDatumRodj.getText().trim().isEmpty()
						|| !tfDatumRodj.getText().matches("[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}")) {
					potvrdi.setEnabled(false);
					tfDatumRodj.setText("Neispravan unos! (dd/MM/yyyy)");
				} else if (tfAdresa.getText().trim().isEmpty()
						|| !tfAdresa.getText().matches("[A-Za-z0-9ŠšŽžĐđĆćČč, ]+")) {
					potvrdi.setEnabled(false);
					tfAdresa.setText("Neispravan unos!");
				} else if (tfBrTel.getText().trim().isEmpty() || !tfBrTel.getText().matches("[+]?[0-9/-]+")) {
					potvrdi.setEnabled(false);
					tfBrTel.setText("Neispravan unos!");
				} else if (tfEmail.getText().trim().isEmpty()) {
					potvrdi.setEnabled(false);
					tfEmail.setText("Neispravan unos!");
				} else if (tfIndeks.getText().trim().isEmpty()) {
					potvrdi.setEnabled(false);
					tfIndeks.setText("Neispravan unos!");
				} else if (existsIndeks(tfIndeks.getText().trim(), student)) {
					potvrdi.setEnabled(false);
					tfIndeks.setText("Indeks mora biti jedinstven!");
				} else if (tfGodUpisa.getText().trim().isEmpty() || !tfGodUpisa.getText().matches("[0-9]{4}")) {
					potvrdi.setEnabled(false);
					tfGodUpisa.setText("Neispravan unos! (yyyy)");
				} else {
					potvrdi.setEnabled(true);
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (tfDatumRodj.getText().equals("Neispravan unos! (dd/MM/yyyy)"))
					tfDatumRodj.setText("");

			}
		});

		tfAdresa.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (tfIme.getText().trim().isEmpty() || !tfIme.getText().matches("[A-Za-zŠšŽžĐđĆćČč ]+")) {
					potvrdi.setEnabled(false);
					tfIme.setText("Neispravan unos!");
				} else if (tfPrezime.getText().trim().isEmpty()
						|| !tfPrezime.getText().matches("[A-Za-zŠšŽžĐđĆćČč ]+")) {
					potvrdi.setEnabled(false);
					tfPrezime.setText("Neispravan unos!");
				} else if (tfDatumRodj.getText().trim().isEmpty()
						|| !tfDatumRodj.getText().matches("[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}")) {
					potvrdi.setEnabled(false);
					tfDatumRodj.setText("Neispravan unos! (dd/MM/yyyy)");
				} else if (tfAdresa.getText().trim().isEmpty()
						|| !tfAdresa.getText().matches("[A-Za-z0-9ŠšŽžĐđĆćČč, ]+")) {
					potvrdi.setEnabled(false);
					tfAdresa.setText("Neispravan unos!");
				} else if (tfBrTel.getText().trim().isEmpty() || !tfBrTel.getText().matches("[+]?[0-9/-]+")) {
					potvrdi.setEnabled(false);
					tfBrTel.setText("Neispravan unos!");
				} else if (tfEmail.getText().trim().isEmpty()) {
					potvrdi.setEnabled(false);
					tfEmail.setText("Neispravan unos!");
				} else if (tfIndeks.getText().trim().isEmpty()) {
					potvrdi.setEnabled(false);
					tfIndeks.setText("Neispravan unos!");
				} else if (existsIndeks(tfIndeks.getText().trim(), student)) {
					potvrdi.setEnabled(false);
					tfIndeks.setText("Indeks mora biti jedinstven!");
				} else if (tfGodUpisa.getText().trim().isEmpty() || !tfGodUpisa.getText().matches("[0-9]{4}")) {
					potvrdi.setEnabled(false);
					tfGodUpisa.setText("Neispravan unos! (yyyy)");
				} else {
					potvrdi.setEnabled(true);
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (tfAdresa.getText().equals("Neispravan unos!"))
					tfAdresa.setText("");
			}
		});

		tfBrTel.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (tfIme.getText().trim().isEmpty() || !tfIme.getText().matches("[A-Za-zŠšŽžĐđĆćČč ]+")) {
					potvrdi.setEnabled(false);
					tfIme.setText("Neispravan unos!");
				} else if (tfPrezime.getText().trim().isEmpty()
						|| !tfPrezime.getText().matches("[A-Za-zŠšŽžĐđĆćČč ]+")) {
					potvrdi.setEnabled(false);
					tfPrezime.setText("Neispravan unos!");
				} else if (tfDatumRodj.getText().trim().isEmpty()
						|| !tfDatumRodj.getText().matches("[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}")) {
					potvrdi.setEnabled(false);
					tfDatumRodj.setText("Neispravan unos! (dd/MM/yyyy)");
				} else if (tfAdresa.getText().trim().isEmpty()
						|| !tfAdresa.getText().matches("[A-Za-z0-9ŠšŽžĐđĆćČč, ]+")) {
					potvrdi.setEnabled(false);
					tfAdresa.setText("Neispravan unos!");
				} else if (tfBrTel.getText().trim().isEmpty() || !tfBrTel.getText().matches("[+]?[0-9/-]+")) {
					potvrdi.setEnabled(false);
					tfBrTel.setText("Neispravan unos!");
				} else if (tfEmail.getText().trim().isEmpty()) {
					potvrdi.setEnabled(false);
					tfEmail.setText("Neispravan unos!");
				} else if (tfIndeks.getText().trim().isEmpty()) {
					potvrdi.setEnabled(false);
					tfIndeks.setText("Neispravan unos!");
				} else if (existsIndeks(tfIndeks.getText().trim(), student)) {
					potvrdi.setEnabled(false);
					tfIndeks.setText("Indeks mora biti jedinstven!");
				} else if (tfGodUpisa.getText().trim().isEmpty() || !tfGodUpisa.getText().matches("[0-9]{4}")) {
					potvrdi.setEnabled(false);
					tfGodUpisa.setText("Neispravan unos! (yyyy)");
				} else {
					potvrdi.setEnabled(true);
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (tfBrTel.getText().equals("Neispravan unos!"))
					tfBrTel.setText("");
			}
		});

		tfEmail.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (tfIme.getText().trim().isEmpty() || !tfIme.getText().matches("[A-Za-zŠšŽžĐđĆćČč ]+")) {
					potvrdi.setEnabled(false);
					tfIme.setText("Neispravan unos!");
				} else if (tfPrezime.getText().trim().isEmpty()
						|| !tfPrezime.getText().matches("[A-Za-zŠšŽžĐđĆćČč ]+")) {
					potvrdi.setEnabled(false);
					tfPrezime.setText("Neispravan unos!");
				} else if (tfDatumRodj.getText().trim().isEmpty()
						|| !tfDatumRodj.getText().matches("[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}")) {
					potvrdi.setEnabled(false);
					tfDatumRodj.setText("Neispravan unos! (dd/MM/yyyy)");
				} else if (tfAdresa.getText().trim().isEmpty()
						|| !tfAdresa.getText().matches("[A-Za-z0-9ŠšŽžĐđĆćČč, ]+")) {
					potvrdi.setEnabled(false);
					tfAdresa.setText("Neispravan unos!");
				} else if (tfBrTel.getText().trim().isEmpty() || !tfBrTel.getText().matches("[+]?[0-9/-]+")) {
					potvrdi.setEnabled(false);
					tfBrTel.setText("Neispravan unos!");
				} else if (tfEmail.getText().trim().isEmpty()) {
					potvrdi.setEnabled(false);
					tfEmail.setText("Neispravan unos!");
				} else if (tfIndeks.getText().trim().isEmpty()) {
					potvrdi.setEnabled(false);
					tfIndeks.setText("Neispravan unos!");
				} else if (existsIndeks(tfIndeks.getText().trim(), student)) {
					potvrdi.setEnabled(false);
					tfIndeks.setText("Indeks mora biti jedinstven!");
				} else if (tfGodUpisa.getText().trim().isEmpty() || !tfGodUpisa.getText().matches("[0-9]{4}")) {
					potvrdi.setEnabled(false);
					tfGodUpisa.setText("Neispravan unos! (yyyy)");
				} else {
					potvrdi.setEnabled(true);
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (tfEmail.getText().equals("Neispravan unos!"))
					tfEmail.setText("");
			}
		});

		tfIndeks.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (tfIme.getText().trim().isEmpty() || !tfIme.getText().matches("[A-Za-zŠšŽžĐđĆćČč ]+")) {
					potvrdi.setEnabled(false);
					tfIme.setText("Neispravan unos!");
				} else if (tfPrezime.getText().trim().isEmpty()
						|| !tfPrezime.getText().matches("[A-Za-zŠšŽžĐđĆćČč ]+")) {
					potvrdi.setEnabled(false);
					tfPrezime.setText("Neispravan unos!");
				} else if (tfDatumRodj.getText().trim().isEmpty()
						|| !tfDatumRodj.getText().matches("[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}")) {
					potvrdi.setEnabled(false);
					tfDatumRodj.setText("Neispravan unos! (dd/MM/yyyy)");
				} else if (tfAdresa.getText().trim().isEmpty()
						|| !tfAdresa.getText().matches("[A-Za-z0-9ŠšŽžĐđĆćČč, ]+")) {
					potvrdi.setEnabled(false);
					tfAdresa.setText("Neispravan unos!");
				} else if (tfBrTel.getText().trim().isEmpty() || !tfBrTel.getText().matches("[+]?[0-9/-]+")) {
					potvrdi.setEnabled(false);
					tfBrTel.setText("Neispravan unos!");
				} else if (tfEmail.getText().trim().isEmpty()) {
					potvrdi.setEnabled(false);
					tfEmail.setText("Neispravan unos!");
				} else if (tfIndeks.getText().trim().isEmpty()) {
					potvrdi.setEnabled(false);
					tfIndeks.setText("Neispravan unos!");
				} else if (existsIndeks(tfIndeks.getText().trim(), student)) {
					potvrdi.setEnabled(false);
					tfIndeks.setText("Indeks mora biti jedinstven!");
				} else if (tfGodUpisa.getText().trim().isEmpty() || !tfGodUpisa.getText().matches("[0-9]{4}")) {
					potvrdi.setEnabled(false);
					tfGodUpisa.setText("Neispravan unos! (yyyy)");
				} else {
					potvrdi.setEnabled(true);
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (tfIndeks.getText().equals("Neispravan unos!")
						|| tfIndeks.getText().equals("Indeks mora biti jedinstven!"))
					tfIndeks.setText("");
			}
		});

		tfGodUpisa.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (tfIme.getText().trim().isEmpty() || !tfIme.getText().matches("[A-Za-zŠšŽžĐđĆćČč ]+")) {
					potvrdi.setEnabled(false);
					tfIme.setText("Neispravan unos!");
				} else if (tfPrezime.getText().trim().isEmpty()
						|| !tfPrezime.getText().matches("[A-Za-zŠšŽžĐđĆćČč ]+")) {
					potvrdi.setEnabled(false);
					tfPrezime.setText("Neispravan unos!");
				} else if (tfDatumRodj.getText().trim().isEmpty()
						|| !tfDatumRodj.getText().matches("[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}")) {
					potvrdi.setEnabled(false);
					tfDatumRodj.setText("Neispravan unos! (dd/MM/yyyy)");
				} else if (tfAdresa.getText().trim().isEmpty()
						|| !tfAdresa.getText().matches("[A-Za-z0-9ŠšŽžĐđĆćČč, ]+")) {
					potvrdi.setEnabled(false);
					tfAdresa.setText("Neispravan unos!");
				} else if (tfBrTel.getText().trim().isEmpty() || !tfBrTel.getText().matches("[+]?[0-9/-]+")) {
					potvrdi.setEnabled(false);
					tfBrTel.setText("Neispravan unos!");
				} else if (tfEmail.getText().trim().isEmpty()) {
					potvrdi.setEnabled(false);
					tfEmail.setText("Neispravan unos!");
				} else if (tfIndeks.getText().trim().isEmpty()) {
					potvrdi.setEnabled(false);
					tfIndeks.setText("Neispravan unos!");
				} else if (existsIndeks(tfIndeks.getText().trim(), student)) {
					potvrdi.setEnabled(false);
					tfIndeks.setText("Indeks mora biti jedinstven!");
				} else if (tfGodUpisa.getText().trim().isEmpty() || !tfGodUpisa.getText().matches("[0-9]{4}")) {
					potvrdi.setEnabled(false);
					tfGodUpisa.setText("Neispravan unos! (yyyy)");
				} else {
					potvrdi.setEnabled(true);
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (tfGodUpisa.getText().equals("Neispravan unos! (yyyy)"))
					tfGodUpisa.setText("");

			}
		});

		potvrdi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				BazaStudenata.getInstance().izmeniStudenta(tfPrezime.getText().trim(), tfIme.getText().trim(),
						getDate(tfDatumRodj.getText().trim()), tfAdresa.getText().trim(), tfBrTel.getText().trim(),
						tfEmail.getText().trim(), tfIndeks.getText().trim(),
						Integer.parseInt(tfGodUpisa.getText().trim()),
						getGodStud((String) comboStudije.getSelectedItem()),
						getStatus((String) comboStatusi.getSelectedItem()), row);
				dispose();

			}
		});

		JButton odustani = new JButton("Odustani");
		odustani.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		info.add(lblIme, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		info.add(tfIme, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		info.add(lblPrezime, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		info.add(tfPrezime, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		info.add(lblDatumRodj, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 50), 100, 15));
		info.add(tfDatumRodj, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		info.add(lblAdresa, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		info.add(tfAdresa, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		info.add(lblBrTel, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		info.add(tfBrTel, new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		info.add(lblEmail, new GridBagConstraints(0, 5, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		info.add(tfEmail, new GridBagConstraints(1, 5, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		info.add(lblIndeks, new GridBagConstraints(0, 6, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		info.add(tfIndeks, new GridBagConstraints(1, 6, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		info.add(lblGodUpisa, new GridBagConstraints(0, 7, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		info.add(tfGodUpisa, new GridBagConstraints(1, 7, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		info.add(lblGodStud, new GridBagConstraints(0, 8, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		info.add(comboStudije, new GridBagConstraints(1, 8, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		info.add(lblStatus, new GridBagConstraints(0, 9, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		info.add(comboStatusi, new GridBagConstraints(1, 9, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		info.add(potvrdi, new GridBagConstraints(0, 10, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 150, 5, 5), 0, 0));
		info.add(odustani, new GridBagConstraints(1, 10, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));

		JPanel ponistiPanel = new JPanel(new FlowLayout());
		JButton ponisti = new JButton("Poništi ocenu");

		ponisti.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabelaPolozenih.getSelectedRow() != -1) {
					String[] options = new String[2];
					options[0] = new String("Da");
					options[1] = new String("Ne");
					int option = JOptionPane.showOptionDialog(instance,
							"Da li ste sigurni da želite da poništite ocenu?", "Poništavanje ocene", 0,
							JOptionPane.QUESTION_MESSAGE, null, options, null);
					if (option == JOptionPane.YES_OPTION) {
						for (Predmet p : BazaPredmeta.getInstance().getPredmeti()) {
							if (student.getPolIspiti().get(tabelaPolozenih.getSelectedRow()).getPredmet().getSifra()
									.equals(p.getSifra())) {
								student.getNepolIspiti().add(p);
							}
						}

						student.getPolIspiti().remove(tabelaPolozenih.getSelectedRow());
					}

					azurirajPrikaz("PONISTEN", -1);
				} else {
					JOptionPane.showMessageDialog(parent, "Morate selektovati predmet");
				}
			}
		});

		ponistiPanel.add(ponisti);
		polozeni.add(ponistiPanel, BorderLayout.NORTH);

		tabelaPolozenih = new TabelaPolozenih(student);
		JScrollPane scrollPanePolozeni = new JScrollPane(tabelaPolozenih);
		polozeni.add(scrollPanePolozeni, BorderLayout.CENTER);

		int ukBrojEspb = 0;
		int zbirOcena = 0;
		for (Ocena o : student.getPolIspiti()) {
			ukBrojEspb += o.getPredmet().getEspb();
			zbirOcena += o.getOcena();
		}
		student.setProsOcena(
				student.getPolIspiti().size() != 0 ? (double) zbirOcena / student.getPolIspiti().size() : 0);
		ukEspb = new JLabel("Ukupno ESPB: " + ukBrojEspb);
		prosekOcena = new JLabel(String.format("Prosečna ocena: %.2f                ", student.getProsOcena()));

		JPanel stats = new JPanel(new FlowLayout());
		stats.add(prosekOcena);
		stats.add(ukEspb);

		polozeni.add(stats, BorderLayout.SOUTH);

		JPanel nepolozeniAkcije = new JPanel(new FlowLayout());
		tabelaNepolozenih = new TabelaNepolozenih(student);
		JScrollPane scrollPaneNepolozeni = new JScrollPane(tabelaNepolozenih);
		JButton dodaj = new JButton("Dodaj");

		dodaj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DodajPredmetStudentu dodajPredmet = new DodajPredmetStudentu(instance, "Dodavanje predmeta", true,
						student);
				dodajPredmet.setVisible(true);

				azurirajPrikaz("DODAT", -1);
			}
		});

		JButton obrisi = new JButton("Obriši");

		obrisi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabelaNepolozenih.getSelectedRow() != -1) {
					String[] options = new String[2];
					options[0] = new String("Da");
					options[1] = new String("Ne");
					int option = JOptionPane.showOptionDialog(instance,
							"Da li ste sigurni da želite da uklonite predmet?", "Uklanjanje predmeta", 0,
							JOptionPane.QUESTION_MESSAGE, null, options, null);
					if (option == JOptionPane.YES_OPTION) {
						student.getNepolIspiti().remove(tabelaNepolozenih.getSelectedRow());
						azurirajPrikaz("OBRISAN", -1);
					}

				} else {
					JOptionPane.showMessageDialog(parent, "Morate selektovati predmet");
				}
			}
		});

		JButton polaganje = new JButton("Polaganje");

		polaganje.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabelaNepolozenih.getSelectedRow() != -1) {
					UpisOcene upis = new UpisOcene(instance, "Upis ocene", true, student,
							student.getNepolIspiti().get(tabelaNepolozenih.getSelectedRow()));
					upis.setVisible(true);
					azurirajPrikaz("PREBACEN", -1);
				} else {
					JOptionPane.showMessageDialog(parent, "Morate selektovati predmet");
				}

			}
		});

		nepolozeniAkcije.add(dodaj);
		nepolozeniAkcije.add(obrisi);
		nepolozeniAkcije.add(polaganje);

		nepolozeni.add(nepolozeniAkcije, BorderLayout.NORTH);

		nepolozeni.add(scrollPaneNepolozeni, BorderLayout.CENTER);

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

	public boolean existsIndeks(String indeks, Student stud) {
		for (Student s : BazaStudenata.getInstance().getStudenti()) {
			if (indeks.equals(s.getIndeks()) && s != stud)
				return true;
		}
		return false;
	}

	public void azurirajPrikaz(String akcija, int vrednost) {
		AbstractTableModelNepolozeni modelNepolozenih = (AbstractTableModelNepolozeni) tabelaNepolozenih.getModel();
		modelNepolozenih.fireTableDataChanged();

		AbstractTableModelPolozeni modelPolozenih = (AbstractTableModelPolozeni) tabelaPolozenih.getModel();
		modelPolozenih.fireTableDataChanged();
		int ukBrojEspb = 0;
		int zbirOcena = 0;
		for (Ocena o : student.getPolIspiti()) {
			ukBrojEspb += o.getPredmet().getEspb();
			zbirOcena += o.getOcena();
		}
		student.setProsOcena(
				student.getPolIspiti().size() != 0 ? (double) zbirOcena / student.getPolIspiti().size() : 0);
		ukEspb.setText("Ukupno ESPB: " + ukBrojEspb);
		prosekOcena.setText(String.format("Prosečna ocena: %.2f                ",
				student.getProsOcena()));

		validate();
	}

}
