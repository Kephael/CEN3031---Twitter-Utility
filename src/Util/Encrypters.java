import java.util.Arrays;

public class Encrypters {

	private static StringBuilder encrypted = new StringBuilder();
	private static StringBuilder tempAt = new StringBuilder();
	private static StringBuilder tempHash = new StringBuilder();

	private static final char[] decoderLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private static int[] encryptionKey;

	/**
	 * Public constructor Encrypters takes a string and key. It is where all the encryption methods are called to
	 * encrypt the tweet.
	 *
	 * @param String encryptMe
	 * @param String key
	 *
	 */
	public Encrypters( String encryptMe, String key ){

		StringBuilder ciphered = new StringBuilder();

		formatEncryption( encryptMe );
		formatKey( key );
		encryption( encrypted, encryptionKey );

		// Debugging
		System.out.println( "-----------------------------" );
		System.out.println( encrypted );
		System.out.println( "-----------------------------" );
		// End of debugging

		ciphered.append( tempAt.toString() );
		ciphered.append( encrypted.toString() );
		ciphered.append( " " + tempHash );

		// Debugging
		System.out.println( "-----------------------------" );
		System.out.println( "The encrypted tweet is: " + ciphered.toString() );
		System.out.println( "-----------------------------" );
		// End of debugging

	}

	/**
	 * Private static method formatEncryption takes the original tweet string and split it into three StringBuilders.
	 *
	 * StringBuilder tempAt contains words beginning with the @ symbol
	 * StringBuilder tempHash contains words beginning with the # symbol
	 * StringBuilder encrypted contains the message which is formatted to uppercase and all spaces are removed
	 *
	 * @param encryptMe
	 *
	 */
	private static void formatEncryption( String encryptMe ){

		String[] encryptMeArray = encryptMe.split( " " );

		for( int i = 0; i < encryptMeArray.length; i++ ){

			if( encryptMeArray[ i ].charAt( 0 ) == '@' ){

				tempAt.append( encryptMeArray[ i ] + " " );

			}else if( !(encryptMeArray[i].charAt( 0 ) == '@' || encryptMeArray[i].charAt( 0 ) == '#') ){

				encrypted.append( encryptMeArray[ i ].toUpperCase() );

			}else if( encryptMeArray[ i ].charAt( 0 ) == '#' ){

				tempHash.append( encryptMeArray[ i ] + " " );;

			}

		}

		// Debugging
		System.out.println( "-----------------------------" );
		System.out.println( tempAt.toString() );
		System.out.println( encrypted.toString() );
		System.out.println( tempHash.toString() );
		System.out.println( "-----------------------------" );
		// End of debugging

	}

	private static void formatKey( String key ){

		encryptionKey = new int[ key.length() ];

		for( int i = 0; i < key.length(); i++ ){

			encryptionKey[i] = key.toUpperCase().charAt(i) - 64;

		}

		// Debugging
		System.out.println( "-----------------------------" );
		System.out.println( Arrays.toString( encryptionKey ) );
		System.out.println( "-----------------------------" );
		// End of debugging

	}

	// Private static method rotate takes the decoderLetters array and rotates it by one
	private static void rotate(){

		for( int i = decoderLetters.length-1; i > 0; i-- ){

			char temp = decoderLetters[ i ];
			decoderLetters[ i ] = decoderLetters[ i-1 ];
			decoderLetters[ i-1 ] = temp;

		}   // End of FOR loop

	}

	/**
	 * Private static method encryption takes either a word or phrase modified by the constructor
	 * and using a user provided key, encrypts using a Vigenère cipher.
	 *
	 * @param StringBuilder encrypted
	 * @param int[] key
	 *
	 * @return encrypted
	 *
	 */
	private static StringBuilder encryption( StringBuilder encrypted, int[] key ){

		boolean hit = false;
		int index = 0;
		int keyIndex = 0;

		for( int i = 0; i < encrypted.length(); i++ ){

			while( !hit ){

				try {

					if (encrypted.charAt(i) == decoderLetters[index % 26]) {

						encrypted.setCharAt( i, decoderLetters[ ( index + key[ keyIndex ] ) % 26 ] );

						hit = true;

					}

					index++;

				}catch( ArrayIndexOutOfBoundsException e ){

					e.printStackTrace();

				}   // End of TRY-CATCH statement

			}   // End of WHILE loop

			try{

				keyIndex = (keyIndex+1) % key.length;

			}catch( ArrayIndexOutOfBoundsException e ){

				e.printStackTrace();

			}   // End of TRY-CATCH statement

			index = 0;
			hit = false;

			// Determines if the encryption is Ceasar or Vigenère
			if( key.length != 1 ) {

				rotate();

			}

		}   // End of FOR loop

		return encrypted;

	}   // End of method encryption

}   // End of class Encrypters