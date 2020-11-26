package com.pe.shopathome.converters;

import com.pe.shopathome.dto.SignUpRequestDTO;
import com.pe.shopathome.dto.UserDTO;
import com.pe.shopathome.entity.User;

public class UserConverter extends  AbstractConverter<User, UserDTO> {
    @Override
    public UserDTO fromEntity(User entity) {
        if(entity == null) return null;
        return UserDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())

                .build();
    }

    @Override
    public User fromDTO(UserDTO dto) {
        if(dto == null) return null;
        return User.builder()
                .Id(dto.getId())
                .username(dto.getUsername())

                .build();
    }

    public User signup(SignUpRequestDTO dto){
        if(dto == null) return null;
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())

                .build();
    }
}
