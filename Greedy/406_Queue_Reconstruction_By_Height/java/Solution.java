// 11 / 37 test cases passed.
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        if (people.length == 0) {
            return new int[0][0];
        }

        // 先將people轉成ArrayList
        List<List<Integer>> lists = getTwoDimenList(people);
        LinkedList<int[]> ansLinkedList = new LinkedList<>();

        int minH = 0;
        int minK = 0;
        int curIndex = 0;
        int minIndex = 0;
        int[] target = new int[2];
        // 優先找出(h最小與k最小的)的序列
        while(lists.size() > 0) {
            minH = lists.get(0).get(0);
            minK = lists.get(0).get(1);
            target[0] = minH;
            target[1] = minK;
            for (List<Integer> p: lists) {
                // 先從K最小的開始找
                if (p.get(1) <= minK) {
                    // 若K相同, 比較H, 取H最小的
                    minIndex = curIndex;
                    target[0] = p.get(0);
                    target[1] = p.get(1);

                    minH = p.get(0);
                    minK = p.get(1);
                }
                curIndex++;
            }
            // 將當前找到的放入ansLinkedList
            ansLinkedList.add(target);
            // 移除lists中的target
            lists.remove(minIndex);

            // reset
            minIndex = 0;
            curIndex = 0;
            target = new int[2];
        }

        int swapIndexer = 0;
        // 對ansLinkedList的每一個people做確認, 遇到不符合的target, 與target的k 和 ansLinkedList k的位置互換
        while(swapIndexer < ansLinkedList.size()) {
            int[] targetPeople = ansLinkedList.get(swapIndexer);
            // 取得targetPeople的height值與K值
            int targetH = targetPeople[0];
            int targetK = targetPeople[1];
            int countPeople = 0;
            // 需要紀錄大於target的people位置
            HashMap<Integer,Integer> map = new HashMap<>();

            for (int i =0; i<swapIndexer; i++) {
                int[] curPeople = ansLinkedList.get(i);
                if (curPeople[0] >= targetH) {
                    // 紀錄大於target的people順序與其對應的位置
                    map.put(countPeople, i);
                    countPeople++;
                }
            }
            // 如果找到不在正確的位置的people, 將當前targetPeople與ansLinkedList的targetK位置做swap
            if (countPeople > targetK) {
                int newPosition = map.get(targetK);
                Collections.swap(ansLinkedList, swapIndexer, newPosition);
                // 將swapIndexer重置到targetPeople所在的新位置.
                swapIndexer = newPosition + 1;

                // reset map
                map = new HashMap<>();
                continue;
            }
            swapIndexer++;
        }

        return getAnsFormat(ansLinkedList);
    }

    private List<List<Integer>> getTwoDimenList(int[][] target) {
        List<List<Integer>> lists = new ArrayList<>();
        for(int[] ints: target) {
            List<Integer> list = new ArrayList<>();
            for (int i: ints) {
                list.add(i);
            }
            lists.add(list);
        }
        return lists;
    }

    private int[][] getAnsFormat(LinkedList<int[]> ansLinkedList) {
        int[][] ans = new int[ansLinkedList.size()][2];

        int index = 0;
        for(int[] x: ansLinkedList) {
            int[] cur = new int[2];

            cur[0] = x[0];
            cur[1] = x[1];
            ans[index] = cur;
            index++;
        }
        int x = 0;
        return ans;
    }
}
