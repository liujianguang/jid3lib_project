/**
 * 
 */
package org.farng.mp3.util;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;

/**
 * @author Administrator
 *
 */
public class StringUtil {
	public static boolean isNull(String str) {
		if (str == null) {
			return true;
		} else if ("".equals(str)) {
			return true;
		} else {
			return false;
		}
	}

	public static String encodeStr(String str) {
		if (!isNull(str)) {
			String strs = getStrEncoding(str);
			byte[] b = null;
			String s;
			try {
				b = str.getBytes(strs);
				if (b != null) {
					if ("UTF-8".equalsIgnoreCase(strs)) {
						b = doTheBytes(b);
					}
				}
				s = new String(b, strs);
			} catch (Exception e) {
				e.printStackTrace();
				s = new String(str);
			}
			return s.trim();

		}
		return str;
	}

	public static String getStrEncoding(String str) {
		CharsetDetector detector = new CharsetDetector();
		byte[] data = str.getBytes();
		detector.setText(data);
		CharsetMatch match = detector.detect();
		String encoding = match.getName();
		return encoding;
	}

	/**
	 * 处理ISO-8859-1的乱码问题,解决方法:去除字节数组里面小于32和等于63的字节<br>
	 * <pre>
	 * 		解决的思路：ISO-8859-1不能显示汉字，所以不存在汉字的两个或三个字节，,即默认为英文字符加上符号;
	 * 				又32之前为控制字符，63为?，所以去除
	 * </pre>
	 * @param b
	 * @return
	 */
	public static byte[] doTheBytesForISO8859_1(byte[] b){
		if(b != null){
			byte[] result = new byte[b.length];
			int j = 0;
			for (byte c : b) {
				if(c < 32 || c == 63){
					result[j++] = 32;
				}else{
					result[j++] = c;
				}
			}
			return result;
		}
		return null;
	}
	public static byte[] doTheBytes(byte[] b){
		if(b != null){
			byte[] result = new byte[b.length];
			int j = 0;
			for (byte c : b) {
				if(c < 32 && c >= 0){
					result[j++] = 32;
				}else{
					result[j++] = c;
				}
			}
			return result;
		}
		return null;
	}
	public static String getStrEncoding(byte[] arr){
		CharsetDetector detector = new CharsetDetector();
		byte[] data = arr;
		detector.setText(data);
		CharsetMatch match = detector.detect();
		String encoding = match.getName();
		return encoding;
	}

}
