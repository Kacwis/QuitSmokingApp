package com.qsa.quitSmokingApp.logic;

import com.qsa.quitSmokingApp.model.SmokingData;
import com.qsa.quitSmokingApp.model.Therapy;
import com.qsa.quitSmokingApp.model.repository.SmokingDataRepository;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.qsa.quitSmokingApp.util.TestingUtil.createNewTherapySample;
import static com.qsa.quitSmokingApp.util.TestingUtil.createSmokingDataSample;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class SmokingDataTest {
    SmokingDataRepository repository = mock(SmokingDataRepository.class);
    SmokingDataService service = new SmokingDataService(repository);

    @Test
    public void getAllSmokingData(){
        var smokingData = createSmokingDataSample();
        List<SmokingData> smokingDataList = Collections.singletonList(smokingData);
        given(repository.findAll()).willReturn(smokingDataList);
        assertThat(service.getAllSmokingData()).isEqualTo(smokingDataList);
    }

    @Test
    public void getSmokingDataById(){
        var smokingData = createSmokingDataSample();
        given(repository.findById(any(Integer.class))).willReturn(Optional.of(smokingData));
        assertThat(service.getSmokingDataById(smokingData.getId()))
                .hasFieldOrPropertyWithValue("cigarettesPerDay", smokingData.getCigarettesPerDay())
                .hasFieldOrPropertyWithValue("ageStartedSmoking", smokingData.getAgeStartedSmoking())
                .hasFieldOrPropertyWithValue("therapyMode", smokingData.getTherapyMode());
    }

    @Test
    public void saveSmokingData(){
        var smokingData = createSmokingDataSample();
        given(repository.save(any(SmokingData.class))).willReturn(smokingData);
        assertThat(service.createSmokingData(smokingData))
                .hasFieldOrPropertyWithValue("cigarettesPerDay", smokingData.getCigarettesPerDay())
                .hasFieldOrPropertyWithValue("ageStartedSmoking", smokingData.getAgeStartedSmoking())
                .hasFieldOrPropertyWithValue("therapyMode", smokingData.getTherapyMode());
    }
}
