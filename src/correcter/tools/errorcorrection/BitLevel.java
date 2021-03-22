package correcter.tools.errorcorrection;

import java.io.ByteArrayOutputStream;

public class BitLevel extends Operations{

    public static byte[] encode(byte[] input) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        byte tempByte = 0;
        boolean bit;

        for (int i = 0; i < 8 * input.length; i++) {

            bit = getBit(input[i / 8], i - 8 * (i / 8));
            tempByte = setBit(tempByte, (i - 3 * (i / 3)) * 2, bit);
            tempByte = setBit(tempByte, (i - 3 * (i / 3)) * 2 + 1, bit);

            if ((i + 1) % 3 == 0) {
                bit = getBit(tempByte, 0) ^ getBit(tempByte, 2) ^ getBit(tempByte, 4);

                tempByte = setBit(tempByte, 6, bit);
                tempByte = setBit(tempByte, 7, bit);

                output.write(tempByte);
                tempByte = 0;
            }
        }

        if (tempByte != 0) {
            bit = getBit(tempByte, 0) ^ getBit(tempByte, 2) ^ getBit(tempByte, 4);

            tempByte = setBit(tempByte, 6, bit);
            tempByte = setBit(tempByte, 7, bit);

            output.write(tempByte);
        }

        return output.toByteArray();
    }

    public static byte[] decode(byte[] input) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        int currentByte = 0;
        byte tempByte = 0;
        byte tempInp;
        boolean bit;

        for (byte b : input) {
            tempInp = correctByte(b);

            for (int j = 0; j < 3; j++) {
                bit = getBit(tempInp, j * 2);
                tempByte = setBit(tempByte, currentByte, bit);
                currentByte++;

                if (currentByte == 8) {
                    output.write(tempByte);
                    tempByte = 0;
                    currentByte = 0;
                }
            }
        }

        if (tempByte != 0) {
            output.write(tempByte);
        }

        return output.toByteArray();
    }

    private static byte correctByte(byte input) {
        int mem = 3;
        boolean[] bit = new boolean[4];
        byte output;

        for (int i = 0; i < 4; i++) {
            bit[i] = getBit(input, i * 2);
            if (bit[i] ^ getBit(input, i * 2 + 1)) {
                mem = i;
            }
        }

        if (mem == 3) {
            return input;
        } else if (bit[3] == (bit[0] ^ bit[1] ^ bit[2])) {
            bit[mem] = bit[mem];
        } else {
            bit[mem] = !bit[mem];
        }

        output = setBit(input, mem * 2, bit[mem]);
        output = setBit(output, mem * 2 + 1, bit[mem]);
        return output;
    }
}
