package correcter.tools.errorcorrection;

import java.io.ByteArrayOutputStream;

public class SymbolLevel {
    public static String tripleSymbol (String input) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            output.append(input.charAt(i)).
                    append(input.charAt(i)).
                    append(input.charAt(i));
        }

        return output.toString();
    }

    public static String correctSymbolLevel(String input) {
        StringBuilder output = new StringBuilder();

        for (int i = 2; i < input.length(); i = i + 3) {
            if (input.charAt(i - 2) == input.charAt(i - 1) || input.charAt(i - 2) == input.charAt(i)) {
                output.append(input.charAt(i - 2));
            } else {
                output.append(input.charAt(i));
            }
        }

        return output.toString();
    }
}
