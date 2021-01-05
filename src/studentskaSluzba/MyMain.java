package studentskaSluzba;

import java.io.FileNotFoundException;
import java.io.IOException;

import view.MainWindow;

public class MyMain {

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		MainWindow mw = MainWindow.getInstance(); 
		mw.setVisible(true);

	}

}
