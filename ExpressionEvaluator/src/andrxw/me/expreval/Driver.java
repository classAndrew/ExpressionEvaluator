package andrxw.me.expreval;

public class Driver {
	
	public static void main(String[] args) {
		Expression expr = new Expression("sin(3*tan(pow(3,4)))");
		expr.evaluate();
		Utils.printArrList(expr.getRPN());
		//Utils.printArrList(Tokenizer.getRPN(Tokenizer.tokenize("(5*4+(3*9*2)sin)-1")));
		
	}

}
