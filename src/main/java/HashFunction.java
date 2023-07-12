import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class HashFunction {
    private MessageDigest messageDigest;

    public HashFunction(String algorithmName) {
        try {
            this.messageDigest = MessageDigest.getInstance(algorithmName);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String hash(String node) {
        messageDigest.reset();
        messageDigest.update(node.getBytes(StandardCharsets.UTF_8));

        Formatter formatter = new Formatter();
        for (byte b : messageDigest.digest()) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
