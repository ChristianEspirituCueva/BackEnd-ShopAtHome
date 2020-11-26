package com.pe.shopathome.services;

import com.pe.shopathome.entity.*;
import com.pe.shopathome.exceptions.GeneralServiceException;
import com.pe.shopathome.exceptions.NoDataFoundException;
import com.pe.shopathome.exceptions.ValidateServiceException;
import com.pe.shopathome.repository.ProductRepository;
import com.pe.shopathome.repository.ProfileRepository;
import com.pe.shopathome.repository.UserRepository;
import com.pe.shopathome.validators.OrderValidator;
import com.pe.shopathome.validators.ProductValidator;
import com.pe.shopathome.validators.ProfileValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    public Profile findById(Long profileId) {
        try {
            log.debug("findById => " + profileId);
            Profile profile = profileRepository.findById(profileId)
                    .orElseThrow(() -> new NoDataFoundException("No existe el perfil"));
            return profile;
        } catch(ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        }catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Transactional
    public void delete(Long profileId) {
        try {
            Profile profile = profileRepository.findById(profileId)
                    .orElseThrow(() -> new NoDataFoundException("No existe el producto"));
            profileRepository.delete(profile);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }


    public List<Profile> findAll(){
        try {
            List<Profile> profiles = profileRepository.findAll();
            return profiles;
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Transactional
    public Profile save(Profile profile) {
        try {
            ProfileValidator.save(profile);

            //User user= UserPrincipal.getCurrentUser();
            User user = userRepository.findById(profile.getUser().getId()).orElse((null));
            Profile exitProfile = profileRepository.findById(profile.getId())
                    .orElseThrow(()->new NoDataFoundException("No existe el perfil"));
            exitProfile.setCity(profile.getCity());
            exitProfile.setCountry(profile.getCountry());
            exitProfile.setFirstName(profile.getFirstName());
            exitProfile.setPhone(profile.getPhone());
            exitProfile.setUser(profile.getUser());
            exitProfile.setLastName(profile.getLastName());

            profileRepository.save(exitProfile);

            return exitProfile;
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }


}
