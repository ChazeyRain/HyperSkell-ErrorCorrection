package correcter.tools.errorcorrection;

public abstract class Operations {
    static boolean getBit(byte input, int index) {
        return (input & (1 << (7 - index))) != 0;
    }

    static byte setBit(byte input, int index, boolean value) {
        if (!value) {
            return (byte) (input & ~(1 << (7 - index)));
        }
        return (byte) (input | (1 << (7 - index)));
    }

    static byte bitValue(byte input, int index) {
        return (byte) ((input >> (7 - index)) & 0b00000001);
    }
}
