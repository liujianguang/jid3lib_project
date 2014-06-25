/**
 * 
 */
package com.string.encode;

/**
 * @author Administrator
 *
 */
public class StringEncodeType {
	private static String[] EncodeTypes= {"IBM420_rtl","KOI8-R","IBM420_ltr","EUC-KR","Big5","IBM424_rtl"};
	
	public static boolean isTheEncode(String encode){
		if(encode == null){
			return false;
		}
		for (String str  : EncodeTypes) {
			if(encode.equals(str)){
				return true;
			}
		}
		return false;
	}

}
