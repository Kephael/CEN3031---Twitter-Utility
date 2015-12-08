package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Two-Way Encryption Algorithm
 * Uses Password Based Encryption
 * (Encryption and Decryption)
 * @author Matthew Hemingway
 */
public class PassEncrypt {

	/**Master Password for PBE */
	private static final byte[] PASSKEY = "Miester9000@30Hz".getBytes();

	/*Output stream*/
	private static FileOutputStream output;

	/*Uses text file for testing purposes*/
	private static File file = new File(util.TwitterParser.KEY_FILE);


	/**
	 * Encrypts configuration property string
	 * @param configuration configuration property 
	 * @return encrypted string
	 */
	private static String encrypt(String encryptMe) 
	{
		String encrypted = "";
		try {
			Key key = new SecretKeySpec(PASSKEY, "AES");;
		    Cipher c = Cipher.getInstance("AES");
		    c.init(Cipher.ENCRYPT_MODE, key);
		    byte[] encodedBytes = c.doFinal(encryptMe.getBytes());
		    encrypted = new BASE64Encoder().encode(encodedBytes);
		} catch (GeneralSecurityException ge) {
			ge.printStackTrace();
		}
		return encrypted;
	}

	/**
	 * Decrypts the encrypted string
	 * @param encrypted encrypted string
	 * @return decrypted string
	 * @throws IOException 
	 */
	public static String decrypt(String encrypted) 
	{
		String decrypted = "";
		try{
			Key key = new SecretKeySpec(PASSKEY, "AES");;
		    Cipher c = Cipher.getInstance("AES");
		    c.init(Cipher.DECRYPT_MODE, key);
		    byte[] decodedBytes = new BASE64Decoder().decodeBuffer(encrypted);
		    byte[] decodedArray = c.doFinal(decodedBytes);
		    decrypted = new String(decodedArray);
		}catch(GeneralSecurityException ge)
		{
			ge.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();

		} 
		catch(IOException ie) {
			ie.printStackTrace();
		}
	}

	/**
	 * Reads file to get the API settings
	 */
	public static Configuration getDecryptedSettings()
	{
		Properties encryptedProperties = new Properties();
		try {
			FileInputStream input = new FileInputStream(file);
			encryptedProperties.load(input);
			input.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException ie) {
			ie.printStackTrace();
		}
		ConfigurationBuilder builder = new ConfigurationBuilder();
		// Load written settings into ConfigurationBuilder
		String key = encryptedProperties.getProperty("oauth.consumerKey:");
		builder.setOAuthConsumerKey(decrypt(key));
		key = encryptedProperties.getProperty("oauth.consumerSecret:");
		builder.setOAuthConsumerSecret(decrypt(key));
		key = encryptedProperties.getProperty("oauth.accessToken:");
		builder.setOAuthAccessToken(decrypt(key));
		key = encryptedProperties.getProperty("oauth.accessTokenSecret:");
		builder.setOAuthAccessTokenSecret(decrypt(key));
		Configuration config = builder.build(); // returns a new config based on the decrypted settings in the stored file
		return config;
	}
}
