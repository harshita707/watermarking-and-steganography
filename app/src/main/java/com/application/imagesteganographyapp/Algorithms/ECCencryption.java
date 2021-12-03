package com.application.imagesteganographyapp.Algorithms;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class ECCencryption {

    public static String encryptText(String inputText, String password) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        KeyPairGenerator ecKeyGen = KeyPairGenerator.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
        ecKeyGen.initialize(new ECGenParameterSpec("secp256r1"));

        KeyPair ecKeyPair = generateKey(password) ;

        Cipher iesCipher = Cipher.getInstance("ECIESwithAES-CBC");

        iesCipher.init(Cipher.ENCRYPT_MODE, ecKeyPair.getPublic());

        byte[] ciphertext = iesCipher.doFinal(inputText.getBytes());
        String outputText = Hex.toHexString(ciphertext);

        return outputText;

    }

    private static KeyPair generateKey(String password) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key =digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "EC");
        KeyPairGenerator ecKeyGen = KeyPairGenerator.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
        ecKeyGen.initialize(new ECGenParameterSpec("secp256r1"));
        KeyPair ecKeyPair = ecKeyGen.generateKeyPair();
        return ecKeyPair;
    }

    public static String decryptText(String inputText, String password) throws Exception {

        Security.addProvider(new BouncyCastleProvider());

        KeyPairGenerator ecKeyGen = KeyPairGenerator.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
        ecKeyGen.initialize(new ECGenParameterSpec("secp256r1"));

        KeyPair ecKeyPair = generateKey(password) ;

        Cipher iesDecipher = Cipher.getInstance("ECIESwithAES-CBC");

        byte[] ciphertext = iesDecipher.doFinal(inputText.getBytes());

        iesDecipher.init(Cipher.DECRYPT_MODE, ecKeyPair.getPrivate(), iesDecipher.getParameters());
        byte[] plaintext = iesDecipher.doFinal(ciphertext);

        String outputText = new String(plaintext);

        return outputText;

    }


}
