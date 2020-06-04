class Solution {
    public int twoCitySchedCost(int[][] costs) {
        int ans = 0;
        int countA = 0;
        int countB = 0;
        int maxCity = costs.length / 2;
        // 計算差值 與對應的people index
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<costs.length; i++) {
            map.put(i, Math.abs(costs[i][0] - costs[i][1]));
        }

        int curIndex = 0;
        int max = Integer.MIN_VALUE;

        // 找出差值最大的people
        while(!map.isEmpty()) {
            if (countA == maxCity && countB == maxCity) {
                break;
            }

            for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
                if (max < entry.getValue()) {
                    curIndex = entry.getKey();
                    max = entry.getValue();
                }
            }

            // 確認A或B是否已經滿了
            if (countA == maxCity) {
                ans += costs[curIndex][1];
                map.remove(curIndex);
                countB++;
                max = Integer.MIN_VALUE;
                continue;
            } else if (countB == maxCity) {
                ans += costs[curIndex][0];
                map.remove(curIndex);
                countA++;
                max = Integer.MIN_VALUE;
                continue;
            }


            if (costs[curIndex][0] < costs[curIndex][1]) {
                ans += costs[curIndex][0];
                countA++;
            } else {
                ans += costs[curIndex][1];
                countB++;
            }
            map.remove(curIndex);
            max = Integer.MIN_VALUE;
        }

        return ans;
    }
}
