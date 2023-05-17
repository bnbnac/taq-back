package sj.taqback.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sj.taqback.domain.User;
import sj.taqback.service.UserService;

import java.time.LocalDateTime;

@Controller
public class SignupController {
    private final UserService userService;

    SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/new")
    public String createAccountForm(User user) {
        return "users/signupForm";
    }

    @PostMapping(value = "/users/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createAccount(@RequestBody SignupForm form) {
        User user = new User();

        user.setAccountId(form.getAccountId());
        user.setPassword(form.getPassword());
        user.setNickname(form.getNickname());
        user.setCreatedAt(LocalDateTime.now());

        userService.createAccount(user);
        return "redirect:/";
    }
}
