package com.qsa.quitSmokingApp.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

public final class LoginInfoUtil {

    private LoginInfoUtil(){}

    private static final SecureRandom random = new SecureRandom();

    public static String createSecureText(String text){
        byte[] salt = new byte[16];
        String securedText = null;
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(text.toCharArray(), salt, 65536, 128);
        try{
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] securedTextBytes = factory.generateSecret(spec).getEncoded();
            securedText = new String(securedTextBytes);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return securedText;
    }

}
