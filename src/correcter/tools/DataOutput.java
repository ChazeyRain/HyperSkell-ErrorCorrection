package correcter.tools;

import java.util.Scanner;

public class DataOutput {
    private static final Scanner scanner = new Scanner(System.in);

    public static void outputBytesBin(byte[] input) {
        for (byte temp : input) {
            String output = String.format("%8s", Integer.toBinaryString(temp & 0xFF)).replace(' ',  '0');
            System.out.print(output + " ");
        }
        System.out.println();
    }

}
