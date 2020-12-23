package view;

import java.awt.BorderLayout;
//import java.awt.BorderLayout;
import java.awt.Dimension;


import javax.swing.JFrame;
import javax.swing.JPanel;

//import com.sun.prism.paint.Color;
//import java.awt.color.*;

import java.awt.Toolkit;



public class MainWindow extends JFrame{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	private static MainWindow instance =null;

	private MainWindow() {
	   initalise();
	}
	


	private void initalise(){
		// TODO Auto-generated constructor stub
	    Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setTitle("Studentska služba");
		setSize(3*screenWidth /4, 3*screenHeight /4);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLocationRelativeTo(null);
		
		JPanel panel=new JPanel();
		java.awt.Color banana=new java.awt.Color(255, 225, 53, 183);
		panel.setBackground(banana);
		
	    this.add(panel);
		
		
		MyToolBar t = new MyToolBar();
		add(BorderLayout.NORTH, t);
		
		Menu menu = new Menu(this);
		this.setJMenuBar(menu);
		
		StatusBar statusBar = new StatusBar();
		this.add(statusBar, BorderLayout.SOUTH);
	}
	
	 
	    
	    public static MainWindow getInstance(){
	    	if(instance==null){
	        instance = new MainWindow();
	    }
	    	return instance;
	    }

}
