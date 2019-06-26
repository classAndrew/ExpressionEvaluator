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
				double res;
				switch(RPN.get(i)) {
				case "+":
					res = Double.valueOf(RPN.get(i-2))+Double.valueOf(RPN.get(i-1));
					RPN.remove(i--);
					RPN.remove(i--);
					RPN.set(i, String.valueOf(res));
					break;
				case "-":
					res = Double.valueOf(RPN.get(i-2))-Double.valueOf(RPN.get(i-1));
					RPN.remove(i--);
					RPN.remove(i--);
					RPN.set(i, String.valueOf(res));
					break;
				case "/":
					res = Double.valueOf(RPN.get(i-2))/Double.valueOf(RPN.get(i-1));
					RPN.remove(i--);
					RPN.remove(i--);
					RPN.set(i, String.valueOf(res));
					break;
				case "*":
					res = Double.valueOf(RPN.get(i-2))*Double.valueOf(RPN.get(i-1));
					RPN.remove(i--);
					RPN.remove(i--);
					RPN.set(i, String.valueOf(res));
					break;
				case "sin":
					res = Double.valueOf(Math.sin(Double.valueOf(RPN.get(i-1))));
					RPN.remove(i--);
					RPN.set(i, String.valueOf(res));
					break;
				case "cos":
					res = Double.valueOf(Math.cos(Double.valueOf(RPN.get(i-1))));
					RPN.remove(i--);
					RPN.set(i, String.valueOf(res));
					break;
				case "tan":
					res = Double.valueOf(Math.tan(Double.valueOf(RPN.get(i-1))));
					RPN.remove(i--);
					RPN.set(i, String.valueOf(res));
					break;
				case "pow":
					res = Double.valueOf(Math.pow(Double.valueOf(RPN.get(i-2)), Double.valueOf(RPN.get(i-1))));
					RPN.remove(i--);
					RPN.remove(i--);
					RPN.set(i, String.valueOf(res));
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
			if (Character.isAlphabetic(charmap[i]) || Character.isDigit(charmap[i]) || charmap[i] == '.') {
				sb.append(String.valueOf(charmap[i]));
			}
			else if (charmap[i] != ' '){
				if (sb.length() > 0 || charmap[i]==',') // The comma condition to check if multi arg the comma is gone in preprocess
					tokenized.add(sb.toString());
				sb = new StringBuilder();
				tokenized.add(String.valueOf(charmap[i]));	
			}
			i++; 
		}
		tokenized.add(sb.toString());
		this.tokenized = tokenized;
		functionPreprocess();
	}
	
	private void functionPreprocess() {
		// Arranged as [[sin, index], [cos, index], [sin, index]...]
		Stack<String> functions = new Stack<String>();
		int i = 0;
		while (i < tokenized.size()) {
			if (Functions.isFunction(tokenized.get(i))) {
				functions.push(tokenized.get(i));
				tokenized.remove(i--);
			}
			else if(tokenized.get(i).equals("") || tokenized.get(i).equals(",")) {
				tokenized.remove(i--);
			}
			else if(tokenized.get(i).equals(")")) {
				String last = functions.pop();
				tokenized.add(++i, last);
			}
			i++;
		}
		Utils.printArrList(tokenized);
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
