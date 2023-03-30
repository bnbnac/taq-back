package sj.taqback;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(getStrength());
    }

    // You are encouraged to tune and test the strength parameter(4 to 31. default 10) on your own system
    // so that it takes roughly 1 second to verify a password. Here is the reference URL.
    // https://docs.spring.io/spring-security/reference/features/authentication/password-storage.html#authentication-password-storage-bcrypt

    public int getStrength() {
        int strength = 3;
        long start = 0;
        long finish = 0;
        String password = "password";

        while ((finish - start) < 1000) {
            if (strength > 31) {
                throw new RuntimeException("There is no secure strength for BCryptPasswordEncoder. " +
                        "need another security policy");
            }

            BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder(++strength);
            String encoded = bcpe.encode(password);

            start = System.currentTimeMillis();
            bcpe.matches(password, encoded);
            finish = System.currentTimeMillis();
        }

        return strength;
    }
}
