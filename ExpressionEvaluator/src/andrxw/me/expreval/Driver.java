package andrxw.me.expreval;

public class Driver {
	
	public static void main(String[] args) {
		Expression expr = new Expression("1 + 1234 + ((2 + 2) + 1)");
		expr.evaluate();
		Utils.printArrList(expr.getRPN());
		
	}

}
