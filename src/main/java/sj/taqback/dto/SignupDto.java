package sj.taqback.dto;

import sj.taqback.entity.User;

import java.time.LocalDateTime;

public class SignupDto {
    private String accountId;
    private String password;
    private String nickname;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public User toEntity() {
        User user = new User();

        user.setAccountId(accountId);
        user.setPassword(password);
        user.setNickname(nickname);

        return user;
    }
}
