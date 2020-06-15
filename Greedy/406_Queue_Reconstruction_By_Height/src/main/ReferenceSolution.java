class ReferenceSolution {

    public int[][] reconstructQueue(int[][] people) {
        // 用hashmap儲存相同height的people
        HashMap<Integer, ArrayList<int[]>> heightGroup = new HashMap<>();
        ArrayList<Integer> heightSet = new ArrayList<>();


        for(int i=0; i<people.length; i++) {
            int heightIndex = people[i][0];
            // 如果該people的height還沒在heightGroup出現過, 宣告一個new ArrayList<int[]>
            if (heightGroup.get(heightIndex) == null) {
                ArrayList<int[]> newGroup = new ArrayList<>();
                newGroup.add(people[i]);
                heightGroup.put(people[i][0], newGroup);

                heightSet.add(people[i][0]);
            } else {
                // 依照k值從小到大做插入
                int newPos = 0;
                ArrayList<int[]> sameHeightSet = heightGroup.get(heightIndex);

                for (int[] x: sameHeightSet) {
                    if (x[1] > people[i][1]) {
                        sameHeightSet.add(newPos, people[i]);
                        break;
                    }
                    newPos++;
                }

                // 如果都比較完, 當前sameHeightSet中沒有比people高的k, 那就直接add到sameHeightSet
                if (newPos == sameHeightSet.size()) {
                    sameHeightSet.add(people[i]);
                }
            }
        }

        // 將heightSet做Sort
        Collections.sort(heightSet);

        ArrayList<int[]> ans = new ArrayList<>();

        // 從最高的height的group開始排序
        for (int i=heightSet.size()-1 ; i>=0; i--) {
            ArrayList<int[]> peopleSet = heightGroup.get(heightSet.get(i));
            // 將每一個值放入ArrayList內, 先從
            for (int j=0; j<peopleSet.size(); j++) {
                ans = insertPeople(ans, peopleSet.get(j));
            }
        }

        return getAnsFormat(ans);
    }

    public ArrayList<int[]> insertPeople (ArrayList<int[]> oldSet, int[] people) {
        ArrayList<int[]> ans = new ArrayList<>();
        // 找到最適合的地方插入

        if (oldSet.size() == 0) {
            ans.add(people);
            return ans;
        }

        for (int i=0; i<oldSet.size(); i++) {
            ans.add(oldSet.get(i));
        }

        // 根據people[1]=newPos 決定要放在ans的newPos, newPos位置的people以及之後都要往後移
        // 如果當前people的height比目前的ans長度還要大, 直接放到最後一個位置
        if (people[1] >= ans.size()) {
            ans.add(people);
        } else {
            ans.add(people[1], people);

        }
        return ans;
    }

    private int[][] getAnsFormat(ArrayList<int[]> ansArrayList) {
        int[][] ans = new int[ansArrayList.size()][2];

        int index = 0;
        for(int[] x: ansArrayList) {
            int[] cur = new int[2];

            cur[0] = x[0];
            cur[1] = x[1];
            ans[index] = cur;
            index++;
        }
        return ans;
    }
}
