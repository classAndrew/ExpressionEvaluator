package andrxw.me.expreval;

public class Driver {
	
	public static void main(String[] args) {
		Expression expr = new Expression("tan(2)+sin(2)+cos(2)");
		expr.evaluate();
		Utils.printArrList(expr.getRPN());
		//Utils.printArrList(Tokenizer.getRPN(Tokenizer.tokenize("(5*4+(3*9*2)sin)-1")));
		
	}

}
