package com.qsa.quitSmokingApp.logic;

import com.qsa.quitSmokingApp.model.SmokingData;
import com.qsa.quitSmokingApp.model.repository.SmokingDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmokingDataService {

    private final SmokingDataRepository smokingDataRepository;

    SmokingDataService(final SmokingDataRepository repository){
        this.smokingDataRepository = repository;
    }

    public List<SmokingData> getAllSmokingData(){
        var result = smokingDataRepository.findAll();
        return result;
    }

    public SmokingData getSmokingDataById(int id){
        var result = smokingDataRepository.findById(id);
        if(result.isEmpty())
            return null;
        return result.get();
    }

    public SmokingData createSmokingData(SmokingData toCreate){
        var result = smokingDataRepository.save(toCreate);
        return result;
    }


}
