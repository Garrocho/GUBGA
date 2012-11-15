package com.gabalise.classes;

import java.io.UnsupportedEncodingException;

public class Descriptografar {
	
	public static int unsignedByte(byte b) {
		return (0x000000FF & ((int)b));
	}

	public static String strFromBytes(byte[] input) {
		return strFromBytes(input, "UTF-8");
	}

	public static String strFromBytes(byte[] input, String charset) {
		int null_index = input.length;
		for(int i = 0; i < input.length; i++) {
			if(input[i] == 0) {
				null_index = i;
				break;
			}
		}
		try {
			return new String(input, 0, null_index, charset);
		} catch(UnsupportedEncodingException e) {
			return null;
		}
	}

	public static int byteArrayToInt(byte[] b) {
		int value = 0;
		int shift = (4 - 1) * 8;
		value += (b[0] & 0x000000FF) << shift;
		return value;
	}

	public static int byteArrayToIntLittleLength(byte[] b, int offset, int length) {
		int value = 0;
		for (int i = 0; i < length; i++) {
			int shift = i * 8;
			value += (b[i + offset] & 0x000000FF) << shift;
		}
		return value;
	}

	public static String strFromBytes16(byte[] input) {
		int null_index = input.length;
		for(int i = 0; i < input.length - 1; i+=2) {
			if(input[i] == 0 && input[i + 1] == 0) {
				null_index = i;
				break;
			}
		}

		try {
			return new String(input, 0, null_index, "UnicodeLittleUnmarked");
		} catch(UnsupportedEncodingException e) {
			return null;
		}
	}

}
