package com.qsa.quitSmokingApp.controller;


import com.qsa.quitSmokingApp.adapter.JsonUtil;
import com.qsa.quitSmokingApp.logic.SmokingDataService;
import com.qsa.quitSmokingApp.model.SmokingData;
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

@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@RunWith(SpringRunner.class)
@WebMvcTest(SmokingDataController.class)
public class SmokingDataControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SmokingDataService service;


    @Test
    public void readAllSmokingData() throws Exception {
         var smokingData = new SmokingData();
         smokingData.setAgeStartedSmoking(20);
         smokingData.setCigarettesPerDay(20);
         smokingData.setTherapyMode("cessation");
         List<SmokingData> smokingDataList = Collections.singletonList(smokingData);
         given(service.getAllSmokingData()).willReturn(smokingDataList);
         mvc.perform(MockMvcRequestBuilders.get("/smkdata")
                 .with(SecurityMockMvcRequestPostProcessors.user("user").password("abcd1234")))
                 .andExpect(MockMvcResultMatchers.status().isOk())
                 .andExpect(MockMvcResultMatchers.jsonPath("$", IsCollectionWithSize.hasSize(1)))
                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].ageStartedSmoking", Is.is(20)))
                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].cigarettesPerDay", Is.is(20)))
                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].therapyMode", Is.is("cessation")));
    }

    @Test
    public void readSmokingDataById() throws Exception {
        var smokingData = new SmokingData();
        smokingData.setAgeStartedSmoking(20);
        smokingData.setCigarettesPerDay(20);
        smokingData.setTherapyMode("cessation");
        smokingData.setId(20);
        given(service.getSmokingDataById(smokingData.getId())).willReturn(smokingData);
        mvc.perform(MockMvcRequestBuilders.get("/smkdata/" + smokingData.getId())
                .with(SecurityMockMvcRequestPostProcessors.user("user").password("abcd1234")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("ageStartedSmoking", Is.is(20)))
                .andExpect(MockMvcResultMatchers.jsonPath("cigarettesPerDay", Is.is(20)))
                .andExpect(MockMvcResultMatchers.jsonPath("therapyMode", Is.is("cessation")));
    }

    @Test
    public void createSmokingData() throws Exception {
        var smokingData = new SmokingData();
        smokingData.setAgeStartedSmoking(20);
        smokingData.setCigarettesPerDay(20);
        smokingData.setTherapyMode("cessation");
        given(service.createSmokingData(any(SmokingData.class))).willReturn(smokingData);
        mvc.perform(MockMvcRequestBuilders.post("/smkdata")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(smokingData))
                .with(SecurityMockMvcRequestPostProcessors.user("user").password("abcd1234")))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("ageStartedSmoking", Is.is(20)))
                .andExpect(MockMvcResultMatchers.jsonPath("cigarettesPerDay", Is.is(20)))
                .andExpect(MockMvcResultMatchers.jsonPath("therapyMode", Is.is("cessation")));
    }

}
