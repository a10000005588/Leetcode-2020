func reverseString(s string) string {
	x := []byte(s)

	for i := 0; i < len(s)/2; i++ {
		x[i], x[(len(s)-1)-i] = x[(len(s)-1)-i], x[i]
	}
	return string(x[:])
}
