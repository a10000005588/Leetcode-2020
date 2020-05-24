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
