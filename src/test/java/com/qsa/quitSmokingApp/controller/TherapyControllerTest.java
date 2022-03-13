package com.qsa.quitSmokingApp.controller;


import com.qsa.quitSmokingApp.logic.TherapyService;
import com.qsa.quitSmokingApp.model.SmokingData;
import com.qsa.quitSmokingApp.model.Therapy;
import com.qsa.quitSmokingApp.model.projection.TherapyWriteModel;
import com.qsa.quitSmokingApp.model.repository.TherapyRepository;
import com.qsa.quitSmokingApp.util.JsonUtil;
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
import java.util.Optional;

import static com.qsa.quitSmokingApp.util.TestingUtil.createNewTherapySample;
import static com.qsa.quitSmokingApp.util.TestingUtil.createSmokingDataSample;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@RunWith(SpringRunner.class)
@WebMvcTest(TherapyController.class)
public class TherapyControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    TherapyRepository repository;

    @MockBean
    TherapyService service;

    @Test
    public void readAllTherapies() throws Exception {
        var therapy = createNewTherapySample();
        List<Therapy> therapyList = Collections.singletonList(therapy);
        given(service.getAllTherapies()).willReturn(therapyList);
        mvc.perform(MockMvcRequestBuilders.get("/therapies")
                .with(SecurityMockMvcRequestPostProcessors.user("user").password("abcd1234")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", IsCollectionWithSize.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].startingDate", Is.is(therapy.getStartingDate().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].plannedEndDate", Is.is(therapy.getPlannedEndDate().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].periodBetweenCigarettes", Is.is(therapy.getPeriodBetweenCigarettes())));
    }

    @Test
    public void readTherapyById() throws Exception {
        var therapy = createNewTherapySample();
        given(service.getTherapyById(any(Integer.class))).willReturn(Optional.of(therapy));
        mvc.perform(MockMvcRequestBuilders.get("/therapies/" + therapy.getId())
                .with(SecurityMockMvcRequestPostProcessors.user("user").password("abcd1234")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("startingDate", Is.is(therapy.getStartingDate().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("plannedEndDate", Is.is(therapy.getPlannedEndDate().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("periodBetweenCigarettes", Is.is(therapy.getPeriodBetweenCigarettes())));
    }

    @Test
    public void createTherapy() throws Exception {
        var therapy = createNewTherapySample();
        var therapyWriteModel = new TherapyWriteModel(therapy.getPeriodBetweenCigarettes(), therapy.getPlannedEndDate(), therapy.getStartingDate(), 10);
        given(service.addNewTherapyToAppUserAndReturnTherapy(any(TherapyWriteModel.class))).willReturn(therapy);
        mvc.perform(MockMvcRequestBuilders.post("/therapies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(therapyWriteModel))
                .with(SecurityMockMvcRequestPostProcessors.user("user").password("abcd1234")))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("startingDate", Is.is(therapy.getStartingDate().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("plannedEndDate", Is.is(therapy.getPlannedEndDate().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("periodBetweenCigarettes", Is.is(therapy.getPeriodBetweenCigarettes())));
    }
}
