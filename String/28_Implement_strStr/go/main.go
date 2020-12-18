
func strStr(haystack string, needle string) int {
	if needle == "" {
		return 0
	}

	if haystack == needle {
		return 0
	}

	if len(needle) > len(haystack) {
		return -1
	}

	hay := []byte(haystack)
	nee := []byte(needle)
	counter := 0
	for i := 0; i < len(hay)-len(needle)+1; i++ {
		for j := 0; j < len(needle); j++ {
			if !checkIsExist(hay, nee[j], i+j) {
				counter = 0
				break
			}
			counter++
		}
		if counter == len(needle) {
			return i
		}
	}

	return -1
}

func checkIsExist(hay []byte, needle byte, pos int) bool {
	if hay[pos] == needle {
		return true
	}
	return false
}
