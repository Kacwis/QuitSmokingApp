package com.qsa.quitSmokingApp.logic;

import com.qsa.quitSmokingApp.model.UserLoginInfo;
import com.qsa.quitSmokingApp.model.repository.UserLoginInfoRepository;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

@Service
public class LoginInfoService {

    private UserLoginInfoRepository repository;

    LoginInfoService(final UserLoginInfoRepository repository){
        this.repository = repository;
    }

    public UserLoginInfo getUserLoginInfo(String password, String login){
        SecureRandom random = new SecureRandom();
        byte[] passwordSalt = new byte[16];
        byte[] loginSalt = new byte[16];
        String hashedPassword = null,
                hashedLogin = null;
        random.nextBytes(passwordSalt);
        KeySpec passwordSpec = new PBEKeySpec(password.toCharArray(), passwordSalt, 65536, 128);
        KeySpec loginSpec = new PBEKeySpec(login.toCharArray(), loginSalt, 65536, 128);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] passwordHash = factory.generateSecret(passwordSpec).getEncoded();
            byte[] loginHash = factory.generateSecret(loginSpec).getEncoded();
            hashedPassword = new String(passwordHash);
            hashedLogin = new String(loginHash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        var result = new UserLoginInfo();
        result.setPassword(hashedPassword);
        result.setLogin(hashedLogin);
        var saveResult= repository.save(result);
        return saveResult;
    }

}
