import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Encrypter {

    private int shift;
    private String encrypted;

    /**
     * Default Constructor
     */
    public Encrypter() {
        this.shift = 0;
        this.encrypted = "";
    }

    /**
     * Non-default Constructor
     *
     * @param s - custom shift amount
     */
    public Encrypter(int s) {
        this.shift = s;
        this.encrypted = "";
    }

    /**
     * Encrypts the content of a file and writes the result to another file.
     *
     * @param inputFilePath      the path to the file containing the text to be encrypted
     * @param encryptedFilePath the path to the file where the encrypted text will be written
     * @throws IOException if an error occurs while reading or writing the files
     */
    public void encrypt(String inputFilePath, String encryptedFilePath) throws IOException {
        // Call the read method, encrypt the file contents, and then write to a new file
        String content = readFile(inputFilePath);
        String encryptedContent = encryptText(content, shift);
        writeFile(encryptedContent, encryptedFilePath);
    }

    /**
     * Decrypts the content of an encrypted file and writes the result to another file.
     *
     * @param messageFilePath    the path to the file containing the encrypted text
     * @param decryptedFilePath the path to the file where the decrypted text will be written
     * @throws IOException if an error occurs while reading or writing the files
     */
    public void decrypt(String messageFilePath, String decryptedFilePath) throws IOException {
        // Call the read method, decrypt the file contents, and then write to a new file
        String encryptedContent = readFile(messageFilePath);
        String decryptedContent = decryptText(encryptedContent, shift);
        writeFile(decryptedContent, decryptedFilePath);
    }

    /**
     * Reads the content of a file and returns it as a string.
     *
     * @param filePath the path to the file to be read
     * @return the content of the file as a string
     * @throws IOException if an error occurs while reading the file
     */
    private static String readFile(String filePath) throws IOException {
        StringBuilder message = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                message.append(scanner.nextLine()).append("\n");
            }
        }
        return message.toString();
    }

    /**
     * Writes data to a file.
     *
     * @param data     the data to be written to the file
     * @param filePath the path to the file where the data will be written
     * @throws IOException if an error occurs while writing to the file
     */
    private static void writeFile(String data, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(new File(filePath))) {
            writer.write(data);
        }
    }

    /**
     * Encrypts text using a simple Caesar cipher with a shift value.
     *
     * @param text  the text to be encrypted
     * @param shift the shift value for the cipher
     * @return the encrypted text
     */
    private static String encryptText(String text, int shift) {
        StringBuilder encryptedText = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                encryptedText.append((char) ((ch - base + shift) % 26 + base));
            } else {
                encryptedText.append(ch);
            }
        }
        return encryptedText.toString();
    }

    /**
     * Decrypts text that was encrypted using a simple Caesar cipher with a shift value.
     *
     * @param text  the text to be decrypted
     * @param shift the shift value for the cipher
     * @return the decrypted text
     */
    private static String decryptText(String text, int shift) {
        return encryptText(text, 26 - shift); // Decrypting is the same as encrypting with the opposite shift
    }

    /**
     * Returns a string representation of the encrypted text.
     *
     * @return the encrypted text
     */
    @Override
    public String toString() {
        return encrypted;
    }
}