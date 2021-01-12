package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import model.BazaPredmeta;
import model.BazaProfesora;
import model.BazaStudenata;
import model.Predmet;
import model.Profesor;
import model.Student;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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

	private MainWindow() throws FileNotFoundException, IOException, ClassNotFoundException {
		tabbedPane = new JTabbedPane();
		initialise();
	}

	@SuppressWarnings("unchecked")
	private void initialise() throws FileNotFoundException, IOException, ClassNotFoundException {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setTitle("Studentska služba");
		setSize(3 * screenWidth / 4, 3 * screenHeight / 4);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		try {
			File f = new File("studentstream.txt");
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
			try {
				BazaStudenata.getInstance().setStudenti((ArrayList<Student>) ois.readObject());
			} finally {
				ois.close();
			}
		} catch (Exception e) {

		}
		
		try {
			File f = new File("predmetstream.txt");
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
			try {
				BazaPredmeta.getInstance().setPredmeti((ArrayList<Predmet>) ois.readObject());
			} finally {
				ois.close();
			}
		} catch (Exception e) {

		}
		
		try {
			File f = new File("profesorstream.txt");
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
			try {
				BazaProfesora.getInstance().setProfesori((ArrayList<Profesor>) ois.readObject());
			} finally {
				ois.close();
			}
		} catch (Exception e) {

		}

		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				String[] options = new String[2];
				options[0] = new String("Da");
				options[1] = new String("Ne");
				int option = JOptionPane.showOptionDialog(instance, "Da li želite da napustite aplikaciju?",
						"Izlazak iz aplikacije", 0, JOptionPane.QUESTION_MESSAGE, null, options, null);
				if (option == JOptionPane.YES_OPTION) {
					File fs = new File("studentstream.txt");
					File fp = new File("predmetstream.txt"); 
					File fprof = new File("profesorstream.txt");
					ObjectOutputStream oos;
					try {
						oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fs)));
						try {
							oos.writeObject(BazaStudenata.getInstance().getStudenti());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} finally {
							try {
								oos.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					try {
						oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fp)));
						try {
							oos.writeObject(BazaPredmeta.getInstance().getPredmeti());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} finally {
							try {
								oos.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					try {
						oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fprof)));
						try {
							oos.writeObject(BazaProfesora.getInstance().getProfesori());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} finally {
							try {
								oos.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					instance.dispose();
				}

			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});

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

		Icon icon = new ImageIcon("images/arrow_two_head_2_icon&16.png");

		tabelaStudenata = new TabelaStudenata();
		TableColumn col = tabelaStudenata.getColumnModel().getColumn(0);
		col.setHeaderRenderer(new ButtonColumnStudenti(new JButton("Indeks", icon)));
		col = tabelaStudenata.getColumnModel().getColumn(1);
		col.setHeaderRenderer(new ButtonColumnStudenti(new JButton("Ime", icon)));
		col = tabelaStudenata.getColumnModel().getColumn(2);
		col.setHeaderRenderer(new ButtonColumnStudenti(new JButton("Prezime", icon)));
		col = tabelaStudenata.getColumnModel().getColumn(3);
		col.setHeaderRenderer(new ButtonColumnStudenti(new JButton("Godina studija", icon)));
		col = tabelaStudenata.getColumnModel().getColumn(4);
		col.setHeaderRenderer(new ButtonColumnStudenti(new JButton("Status", icon)));
		col = tabelaStudenata.getColumnModel().getColumn(5);
		col.setHeaderRenderer(new ButtonColumnStudenti(new JButton("Prosek", icon)));

		JScrollPane scrollPaneStud = new JScrollPane(tabelaStudenata);
		studenti.add(scrollPaneStud, BorderLayout.CENTER);

		JPanel profesori = new JPanel(new BorderLayout());
		tabbedPane.addTab("Profesori", profesori);

		tabelaProfesora = new TabelaProfesora();
		col = tabelaProfesora.getColumnModel().getColumn(0);
		col.setHeaderRenderer(new ButtonColumnProfesori(new JButton("Ime", icon)));
		col = tabelaProfesora.getColumnModel().getColumn(1);
		col.setHeaderRenderer(new ButtonColumnProfesori(new JButton("Prezime", icon)));
		col = tabelaProfesora.getColumnModel().getColumn(2);
		col.setHeaderRenderer(new ButtonColumnProfesori(new JButton("Titula", icon)));
		col = tabelaProfesora.getColumnModel().getColumn(3);
		col.setHeaderRenderer(new ButtonColumnProfesori(new JButton("Zvanje", icon)));

		JScrollPane scrollPaneProf = new JScrollPane(tabelaProfesora);
		profesori.add(scrollPaneProf, BorderLayout.CENTER);

		JPanel predmeti = new JPanel(new BorderLayout());
		tabbedPane.addTab("Predmeti", predmeti);

		tabelaPredmeta = new TabelaPredmeta();
		col = tabelaPredmeta.getColumnModel().getColumn(0);
	    col.setHeaderRenderer(new ButtonColumnPredmeti( new JButton("Šifra predmeta", icon)));
	    col = tabelaPredmeta.getColumnModel().getColumn(1);
	    col.setHeaderRenderer(new ButtonColumnPredmeti( new JButton("Naziv predmeta", icon)));
	    col = tabelaPredmeta.getColumnModel().getColumn(2);
	    col.setHeaderRenderer(new ButtonColumnPredmeti( new JButton("Broj ESPB bodova", icon)));
	    col = tabelaPredmeta.getColumnModel().getColumn(3);
	    col.setHeaderRenderer(new ButtonColumnPredmeti( new JButton("Godina izvođenja predmeta", icon)));
	    col = tabelaPredmeta.getColumnModel().getColumn(4);
	    col.setHeaderRenderer(new ButtonColumnPredmeti( new JButton("Semestar izvođenja predmeta", icon)));

		JScrollPane scrollPanePred = new JScrollPane(tabelaPredmeta);
		predmeti.add(scrollPanePred, BorderLayout.CENTER);

		azurirajPrikaz(null, -1);
	}

	public static MainWindow getInstance() throws FileNotFoundException, ClassNotFoundException, IOException {
		if (instance == null) {
			instance = new MainWindow();
		}
		return instance;
	}

	public int getTabIndex() {
		return tabbedPane.getSelectedIndex();
	}

	public int getStudentRow() {
		return tabelaStudenata.getSelectedRow();
	}

	public int getProfesorRow() {
		return tabelaProfesora.getSelectedRow();
	}

	public int getPredmetRow() {
		return tabelaPredmeta.getSelectedRow();
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
