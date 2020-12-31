package view;

import java.awt.BorderLayout;
//import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;


//import com.sun.prism.paint.Color;
//import java.awt.color.*;

import java.awt.Toolkit;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static MainWindow instance = null;
	private JTabbedPane tabbedPane;
	private JTable tabelaStudenata;
	private JTable tabelaProfesora;
	private JTable tabelaPredmeta;

	private MainWindow() {
		tabbedPane = new JTabbedPane();
		initalise();
	}

	private void initalise() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setTitle("Studentska služba");
		setSize(3 * screenWidth / 4, 3 * screenHeight / 4);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		java.awt.Color banana = new java.awt.Color(255, 225, 53, 183);
		panel.setBackground(banana);

		this.add(panel);

		MyToolBar t = new MyToolBar(this);
		add(BorderLayout.NORTH, t);

		Menu menu = new Menu(this);
		this.setJMenuBar(menu);

		StatusBar statusBar = new StatusBar();
		this.add(statusBar, BorderLayout.SOUTH);

		this.add(tabbedPane, BorderLayout.CENTER);

		JPanel studenti = new JPanel(new BorderLayout());
		tabbedPane.addTab("Studenti", studenti);

		tabelaStudenata = new TabelaStudenata();

		JScrollPane scrollPaneStud = new JScrollPane(tabelaStudenata);
		studenti.add(scrollPaneStud, BorderLayout.CENTER);

		JPanel profesori = new JPanel(new BorderLayout());
		tabbedPane.addTab("Profesori", profesori);

		tabelaProfesora = new TabelaProfesora();

		JScrollPane scrollPaneProf = new JScrollPane(tabelaProfesora);
		profesori.add(scrollPaneProf, BorderLayout.CENTER);
		
		JPanel predmeti = new JPanel(new BorderLayout());
		tabbedPane.addTab("Predmeti", predmeti);

		tabelaPredmeta = new TabelaPredmeta();

		JScrollPane scrollPanePred = new JScrollPane(tabelaPredmeta);
		predmeti.add(scrollPanePred, BorderLayout.CENTER);
		
		azurirajPrikaz(null, -1);
	}

	public static MainWindow getInstance() {
		if (instance == null) {
			instance = new MainWindow();
		}
		return instance;
	}
	
	public int getTabIndex() {
		return tabbedPane.getSelectedIndex();
	}
	
	public void azurirajPrikaz(String akcija, int vrednost) {
		AbstractTableModelStudent modelStud = (AbstractTableModelStudent) tabelaStudenata.getModel();
		modelStud.fireTableDataChanged();
		
		AbstractTableModelProfesor modelProf = (AbstractTableModelProfesor) tabelaProfesora.getModel();
		modelProf.fireTableDataChanged();
		
		AbstractTableModelPredmet modelPred = (AbstractTableModelPredmet) tabelaPredmeta.getModel();
		modelPred.fireTableDataChanged();
		validate();
	}

}
