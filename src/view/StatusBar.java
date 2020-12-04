package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class StatusBar extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StatusBar() {
		setLayout(new BorderLayout());
		java.awt.Color juneau_spring=new java.awt.Color(102, 181, 166, 250);
		setBackground(juneau_spring);
		
		JPanel rightPan = new JPanel(new FlowLayout());
		rightPan.setBackground(juneau_spring);
		
		new Timer(0, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Date d = new Date();
				SimpleDateFormat s = new SimpleDateFormat("HH:mm dd.MM.yyyy.");
				JLabel time = new JLabel(s.format(d));
				rightPan.removeAll();
				rightPan.add(time, BorderLayout.EAST);
				validate();
				
			}
		}).start();
		
		add(new JLabel("Studentska sluzba"), BorderLayout.WEST);
		add(rightPan, BorderLayout.EAST);
		
	}
	
}
