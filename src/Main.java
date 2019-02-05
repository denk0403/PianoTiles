import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
//		LoopedList<Integer> llInt = new LoopedList<>(100,3);
//		llInt.add(2);
//		llInt.add(4);
//		llInt.add(5);
//		llInt.add(7);
//		llInt.add(9);
//		llInt.add(1);
//		for(int i = 0; i < 20; i++) {
//			System.out.println(llInt.get(i));
//		}
		JFrame frame = new JFrame("PianoTiles");
		
		// creates main window for game
		frame.setSize(new Dimension(450, 800));
		frame.setResizable(false);

		// makes the "close" button simply exit out of the application
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// centers the frame on screen
		frame.setLocationRelativeTo(null);
		
		Song s1 = new Song();
		frame.add(s1);
		frame.setVisible(true);
		Thread t1 = new Thread(() -> {
			while(!s1.missed()) {
				try {
					Thread.sleep(5000);
					s1.incrementSongSpeed(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		t1.start();
		while(!s1.missed()) {
			try {
				frame.repaint();
				s1.checkForMissed();
				Thread.sleep(25);
				s1.moveSongPos();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		while(s1.rollingback()) {
			frame.repaint();
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
