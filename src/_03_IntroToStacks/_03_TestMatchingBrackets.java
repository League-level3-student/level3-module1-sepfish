package _03_IntroToStacks;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Stack;

import org.junit.Test;

public class _03_TestMatchingBrackets {

	@Test
	public void testMatchingBrackets() {
		assertTrue(doBracketsMatch("{}"));
		assertTrue(doBracketsMatch("{{}}"));
		assertTrue(doBracketsMatch("{}{}{{}}"));
		assertFalse(doBracketsMatch("{{}"));
		assertFalse(doBracketsMatch("}{"));
	}

	// USE A STACK TO COMPLETE THE METHOD FOR CHECKING IF EVERY OPENING BRACKET HAS A MATCHING CLOSING BRACKET
	private boolean doBracketsMatch(String b) {
		Stack<Character> stringB = new Stack<Character>();
		for (int i = 0; i < b.length(); i++) {
			stringB.push(b.charAt(i));
			stringB.push('n');
		}
		
		for (int j = 0; j < stringB.size(); j++) {
			if(stringB.get(j).equals('{')) {
				for (int k = 0; k < stringB.size(); k++) {
					if (stringB.get(k).equals('}') && k > j) {
						stringB.remove(j);
						stringB.remove(k-1);
						break;
					}
				}
			}
		}
		
		for (int a = 0; a < stringB.size(); a++) {
			if(stringB.get(a).equals('n')) {
				stringB.remove(a);
				a = a - 1;
			}
		}
		
		if (stringB.empty()) {
			System.out.println("true");
			return true;
		} else {
			System.out.println("false");
			return false;
		}
	}

}