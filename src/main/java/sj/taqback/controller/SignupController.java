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
        Long id = userService.createAccount(signupDto.toEntity());

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
