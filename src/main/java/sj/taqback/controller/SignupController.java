package sj.taqback.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sj.taqback.dto.SignupDto;
import sj.taqback.service.UserService;


@Controller
public class SignupController {
    private final UserService userService;

    SignupController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/users/new")
    public ResponseEntity<Long> createAccount(@RequestBody SignupDto signupDto) {
        if (signupDto.getAccountId() == null || signupDto.getAccountId().trim().length() == 0) {
            throw new IllegalArgumentException("아이디는 필수입니다.");
        }
        if (signupDto.getPassword() == null || signupDto.getPassword().trim().length() == 0) {
            throw new IllegalArgumentException("비밀번호는 필수입니다.");
        }

        Long id = userService.createAccount(signupDto.toEntity());

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
