package com.example.articleapp.models;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {
    public static String md5String (String password) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(password.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger bigInteger = new BigInteger(1, digest);
        StringBuilder md5Hex = new StringBuilder(bigInteger.toString(16));

        while (md5Hex.length() < 32)
            md5Hex.insert(0, "0");

        return md5Hex.toString();
    }
}
