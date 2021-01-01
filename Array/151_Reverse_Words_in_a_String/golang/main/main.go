package main

func reverseWords(s string) string {
	var stringContainer []string

    for i:=0; i<len(s); i++ {
    	var newWord = ""

    	if s[i] != ' ' {
    		// keep record the char until next ' '
			for true {
				// if ' ', stop record
				if s[i] == ' ' {
					break
				}

				// append character into the newWord, until the other empty space
				newWord = newWord + string(s[i])
				// increment i.
				i++

				// if the end, stop record
				if i == len(s) {
					break
				}
				continue
			}
			// push the word into the stringContainer
			stringContainer = append(stringContainer, newWord)
		}
    }
    ans := ""
    for i, _ := range stringContainer {
    	ans += stringContainer[len(stringContainer)-1-i]
    	if i != len(stringContainer) -1 {
			ans += " "
		}
	}

	return ans
}

func main() {
	input := "a good   example"
	reverseWords(input)
}