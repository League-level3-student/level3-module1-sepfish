package _03_IntroToStacks;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Stack;

public class _02_TextUndoRedo implements KeyListener {
	/* 
	 * Create a JFrame with a JPanel and a JLabel.
	 * 
	 * Every time a key is pressed, add that character to the JLabel. It should look like a basic text editor.
	 * 
	 * Make it so that every time the BACKSPACE key is pressed, the last character is erased from the JLabel.
	 * Save that deleted character onto a Stack of Characters.
	 * 
	 * Choose a key to be the Undo key. Make it so that when that key is pressed, the top Character is popped 
	 * off the Stack and added back to the JLabel.
	 * 
	 * */
	
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel label = new JLabel();
	String labelText = "";
	Stack<String> typed = new Stack<String>();
	Stack<String> deleted = new Stack<String>();
	
	void reat() {
		panel.add(label);
		frame.add(panel);
		frame.addKeyListener(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		_02_TextUndoRedo g = new _02_TextUndoRedo();
		g.reat();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getExtendedKeyCode() == 8) {
			deleted.add(typed.pop());
		} else if (e.getExtendedKeyCode() == 90) {
			typed.add(deleted.pop());
		} else {
			char charar = e.getKeyChar();
			String chara = Character.toString(charar);
			typed.push(chara);
		}
		labelText = "";
		for (int i = 0; i < typed.size(); i++) {
			labelText = labelText + typed.get(i);
		}
		label.setText(labelText);
		frame.pack();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
