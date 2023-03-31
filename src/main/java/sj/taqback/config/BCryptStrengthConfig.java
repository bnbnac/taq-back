package sj.taqback.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptStrengthConfig {
    public static int findStrength(int minStrength, int limitStrength) {
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

    private static long measureMatchingTime(BCryptPasswordEncoder bcpe) {
        String encoded = bcpe.encode("rawPassword");

        long start = System.currentTimeMillis();
        bcpe.matches("rawPassword", encoded);
        long finish = System.currentTimeMillis();

        return finish - start;
    }
}
