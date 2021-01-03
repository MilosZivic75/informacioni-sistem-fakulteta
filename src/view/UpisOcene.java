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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Ocena;
import model.Predmet;
import model.Student;

public class UpisOcene extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UpisOcene(JDialog parent, String title, boolean mod, Student student, Predmet predmet) {
		super(parent, title, mod);

		Dimension dim = parent.getSize();
		setSize(dim.width / 2, dim.height / 2);
		setLocationRelativeTo(parent);

		JPanel panel = new JPanel(new GridBagLayout());
		JButton potvrdi = new JButton("Potvrdi");

		JLabel lblSifra = new JLabel("Šifra*");
		JTextField tfSifra = new JTextField(predmet.getSifra(), 15);
		tfSifra.setEditable(false);

		JLabel lblNaziv = new JLabel("Naziv*");
		JTextField tfNaziv = new JTextField(predmet.getNaziv(), 15);
		tfNaziv.setEditable(false);

		JLabel lblOcena = new JLabel("Ocena*");
		Integer[] ocene = { 6, 7, 8, 9, 10 };
		JComboBox<Integer> comboOcene = new JComboBox<Integer>(ocene);
		comboOcene.setSelectedIndex(0);

		JLabel lblDatum = new JLabel("Datum*");
		JTextField tfDatum = new JTextField(15);
		
		tfDatum.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if (tfDatum.getText().trim().isEmpty()
						|| !tfDatum.getText().matches("[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}")) {
					potvrdi.setEnabled(false);
					tfDatum.setText("Neispravan unos! (dd/MM/yyyy)");
				} else
					potvrdi.setEnabled(true);
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (tfDatum.getText().equals("Neispravan unos! (dd/MM/yyyy)"))
					tfDatum.setText("");				
			}
		});
		
		potvrdi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Ocena ocena = new Ocena(student, predmet, (Integer)comboOcene.getSelectedItem(), getDate(tfDatum.getText().trim()));
				student.getPolIspiti().add(ocena);
				student.getNepolIspiti().remove(predmet);
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
		
		panel.add(lblSifra, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		panel.add(tfSifra, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panel.add(lblNaziv, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		panel.add(tfNaziv, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panel.add(lblOcena, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		panel.add(comboOcene, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panel.add(lblDatum, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 50), 100, 15));
		panel.add(tfDatum, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panel.add(potvrdi, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 150, 5, 5), 0, 0));
		panel.add(odustani, new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));

		add(panel, BorderLayout.CENTER);
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
