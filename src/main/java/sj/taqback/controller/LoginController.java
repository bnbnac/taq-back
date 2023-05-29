package sj.taqback.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sj.taqback.dto.LoginDto;
import sj.taqback.service.LoginService;


@Controller
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody LoginDto loginDto) {
        return loginService.login(loginDto);
    }
}
