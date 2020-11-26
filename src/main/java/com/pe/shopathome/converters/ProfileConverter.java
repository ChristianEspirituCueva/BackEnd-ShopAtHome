package com.pe.shopathome.converters;

import com.pe.shopathome.dto.ProfileDTO;
import com.pe.shopathome.entity.Profile;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProfileConverter extends AbstractConverter<Profile, ProfileDTO>{
    private UserConverter userConverter;
    @Override
    public ProfileDTO fromEntity(Profile entity) {
        if(entity == null) return null;

        return ProfileDTO.builder()
                .id(entity.getId())
                .city(entity.getCity())
                .country(entity.getCountry())
                .firstName(entity.getFirstName())
                .role(entity.getRole())
                .lastName(entity.getLastName())
                .phone(entity.getPhone())
                .user(userConverter.fromEntity(entity.getUser()))
                .build();
    }

    @Override
    public Profile fromDTO(ProfileDTO dto) {
        if (dto == null) return null;

        return Profile.builder()
                .id(dto.getId())
                .city(dto.getCity())
                .country(dto.getCountry())
                .firstName(dto.getFirstName())
                .role(dto.getRole())
                .lastName(dto.getLastName())
                .phone(dto.getPhone())
                .user(userConverter.fromDTO(dto.getUser()))
                .build();
    }
}
