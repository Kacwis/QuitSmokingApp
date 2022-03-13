package com.qsa.quitSmokingApp.logic;

import com.qsa.quitSmokingApp.model.AppUser;
import com.qsa.quitSmokingApp.model.SmokingData;
import com.qsa.quitSmokingApp.model.Therapy;
import com.qsa.quitSmokingApp.model.UserLoginInfo;
import com.qsa.quitSmokingApp.model.projection.NewUserWriteModel;
import com.qsa.quitSmokingApp.model.repository.SmokingDataRepository;
import com.qsa.quitSmokingApp.model.repository.UserLoginInfoRepository;
import com.qsa.quitSmokingApp.model.repository.UserRepository;
import com.qsa.quitSmokingApp.util.TestingUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.qsa.quitSmokingApp.util.TestingUtil.createAppUserSample;
import static com.qsa.quitSmokingApp.util.TestingUtil.createNewTherapySample;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class AppUserServiceTest {

    SmokingDataRepository smokingDataRepository = mock(SmokingDataRepository.class);
    UserLoginInfoRepository userLoginInfoRepository = mock(UserLoginInfoRepository.class);
    LoginInfoService loginInfoService = mock(LoginInfoService.class);
    UserRepository userRepository = mock(UserRepository.class);
    AppUserService service = new AppUserService(smokingDataRepository, userLoginInfoRepository, loginInfoService, userRepository);

    @Test
    public void createNewUserWithValidData(){
        var appUser = TestingUtil.createAppUserSample(10);
        var newUserWriteModel = TestingUtil.createNewUserWriteModelSample(appUser);
        var userLoginResult = new UserLoginInfo();
        var smokingDataResult = TestingUtil.createSmokingDataSample(newUserWriteModel);
        userLoginResult.setLogin(newUserWriteModel.getLogin());
        userLoginResult.setPassword(newUserWriteModel.getPassword());
        given(userRepository.save(any(AppUser.class))).willReturn(appUser);
        given(loginInfoService.getUserLoginInfo(newUserWriteModel.getLogin(), newUserWriteModel.getPassword())).willReturn(userLoginResult);
        given(userLoginInfoRepository.save(any(UserLoginInfo.class))).willReturn(userLoginResult);
        given(smokingDataRepository.save(any(SmokingData.class))).willReturn(smokingDataResult);
        assertThat(service.createNewUser(newUserWriteModel))
                .hasFieldOrPropertyWithValue("gender", appUser.getGender())
                .hasFieldOrPropertyWithValue("dateOfBirth", appUser.getDateOfBirth())
                .hasFieldOrPropertyWithValue("averageSleepingTime", appUser.getAverageSleepingTime());
    }

    @Test
    public void getAllAppUsers(){
        var appUser = createAppUserSample(10);
        List<AppUser> therapyList = Collections.singletonList(appUser);
        given(userRepository.findAll()).willReturn(therapyList);
        assertThat(service.getAllUsers()).isEqualTo(therapyList);
    }

    @Test
    public void getAppUserById(){
        var appUser = createAppUserSample(10);
        given(userRepository.findById(any(Integer.class))).willReturn(Optional.of(appUser));
        assertThat(service.getUserById(appUser.getId()))
                .hasFieldOrPropertyWithValue("gender", appUser.getGender())
                .hasFieldOrPropertyWithValue("dateOfBirth", appUser.getDateOfBirth())
                .hasFieldOrPropertyWithValue("averageSleepingTime", appUser.getAverageSleepingTime());
    }








}