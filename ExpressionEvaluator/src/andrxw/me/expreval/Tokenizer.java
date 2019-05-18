package andrxw.me.expreval;

import java.util.ArrayList;

public class Tokenizer {
	
	public static ArrayList<String> tokenize(String s) {
		ArrayList<String> tokenized = new ArrayList<String>();
		char[] charmap = s.toCharArray();
		// String Buffer
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (i < s.length()) {
			// if is character then add it to the buffer
			if (Character.isAlphabetic(charmap[i]) || Character.isDigit(charmap[i])) {
				sb.append(String.valueOf(charmap[i]));
			}
			else if (charmap[i] != ' '){
				if (sb.length() > 0)
					tokenized.add(sb.toString());
				sb = new StringBuilder();
				tokenized.add(String.valueOf(charmap[i]));
			}
			i++; 
		}
		return tokenized;
	}

}
