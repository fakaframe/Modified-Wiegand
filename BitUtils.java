public class BitUtils {
    public static int getBit(byte[] arr, int bitIndex) {
        int byteIndex = bitIndex / 8;
        int offset = 7 - (bitIndex % 8);
        return (arr[byteIndex] >> offset) & 1;
    }

    public static void setBit(byte[] arr, int bitIndex, int value) {
        int byteIndex = bitIndex / 8;
        int offset = 7 - (bitIndex % 8);
        if (value == 1)
            arr[byteIndex] |= (1 << offset);
        else
            arr[byteIndex] &= ~(1 << offset);
    }

    public static void printBits(byte[] data, int bitLen) {
        for (int i = 0; i < bitLen; i++) {
            int bit = getBit(data, i);
            System.out.print(bit);
            if ((i + 1) % 8 == 0) System.out.print(" ");
        }
        System.out.println();
    }
}
