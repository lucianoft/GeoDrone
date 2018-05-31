package br.com.geodrone.crypt;

public class CryptoUtils {

	private Cryptography getCryptography() throws Exception {
		return Cryptography.getInstance(Cryptography.CRYPTO_CIPHER, KeyUtils.SECRET_KEY);
	}

	public String encrypt(String plaintext) throws Exception {
		Cryptography cryptography = getCryptography();
		return cryptography.encrypt(plaintext);
	}

	public String decrypt(String hashed) throws Exception {
		Cryptography cryptography = getCryptography();
		return cryptography.decrypt(hashed);
	}

}