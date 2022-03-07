package com.qsa.quitSmokingApp.logic;

import com.qsa.quitSmokingApp.model.AppUser;
import com.qsa.quitSmokingApp.model.Therapy;
import com.qsa.quitSmokingApp.model.projection.TherapyWriteModel;
import com.qsa.quitSmokingApp.model.repository.TherapyRepository;
import com.qsa.quitSmokingApp.model.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TherapyService {

    final private TherapyRepository therapyRepository;
    final private UserRepository userRepository;

    TherapyService(final TherapyRepository therapyRepository,
                   final UserRepository userRepository){

        this.therapyRepository = therapyRepository;
        this.userRepository = userRepository;
    }

    public Therapy addNewTherapyToAppUserAndReturnTherapy(TherapyWriteModel therapyWriteModel){
        AppUser appUser = null;
        Therapy therapy = therapyWriteModel.toTherapy();
        if(!userRepository.existsById(therapyWriteModel.getUserId())) {
            var therapyResult = therapyRepository.save(therapy);
            return therapyResult;
        }
        appUser = userRepository.findById(therapyWriteModel.getUserId()).get();
        therapy.setAppUser(appUser);
        var therapyResult = therapyRepository.save(therapy);
        appUser.getTherapies().add(therapyResult);
        return therapyResult;
    }




}
