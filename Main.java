public class Main {
    public static void main(String[] args) {
        ModifiedWiegand mw = new ModifiedWiegand();
        WiegandIO io = new WiegandIO();

        // ===== ENCODE =====
        String inputStr = io.getInput("Enter bitstream to encode (e.g. 1011010001): ");
        int bitLenWrite = inputStr.length();

        byte[] input = io.parseBitString(inputStr, bitLenWrite);
        if (input == null) return;

        byte[] D0 = new byte[(bitLenWrite + 7) / 8];
        byte[] D1 = new byte[(bitLenWrite + 7) / 8];

        mw.write(input, D0, D1, bitLenWrite);

        System.out.println("\nEncoded Output:");
        io.displayResult("Input ", input, bitLenWrite);
        io.displayResult("D0    ", D0, bitLenWrite);
        io.displayResult("D1    ", D1, bitLenWrite);

        // ===== DECODE =====
        System.out.println("\nDecoding from custom D0/D1 lines:");
        String d0Str = io.getInput("Enter D0 line to decode: ");
        String d1Str = io.getInput("Enter D1 line to decode: ");

        if (d0Str.length() != d1Str.length()) {
            System.out.println("D0 and D1 must be of equal length.");
            return;
        }

        int bitLenRead = d0Str.length();
        byte[] d0Bits = io.parseBitString(d0Str, bitLenRead);
        byte[] d1Bits = io.parseBitString(d1Str, bitLenRead);
        if (d0Bits == null || d1Bits == null) return;

        byte[] decoded = mw.read(d0Bits, d1Bits, bitLenRead);

        System.out.println("Decoded Output:");
        io.displayResult("Decoded", decoded, bitLenRead);
    }
}
