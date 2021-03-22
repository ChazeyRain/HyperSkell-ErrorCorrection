package correcter.tools;

import java.util.Random;

public class ErrorGenerator {

    public static String symbolLevel(String input) {
        StringBuilder output = new StringBuilder(input);
        Random random = new Random();
        char temp;
        int index;
        for (int i = 2; i < input.length(); i = i + 3) {
            index = i - random.nextInt(3);
            temp = input.charAt(index);

            while (temp == input.charAt(index)) {
                temp = generateRandomSymbol();
            }

            output.setCharAt(index, temp);
        }
        return output.toString();
    }

    private static char generateRandomSymbol() {
        char output = '.';
        Random random = new Random();
        while (!(output + "").matches("[\\w\\d ]")) {
            output = (char) (random.nextInt(75) + 48);
        }
        return output;
    }

    public static byte[] bitLevel(byte[] inputBits) {
        Random random = new Random();
        for (int i = 0; i < inputBits.length; i++) {
            inputBits[i] ^= (1 << random.nextInt(7));
        }
        return inputBits;
    }
}
