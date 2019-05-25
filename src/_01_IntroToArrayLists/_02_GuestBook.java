package _01_IntroToArrayLists;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class _02_GuestBook implements ActionListener {
	// Create a GUI with two buttons. One button reads "Add Name" and the other button reads "View Names". 
	// When the add name button is clicked, display an input dialog that asks the user to enter a name. Add
	// that name to an ArrayList. When the "View Names" button is clicked, display a message dialog that displays
	// all the names added to the list. Format the list as follows:
	// Guest #1: Bob Banders
	// Guest #2: Sandy Summers
	// Guest #3: Greg Ganders
	// Guest #4: Donny Doners
	
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton addName = new JButton();
	JButton viewName = new JButton();
	ArrayList<String> names = new ArrayList();
	
	void reat() {
		addName.setText("Add Name");
		addName.setToolTipText("Add names to the guestbook");
		addName.addActionListener(this);
		viewName.setText("View Names");
		viewName.setToolTipText("View names in the guestbook");
		viewName.addActionListener(this);
		panel.add(addName);
		panel.add(viewName);
		frame.add(panel);
		frame.setTitle("Guest Book");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}
	
	public static void main(String[] args) {
		_02_GuestBook g = new _02_GuestBook();
		g.reat();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(addName)) {
			String name = JOptionPane.showInputDialog("Enter a name to add to the Guest Book:");
			names.add(name);
		} else if (e.getSource().equals(viewName)) {
			String formatting = "";
			for (int i = 0; i < names.size(); i++) {
				formatting = formatting + "Guest #" + (i + 1) + ": " + names.get(i) + "\n";
			}
			JOptionPane.showMessageDialog(null, formatting, "Guest Book", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
}
