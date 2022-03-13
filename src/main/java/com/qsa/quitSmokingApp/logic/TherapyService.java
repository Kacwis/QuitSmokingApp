package com.qsa.quitSmokingApp.logic;

import com.qsa.quitSmokingApp.model.AppUser;
import com.qsa.quitSmokingApp.model.Therapy;
import com.qsa.quitSmokingApp.model.projection.TherapyWriteModel;
import com.qsa.quitSmokingApp.model.repository.TherapyRepository;
import com.qsa.quitSmokingApp.model.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Therapy therapy = therapyWriteModel.toTherapy();
        if(!userRepository.existsById(therapyWriteModel.getUserId())) {
            return therapyRepository.save(therapy);
        }
        var appUser = userRepository.findById(therapyWriteModel.getUserId()).get();
        therapy.setAppUser(appUser);
        var therapyResult = therapyRepository.save(therapy);
        appUser.getTherapies().add(therapyResult);
        return therapyResult;
    }

    public List<Therapy> getAllTherapies(){
        var result = therapyRepository.findAll();
        return result;
    }

    public Optional<Therapy> getTherapyById(int id){
        var result = therapyRepository.findById(id);
        return result;
    }




}
