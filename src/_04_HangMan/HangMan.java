package _04_HangMan;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.SynchronousQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class HangMan implements KeyListener{
	
	//make it so that the ex-hmm loop in the main method (now with nextWord (tm)) only loops if a key is pressed :)
	
	Stack<String> words = new Stack<String>();
	ArrayList<String> labelText = new ArrayList<String>();
	Stack<Character> wordLetters = new Stack<Character>();
	int lives = 6;
	String randword;
	JFrame frame;
	JPanel panel;
	JLabel label;
	String labeltext;
	int letterCount = 0;
	static int timesPlayed = 0; 
	static int userNum;
	static boolean nextWord = false;
	
	public void hehe() {
		String userInput = JOptionPane.showInputDialog("How many words would you like to guess?\n(Enter a number between 1 and 266, inclusive.)");
		userNum = Integer.parseInt(userInput);
		for (int i = 0; i < userNum; i++) {
			randword = Utilities.readRandomLineFromFile("dictionary.txt");
			if (!words.contains(randword)) {
				words.push(randword);
			}
			System.out.println(randword);
		}
		
		System.out.println("hehe() is finished");
	}
	
	public void hehehe() {
		System.out.println("hehehe() is started");
		for (int j = 0; j < words.get(timesPlayed).length(); j++) {
			wordLetters.push(words.get(timesPlayed).charAt(j));
		}
		frame = new JFrame();
		panel = new JPanel();
		label = new JLabel();
		panel.add(label);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("frame created");
		for (int i = 0; i < wordLetters.size(); i++) {
			labelText.add("_");
		}
		setLabelText();
		frame.addKeyListener(this);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void setLabelText() {
		labeltext = "";
		for (int i = 0; i < labelText.size(); i++) {
			labeltext = labeltext + labelText.get(i);
		}
		label.setText(labeltext + " Lives: " + lives);
		System.out.println("label text is set");
	}
	
	public void yayIWon() {
		int hmmQ = JOptionPane.showOptionDialog(null, "You won! Do you want to play again?", "YES OR NO!!!!!", 0, JOptionPane.INFORMATION_MESSAGE, null, new String[] {"No", "Yes"}, null);
		if (hmmQ == 1) {
			frame.dispose();
			System.out.println("yes");
			hehe();
		} else {
			System.out.println("no");
			JOptionPane.showMessageDialog(null, "Have a nice day!");
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		HangMan hehe = new HangMan();
		hehe.hehe();
		hehe.hehehe();
		for (int i = 0; i < userNum;) {
			System.out.println(i);
			if (nextWord) {
				System.out.println("set nextWord to false");
				nextWord = false;
				hehe.hehehe();
				i++;
				System.out.println("hehehe() should run again");
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char letterGuess = e.getKeyChar();
		boolean found = false;
		for (int i = 0; i < wordLetters.size(); i++) {
			if (wordLetters.get(i).equals(letterGuess) && labelText.get(i).contentEquals("_")) {
				labelText.remove(i);
				labelText.add((i), String.valueOf(letterGuess));
				found = true;
				setLabelText();
			}
		}
		if(!found) {
			lives--;
			setLabelText();
		}
		if (labeltext.indexOf('_') < 0 && timesPlayed < userNum) {
			JOptionPane.showMessageDialog(null, "You guessed the word!");
			frame.dispose();
			timesPlayed++;
			nextWord = true;
		} else if (labeltext.indexOf('_') < 0) {
			yayIWon();
		}
		if (lives == 0) {
			JOptionPane.showMessageDialog(null, "Oh no! You lost the game.");
			JOptionPane.showMessageDialog(null, "The word was '" + randword + "'. Better luck next time!");
			System.exit(0);
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}