package andrxw.me.expreval;

public class Driver {
	
	public static void main(String[] args) {
		Utils.printArrList(Tokenizer.tokenize("(5x+1) + 1234 *"));
	}

}
