package sj.taqback.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecureBCryptPasswordEncoder implements PasswordEncoder {
    private final BCryptPasswordEncoder bcpe;

    public SecureBCryptPasswordEncoder(int minStrength, int limitStrength) {
        this.bcpe = new BCryptPasswordEncoder(findStrength(minStrength, limitStrength));
    }

    private int findStrength(int minStrength, int limitStrength) {
        int strength = minStrength - 1;
        long timeTaken = 0;

        while (timeTaken < 1000) {
            if (strength > limitStrength) {
                throw new RuntimeException("There is no secure strength for BCryptPasswordEncoder. " +
                        "need another security policy");
            }

            BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder(++strength);
            timeTaken = measureMatchingTime(bcpe);
        }

        return strength;
    }

    public long measureMatchingTime(BCryptPasswordEncoder bcpe) {
        String encoded = bcpe.encode("rawPassword");

        long start = System.currentTimeMillis();
        bcpe.matches("rawPassword", encoded);
        long finish = System.currentTimeMillis();

        return finish - start;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return bcpe.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return bcpe.matches(rawPassword, encodedPassword);
    }
}
