package com.pe.shopathome.controller;

import com.pe.shopathome.converters.AbstractConverter;
import com.pe.shopathome.converters.ProductConverter;
import com.pe.shopathome.converters.ProfileConverter;
import com.pe.shopathome.converters.UserConverter;
import com.pe.shopathome.dto.ProductDTO;
import com.pe.shopathome.dto.ProfileDTO;
import com.pe.shopathome.entity.Order;
import com.pe.shopathome.entity.OrderLine;
import com.pe.shopathome.entity.Product;
import com.pe.shopathome.entity.Profile;
import com.pe.shopathome.services.ProductService;
import com.pe.shopathome.services.ProfileService;
import com.pe.shopathome.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/profiles")
public class ProfileController  {
    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileConverter profileConverter;

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileDTO> findById(@PathVariable("profileId") Long profileId) {
        Profile profile = profileService.findById(profileId);
        ProfileDTO profileDTO = profileConverter.fromEntity(profile);
        return new WrapperResponse(true, "success", profileDTO).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity<?> delete(@PathVariable("profileId") Long profileId) {
        profileService.delete(profileId);
        return new WrapperResponse(true, "success", null)
                .createResponse(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProfileDTO>> findAll(){
        List<Profile> profiles = profileService.findAll();
        List<ProfileDTO> dtoProfiles = profileConverter.fromEntity(profiles);

        return new WrapperResponse(true, "success", dtoProfiles)
                .createResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProfileDTO> create(@RequestBody ProfileDTO profile) {
        Profile newProfile = profileService.save(profileConverter.fromDTO(profile));
        ProfileDTO profileDTO = profileConverter.fromEntity(newProfile);

        return new WrapperResponse(true, "success", profileDTO)
                .createResponse(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProfileDTO> update(@RequestBody ProfileDTO profile) {
        Profile updateProfile = profileService.save(profileConverter.fromDTO(profile));
        ProfileDTO profileDTO = profileConverter.fromEntity(updateProfile);

        return new WrapperResponse(true, "success", profileDTO)
                .createResponse(HttpStatus.OK);
    }
}
