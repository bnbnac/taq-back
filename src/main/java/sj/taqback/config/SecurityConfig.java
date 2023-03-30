package sj.taqback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    // BCryptPasswordEncoder
    // You are encouraged to tune and test the strength parameter(4 to 31. default 10) on your own system
    // so that it takes roughly 1 second to verify a password. Here is the reference URL.
    // https://docs.spring.io/spring-security/reference/features/authentication/password-storage.html#authentication-password-storage-bcrypt

    private final int MIN_STRENGTH = 4;
    private final int LIMIT_STRENGTH = 31;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new SecureBCryptPasswordEncoder(MIN_STRENGTH, LIMIT_STRENGTH);
    }
}
