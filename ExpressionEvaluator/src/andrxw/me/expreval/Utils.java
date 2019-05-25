package andrxw.me.expreval;

import java.util.ArrayList;

public class Utils {

	
	
	
	public static void printArrList(ArrayList<String> obj) {
		for (int i = 0; i < obj.size(); i++) {
			System.out.print("'" + obj.get(i) + "'" + ((i == obj.size()-1) ? "" : ", "));
		}
		System.out.println("");
	}
	
	
	
}
