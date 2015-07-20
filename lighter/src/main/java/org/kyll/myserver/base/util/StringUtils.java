package org.kyll.myserver.base.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * User: Kyll
 * Date: 2015-05-23 9:19
 */
public class StringUtils {
	public static String encryptSHA(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if (messageDigest == null) {
			return null;
		}

		messageDigest.update(str.getBytes(Charset.forName("UTF-8")));
		byte[] bytes = messageDigest.digest();

		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString();
	}

	public static String generateUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
