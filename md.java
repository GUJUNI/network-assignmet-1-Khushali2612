import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class FileDigestCalculator {
    public static void main(String[] args) {
        String filePath = "path/to/file.txt";

        try {
            byte[] digest = computeFileDigest(filePath);
            String digestHex = bytesToHex(digest);

            System.out.println("File Digest (SHA-256): " + digestHex);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static byte[] computeFileDigest(String filePath) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        try (InputStream fis = new FileInputStream(filePath)) {
            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                md.update(buffer, 0, bytesRead);
            }
        }

        return md.digest();
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        for (byte b : bytes) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
}
