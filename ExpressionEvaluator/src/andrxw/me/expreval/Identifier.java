package andrxw.me.expreval;

import java.util.HashMap;
import java.util.HashSet;

public class Identifier {
	
	static HashMap<String, Integer> operations = new HashMap<String, Integer>();
	static HashSet<String> symbols = new HashSet<String>();
	static HashSet<String> leftAssociatives = new HashSet<String>();
	static String[] opslist = {"+","-","*","/","^"};
	static int[] precedence = {1,1,2,2,3};
	static String[] sym = {"(",")",","};
	static String[] lefts = {"^"};
	
	static {
		for (int i = 0; i < opslist.length; i++) {
			operations.put(opslist[i], precedence[i]);
		}
		for (int i = 0; i < sym.length; i++) {
			symbols.add(sym[i]);
		}
		for ( int i = 0; i < lefts.length; i++) {
			leftAssociatives.add(opslist[i]);
		}
	}
	
	public static boolean isNumber(String s) {
		return !(operations.containsKey(s) || symbols.contains(s));
	}
	
	
	public static Integer getPrecedence(String s) {
		return operations.get(s);
	}
	
	public static boolean isLAssoc(String s) {
		return leftAssociatives.contains(s);
	}

}
