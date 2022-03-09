package com.qsa.quitSmokingApp.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.qsa.quitSmokingApp.adapter.JsonUtil;
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
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
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
        AppUser appUser = new AppUser();
        appUser.setDateOfBirth(LocalDate.parse("2012-02-10"));
        appUser.setGender("Male");
        appUser.setAverageSleepingTime(10);
        List<AppUser> allUsers = Collections.singletonList(appUser);
        given(service.getAllUsers()).willReturn(allUsers);
        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/users" )
                .contentType(MediaType.APPLICATION_JSON)
                .with(SecurityMockMvcRequestPostProcessors.user("user").password("abcd1234")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", IsCollectionWithSize.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].gender", Is.is(appUser.getGender())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].averageSleepingTime", Is.is(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dateOfBirth", Is.is("2012-02-10")));
    }

    @Test
    public void readUserById() throws Exception {
        AppUser appUser = new AppUser();
        appUser.setDateOfBirth(LocalDate.parse("2012-10-02"));
        appUser.setGender("Male");
        appUser.setAverageSleepingTime(10);
        appUser.setId(1);
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
        AppUser appUser = new AppUser();
        appUser.setDateOfBirth(LocalDate.parse("2012-10-02"));
        appUser.setGender("Male");
        appUser.setAverageSleepingTime(10);
        appUser.setId(1);
        given(service.getUserById(appUser.getId())).willReturn(appUser);
        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/users/" + 1000)
                .with(SecurityMockMvcRequestPostProcessors.user("user").password("abcd1234")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void createUser_without_smokingData_and_therapies() throws Exception {
        int averageSleepingTime = 10,
                ageStartedSmoking = 20,
                cigarettesPerDay = 20;
        String gender = "Male",
                login = "login",
                password = "password",
                therapyMode = "cessation";
        LocalDate dateOfBirth = LocalDate.parse("2012-02-02");
        AppUser appUser = new AppUser();
        appUser.setDateOfBirth(dateOfBirth);
        appUser.setGender(gender);
        appUser.setAverageSleepingTime(averageSleepingTime);
        appUser.setId(50);
        var newUserWriteModel = new NewUserWriteModel(averageSleepingTime, dateOfBirth, gender, login, password, ageStartedSmoking, cigarettesPerDay, therapyMode);
        when(service.createNewUser(any(NewUserWriteModel.class))).thenReturn(appUser);
        mvc.perform(MockMvcRequestBuilders.post("/users")
                .with(SecurityMockMvcRequestPostProcessors.user("user").password("abcd1234"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(newUserWriteModel)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("dateOfBirth",Is.is("2012-02-02")))
                .andExpect(MockMvcResultMatchers.jsonPath("gender", Is.is(gender)))
                .andExpect(MockMvcResultMatchers.jsonPath("averageSleepingTime", Is.is(averageSleepingTime)));
    }



}
