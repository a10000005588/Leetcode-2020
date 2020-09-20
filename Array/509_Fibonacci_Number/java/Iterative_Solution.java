class IterativeSolution {
    // iterate方法
    public int fib(int N) {
        if (N == 0 || N == 1) {
            return N;
        }

        int a = 0;
        int b = 1;

        int lastA = a;
        for (int i=0; i<N; i++) {
            lastA = a;

            if (i==0) {
                a = 0;
            } else {
                a = b;
            }

            b = b + lastA;
        }

        return b;
    }
}
