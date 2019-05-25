package andrxw.me.expreval;

import java.util.ArrayList;
import java.util.Stack;

public class Expression {
	
	private String expr;
	private ArrayList<String> tokenized;
	private ArrayList<String> RPN;
	
	public Expression(String expr) {
		this.expr = expr;
		tokenize();
		toRPN();
		Utils.printArrList(RPN);
	}
	
	public ArrayList<String> getRPN() {
		return RPN;
	}

	public void evaluate() {
		int i = 0;
		while (i < RPN.size()) {
			Integer prec = Identifier.getPrecedence(RPN.get(i));
			if (prec != null) {
				switch(RPN.get(i)) {
				case "+":
					int sum = Integer.valueOf(RPN.get(i-2))+Integer.valueOf(RPN.get(i-1));
					RPN.remove(i--);
					RPN.remove(i--);
					RPN.set(i, String.valueOf(sum));
					break;
				case "-":
					int left = Integer.valueOf(RPN.get(i-2))-Integer.valueOf(RPN.get(i-1));
					RPN.remove(i--);
					RPN.remove(i--);
					RPN.set(i, String.valueOf(left));
					break;
				case "*":
					int product = Integer.valueOf(RPN.get(i-2))*Integer.valueOf(RPN.get(i-1));
					RPN.remove(i--);
					RPN.remove(i--);
					RPN.set(i, String.valueOf(product));
					break;
				default:
					System.out.println("Unknown Operation");
					break;
				}
			}
			i++;
		}
	}
	
	private void tokenize() {
		ArrayList<String> tokenized = new ArrayList<String>();
		char[] charmap = expr.toCharArray();
		// String Buffer
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (i < expr.length()) {
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
		this.tokenized = tokenized;
	}
	
	private void toRPN() {
		ArrayList<String> RPN = new ArrayList<String>();
		Stack<String> stack = new Stack<String>();
		int i = 0;
		while (i < tokenized.size()) {
			String token = tokenized.get(i);
			if (Identifier.isNumber(token)) {
				RPN.add(token);
			} else if (token.equals("(")) {
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
		this.RPN = RPN;
	}
	
}
