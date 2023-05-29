package sj.taqback.security;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import sj.taqback.config.BCryptHelper;

public class SecurityConfigTest {
    int MAX_STRENGTH_SUPPORTED_BCRYPT_PASSWORD_ENCODER = 31;
    int LOW_LIMIT = 5;

    @Test
    void createBCryptPasswordEncoder() {
        Assertions.assertThat(BCryptHelper
                .findStrength(4, MAX_STRENGTH_SUPPORTED_BCRYPT_PASSWORD_ENCODER))
                .isInstanceOf(Integer.class);

    }

    @Test
    void giveBCryptPasswordVeryLowLimitStrength() {
        Assertions.assertThatThrownBy(() -> BCryptHelper.findStrength(4, LOW_LIMIT))
                .isInstanceOf(RuntimeException.class);
    }

}
