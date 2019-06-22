package _04_HangMan;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class HangMan implements KeyListener{
	
	//need to be able to start over!!!
	//need to be able to separate multiple words in the wordLetters when you start over
	//idea: make an int, first time you play that int is set to 0, as you play more that int++
	//		every time you play again, the word at that int is pushed 
	//		now how do you implement that :(
	
	Stack<String> words = new Stack<String>();
	Stack<String> labelText = new Stack<String>();
	Stack<Character> wordLetters = new Stack<Character>();
	int lives = 6;
	String randword;
	JFrame frame;
	JPanel panel;
	JLabel label;
	String labeltext;
	int letterCount = 0;
	static boolean playAgain = true;
	
	public void hehe() {
		String userInput = JOptionPane.showInputDialog("Enter a number between 1 and 266, inclusive.");
		int userNum = Integer.parseInt(userInput);
		for (int i = 0; i < userNum; i++) {
			randword = Utilities.readRandomLineFromFile("dictionary.txt");
			if (!words.contains(randword)) {
				words.push(randword);
			}
		}
		for (int j = 0; j < randword.length(); j++) {
			wordLetters.push(randword.charAt(j));
		}
		System.out.println(randword);
		frame = new JFrame();
		panel = new JPanel();
		label = new JLabel();
		panel.add(label);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for (int i = 0; i < randword.length(); i++) {
			labelText.push("_");
		}
		setLabelText();
		frame.addKeyListener(this);
		frame.pack();
		frame.setVisible(true);
		JOptionPane.showMessageDialog(null, "Guess a letter! You have " + lives + " chances to guess.");
	}
	
	public void setLabelText() {
		labeltext = "";
		for (int i = 0; i < labelText.size(); i++) {
			labeltext = labeltext + labelText.get(i);
		}
		label.setText(labeltext);
	}
	
	public static void main(String[] args) {
		HangMan hehe = new HangMan();
		if (playAgain) {
			hehe.hehe();
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
				setLabelText();
				found = true;
			}
		}
		if(!found) {
			lives--;
			JOptionPane.showMessageDialog(null, "You lost a life! You have " + lives + " lives left.");
		}
		if (labeltext.indexOf('_') < 0) {
			int hmmQ = JOptionPane.showOptionDialog(null, "You won! Do you want to play again?", "YES OR NO!!!!!", 0, JOptionPane.INFORMATION_MESSAGE, null, new String[] {"Yes", "No"}, null);
			if (hmmQ == 1) {
				JOptionPane.showMessageDialog(null, "Oh, okay. Have a nice day!");
				System.exit(0);
			}
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