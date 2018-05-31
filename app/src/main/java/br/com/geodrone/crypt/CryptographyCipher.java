package br.com.geodrone.crypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class CryptographyCipher extends Cryptography {

	private final String ALGORITHM_CIPHER = "DESede/ECB/PKCS5Padding";
	private final String ALGORITHM_KEY    = "DESede";

	private SecretKey key = null;
	private Cipher cipher = null;

	protected CryptographyCipher(String secretKey) throws Exception {
		if (secretKey == null) {
			throw new IllegalArgumentException("SecretKey must be not null.");
		}
		initialize(secretKey);
	}

	private void initialize(String secretKey) throws Exception {
		byte[] keyBytes = secretKey.getBytes();
		DESedeKeySpec keySpec = new DESedeKeySpec(keyBytes);
		SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM_KEY);
		key = skf.generateSecret(keySpec);
		cipher = Cipher.getInstance(ALGORITHM_CIPHER);
	}

	@Override
	public String encrypt(String input) throws Exception {
		if (input == null) {
			return null;
		}

		byte[] encryptBytes = encrypt(input.getBytes());
		return encode(encryptBytes);

	}

	@Override
	public byte[] encrypt(byte[] bytes) throws Exception {
		if (bytes == null) {
			return null;
		}

		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(bytes);
	}

	@Override
	public String decrypt(String input) throws Exception {
		if (input == null) {
			return null;
		}

		byte[] bytes = decode(input);
		return new String(decrypt(bytes));
	}

	@Override
	public byte[] decrypt(byte[] bytes) throws Exception {
		if (bytes == null) {
			return null;
		}

		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(bytes);
	}

}