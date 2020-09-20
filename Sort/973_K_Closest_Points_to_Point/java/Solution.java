class Solution {
    public int[][] kClosest(int[][] points, int K) {
        // O(NlogN), N =  length of points

        int N = points.length;
        int[] dists = new int[N];
        for (int i=0; i < N; i++) {
            dists[i] = calDistance(points[i]);
        }

        // find the K shortest distance.
        Arrays.sort(dists);
        int distK = dists[K-1];

        int[][] ans = new int[K][2];
        int t =0;

        // compare with the distK one by one...
        for (int i=0; i<N; i++) {
            if(calDistance(points[i]) <= distK) {
                ans[t] = points[i];
                t++;
            }
        }
        return ans;
    }

    public int calDistance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}
