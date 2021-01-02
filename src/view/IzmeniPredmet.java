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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import model.BazaPredmeta;
import model.Predmet;
import model.Predmet.Semestar;

public class IzmeniPredmet extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IzmeniPredmet(JFrame parent, String title, boolean mod, int row) {
		super(parent, title, mod);

		Dimension dim = parent.getSize();
		setSize(dim.width / 2, 2 * dim.height / 3);
		setLocationRelativeTo(parent);

		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel info = new JPanel(new GridBagLayout());
		JPanel profesori = new JPanel();
		JPanel suPolozili = new JPanel();
		JPanel nisuPolozili = new JPanel();

		add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("Informacije", info);
		tabbedPane.addTab("Profesori", profesori);
		tabbedPane.addTab("Studenti položili", suPolozili);
		tabbedPane.addTab("Studenti nisu položili", nisuPolozili);
		
		Predmet predmet = BazaPredmeta.getInstance().getPredmeti().get(row);
		
		JButton potvrdi = new JButton("Potvrdi");

		JLabel lblSifra = new JLabel("Šifra predmeta*");
		JTextField tfSifra = new JTextField(predmet.getSifra(), 15);

		JLabel lblNaziv = new JLabel("Naziv predmeta*");
		JTextField tfNaziv = new JTextField(predmet.getNaziv(), 15);

		JLabel lblEspb = new JLabel("Broj ESPB bodova*");
		JTextField tfEspb = new JTextField(Integer.toString(predmet.getEspb()), 15);

		JLabel lblGodina = new JLabel("Godina na kojoj se predmet izvodi*");
		JTextField tfGodina = new JTextField(Integer.toString(predmet.getGodStudija()), 15);

		JLabel lblSemestar = new JLabel("Semestar u kome se predmet izvodi*");
		String[] semestri = { "Zimski", "Letnji" };
		JComboBox<String> comboSemestri = new JComboBox<String>(semestri);
		comboSemestri.setSelectedItem((predmet.getSemestar() == Semestar.zimski) ? "Zimski" : "Letnji");

		tfSifra.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (tfSifra.getText().trim().isEmpty() || !tfSifra.getText().matches("[A-Za-zŠšŽžĐđĆćČč0-9.]+")) {
					potvrdi.setEnabled(false);
					tfSifra.setText("Neispravan unos!");
				} else if (existsSifra(tfSifra.getText().trim(), predmet)) {
					potvrdi.setEnabled(false);
					tfSifra.setText("Šifra predmeta mora biti jedinstvena!");
				} else if (tfNaziv.getText().trim().isEmpty()
						|| !tfNaziv.getText().matches("[A-Za-zŠšŽžĐđĆćČč0-9 ]+")) {
					potvrdi.setEnabled(false);
					tfNaziv.setText("Neispravan unos!");
				} else if (tfEspb.getText().trim().isEmpty() || !tfEspb.getText().matches("[0-9]{1,2}")
						|| Integer.parseInt(tfEspb.getText().trim()) < 1
						|| Integer.parseInt(tfEspb.getText().trim()) > 60) {
					potvrdi.setEnabled(false);
					tfEspb.setText("Neispravan unos! (1-60)");
				} else if (tfGodina.getText().trim().isEmpty() || !tfGodina.getText().matches("[1-9]")) {
					potvrdi.setEnabled(false);
					tfGodina.setText("Neispravan unos!");
				} else {
					potvrdi.setEnabled(true);
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (tfSifra.getText().equals("Neispravan unos!")
						|| tfSifra.getText().equals("Šifra predmeta mora biti jedinstvena!"))
					tfSifra.setText("");

			}
		});

		tfNaziv.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (tfSifra.getText().trim().isEmpty() || !tfSifra.getText().matches("[A-Za-zŠšŽžĐđĆćČč0-9.]+")) {
					potvrdi.setEnabled(false);
					tfSifra.setText("Neispravan unos!");
				} else if (existsSifra(tfSifra.getText().trim(), predmet)) {
					potvrdi.setEnabled(false);
					tfSifra.setText("Šifra predmeta mora biti jedinstvena!");
				} else if (tfNaziv.getText().trim().isEmpty()
						|| !tfNaziv.getText().matches("[A-Za-zŠšŽžĐđĆćČč0-9 ]+")) {
					potvrdi.setEnabled(false);
					tfNaziv.setText("Neispravan unos!");
				} else if (tfEspb.getText().trim().isEmpty() || !tfEspb.getText().matches("[0-9]{1,2}")
						|| Integer.parseInt(tfEspb.getText().trim()) < 1
						|| Integer.parseInt(tfEspb.getText().trim()) > 60) {
					potvrdi.setEnabled(false);
					tfEspb.setText("Neispravan unos! (1-60)");
				} else if (tfGodina.getText().trim().isEmpty() || !tfGodina.getText().matches("[1-9]")) {
					potvrdi.setEnabled(false);
					tfGodina.setText("Neispravan unos!");
				} else {
					potvrdi.setEnabled(true);
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (tfNaziv.getText().equals("Neispravan unos!"))
					tfNaziv.setText("");

			}
		});

		tfEspb.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (tfSifra.getText().trim().isEmpty() || !tfSifra.getText().matches("[A-Za-zŠšŽžĐđĆćČč0-9.]+")) {
					potvrdi.setEnabled(false);
					tfSifra.setText("Neispravan unos!");
				} else if (existsSifra(tfSifra.getText().trim(), predmet)) {
					potvrdi.setEnabled(false);
					tfSifra.setText("Šifra predmeta mora biti jedinstvena!");
				} else if (tfNaziv.getText().trim().isEmpty()
						|| !tfNaziv.getText().matches("[A-Za-zŠšŽžĐđĆćČč0-9 ]+")) {
					potvrdi.setEnabled(false);
					tfNaziv.setText("Neispravan unos!");
				} else if (tfEspb.getText().trim().isEmpty() || !tfEspb.getText().matches("[0-9]{1,2}")
						|| Integer.parseInt(tfEspb.getText().trim()) < 1
						|| Integer.parseInt(tfEspb.getText().trim()) > 60) {
					potvrdi.setEnabled(false);
					tfEspb.setText("Neispravan unos! (1-60)");
				} else if (tfGodina.getText().trim().isEmpty() || !tfGodina.getText().matches("[1-9]")) {
					potvrdi.setEnabled(false);
					tfGodina.setText("Neispravan unos!");
				} else {
					potvrdi.setEnabled(true);
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (tfEspb.getText().equals("Neispravan unos! (1-60)"))
					tfEspb.setText("");

			}
		});

		tfGodina.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (tfSifra.getText().trim().isEmpty() || !tfSifra.getText().matches("[A-Za-zŠšŽžĐđĆćČč0-9.]+")) {
					potvrdi.setEnabled(false);
					tfSifra.setText("Neispravan unos!");
				} else if (existsSifra(tfSifra.getText().trim(), predmet)) {
					potvrdi.setEnabled(false);
					tfSifra.setText("Šifra predmeta mora biti jedinstvena!");
				} else if (tfNaziv.getText().trim().isEmpty()
						|| !tfNaziv.getText().matches("[A-Za-zŠšŽžĐđĆćČč0-9 ]+")) {
					potvrdi.setEnabled(false);
					tfNaziv.setText("Neispravan unos!");
				} else if (tfEspb.getText().trim().isEmpty() || !tfEspb.getText().matches("[0-9]{1,2}")
						|| Integer.parseInt(tfEspb.getText().trim()) < 1
						|| Integer.parseInt(tfEspb.getText().trim()) > 60) {
					potvrdi.setEnabled(false);
					tfEspb.setText("Neispravan unos! (1-60)");
				} else if (tfGodina.getText().trim().isEmpty() || !tfGodina.getText().matches("[1-9]")) {
					potvrdi.setEnabled(false);
					tfGodina.setText("Neispravan unos!");
				} else {
					potvrdi.setEnabled(true);
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (tfGodina.getText().equals("Neispravan unos!"))
					tfGodina.setText("");
			}
		});

		potvrdi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				BazaPredmeta.getInstance().izmeniPredmet(tfSifra.getText().trim(), tfNaziv.getText().trim(),
						getSemestar((String) comboSemestri.getSelectedItem()),
						Integer.parseInt(tfGodina.getText().trim()), Integer.parseInt(tfEspb.getText().trim()), row);
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

		info.add(lblSifra, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		info.add(tfSifra, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		info.add(lblNaziv, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		info.add(tfNaziv, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		info.add(lblEspb, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		info.add(tfEspb, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		info.add(lblGodina, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		info.add(tfGodina, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		info.add(lblSemestar, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 50), 100, 15));
		info.add(comboSemestri, new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		info.add(potvrdi, new GridBagConstraints(0, 5, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 150, 5, 5), 0, 0));
		info.add(odustani, new GridBagConstraints(1, 5, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
	}
	
	public Semestar getSemestar(String s) {
		switch (s) {
		case "Zimski":
			return Semestar.zimski;
		case "Letnji":
			return Semestar.letnji;
		default:
			return null;
		}
	}

	public boolean existsSifra(String s, Predmet pred) {
		for (Predmet p : BazaPredmeta.getInstance().getPredmeti()) {
			if (s.equals(p.getSifra()) && p != pred)
				return true;
		}
		return false;
	}

}
