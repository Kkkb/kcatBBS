package kybmig.ssm;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Digest {
    public static String hexFromBytes(byte[] array) {
        String hex = new BigInteger(1, array).toString(16);
        int zeroLength = array.length * 2 - hex.length();
        for (int i = 0; i < zeroLength; i++) {
            hex = "0" + hex;
        }
        return hex;
    }

    public static String md5(String origin) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("error: %s", e));
        }
        md.update(origin.getBytes(StandardCharsets.UTF_8));

        byte[] result = md.digest();
        String hex = hexFromBytes(result);
        return hex;
    }

}
