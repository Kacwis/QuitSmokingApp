package com.qsa.quitSmokingApp.controller;

import com.qsa.quitSmokingApp.util.TestingUtil;
import com.qsa.quitSmokingApp.util.JsonUtil;
import com.qsa.quitSmokingApp.logic.AppUserService;
import com.qsa.quitSmokingApp.model.AppUser;
import com.qsa.quitSmokingApp.model.projection.NewUserWriteModel;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private AppUserService service;

    @Test
    public void readAllUsers() throws Exception{
        AppUser appUser = TestingUtil.createAppUserSample(1);
        List<AppUser> allUsers = Collections.singletonList(appUser);
        given(service.getAllUsers()).willReturn(allUsers);
        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/users" )
                .contentType(MediaType.APPLICATION_JSON)
                .with(SecurityMockMvcRequestPostProcessors.user("user").password("abcd1234")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", IsCollectionWithSize.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].gender", Is.is(appUser.getGender())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].averageSleepingTime", Is.is(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dateOfBirth", Is.is("2012-10-02")));
    }

    @Test
    public void readUserById() throws Exception {
        AppUser appUser = TestingUtil.createAppUserSample(1);
        given(service.getUserById(appUser.getId())).willReturn(appUser);
        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/users/" + appUser.getId())
                .with(SecurityMockMvcRequestPostProcessors.user("user").password("abcd1234")))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("gender", Is.is("Male")))
                .andExpect(MockMvcResultMatchers.jsonPath("averageSleepingTime", Is.is(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("dateOfBirth", Is.is("2012-10-02")));
    }

    @Test
    public void readUserById_whenIdIsIncorrect() throws Exception {
        AppUser appUser = TestingUtil.createAppUserSample(1);
        given(service.getUserById(appUser.getId())).willReturn(appUser);
        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/users/" + 1000)
                .with(SecurityMockMvcRequestPostProcessors.user("user").password("abcd1234")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void createUser_without_smokingData_and_therapies() throws Exception {
        var appUser = TestingUtil.createAppUserSample(50);
        var newUserWriteModel = TestingUtil.createNewUserWriteModelSample(appUser);
        when(service.createNewUser(any(NewUserWriteModel.class))).thenReturn(appUser);
        mvc.perform(MockMvcRequestBuilders.post("/users")
                .with(SecurityMockMvcRequestPostProcessors.user("user").password("abcd1234"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(newUserWriteModel)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("dateOfBirth",Is.is("2012-10-02")))
                .andExpect(MockMvcResultMatchers.jsonPath("gender", Is.is(appUser.getGender())))
                .andExpect(MockMvcResultMatchers.jsonPath("averageSleepingTime", Is.is(appUser.getAverageSleepingTime())));
    }



}
