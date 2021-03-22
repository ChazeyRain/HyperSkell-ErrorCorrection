package correcter;

import correcter.tools.*;
import correcter.tools.errorcorrection.BitLevel;
import correcter.tools.errorcorrection.Hammering;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        menuAction(scanner.next());

//        byte[] send = FileManager.getBytes("send.txt");
//        DataOutput.outputBytesBin(send);
//
//        byte[] encoded = Hammering.encode(send);
//        DataOutput.outputBytesBin(encoded);
//
//        byte[] received = ErrorGenerator.bitLevel(encoded);
//        DataOutput.outputBytesBin(received);
//
//        byte[] decoded = Hammering.decode(received);
//        DataOutput.outputBytesBin(decoded);

    }

    public static void menuAction(String i) {

        byte[] encoded;
        byte[] send;
        byte[] received;
        byte[] decoded;

        switch (i) {
            case "encode":
                send = FileManager.getBytes("send.txt");
                DataOutput.outputBytesBin(send);
                encoded = Hammering.encode(send);
                DataOutput.outputBytesBin(encoded);
                FileManager.writeBytes("encoded.txt", encoded);
                break;
            case "send":
                encoded = FileManager.getBytes("encoded.txt");
                DataOutput.outputBytesBin(encoded);
                received = ErrorGenerator.bitLevel(encoded);
                DataOutput.outputBytesBin(received);
                FileManager.writeBytes("received.txt", received);
                break;
            case "decode":
                received = FileManager.getBytes("received.txt");
                DataOutput.outputBytesBin(received);
                decoded = Hammering.decode(received);
                DataOutput.outputBytesBin(decoded);
                FileManager.writeBytes("decoded.txt", decoded);
                break;
        }
    }
}
