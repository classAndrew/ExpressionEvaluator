package andrxw.me.expreval;

import java.util.ArrayList;
import java.util.Stack;

public class Tokenizer {
	
	public static ArrayList<String> tokenize(String s) {
		ArrayList<String> tokenized = new ArrayList<String>();
		char[] charmap = s.toCharArray();
		// String Buffer
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (i < s.length()) {
			// if is character then add it to the buffer
			if (Character.isAlphabetic(charmap[i]) || Character.isDigit(charmap[i])) {
				sb.append(String.valueOf(charmap[i]));
			}
			else if (charmap[i] != ' '){
				if (sb.length() > 0)
					tokenized.add(sb.toString());
				sb = new StringBuilder();
				tokenized.add(String.valueOf(charmap[i]));
			}
			i++; 
		}
		tokenized.add(sb.toString());
		return tokenized;
	}
	
	// Shunting-Yard
	
	public static ArrayList<String> getRPN(ArrayList<String> tokenized) {
		ArrayList<String> RPN = new ArrayList<String>();
		Stack<String> stack = new Stack<String>();
		int i = 0;
		while (i < tokenized.size()) {
			String token = tokenized.get(i);
			if (Identifier.isNumber(token)) {
				RPN.add(token);
			}
			else if (token.equals("(")) {
				stack.push(token);
			}
			else if (token.equals(")")) {
				while (true) {
					String s = stack.pop();
					if (s.equals("(")) {
						break;
					} else {
						RPN.add(s);
					}
				}
			} else {
				if (stack.isEmpty() || stack.peek().equals("(")) {
					stack.push(token);
				} else if (Identifier.getPrecedence(token) > Identifier.getPrecedence(stack.peek())) {
					stack.push(token);
				} else {
					RPN.add(stack.pop());
					stack.push(token);
				}
			}
			i++;
		}
		for (int j = 0; j < stack.size(); j++) {
			RPN.add(stack.pop());
		}
		return RPN;
	}
	
	private static void breakVariables(ArrayList<String> ref) {
		// will turn '5x' into '5','*','x'
	}

}
