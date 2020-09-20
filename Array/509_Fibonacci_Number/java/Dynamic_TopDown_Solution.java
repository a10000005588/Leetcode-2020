class DP_Solution {
    static int[] memo = new int[31]; //  0 < N < 30

    public int fib(int N) {
        if (N == 0 || N == 1) {
            return N;
        }

        if (memo[N] == 0) {
            return memo[N] = fib(N-1) + fib(N-2);
        }

        return memo[N];
    }
}
