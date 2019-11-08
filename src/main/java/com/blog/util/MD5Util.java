package com.blog.util;

import java.security.MessageDigest;

public class MD5Util {
	
	/**
	 * MD5加密
	 * @param str 要加密的字符串
	 * @return	  加密后的字符串
	 */
	public static String code(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] byteDigest = md.digest();
			System.out.println(byteDigest.hashCode());
//			for (byte b : byteDigest) {
//				System.out.println(b);
//			}
			int i;
			StringBuffer bf = new StringBuffer("");
			for (int offset = 0; offset < byteDigest.length; offset++) {
				i = byteDigest[offset];
				if(i < 0){
					i += 256;
				}
				if (i < 16) {
					bf.append("0");	
				}
				bf.append(Integer.toHexString(i));
			}
			//32位加密
			return bf.toString();
			//16位加密
			//return bf.toString().substring(8, 24);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(code("123456"));
		System.out.println(code("e10adc3949ba59abbe56e057f20f883e"));
	}
}
