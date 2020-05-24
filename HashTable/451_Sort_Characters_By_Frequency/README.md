# 451. Sort Characters By Frequency (medium)

Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

```
Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
```
Example 2:

```
Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.
```
Example 3:

```
Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
```

## 我的作法

建立一個map, 紀錄字母與對應的次數

然後在取得map的key(字母)的集合

根據集合來組成最後答案

### Code
> 大概花了40分

```java=
class Solution {
    public String frequencySort(String s) {
        if (s.equals("")) {
            return "";
        }

        HashMap<Character, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        char[] ch = toCharArr(s);

        // 計算character的次數
        for (char c: ch) {
            int freq = map.getOrDefault(c, 0);
            freq++;
            map.put(c, freq);
        }

        // 取得key list
        List<Character> keyList = new ArrayList<Character>(map.keySet());

        while (!keyList.isEmpty()) {
            int maxFreq = 0;
            char maxFreqKey = 0;
            // 取出最高的印出來, 並排除
            for (Character key: keyList) {
                int freq = map.get(key);
                if (maxFreq < freq) {
                    maxFreq = freq;
                    maxFreqKey = key;
                }
            }

            // 根據次數印出char
            for(int i=0; i<maxFreq; i++) {
                sb.append(maxFreqKey);
            }

            // 排除已經加過的char
            keyList.remove(keyList.indexOf(maxFreqKey));
        }

        String ans = sb.toString();
        return ans;
    }

    private char[] toCharArr(String str) {
        char[] ch = new char[str.length()];

        for (int i=0; i<str.length(); i++) {
            ch[i] = str.charAt(i);
        }

        return ch;
    }
}
```

* Runtime: 14 ms, faster than 53.69% of Java online submissions for Sort Characters By Frequency.
* Memory Usage: 40.2 MB, less than 18.52% of Java online submissions for Sort Characters By Frequency.
