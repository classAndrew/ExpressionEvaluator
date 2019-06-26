package andrxw.me.expreval;

import java.util.HashSet;

public class Functions {
	
	private static HashSet<String> fxs = new HashSet<String>();
	private static String[] functions = {"tan", "sin", "cos", "pow"};
	static {
		for (String s : functions) {
			fxs.add(s);
		}
	}
	public static boolean isFunction(String s) {
		return fxs.contains(s);
	}
}
