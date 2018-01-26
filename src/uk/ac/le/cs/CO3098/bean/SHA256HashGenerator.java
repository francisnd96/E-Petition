package uk.ac.le.cs.CO3098.bean;

import java.security.*;
import java.math.*;

import java.security.MessageDigest;
import java.util.Scanner;
import javax.xml.bind.DatatypeConverter;

/**
 * Demonstrates how to generate MD5 hash using Java
 * 
 * @author JJ
 */
public class SHA256HashGenerator {

	public static void main(String[] args) {
		Scanner sn = new Scanner(System.in);
		System.out.print("Please enter data for which MD5 is required:");
		String data = sn.nextLine();

		SHA256HashGenerator sj = new SHA256HashGenerator();
		String hash = sj.getSHA256Hash(data);
		System.out.println("The SHA256 hash is:" + hash);
	}

	/**
	 * Returns a hexadecimal encoded SHA256 hash for the input String.
	 * 
	 * @param data
	 * @return
	 */

	public static String getSHA256Hash(String data) {
		String result = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(data.getBytes("UTF-8"));
			return bytesToHex(hash); // make it printable
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	private static String bytesToHex(byte[] hash) {
		return DatatypeConverter.printHexBinary(hash);
	}

}