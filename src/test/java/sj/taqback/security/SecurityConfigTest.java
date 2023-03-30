package sj.taqback.security;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import sj.taqback.config.SecureBCryptPasswordEncoder;

public class SecurityConfigTest {
    int MAX_STRENGTH_SUPPORTED_BCRYPT_PASSWORD_ENCODER = 31;
    int LOW_LIMIT = 5;

    @Test
    void createBCryptPasswordEncoder() {
        new SecureBCryptPasswordEncoder(4, MAX_STRENGTH_SUPPORTED_BCRYPT_PASSWORD_ENCODER);
    }

    @Test
    void giveBCryptPasswordVeryLowLimitStrength() {
        Assertions.assertThatThrownBy(() -> new SecureBCryptPasswordEncoder(4, LOW_LIMIT))
                .isInstanceOf(RuntimeException.class);
    }

}
