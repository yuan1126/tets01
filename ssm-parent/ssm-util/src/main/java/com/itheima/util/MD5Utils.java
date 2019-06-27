package com.itheima.util;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
	/**
	 * 使用md5的算法进行加密
	 */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法！");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
		// 如果生成数字未满32位，需要前面补0
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}

	public static void main(String[] args) {
		//System.out.println(md5("1234"));

		//Object source,  要加密的字符串
		// Object salt,   盐，扰乱码
		// int hashIterations, md5次数， 散列次数
		Md5Hash md5Hash = new Md5Hash("1234","aa234#!@#!;asd",2);
		System.out.println(md5Hash.toString());
	}

}