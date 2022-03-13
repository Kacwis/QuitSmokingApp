package com.qsa.quitSmokingApp.logic;

import com.qsa.quitSmokingApp.model.UserLoginInfo;
import com.qsa.quitSmokingApp.model.repository.UserLoginInfoRepository;
import com.qsa.quitSmokingApp.util.LoginInfoUtil;
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
        var result = new UserLoginInfo();
        result.setPassword(LoginInfoUtil.createSecureText(password));
        result.setLogin(LoginInfoUtil.createSecureText(login));
        return repository.save(result);
    }

}
