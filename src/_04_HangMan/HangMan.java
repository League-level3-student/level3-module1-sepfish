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
	
	//Lives start increasing if there are duplicate letters
	//You can't win but you can lose haha :(( (need way to figure out that all letters were guessed)
	
	Stack<String> words = new Stack<String>();
	Stack<String> labelText = new Stack<String>();
	Stack<Character> wordLetters = new Stack<Character>();
	int lives = 6;
	String randword;
	JFrame frame;
	JPanel panel;
	JLabel label;
	String labeltext;
	
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
		JOptionPane.showMessageDialog(null, "Guess a letter!");
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
		hehe.hehe();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char letterGuess = e.getKeyChar();
		for (int i = 0; i < wordLetters.size(); i++) {
			if (wordLetters.get(i).equals(letterGuess) && labelText.get(i).contentEquals("_")) {
				System.out.println("right");
				labelText.remove(i);
				String chara = Character.toString(wordLetters.get(i));
				labelText.add((i), chara);
				setLabelText();
				lives++;
			}
		}
		if (lives > 0) {
			lives--;
		} else {
			JOptionPane.showMessageDialog(null, "Oh no! You lost.");
		}
		
		System.out.println("Lives: " + lives);
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
