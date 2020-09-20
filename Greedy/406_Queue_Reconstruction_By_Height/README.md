# 406. Queue Reconstruction by Height (medium)

Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.


```
Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
```

## 我的作法

> 6/7 一刷

先用Greedy作法, 先從一堆(h,k)的people中挑出 h與k最小的出來 (優先先選k小的person), 依序排列

例如: 
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

->

[[5,0], [7,0], [6,1], [7,1], [5,2], [4,4]]


然後在loop每一個people, 調整到最適當的位置

例如:
[[5,0], [7,0], [6,1], [7,1], [5,2], [4,4]]


可以看到 [5,2]需要與[4,4]交換, 因為[4,4] 前面有5個people的height大於它, 但他的k是4


### Java

> 大概做了2個晚上的時間, 不過還是有問題

```java=
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
```

```
11 / 37 test cases passed.
```

遇到這組測資會爆炸, 原因是因為在交換的過程中, 假如x被交換的到了新位置後, 卻與其height變得不一致, 然後上面的code又不會再次對x做調整
```

Input:
[[8,2],[4,2],[4,5],[2,0],[7,2],[1,4],[9,1],[3,1],[9,0],[1,0]]
Output:
[[1,0],[2,0],[9,0],[3,1],[1,4],[4,2],[7,2],[8,2],[9,1],[4,5]]
Expected:
[[1,0],[2,0],[9,0],[3,1],[1,4],[9,1],[4,2],[7,2],[8,2],[4,5]]
```

## 參考方法

1. 先找出最高的h, 並根據k做排序 (從k最小的到最大的)

```
例如

[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

挑出 [[7,0], [7,1]]
```

2. 再挑出第二高的h, 得到 [[6,1]], 依序插入剛剛排序好的k=7的set


```
例如

[6,1]

插入[[7,0], [7,1]] 的中間 -> [[7,0], [6,1], [7,1]]
```

以此類推...

### Java

```java=
class Solution {

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
```

## 心得

若想了&coding一個晚上, 程式還是有問題, 那就表示想法要換了！
一開始想的方法不見得能夠滿足所有條件



