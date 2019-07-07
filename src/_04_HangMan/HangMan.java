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
	
	//lives do not reset
	
	Stack<String> words = new Stack<String>();
	ArrayList<String> labelText;
	Stack<Character> wordLetters;
	static int lives = 10;
	static String randword;
	JFrame frame;
	JPanel panel;
	JLabel label;
	String labeltext;
	int letterCount = 0;
	int timesPlayed = 0; 
	static int userNum;
	static boolean nextWord = false;
	
	static boolean winTemp = true;
	
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
		winTemp = false;
		System.out.println("hehe() is finished");
	}
	
	public void hehehe() {
		System.out.println("hehehe() is started");
		if (wordLetters == null) {
			wordLetters = new Stack<Character>();
		}
		if (labelText == null) {
			labelText = new ArrayList<String>();
		}
		wordLetters.clear();
		labelText.clear();
		//it finally worked!!
		if (timesPlayed >= words.size()) {
			frame.dispose();
			winTemp = true;
			nextWord = false;
			timesPlayed = 0;
			lives = 10;
			yayIWon();
		} else { 
			for (int j = 0; j < words.get(timesPlayed).length(); j++) {
				wordLetters.push(words.get(timesPlayed).charAt(j));
			}
			makeFrame();
		}
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
			//hehe();			/* TIM: do not call */
			//hehehe();		/* TIM: do not call */
			/* TIM: here, set the boolean variable to show that all words were won */
		} else {
			lives = 0;
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
		
		
		/* TIM: this is the start of the while loop */
		do {
			winTemp = false;
			hehe.hehe();
			hehe.hehehe();
			for (int i = 0; i < userNum;) {
				if (nextWord) {
					System.out.println("set nextWord to false");
					nextWord = false;
					lives = 10;
					JOptionPane.showMessageDialog(null, "nextWord is: " + nextWord);
					hehe.hehehe();
					i++;
					System.out.println("hehehe() should run again");
				}
			}
			
			/* TIM: here, check if user won all words */
			/* TIM: if so, stay in while loop */
			/* TIM: if not, exit while loop and display your ending message */
			/* TIM: end of the while loop */
		} while (winTemp && (lives>0));
		if (lives==0) {
			JOptionPane.showMessageDialog(null, "Oh no! You lost the game.");
			JOptionPane.showMessageDialog(null, "The word was '" + randword + "'. Better luck next time!");
		} else {
			JOptionPane.showMessageDialog(null, "Have a nice day!");
		}
		System.exit(0);
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