package Util;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * Two-Way Encryption Algorithm
 * Uses Password Based Encryption
 * (Encryption and Decryption)
 * @author Matthew Hemingway
 */
public class PassEncrypt {

	/**Master Password for PBE */
	private static final char[] PASSWORD = "Miester9000@30Hz".toCharArray();
	
	/**Salt byte array for encoding and decoding*/
	private static byte[] salt = new byte[8];
	
	/**Generates random byte values*/
	private static SecureRandom secureRandGen;
	
	public PassEncrypt() throws NoSuchAlgorithmException
	{
		secureRandGen = SecureRandom.getInstance("SHA1PRNG");
		secureRandGen.nextBytes(salt);
	}
	
	/**
	 * Encrypts string
	 * @param property the string
	 * @return encrypted string
	 * @throws GeneralSecurityException
	 * @throws UnsupportedEncodingException
	 */
	public static String encrypt(String property) throws GeneralSecurityException, UnsupportedEncodingException
	{
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
		Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDes");
		pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(salt, 20));
		String encrypted = new String(pbeCipher.doFinal(property.getBytes("UTF-8")));
		return encrypted;
	}
	
	/**
	 * Decrypts the encrypted string
	 * @param property encrypted string
	 * @return decrypted string
	 * @throws GeneralSecurityException
	 * @throws UnsupportedEncodingException
	 */
	public static String decrypt(String property) throws GeneralSecurityException, UnsupportedEncodingException
	{
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
		Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDes");
		pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(salt, 20));
		String decrypted = new String(pbeCipher.doFinal(property.getBytes("UTF-8")), "UTF-8");
		return decrypted;
	}
}
