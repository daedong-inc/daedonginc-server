package com.daedonginc.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author domo
 * Created on 2023/03/28
 */
public class SHA256Util {
	private static final String ENCRYPT_TYPE = "SHA-256";

	public static String encrypt(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			byte[] input = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < input.length; i++) {
				sb.append(Integer.toString((input[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("[SHA256Util.encrypt] 암호화 에러 발생", e);
		}
	}
}
