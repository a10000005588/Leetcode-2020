package main

func main() {
	reverseWords("I love u")
}

func reverseWords(s string) string {
	if len(s) == 0 {
		return ""
	}
	var ans = ""
	for i:=0; i<len(s); i++ {
		var incomingStr = ""
		for true {
			if s[i] == ' ' {
				break
			}
			incomingStr += string(s[i])
			i++
			if i == len(s) {
				break
			}
		}

		// record the string in reverse order
		for i := range incomingStr {
			ans += string(incomingStr[len(incomingStr)-1-i])
		}

		if (i != len(s)-1) {
			ans += " "
		}
	}
	// trim extra space in last char
	ans = ans[:len(ans)-1]
	return ans
}
