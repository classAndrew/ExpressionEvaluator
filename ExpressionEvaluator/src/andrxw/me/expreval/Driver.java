package andrxw.me.expreval;

public class Driver {
	
	public static void main(String[] args) {
		Expression expr = new Expression("(5*4+3*2)-1");
		expr.evaluate();
		Utils.printArrList(expr.getRPN());
		Utils.printArrList(Tokenizer.getRPN(Tokenizer.tokenize("(5*4+3*2)-1")));
		
	}

}
