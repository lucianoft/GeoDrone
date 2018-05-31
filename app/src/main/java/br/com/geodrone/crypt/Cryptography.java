package br.com.geodrone.crypt;

import android.util.Base64;

import java.io.IOException;


@SuppressWarnings("restriction")
public abstract class Cryptography {

	public static final String CRYPTO_CIPHER = "CIPHER";

	public abstract String encrypt(String input) throws Exception;

	public abstract byte[] encrypt(byte[] bytes) throws Exception;

	public abstract String decrypt(String input) throws Exception;

	public abstract byte[] decrypt(byte[] bytes) throws Exception;

	public static Cryptography getInstance(String algorithm) throws Exception {
		return getInstance(algorithm, null);
	}

	public static Cryptography getInstance(String algorithm, String secretKey) throws Exception {
		if (algorithm == null) {
			throw new IllegalArgumentException("Algorithm must be not null.");
		}

		if (algorithm.equals(Cryptography.CRYPTO_CIPHER)) {
			return new CryptographyCipher(secretKey);

		} else {
			throw new IllegalArgumentException("Algorithm [" + algorithm + "] invalid.");
		}
	}

	protected String encode(byte[] bytes) {
		if (bytes == null) {
			return null;
		}

		return Base64.encodeToString(bytes, Base64.DEFAULT);

		/*Base64.Encoder encoder = new BASE64Encoder();
		return encoder.encode(bytes);*/
	}

	protected byte[] decode(String input) throws IOException {
		if (input == null) {
			return null;
		}

		return Base64.decode(input, Base64.DEFAULT);
		/*BASE64Decoder decoder = new BASE64Decoder();
		return decoder.decodeBuffer(input);*/
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(new BCryptPasswordEncoder().encode("@ngul@r0"));
            System.out.println(BCrypt.hashpw("@ngul@r0", BCrypt.gensalt()));
			Cryptography cryptography = Cryptography.getInstance(Cryptography.CRYPTO_CIPHER, KeyUtils.SECRET_KEY);
			System.out.println(cryptography.encrypt("@ngul@r0"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}