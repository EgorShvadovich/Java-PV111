package step.learning.services.hash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MdHashService implements HashService{
    @Override
    public String hash(String input) {
        try{
            MessageDigest digest = MessageDigest.getInstance("MD5");
            StringBuilder stringBuilder = new StringBuilder();
            for(byte b : input.getBytes(StandardCharsets.UTF_8)){
                digest.digest(input.getBytes(StandardCharsets.UTF_8));
                stringBuilder.append(String.format("%02x",b));
            }
            return stringBuilder.toString();
        }
        catch(NoSuchAlgorithmException e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
