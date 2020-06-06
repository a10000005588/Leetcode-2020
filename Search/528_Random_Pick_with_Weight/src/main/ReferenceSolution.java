class Solution {
    int totalWeight;
    int[] cumulativeWeight;

    public Solution(int[] w) {
        totalWeight = 0;
        cumulativeWeight = new int[w.length];

        for (int i=0; i< w.length; i++) {
            totalWeight += w[i];
            // 階段性的紀錄index在哪個區間
            cumulativeWeight[i] = totalWeight;
        }
    }

    public int pickIndex() {
        int index = (int)(Math.random() * totalWeight);
        return binarySearch(index + 1);
    }

    public int binarySearch(int val) {
        int l = 0;
        int r = cumulativeWeight.length - 1;
        while(l < r) {
            int mid = l + (r-l)/2;
            if (cumulativeWeight[mid] < val)
                l = mid + 1;
            else
                r = mid;
        }
        return l;
    }
}
