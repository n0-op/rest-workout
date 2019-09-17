package com.n0op.app.ws.utils;

import com.n0op.app.ws.shared.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

/**
 * @author DanM
 */
public class UserUtils {
    private static final Logger logger = LoggerFactory.getLogger(UserUtils.class);
    private final Random RANDOM = new SecureRandom();
    private final java.lang.String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final String ALGORITHM = "PBKDF2WithHmacSHA512";
    private final int ITERATIONS = 65536;
    private final int KEY_LENGTH = 512;


    public boolean validateRunWorkoutRequiredFields(UserDTO userDTO) {
        boolean valid = false;

        if(!userDTO.getFirstName().isEmpty() || !userDTO.getLastName().isEmpty()
        || !userDTO.getEmail().isEmpty() || !userDTO.getPassword().isEmpty()) {
            valid = true;
        }
        return valid;
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

    public Optional<String> generateSalt (final int length) {
        if(length < 1) {
            System.err.println("Error in salt generator: length much be greater than 0");
            return Optional.empty();
        }
        System.out.println("Length provided: " + length);
        byte[] salt = new byte[length];
        System.out.println("Salt array: " + Arrays.toString(salt));
        System.out.println("Byte length: " + salt.length);
        RANDOM.nextBytes(salt);
        System.out.println("Salt array: " + Arrays.toString(salt));
        return Optional.of(Base64.getEncoder().encodeToString(salt));
    }

    public Optional<String> hashPassword(String password, String salt) {
        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

        Arrays.fill(chars, Character.MIN_VALUE);

        try{
            SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Optional.of(Base64.getEncoder().encodeToString(securePassword));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.err.println("Exception encountered with hashing password");
            return Optional.empty();
        }  finally {
            spec.clearPassword();
        }
    }

    public boolean verifyPassword(String password, String key, String salt) {
        Optional<String> optEncrypted = hashPassword(password,salt);
        if(!optEncrypted.isPresent()) return false;
        return optEncrypted.get().equals(key);

    }
}
