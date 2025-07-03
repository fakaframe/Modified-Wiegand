import java.util.Scanner;
public class WiegandIO {
    private final Scanner sc = new Scanner(System.in);

    public byte[] parseBitString(String bitStr, int bitLen) {
        byte[] result = new byte[(bitLen + 7) / 8];
        for (int i = 0; i < bitLen; i++) {
            char c = bitStr.charAt(i);
            if (c != '0' && c != '1') {
                System.out.println("Invalid input at position " + i);
                return null;
            }
            BitUtils.setBit(result, i, c - '0');
        }
        return result;
    }

    public String getInput(String message) {
        System.out.print(message);
        return sc.nextLine().trim();
    }

    public void displayResult(String label, byte[] data, int bitLen) {
        System.out.print(label + " : ");
        BitUtils.printBits(data, bitLen);
    }
}
