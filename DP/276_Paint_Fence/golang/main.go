var memo []int

func numWays(n int, k int) int {
	memo = make([]int, n)

	if n == 0 || k == 0 {
		return 0
	}

	return totalWays(n, k)
}

func totalWays(posts int, k int) int {
	if posts == 1 {
		return k
	}

	if posts == 2 {
		return k * k
	}

	if memo[posts-1] == 0 {
		memo[posts-1] = totalWays(posts-1, k)
	}

	if memo[posts-2] == 0 {
		memo[posts-2] = totalWays(posts-2, k)
	}

	return memo[posts-1]*(k-1) + memo[posts-2]*(k-1)
}