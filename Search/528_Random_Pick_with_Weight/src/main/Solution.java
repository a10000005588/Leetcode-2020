class Solution {
    int totalWeight = 0;
    int[] territory;

    public Solution(int[] w) {
         // 先計算w[] 內每個index的權重總和有多少
        for (int i=0; i< w.length; i++) {
            totalWeight += w[i];
        }

        int count = 0;
        // 設定每個index在territory所佔的大小
        territory = new int[totalWeight];
        for (int i=0; i< w.length; i++) {
            // 看w[i]的權重是多少, 分配多少土地
            for (int j =0; j < w[i]; j++) {
                territory[count] = i;
                count++;
            }
        }
    }

    public int pickIndex() {
        int r = (int)(Math.random() * totalWeight + 1) - 1;
        return territory[r];
    }
}
