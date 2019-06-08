package _03_IntroToStacks;

import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

public class _01_IntroToStack {
	public static void main(String[] args) {
		//1. Create a Stack of Doubles
		//   Don't forget to import the Stack class
		Stack<Double> doubles = new Stack<Double>();
		
		//2. Use a loop to push 100 random doubles between 0 and 100 to the Stack.
		Random random = new Random();
		Double rand;
		for (int i = 0; i < 100; i++) {
			rand = random.nextDouble() * 100;
			System.out.println(rand);
			doubles.push(rand);
		}
		
		//3. Ask the user to enter in two numbers between 0 and 100, inclusive. 
		
		String u1 = JOptionPane.showInputDialog("Enter a number between 0 and 100, inclusive.");
		int user1 = Integer.parseInt(u1);
		String u2 = JOptionPane.showInputDialog("Enter another number between 0 and 100, inclusive.");
		int user2 = Integer.parseInt(u2);
		
		//4. Pop all the elements off of the Stack. Every time a double is popped that is
		//   between the two numbers entered by the user, print it to the screen.
		
		int temp = 0;
		if (user2 < user1) {
			temp = user1;
			user1 = user2; 
			user2 = temp;
		}
		
		System.out.println("\nPopping elements...\n");
		System.out.println("Elements between " + user1 + " and " + user2 + ":");
		for (int i = 0; i < doubles.size(); i++) {
			if (user1 < doubles.get(doubles.size() - 1) && doubles.get(doubles.size() - 1) < user2) {
				System.out.println(doubles.get(doubles.size() - 1));
			}
			doubles.pop();
		}
		
		
		//   EXAMPLE:
		//   NUM 1: 65
		//   NUM 2: 75
		
		//   Popping elements off stack...
		//   Elements between 65 and 75:
		//   66.66876846
		//   74.51651681
		//   70.05110654
		//   69.21350456
		//   71.54506465
		//   66.47984807
		//   74.12121224
	}
}
