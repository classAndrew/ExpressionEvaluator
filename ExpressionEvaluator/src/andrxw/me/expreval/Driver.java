package andrxw.me.expreval;

public class Driver {
	
	public static void main(String[] args) {
		Utils.printArrList(Tokenizer.getRPN(Tokenizer.tokenize("1 + 1234 + ((2 + 2) * 1)")));
		
	}

}
