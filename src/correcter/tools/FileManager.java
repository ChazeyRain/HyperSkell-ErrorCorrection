package correcter.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileManager {

    public static byte[] getBytes(String fileName) {
        File file = new File(fileName);
        System.out.println(file.getAbsolutePath());
        try {
            FileInputStream fileInput = new FileInputStream(file);
            byte[] input;

            input = fileInput.readAllBytes();

            fileInput.close();
            return input;
        } catch (Exception e) {
            return null;
        }
    }

    public static void writeBytes(String fileName, byte[] input) {
        File file = new File(fileName);
        try {
            FileOutputStream fileOutput = new FileOutputStream(file);
            fileOutput.write(input);
            fileOutput.flush();
        } catch (Exception ignored){

        }
    }


}
