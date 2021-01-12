package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import model.BazaProfesora;
import model.Profesor;
import model.Profesor.Titule;
import model.Profesor.Zvanja;

public class IzmeniProfesora extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IzmeniProfesora(JFrame parent, String title, boolean mod, int row) {
		super(parent, title, mod);

		Dimension dim = parent.getSize();
		setSize(dim.width / 2, 95 * dim.height / 100);
		setLocationRelativeTo(parent);

		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel info = new JPanel(new GridBagLayout());
		JPanel predmeti = new JPanel();

		add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("Informacije", info);
		tabbedPane.addTab("Predmeti", predmeti);

		Profesor profesor = BazaProfesora.getInstance().getFiltriraniProfesori().get(row);

		JButton potvrdi = new JButton("Potvrdi");

		JLabel lblIme = new JLabel("Ime*");
		JTextField tfIme = new JTextField(profesor.getIme(), 15);

		JLabel lblPrezime = new JLabel("Prezime*");
		JTextField tfPrezime = new JTextField(profesor.getPrezime(), 15);

		JLabel lblDatumRodj = new JLabel("Datum rođenja*");
		JTextField tfDatumRodj = new JTextField(new SimpleDateFormat("dd/MM/yyyy").format(profesor.getDatRodjenja()),
				15);

		JLabel lblAdresa = new JLabel("Adresa stanovanja*");
		JTextField tfAdresa = new JTextField(profesor.getAdresa(), 15);

		JLabel lblBrTel = new JLabel("Broj telefona*");
		JTextField tfBrTel = new JTextField(profesor.getBrTelefona(), 15);

		JLabel lblEmail = new JLabel("E-mail adresa*");
		JTextField tfEmail = new JTextField(profesor.getEmail(), 15);

		JLabel lblAdresaKanc = new JLabel("Adresa kancelarije*");
		JTextField tfAdresaKanc = new JTextField(profesor.getAdresaKanc(), 15);

		JLabel lblBrLicne = new JLabel("Broj lične karte*");
		JTextField tfBrLicne = new JTextField(profesor.getBrLicne(), 15);

		JLabel lblTitula = new JLabel("Titula*");
		String[] titule = { "BSc", "MSc", "PhD", "Dr", "Mr", "prof. Dr" };
		JComboBox<String> comboTitule = new JComboBox<String>(titule);
		comboTitule.setSelectedItem(profesor.getTitulaString(profesor.getTitula()));

		JLabel lblZvanje = new JLabel("Zvanje*");
		String[] zvanja = { "Saradnik u nastavi", "Asistent", "Asistent sa doktoratom", "Docent", "Vanredni profesor",
				"Redovni profesor", "Profesor emeritus" };
		JComboBox<String> comboZvanja = new JComboBox<String>(zvanja);
		comboZvanja.setSelectedItem(profesor.getZvanjeString(profesor.getZvanje()));

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
				} else if (tfAdresaKanc.getText().trim().isEmpty()
						|| !tfAdresaKanc.getText().matches("[A-Za-z0-9ŠšŽžĐđĆćČč, ]+")) {
					potvrdi.setEnabled(false);
					tfAdresaKanc.setText("Neispravan unos!");
				} else if (tfBrLicne.getText().trim().isEmpty() || !tfBrLicne.getText().matches("[0-9]{9}")) {
					potvrdi.setEnabled(false);
					tfBrLicne.setText("Neispravan unos! : 9 cifara");
				} else if (existsLicna(tfBrLicne.getText().trim(), profesor)) {
					potvrdi.setEnabled(false);
					tfBrLicne.setText("Broj lične karte mora biti jedinstven!");
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
				} else if (tfAdresaKanc.getText().trim().isEmpty()
						|| !tfAdresaKanc.getText().matches("[A-Za-z0-9ŠšŽžĐđĆćČč, ]+")) {
					potvrdi.setEnabled(false);
					tfAdresaKanc.setText("Neispravan unos!");
				} else if (tfBrLicne.getText().trim().isEmpty() || !tfBrLicne.getText().matches("[0-9]{9}")) {
					potvrdi.setEnabled(false);
					tfBrLicne.setText("Neispravan unos! : 9 cifara");
				} else if (existsLicna(tfBrLicne.getText().trim(), profesor)) {
					potvrdi.setEnabled(false);
					tfBrLicne.setText("Broj lične karte mora biti jedinstven!");
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
				} else if (tfAdresaKanc.getText().trim().isEmpty()
						|| !tfAdresaKanc.getText().matches("[A-Za-z0-9ŠšŽžĐđĆćČč, ]+")) {
					potvrdi.setEnabled(false);
					tfAdresaKanc.setText("Neispravan unos!");
				} else if (tfBrLicne.getText().trim().isEmpty() || !tfBrLicne.getText().matches("[0-9]{9}")) {
					potvrdi.setEnabled(false);
					tfBrLicne.setText("Neispravan unos! : 9 cifara");
				} else if (existsLicna(tfBrLicne.getText().trim(), profesor)) {
					potvrdi.setEnabled(false);
					tfBrLicne.setText("Broj lične karte mora biti jedinstven!");
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
				} else if (tfAdresaKanc.getText().trim().isEmpty()
						|| !tfAdresaKanc.getText().matches("[A-Za-z0-9ŠšŽžĐđĆćČč, ]+")) {
					potvrdi.setEnabled(false);
					tfAdresaKanc.setText("Neispravan unos!");
				} else if (tfBrLicne.getText().trim().isEmpty() || !tfBrLicne.getText().matches("[0-9]{9}")) {
					potvrdi.setEnabled(false);
					tfBrLicne.setText("Neispravan unos! : 9 cifara");
				} else if (existsLicna(tfBrLicne.getText().trim(), profesor)) {
					potvrdi.setEnabled(false);
					tfBrLicne.setText("Broj lične karte mora biti jedinstven!");
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
				} else if (tfAdresaKanc.getText().trim().isEmpty()
						|| !tfAdresaKanc.getText().matches("[A-Za-z0-9ŠšŽžĐđĆćČč, ]+")) {
					potvrdi.setEnabled(false);
					tfAdresaKanc.setText("Neispravan unos!");
				} else if (tfBrLicne.getText().trim().isEmpty() || !tfBrLicne.getText().matches("[0-9]{9}")) {
					potvrdi.setEnabled(false);
					tfBrLicne.setText("Neispravan unos! : 9 cifara");
				} else if (existsLicna(tfBrLicne.getText().trim(), profesor)) {
					potvrdi.setEnabled(false);
					tfBrLicne.setText("Broj lične karte mora biti jedinstven!");
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
				} else if (tfAdresaKanc.getText().trim().isEmpty()
						|| !tfAdresaKanc.getText().matches("[A-Za-z0-9ŠšŽžĐđĆćČč, ]+")) {
					potvrdi.setEnabled(false);
					tfAdresaKanc.setText("Neispravan unos!");
				} else if (tfBrLicne.getText().trim().isEmpty() || !tfBrLicne.getText().matches("[0-9]{9}")) {
					potvrdi.setEnabled(false);
					tfBrLicne.setText("Neispravan unos! : 9 cifara");
				} else if (existsLicna(tfBrLicne.getText().trim(), profesor)) {
					potvrdi.setEnabled(false);
					tfBrLicne.setText("Broj lične karte mora biti jedinstven!");
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

		tfAdresaKanc.addFocusListener(new FocusListener() {

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
				} else if (tfAdresaKanc.getText().trim().isEmpty()
						|| !tfAdresaKanc.getText().matches("[A-Za-z0-9ŠšŽžĐđĆćČč, ]+")) {
					potvrdi.setEnabled(false);
					tfAdresaKanc.setText("Neispravan unos!");
				} else if (tfBrLicne.getText().trim().isEmpty() || !tfBrLicne.getText().matches("[0-9]{9}")) {
					potvrdi.setEnabled(false);
					tfBrLicne.setText("Neispravan unos! : 9 cifara");
				} else if (existsLicna(tfBrLicne.getText().trim(), profesor)) {
					potvrdi.setEnabled(false);
					tfBrLicne.setText("Broj lične karte mora biti jedinstven!");
				} else {
					potvrdi.setEnabled(true);
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (tfAdresaKanc.getText().equals("Neispravan unos!"))
					tfAdresaKanc.setText("");
			}
		});

		tfBrLicne.addFocusListener(new FocusListener() {

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
				} else if (tfAdresaKanc.getText().trim().isEmpty()
						|| !tfAdresaKanc.getText().matches("[A-Za-z0-9ŠšŽžĐđĆćČč, ]+")) {
					potvrdi.setEnabled(false);
					tfAdresaKanc.setText("Neispravan unos!");
				} else if (tfBrLicne.getText().trim().isEmpty() || !tfBrLicne.getText().matches("[0-9]{9}")) {
					potvrdi.setEnabled(false);
					tfBrLicne.setText("Neispravan unos! : 9 cifara");
				} else if (existsLicna(tfBrLicne.getText().trim(), profesor)) {
					potvrdi.setEnabled(false);
					tfBrLicne.setText("Broj lične karte mora biti jedinstven!");
				} else {
					potvrdi.setEnabled(true);
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (tfBrLicne.getText().equals("Neispravan unos! : 9 cifara")
						|| tfBrLicne.getText().equals("Broj lične karte mora biti jedinstven!"))
					tfBrLicne.setText("");

			}
		});

		potvrdi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				BazaProfesora.getInstance().izmeniProfesora(tfPrezime.getText().trim(), tfIme.getText().trim(),
						getDate(tfDatumRodj.getText().trim()), tfAdresa.getText().trim(), tfBrTel.getText().trim(),
						tfEmail.getText().trim(), tfAdresaKanc.getText().trim(), tfBrLicne.getText().trim(),
						getTitulaFromString((String) comboTitule.getSelectedItem()),
						getZvanjeFromString((String) comboZvanja.getSelectedItem()), row);
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
		info.add(lblAdresaKanc, new GridBagConstraints(0, 6, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 50), 100, 15));
		info.add(tfAdresaKanc, new GridBagConstraints(1, 6, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		info.add(lblBrLicne, new GridBagConstraints(0, 7, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		info.add(tfBrLicne, new GridBagConstraints(1, 7, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		info.add(lblTitula, new GridBagConstraints(0, 8, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		info.add(comboTitule, new GridBagConstraints(1, 8, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		info.add(lblZvanje, new GridBagConstraints(0, 9, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		info.add(comboZvanja, new GridBagConstraints(1, 9, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		info.add(potvrdi, new GridBagConstraints(0, 10, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 150, 5, 5), 0, 0));
		info.add(odustani, new GridBagConstraints(1, 10, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
	}

	public Titule getTitulaFromString(String s) {
		switch (s) {
		case "BSc":
			return Titule.BSc;
		case "MSc":
			return Titule.MSc;
		case "PhD":
			return Titule.PhD;
		case "Dr":
			return Titule.dr;
		case "Mr":
			return Titule.mr;
		case "prof. Dr":
			return Titule.profDr;
		default:
			return null;
		}
	}

	public Zvanja getZvanjeFromString(String s) {
		switch (s) {
		case "Saradnik u nastavi":
			return Zvanja.saradnikNastava;
		case "Asistent":
			return Zvanja.asistent;
		case "Asistent sa doktoratom":
			return Zvanja.asistentDr;
		case "Docent":
			return Zvanja.docent;
		case "Vanredni profesor":
			return Zvanja.vanrProf;
		case "Redovni profesor":
			return Zvanja.redProf;
		case "Profesor emeritus":
			return Zvanja.profEmeritus;
		default:
			return null;
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

	public boolean existsLicna(String s, Profesor prof) {
		for (Profesor p : BazaProfesora.getInstance().getProfesori()) {
			if (s.equals(p.getBrLicne()) && p != prof)
				return true;
		}
		return false;
	}

}
