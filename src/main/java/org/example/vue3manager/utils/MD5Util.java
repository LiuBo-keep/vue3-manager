package org.example.vue3manager.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

  public static String md5WithSalt(String input, String salt) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      String combinedInput = input + salt;
      byte[] messageDigest = md.digest(combinedInput.getBytes());
      StringBuilder hexString = new StringBuilder();
      for (byte b : messageDigest) {
        String hex = Integer.toHexString(0xff & b);
        if (hex.length() == 1) {
          hexString.append('0');
        }
        hexString.append(hex);
      }
      return hexString.toString();
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("MD5 algorithm not found", e);
    }
  }
}

