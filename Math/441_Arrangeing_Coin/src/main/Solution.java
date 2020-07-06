class Solution {
    public int arrangeCoins(int n) {
        int curTotal = 0;

        int i = 1;
        while(true) {
            if(i>n) {
                return i-1;
            }

            curTotal += i;
            n -= i; // 扣掉已經被拿來排第n排數量(i)的coin
            i++;
        }
    }
}
