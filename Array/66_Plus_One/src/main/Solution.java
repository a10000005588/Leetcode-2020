class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        // 最小位數為digits的最後一個
        for(int i=n-1; i>=0; i--) {
            if(digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            // 因為digits[i]=9 +1 後變成0, 下一回合的loop就會自動+1做進位
            digits[i] =0;
        }
        //假如每一個回合都是9，那新增一個array, index=0放入1
        int[] newArray = new int[n+1];
        newArray[0] = 1;
        return newArray;
    }
}
