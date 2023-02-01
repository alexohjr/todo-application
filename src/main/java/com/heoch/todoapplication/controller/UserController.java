package com.heoch.todoapplication.controller;

import com.heoch.todoapplication.dto.ResponseDTO;
import com.heoch.todoapplication.dto.UserDTO;
import com.heoch.todoapplication.model.UserEntity;
import com.heoch.todoapplication.security.TokenProvider;
import com.heoch.todoapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final TokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        try {
            // 요청을 이용해 저장할 사용자 만들기
            UserEntity user = UserEntity.builder()
                    .email(userDTO.getEmail())
                    .userName(userDTO.getUserName())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .build();
            // 서비스를 이용해 리포지터리에 사용자 저장
            UserEntity registeredUser = userService.create(user);
            UserDTO responseUserDTO = UserDTO.builder()
                    .email(registeredUser.getEmail())
                    .id(registeredUser.getUserId())
                    .userName(registeredUser.getUserName())
                    .build();

            // 사용자 정보는 항상 하나이므로 리스트로 만들어야 하는 ResponseDTO를 사용하지 않고 그냥 UserDTO리턴
            return ResponseEntity.ok().body(responseUserDTO);
        } catch(Exception e) {
            ResponseDTO<String> responseDTO = ResponseDTO.<String>builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO) {

        log.info("##### signin");


        UserEntity user = userService.getByCredentials(userDTO.getEmail(), userDTO.getPassword(), passwordEncoder);
        if(user != null) {
            // 토큰 생성
            final String token = tokenProvider.create(user);
            final UserDTO responseUserDTO = UserDTO.builder()
                    .email(user.getEmail())
                    .id(user.getUserId())
                    .token(token)
                    .build();

            return ResponseEntity.ok().body(responseUserDTO);
        } else {
            ResponseDTO<String> responseDTO = ResponseDTO.<String>builder().error("Login faild.").build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

}
