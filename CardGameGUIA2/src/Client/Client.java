package Client;

import javax.swing.SwingUtilities;
/****
 * @author Karan
 */

import view.Appframe;

public class Client {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				new Appframe();
			}	
		});
	}
	

}
