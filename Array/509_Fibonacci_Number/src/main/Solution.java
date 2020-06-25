class Solution {
    public int fib(int N) {
        if (N == 0)
            return 0;
        if (N == 1)
            return 1;
        return getFib(N-1) + getFib(N-2);
    }
}
