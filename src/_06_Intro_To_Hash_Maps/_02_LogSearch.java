package _06_Intro_To_Hash_Maps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class _02_LogSearch implements ActionListener {
	
	 //Crate a HashMap of Integers for the keys and Strings for the values.
	HashMap<Integer, String> list = new HashMap<Integer, String>();
	JFrame frame;
	JPanel panel;
	JLabel label;
	JButton addEntry;
	JButton searchByAiDee;
	JButton viewList;
	JButton removeEntry;
	
	public void trouble() {
		frame = new JFrame();
		panel = new JPanel();
		label = new JLabel();
		addEntry = new JButton();
		searchByAiDee = new JButton();
		viewList = new JButton();
		removeEntry = new JButton();
		addEntry.setText("Add Entry");
		searchByAiDee.setText("Search by ID");
		viewList.setText("View List");
		removeEntry.setText("Remove Entry");
		addEntry.addActionListener(this);
		searchByAiDee.addActionListener(this);
		viewList.addActionListener(this);
		removeEntry.addActionListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.add(addEntry);
		panel.add(searchByAiDee);
		panel.add(viewList);
		panel.add(removeEntry);
		panel.add(label);
		frame.add(panel);
		frame.pack();
		frame.setTitle("Log Search");
		frame.setVisible(true);
	}
	 /* Create a GUI with three buttons. 
	 * Button 1: Add Entry
	 * 				When this button is clicked, use an input dialog to ask the user to enter an ID number.
	 * 				After an ID is entered, use another input dialog to ask the user to enter a name.
	 * 				Add this information as a new entry to your HashMap.
	 * 
	 * Button 2: Search by ID
	 * 				When this button is clicked, use an input dialog to ask the user to enter an ID number.
	 * 				If that ID exists, display that name to the user.
	 * 				Otherwise, tell the user that that entry does not exist.
	 * 
	 * Button 3: View List
	 * 				When this button is clicked, display the entire list in a message dialog in the following format:
	 * 				ID: 123  Name: Harry Howard
	 * 				ID: 245  Name: Polly Powers
	 * 				ID: 433  Name: Oliver Ortega
	 * 				etc...
	 * 
	 * When this is complete, add a fourth button to your window.
	 * Button 4: Remove Entry
	 * 				When this button is clicked, prompt the user to enter an ID using an input dialog.
	 * 				If this ID exists in the HashMap, remove it. Otherwise, notify the user that the ID
	 * 				is not in the list. 
	 *
	 * */
	
	public static void main(String[] args) {
		_02_LogSearch we_veGot = new _02_LogSearch();
		we_veGot.trouble();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(addEntry)) {
			String idNum = JOptionPane.showInputDialog(null, "Enter a new ID:", "New ID", JOptionPane.INFORMATION_MESSAGE);
			int idNumber = Integer.parseInt(idNum);
			String nam = JOptionPane.showInputDialog(null, "Enter a name:", "New Name", JOptionPane.INFORMATION_MESSAGE);
			list.put(idNumber, nam);
		} else if (e.getSource().equals(searchByAiDee)) {
			String idQ = JOptionPane.showInputDialog(null, "Search for an ID:", "New ID", JOptionPane.INFORMATION_MESSAGE);
			int idQInt = Integer.parseInt(idQ);
			boolean found = false;
			String foundname = "";
			for (Integer i : list.keySet()) {
				if (i == idQInt) {
					found = true;
					foundname = list.get(i);
				}
			}
			if (found) {
				JOptionPane.showMessageDialog(null, "The name at that ID is " + foundname + ".");
			} else {
				JOptionPane.showMessageDialog(null, "That ID does not exist.");
			}
		} else if (e.getSource().equals(viewList)) {
			String listList = "";
			ArrayList<Integer> keys = new ArrayList<Integer>();
			for(Integer i : list.keySet()){
				keys.add(i);
			}
			for (int i = 0; i < list.size(); i++) {
				listList = listList + "ID: ";
				listList = listList + keys.get(i);
				listList = listList + " Name: ";
				listList = listList + list.get(keys.get(i));
				listList = listList + "\n";
			}
			JOptionPane.showMessageDialog(null, listList);
		} else if (e.getSource().equals(removeEntry)) {
			String idQ = JOptionPane.showInputDialog(null, "Enter an ID:", "New ID", JOptionPane.INFORMATION_MESSAGE);
			int idQInt = Integer.parseInt(idQ);
			boolean found = false;
			String foundname = "";
			int foundkey = 0;
			for (Integer i : list.keySet()) {
				if (i == idQInt) {
					found = true;
					foundkey = i;
					foundname = list.get(i);
				}
			}
			if (found) {
				list.remove(foundkey, foundname);
				JOptionPane.showMessageDialog(null, "The entry at that ID was deleted.");
			} else {
				JOptionPane.showMessageDialog(null, "That ID does not exist.");
			}
		}
	}
	
}
