package _01_IntroToArrayLists;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


//Copyright The League of Amazing Programmers, 2015

public class _06_IPodShuffle implements ActionListener{
	
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton surprise = new JButton();
	ArrayList<Song> playlist = new ArrayList<Song>();
	
	void setUp() {
		surprise.setText("Surprise Me!");
		surprise.addActionListener(this);
		panel.add(surprise);
		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setTitle("iPod Shuffle");
		
		playlist.add(new Song("nights.mp3"));
		playlist.add(new Song("monochrome.mp3"));
		playlist.add(new Song("stars.mp3"));
		playlist.add(new Song("art.mp3"));
		playlist.add(new Song("phone.mp3"));
	}
	
	
	public _06_IPodShuffle() {
		// 1. Use the Song class the play the demo.mp3 file.
			//Song song = new Song("demo.mp3");
			//song.play();
				
		/**
		 * 2. Congratulations on completing the sound check! * Now we want to make an
		 * iPod Shuffle that plays random music. * Create an ArrayList of Songs and a
		 * "Surprise Me!" button that will play a random song when it is clicked. * If
		 * you're really cool, you can stop all the songs, before playing a new one on
		 * subsequent button clicks.
		 */
		
	}
	
	
	
	public static void main(String[] args) {
		_06_IPodShuffle ipod = new _06_IPodShuffle();
		ipod.setUp();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(surprise)) {
			for (Song s: playlist) {
				s.stop();
			}
			Random random = new Random();
			int rand = random.nextInt(5);
			playlist.get(rand).play();
		}
	}
}