class Solution {
    public int hammingDistance(int x, int y) {
        // 先轉二進制, 然候用XOR, 看有多少個1

        // 先計算看有幾位數
        int maxDigitsLength = getMaxDigitsLength(x, y);

        int[] Xdigits = getDigits(x, maxDigitsLength);
        int[] Ydigits = getDigits(y, maxDigitsLength);

        // 做XOR
        int haminDistance;
        int[] XORarray = new int[maxDigitsLength];
        int index = 0;
        int ans = 0;
        while(index < maxDigitsLength) {
            if (Xdigits[index] != Ydigits[index]) {
                XORarray[index] = 1;
                ans++;
            }
            index++;
        }

        return ans;
    }

    public int getMaxDigitsLength(int x, int y) {
        int maxDigits = 0;
        while (x != 0 || y!= 0) {
            maxDigits += 1;
            x /= 2;
            y /= 2;
        }
        return maxDigits;
    }

    public int[] getDigits(int hexNum, int maxDigits) {
        int[] n = new int[maxDigits];
        int index = maxDigits - 1; // 從array的最後面開始放置
        while(hexNum != 0) {
            int remainder = hexNum % 2;
            if (remainder != 0) {
                n[index] = 1;
            }
            hexNum /= 2;
            index--;
        }

        return n;
    }
}
