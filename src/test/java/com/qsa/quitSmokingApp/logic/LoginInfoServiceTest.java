package com.qsa.quitSmokingApp.logic;

import com.qsa.quitSmokingApp.model.UserLoginInfo;
import com.qsa.quitSmokingApp.model.repository.UserLoginInfoRepository;
import com.qsa.quitSmokingApp.util.LoginInfoUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class LoginInfoServiceTest {

    UserLoginInfoRepository repository = mock(UserLoginInfoRepository.class);
    LoginInfoService service = new LoginInfoService(repository);

    @Test
    public void getUserLoginInfo(){
        var password = "abcd1234";
        var login = "login";
        var result = new UserLoginInfo();
        result.setPassword(LoginInfoUtil.createSecureText(password));
        result.setLogin(LoginInfoUtil.createSecureText(login));
        given(repository.save(any(UserLoginInfo.class))).willReturn(result);
        assertThat(service.getUserLoginInfo(password, login))
                .hasFieldOrPropertyWithValue("login", result.getLogin())
                .hasFieldOrPropertyWithValue("password", result.getPassword());
    }
}
