package com.pe.shopathome.controller;

import com.pe.shopathome.converters.UserConverter;
import com.pe.shopathome.dto.LoginRequestDTO;
import com.pe.shopathome.dto.LoginResponseDTO;
import com.pe.shopathome.dto.SignUpRequestDTO;
import com.pe.shopathome.dto.UserDTO;
import com.pe.shopathome.entity.User;
import com.pe.shopathome.repository.UserRepository;
import com.pe.shopathome.services.UserService;
import com.pe.shopathome.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @PostMapping("/signup")
    public ResponseEntity<WrapperResponse<UserDTO>> signup(@RequestBody SignUpRequestDTO request){
        User user=userService.createUser(userConverter.signup(request));
        return new WrapperResponse<>(true,"success",userConverter.fromEntity(user))
                .createResponse();
    }

    @PostMapping("/login")
    public ResponseEntity<WrapperResponse<LoginResponseDTO>> login(@RequestBody LoginRequestDTO request){
        LoginResponseDTO response=userService.login(request);
        return new WrapperResponse<>(true,"success",response)
                .createResponse();
    }

}
