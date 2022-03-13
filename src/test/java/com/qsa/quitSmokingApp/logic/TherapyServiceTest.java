package com.qsa.quitSmokingApp.logic;

import com.qsa.quitSmokingApp.model.Therapy;
import com.qsa.quitSmokingApp.model.projection.TherapyWriteModel;
import com.qsa.quitSmokingApp.model.repository.TherapyRepository;
import com.qsa.quitSmokingApp.model.repository.UserRepository;
import com.qsa.quitSmokingApp.util.TestingUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.qsa.quitSmokingApp.util.TestingUtil.createNewTherapySample;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class TherapyServiceTest {
    UserRepository userRepository = mock(UserRepository.class);
    TherapyRepository therapyRepository = mock(TherapyRepository.class);
    TherapyService therapyService = new TherapyService(therapyRepository, userRepository);

    @Test
    public void addNewTherapyToAppUser_should_return_therapy_without_appUser(){
        var therapy = createNewTherapySample();
        given(userRepository.existsById(any(Integer.class))).willReturn(false);
        given(therapyRepository.save(any(Therapy.class))).willReturn(therapy);
        var therapyWriteModel = new TherapyWriteModel(10, LocalDate.parse("2012-02-02"), LocalDate.parse("2012-01-01"), 1);
        assertThat(therapyService.addNewTherapyToAppUserAndReturnTherapy(therapyWriteModel)).hasFieldOrPropertyWithValue("appUser", null);
    }

    @Test
    public void addNewTherapyToAppUser_should_return_therapy_with_appUser(){
        var appUser = TestingUtil.createAppUserSample(10);
        var therapy = createNewTherapySample(appUser);
        given(userRepository.existsById(any(Integer.class))).willReturn(true);
        given(therapyRepository.save(any(Therapy.class))).willReturn(therapy);
        given(userRepository.findById(any(Integer.class))).willReturn(Optional.of(appUser));
        var therapyWriteModel = new TherapyWriteModel(10, LocalDate.parse("2012-02-02"), LocalDate.parse("2012-01-01"), 1);
        assertThat(therapyService.addNewTherapyToAppUserAndReturnTherapy(therapyWriteModel)).hasFieldOrPropertyWithValue("appUser", appUser);
    }

    @Test
    public void getAllTherapies(){
        var therapy = createNewTherapySample();
        List<Therapy> therapyList = Collections.singletonList(therapy);
        given(therapyRepository.findAll()).willReturn(therapyList);
        assertThat(therapyService.getAllTherapies()).isEqualTo(therapyList);
    }

    @Test
    public void getTherapyById(){
        var therapy = createNewTherapySample();
        given(therapyRepository.findById(any(Integer.class))).willReturn(Optional.of(therapy));
        assertThat(therapyService.getTherapyById(therapy.getId())).isEqualTo(Optional.of(therapy));
    }
}
