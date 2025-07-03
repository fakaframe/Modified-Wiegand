public class ModifiedWiegand {
    public void write(byte[] data, byte[] D0, byte[] D1, int size) {
        int lastBit = -1;

        for (int i = 0; i < size; i++) {
            int bit = BitUtils.getBit(data, i);
            int d0Bit, d1Bit;

            if (bit == 1) {
                if (lastBit == 1) {
                    d0Bit = 1 - BitUtils.getBit(D0, i - 1);
                    d1Bit = 0;
                } else {
                    d0Bit = 1;
                    d1Bit = 0;
                }
            } else {
                if (lastBit == 0) {
                    d0Bit = 0;
                    d1Bit = 1 - BitUtils.getBit(D1, i - 1);
                } else {
                    d0Bit = 0;
                    d1Bit = 1;
                }
            }

            BitUtils.setBit(D0, i, d0Bit);
            BitUtils.setBit(D1, i, d1Bit);
            lastBit = bit;
        }
    }

    public byte[] read(byte[] D0, byte[] D1, int size) {
        byte[] result = new byte[(size + 7) / 8];
        int lastD0 = -1, lastD1 = -1;

        for (int i = 0; i < size; i++) {
            int d0 = BitUtils.getBit(D0, i);
            int d1 = BitUtils.getBit(D1, i);
            int bit;

            if (d0 == 0 && d1 == 1) {
                bit = 0;
            } else if (d0 == 1 && d1 == 0) {
                bit = 1;
            } else if (d0 == 0 && lastD1 != -1 && d1 != lastD1) {
                bit = 0;
            } else if (d1 == 0 && lastD0 != -1 && d0 != lastD0) {
                bit = 1;
            } else if (d0 == 1 && d1 == 1) {
                break;
            } else {
                continue;
            }

            BitUtils.setBit(result, i, bit);
            lastD0 = d0;
            lastD1 = d1;
        }

        return result;
    }
}
