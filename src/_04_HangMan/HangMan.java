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
	
	//ask for help on this awful thing i'm moving on
	//run it multiple times you'll see the problem
	
	Stack<String> words = new Stack<String>();
	ArrayList<String> labelText;
	Stack<Character> wordLetters;
	int lives = 10;
	String randword;
	JFrame frame;
	JPanel panel;
	JLabel label;
	String labeltext;
	int letterCount = 0;
	int timesPlayed = 0; 
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
			JOptionPane.showMessageDialog(null, randword);
		}
		
		System.out.println("hehe() is finished");
	}
	
	public void hehehe() {
		System.out.println("hehehe() is started");
		wordLetters = new Stack<Character>();
		labelText = new ArrayList<String>();
		//it finally worked!!
		if (timesPlayed >= words.size()) {
			nextWord = false;
			timesPlayed = 0;
			lives = 10;
			yayIWon();
		} else { 
			for (int j = 0; j < words.get(timesPlayed).length(); j++) {
				wordLetters.push(words.get(timesPlayed).charAt(j));
			}
		}
		makeFrame();
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
		int hmmQ = JOptionPane.showOptionDialog(null, "You won! Do you want to play again?", "Is this a message?", 0, JOptionPane.INFORMATION_MESSAGE, null, new String[] {"No", "Yes"}, null);
		if (hmmQ == 1) {
			frame.dispose();
			System.out.println("yes");
			hehe();
			hehehe();
		} else {
			System.out.println("no");
			JOptionPane.showMessageDialog(null, "Have a nice day!");
			System.exit(0);
		}
	}
	
	public void makeFrame() {
		if(timesPlayed >0) {
			frame.dispose();
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
		JOptionPane.showMessageDialog(null, "size of labelText is " + labelText.size());
		setLabelText();
		frame.addKeyListener(this);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		HangMan hehe = new HangMan();
		hehe.hehe();
		hehe.hehehe();
		for (int i = 0; i < userNum;) {
			System.out.println(i);
			System.out.println(nextWord);
			if (nextWord) {
				System.out.println("set nextWord to false");
				nextWord = false;
				JOptionPane.showMessageDialog(null, "nextWord is: " + nextWord);
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