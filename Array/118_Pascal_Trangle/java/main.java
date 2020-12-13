class Solution {
    public List<List<Integer>> generate(int numRows) {
        // 宣告一個 可以塞入List<Integer>的List...
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        List<Integer> previous = new ArrayList<Integer>();
        
        for(int i=1; i<=numRows; i++) {
            List<Integer> current = new ArrayList<Integer>();
            
            // 如果只有1排
            if(i == 1) {
                current.add(1);
                
            } else if(i == 2) {
                current.add(1);
                current.add(1);
            } else {
                for(int j=0; j<i; j++) {
                    // 第一個是1
                    if(j==0) {
                        current.add(1);
                    } else if (j==(i-1)) { // 最後一個也是1
                        current.add(1);
                    } else {
                        int value = previous.get(j) + previous.get(j-1);
                        current.add(value);
                    }
                }
            }
            result.add(current);
            previous = current;
        }
        
        return result;
    }
}