package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import controller.PredmetiController;
import controller.ProfesoriController;
import controller.StudentiController;
import model.BazaStudenata;
import model.Student;

public class MyToolBar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyToolBar(final JFrame parent) {
		super(SwingConstants.HORIZONTAL);
		// dodaj
		Icon a = new ImageIcon("images/add_n.png");

		JButton dodaj = new JButton(a);

		dodaj.setToolTipText("kreiranje entiteta informacionog sistema ");

		dodaj.setContentAreaFilled(false);
		dodaj.setFocusPainted(false);
		dodaj.setBorderPainted(false);

		add(dodaj);

		addSeparator();

		dodaj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (MainWindow.getInstance().getTabIndex() == 0) {
					StudentiController.getInstance().dodajStudenta(parent);
				} else if (MainWindow.getInstance().getTabIndex() == 1) {
					ProfesoriController.getInstance().dodajProfesora(parent);
				} else if (MainWindow.getInstance().getTabIndex() == 2) {
					PredmetiController.getInstance().dodajPredmet(parent);
				}

			}
		});

		// izmeni
		Icon b = new ImageIcon("images/edit_n.png");

		JButton izmeni = new JButton(b);

		izmeni.setToolTipText("otvaranje dijaloga za izmenu označenog entiteta");

		izmeni.setContentAreaFilled(false);
		izmeni.setFocusPainted(false);
		izmeni.setBorderPainted(false);

		add(izmeni);

		addSeparator();

		izmeni.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (MainWindow.getInstance().getTabIndex() == 0) {
					StudentiController.getInstance().izmeniStudenta(parent);
				} else if (MainWindow.getInstance().getTabIndex() == 1) {
					ProfesoriController.getInstance().izmeniProfesora(parent);
				} else if (MainWindow.getInstance().getTabIndex() == 2) {
					PredmetiController.getInstance().izmeniPredmet(parent);
				}
			}
		});

		// obrisi
		Icon c = new ImageIcon("images/delete_n.png");

		JButton obrisi = new JButton(c);

		obrisi.setToolTipText(" brisanje označenog eniteta iz sistema");

		obrisi.setContentAreaFilled(false);
		obrisi.setFocusPainted(false);
		obrisi.setBorderPainted(false);

		add(obrisi);

		obrisi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (MainWindow.getInstance().getTabIndex() == 0) {
					StudentiController.getInstance().obrisiStudenta(parent);
				} else if (MainWindow.getInstance().getTabIndex() == 1) {
					ProfesoriController.getInstance().obrisiProfesora(parent);
				} else if (MainWindow.getInstance().getTabIndex() == 2) {
					PredmetiController.getInstance().obrisiPredmet(parent);
				}
			}
		});

		java.awt.Color juneau_spring = new java.awt.Color(102, 181, 166, 250);
		JPanel pan = new JPanel();
		pan.setBackground(juneau_spring);
		BoxLayout box = new BoxLayout(pan, BoxLayout.X_AXIS);
		pan.setLayout(box);
		pan.add(Box.createGlue());
		Dimension dim = new Dimension(5000, 26);
		JTextField txt = new JTextField();

		pan.add(txt);
		txt.setMaximumSize(dim);
		txt.setMinimumSize(dim);
		add(pan);

		Icon q = new ImageIcon("images/search_n.png");
		JButton pronadji = new JButton(q);

		pronadji.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BazaStudenata.getInstance().setFilter(txt.getText().trim());
				BazaStudenata.getInstance().setFiltriraniStudenti(new ArrayList<Student>());
				MainWindow.getInstance().azurirajPrikaz("FILTER", -1);

			}
		});

		pronadji.setToolTipText("pronadji oznaceni entitet u sistemu");
		pronadji.setContentAreaFilled(false);
		pronadji.setFocusPainted(false);
		pronadji.setBorderPainted(false);
		add(pronadji);

		setFloatable(false);

		setBackground(juneau_spring);

	}

}
