package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;

import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import twitter4j.conf.Configuration;

/**
 * Two-Way Encryption Algorithm
 * Uses Password Based Encryption
 * (Encryption and Decryption)
 * @author Matthew Hemingway
 */
public class PassEncrypt {

	/**Master Password for PBE */
	private static final char[] PASSKEY = "Miester9000@30Hz".toCharArray();
	
	/**Salt byte array for encoding and decoding*/
	private static byte[] salt = new byte[8];
	
	/**Generates random byte values*/
	private static SecureRandom secureRandGen;
	
	/*Output stream*/
	private static FileOutputStream output;
	
	/*Uses text file for testing purposes*/
	private static File file = new File("twitter4j.properties");
	
	
	/**
	 * Encrypts configuration property string
	 * @param configuration config property 
	 * @return encrypted string
	 */
	private static String encrypt(String configuration) 
	{
		String encrypted = "";
		try {
			secureRandGen = SecureRandom.getInstance("SHA1PRNG");
			secureRandGen.nextBytes(salt);
			SecretKeyFactory keyHolder = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			SecretKey desPBEkey = keyHolder.generateSecret(new PBEKeySpec(PASSKEY));
			Cipher cipher = Cipher.getInstance("PBEWithMD5AndDes");
			cipher.init(Cipher.ENCRYPT_MODE, desPBEkey, new PBEParameterSpec(salt, 20));
			encrypted = new String(cipher.doFinal(configuration.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (GeneralSecurityException ge)
		{
			ge.printStackTrace();
		}
		return encrypted;
	}
	
	/**
	 * Decrypts the encrypted string
	 * @param encrypted encrypted string
	 * @return decrypted string
	 */
	public static String decrypt(String encrypted)
	{
		String decrypted = "";
		try{
			secureRandGen = SecureRandom.getInstance("SHA1PRNG");
			secureRandGen.nextBytes(salt);
			SecretKeyFactory keyHolder = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			SecretKey desKey = keyHolder.generateSecret(new PBEKeySpec(PASSKEY));
			Cipher cipher = Cipher.getInstance("PBEWithMD5AndDes");
			cipher.init(Cipher.DECRYPT_MODE, desKey, new PBEParameterSpec(salt, 20));
			decrypted = new String(cipher.doFinal(encrypted.getBytes()));
		}catch(GeneralSecurityException ge)
		{
			ge.printStackTrace();
		}
			return decrypted;
	}
	
	/**
	 * creates a file with encrypted settings
	 * @param Configuration config
	 */
	public static void writeEncryptedSettings(Configuration config)
	{
		try {
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Properties configProperties = new Properties();
		
		configProperties.setProperty("oauth.consumerKey:", 
					encrypt(config.getOAuthConsumerKey()));
		configProperties.setProperty("oauth.consumerSecret:", 
					encrypt(config.getOAuthConsumerSecret()));
		configProperties.setProperty("oauth.accessToken:", 
					encrypt(config.getOAuthAccessToken()));
		configProperties.setProperty("oauth.accessTokenSecret:", 
					encrypt(config.getOAuthAccessTokenSecret()));
		
		try 
		{
			output = new FileOutputStream(file);
			configProperties.store(output, "Twitter4j Properties");
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			
		} catch(IOException ie)
		{
			ie.printStackTrace();
		}
	}
	
	/**
	 * Reads file to get the API settings
	 * Overwrites file with decrypted settings
	 */
	public static void writeDecryptedSettings()
	{
		Properties encryptedProperties = new Properties();
		try {
			FileInputStream input = new FileInputStream(file);
			encryptedProperties.load(input);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}catch(IOException ie)
		{
			ie.printStackTrace();
		}
		
		//replace written settings with decrypted settings
		encryptedProperties.setProperty("oauth.consumerKey:", 
				decrypt(encryptedProperties.getProperty("oauth.consumerKey:")));
		encryptedProperties.setProperty("oauth.consumerSecret:",
				decrypt(encryptedProperties.getProperty("oauth.consumerSecret:")));
		encryptedProperties.setProperty("oauth.accessToken:", 
				decrypt(encryptedProperties.getProperty("oauth.accessToken:")));
		encryptedProperties.setProperty("oauth.accessTokenSecret:", 
				decrypt(encryptedProperties.getProperty("oauth.accessTokenSecret:")));
		
		try {
			encryptedProperties.store(output, "Twitter4j Properties");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}
