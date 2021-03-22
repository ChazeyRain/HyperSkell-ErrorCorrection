package correcter.tools;

import java.util.Scanner;

public class DataInput {
    public static String getConsoleInput() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder input = new StringBuilder();
        while (scanner.hasNextLine()) {
            input.append(scanner.nextLine());
        }
        return input.toString();
    }
}