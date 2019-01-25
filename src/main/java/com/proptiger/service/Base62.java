
/*
 * Base62 class
 * Taken From :- https://github.com/seruco/base62
 * Edited by : Sachin Chauhan
 * 
 * This class is to perform operations based on Base-62 (0-1,A-Z,a-z)
 * It is useful because we are using 62 Character set to generate shortURL
 * 
 */

package com.proptiger.service;

public class Base62 {

	public static final String ALPHABET = Constants.ALPHABET;

	public static final int BASE = Constants.BASE;

	// Singleton class
	private Base62() {}

	
	// Converts into base62 from base10
	public static String fromBase10(int i) {
		StringBuilder sb = new StringBuilder("");
		if (i == 0) {
			return "a";
		}
		while (i > 0) {
			i = fromBase10(i, sb);
		}
		
		String s = sb.reverse().toString();
		String ans="";
		for(int ii=0;ii<6-s.length();ii++) ans += "a";
		ans += s;
		return ans;
		
	}

	private static int fromBase10(int i, final StringBuilder sb) {
		int rem = i % BASE;
		sb.append(ALPHABET.charAt(rem));
		return i / BASE;
	}

	public static int toBase10(String str) {
		return toBase10(new StringBuilder(str).reverse().toString().toCharArray());
	}

	private static int toBase10(char[] chars) {
		int n = 0;
		for (int i = chars.length - 1; i >= 0; i--) {
			n += toBase10(ALPHABET.indexOf(chars[i]), i);
		}
		return n;
	}

	private static int toBase10(int n, int pow) {
		return n * (int) Math.pow(BASE, pow);
	}
	
	
	public static String randomString(Integer length)
	// It Generates Random String in Base62 of length = length(parameter) 
	{
		String ans = "";
		for(int i=0;i<length;i++) {
			int index = (int)(Math.random()*((double)BASE));
			ans += ALPHABET.charAt(index);
		}
		return ans;
	}
}
