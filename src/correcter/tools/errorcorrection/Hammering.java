package correcter.tools.errorcorrection;

import java.io.ByteArrayOutputStream;

public class Hammering extends Operations{

    public static byte[] encode(byte[] input) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        byte firstHalf;
        byte secondHalf;

        for (byte b : input) {
            firstHalf = (byte) (b >>> 4);
            secondHalf = (byte) (b & 0b00001111);

            output.write(generateByte(firstHalf));
            output.write(generateByte(secondHalf));
        }

        return output.toByteArray();
    }

    private static byte generateByte(byte input) {
        byte output = 0;
        output = (byte) (((input << 1) & 0b00010000) + (input & 0b00000111));
        output = (byte) (output + (0b01000000 *
                ((bitValue(output, 3) +
                        bitValue(output, 5) +
                        bitValue(output, 7))
                        % 2)));
        output = (byte) (output + (0b00100000 *
                ((bitValue(output, 3) +
                        bitValue(output, 6) +
                        bitValue(output, 7))
                        % 2)));
        output = (byte) (output + (0b00001000 *
                ((bitValue(output, 5) +
                        bitValue(output, 6) +
                        bitValue(output, 7))
                        % 2)));
        return output;
    }


    public static byte[] decode(byte[] input) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        byte firstHalf;
        byte secondHalf;

        for (int i = 0; i < input.length; i += 2) {
            firstHalf = (byte) (decodeByte(correctByte(input[i])) << 4);
            secondHalf = decodeByte(correctByte(input[i + 1]));

            output.write(firstHalf + secondHalf);
        }

        return output.toByteArray();
    }

    private static byte correctByte(byte input) {

        int errIndex = !(bitValue(input, 1) ==
                (bitValue(input, 3) +
                        bitValue(input, 5) +
                        bitValue(input, 7)) % 2) ? 1 : 0;

        errIndex += !(bitValue(input, 2) ==
                (bitValue(input, 3) +
                        bitValue(input, 6) +
                        bitValue(input, 7)) % 2) ? 2 : 0;

        errIndex += !(bitValue(input, 4) ==
                (bitValue(input, 5) +
                        bitValue(input, 6) +
                        bitValue(input, 7)) % 2) ? 4 : 0;

        return (byte) (input ^ (1 << (7 - errIndex)));
    }

    private static byte decodeByte (byte input) {
        return (byte) ((input & 0b00000111) + ((input >> 1) & 0b00001000));
    }

}
