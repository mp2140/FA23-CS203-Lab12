import java.io.IOException;

public class EncryptionTester {

    public static void main(String[] args) {
        String encryptedFilePath = "src/encrypted.txt";
        String decryptedFilePath = "src/decrypted.txt";
        String inputFilePath = "src/encryptMe.txt";
        String outputFilePath = "src/newEncrypted.txt";

        int shift = 4; // Says how much you would like to shift

        Encrypter enc = new Encrypter(shift);

        try {
            enc.encrypt(inputFilePath, outputFilePath);
            enc.decrypt(encryptedFilePath, decryptedFilePath);
            System.out.println("Encryption and decryption completed successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}