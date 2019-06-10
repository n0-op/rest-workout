package com.n0op.app.ws.utils;

import com.n0op.app.ws.shared.dto.RunDTO;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

/**
 * @author DanM
 */
public class RunWorkoutUtils {
    private final Random RANDOM = new SecureRandom();
    private final java.lang.String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public boolean validateRunWorkoutRequiredFields(RunDTO runDTO) {
        return true;
    }

    public String generateUUID() {

        String returnValue = UUID.randomUUID().toString().replaceAll("-", "");
        return returnValue;
    }

    public String generateUserId(int length) {
        return generateRandomString(length);
    }

    private String generateRandomString(int length) {
        StringBuilder returnValue  = new StringBuilder(length);

        for(int i = 0; i < length; i++){
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }
}
