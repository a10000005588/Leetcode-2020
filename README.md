# Leetcode 練習記錄

這個專案主要存放我練習Leetcode有針對難度分類的集合題庫(Collection Question)
## 準備方式

分析tag的熱門標籤，熟悉各個標籤解題的思路(解決該標籤全部的easy和medium為主)，再根據各個標籤建立hackmd筆記，紀錄自己解決的過程。

## 各類別題目筆記

### Array

| Index | Question Name                               | Type   | Note | C++ | Java | Go | Comment|
|-------|---------------------------------------------|--------|------|-----|------|----|---|
| 33    | Search in Rotated Sorted Array                                    | medium   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Array/33_Search_in_Rotated_Sorted_Array) |  O  |     |    ||
| 54    | Spiral Matrix                                    | medium   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Array/54_Spiral_Matrix) |  O  |     |    ||
| 66    | Plus One                                    | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Array/66_Plus_One) |  O  | O    |    ||
| 118    | Pascal's Triangle                        | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Array/118_Pascal_Trangle) |  O  | O    |    ||
| 119    | Pascal's Triangle II                       | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Array/118_Pascal_Trangle) |   |     |  O  | 要滿足只用一個array大小空間 O(k) k為input大小 來完成，須具備backtracking概念|
| 151    | Reverse Words in a String                  | medium   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Array/151_Reverse_Words_in_a_String) |   |     |  O  | 這題有點算是easy的程度, 目前解法為O(n^2)的時間複雜度就能解出來了，待研究 |
| 189    | Rotate Array                        | medium   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Array/189_Rotate_Array) |  O  | O    |    | 此題建議用O(1) extra space來解，目前是用了O(n) extra space & O(n) Time Complexity解此題目|
| 283   | Move Zeroes                   | easy | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Array/283_Move_Zeroes) |     |     |  O  ||
| 287   | Find the Duplicate Number                   | medium | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Array/287_Find_the_Duplicate_Number) |  O   | O    |    ||
| 498   | Diagonal Traverse                   | medium | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Array/498_Diagonal_Traverse) |  O   |     |    ||
| 509   | Fibonacci Number                            | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Array/509_Fibonacci_Number) |     | O    |    ||
| 557   | Reverse Words in a String III                           | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Array/509_Fibonacci_Number) |     |     |  O  | 注意edge case. |
| 724   | Find Pivot Index                            | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Array/724_Find_Pivot_Index) | O   |      |    ||
| 747   | Largest Number At Least Twice of Others                            | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Array/747_Largest_Number_At_Least_Twice_of_Others) | O   |      |    ||
| 1464  | Maximum Product of Two Elements in an Array | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Array/1464_Maximum_Product_of_Two_Elements_in_an_array) |     | O    |    ||
| 1480  | Running Sum of 1d Array                     | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Array/1480_Running_Sum_of_1D_Array) |     | O    |    ||
| 1640  | Check Array Formation Through Concatenation  | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Array/1640_Check_Array_Formation_Through_Concatenation) |     |     |  O  |此題為Leetcode Challenge 2020/Janunary 題目，目前用golang的解法佔據太多space空間，待優化|


### Search

| Index | Question Name                               | Type   | Note | C++ | Java | Go |
|-------|---------------------------------------------|--------|------|-----|------|----|
| 441    | Arranging Coins                                    | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Search/441_Arranging_Coins) |     | O    |    |
| 528    | Random Pick with Weight                                    | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Search/528_Random_Pick_with_Weight) |     | O    |    |

### LinkedList

| Index | Question Name                               | Type   | Note | C++ | Java | Go |
|-------|---------------------------------------------|--------|------|-----|------|----|
| 237    | Delete Node in a Linked List                                   | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/LinkedList/237_Deleting_Node) |     | O    |    |
| 707    | Design Linked List                                    | medium   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/LinkedList/707_Design_Linked_List) |     | O    |    |

### Sort

| Index | Question Name                               | Type   | Note | C++ | Java | Go |
|-------|---------------------------------------------|--------|------|-----|------|----|
| 973    | K Closest Points to Origin                                   | medium   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Sort/973_K_Closest_Points_to_Point) |     | O    |    |

### Depth First Search

| Index | Question Name                               | Type   | Note | C++ | Java | Go |
|-------|---------------------------------------------|--------|------|-----|------|----|
| 129    | Sum Root to Leaf Numbers                                   | medium   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/DepthFirstSearch/129_Sum_Root_to_Leaf_Numbers) |     | O    |    |

### Tree

| Index | Question Name                               | Type   | Note | C++ | Java | Go | Comment |
|-------|---------------------------------------------|--------|------|-----|------|----|-------|
| 107    | Binary Tree Level Order Traversal II                                   | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Tree/107_Binary_Tree_Level_Order_Traversal_II) |     | O    |    | |
| 111    | Minimum Depth of Binary Tree II                                   | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Tree/111_Minimum_Depth_of_Binary_Tree) |     | O    |  O  | 此題可練習使用DFS與BFS的寫法，DFS為遞迴版本 (效率較差), BFS為iterative版本，效率較好|
| 230    | Kth Smallest Element in a BST                                   | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Tree/230_Kth_Smallest_Element_in_a_BST) |     | O    |    ||
| 563    | Binary Tree Tilt                                   | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Tree/563_Binary_Tree_Tilt) |     | O    |    ||
| 1379    | Find a Corresponding Node of a Binary Tree in a Clone of That Tree                                   | medium   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Tree/1379_Find_a_Corresponding_Node_of_a_Binary_Tree_in_a_Clone_of_That_Tree) | O    |     |    |此題為Leetcode Chanllenge 2021 Janunary day 2題目, 不過此題難度應該為easy才對|

### Greedy

| Index | Question Name                               | Type   | Note | C++ | Java | Go |
|-------|---------------------------------------------|--------|------|-----|------|----|
| 406    | Queue Reconstruction by Height                                   | medium   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Greedy/406_Queue_Reconstruction_By_Height) |     | O    |    |
| 1029    | Two City Scheduling                                   | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Greedy/1029_Two_City_Scheduling) |     | O    |    |


### Dynamic Programming

| Index | Question Name                               | Type   | Note | C++ | Java | Go |
|-------|---------------------------------------------|--------|------|-----|------|----|
| 62    | Unique Path                                   | medium   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/DP/62_Unique_Path) |     | O    |    |
| 276    | Paint Fence     | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/DP/276_Paint_Fence) |     | O    |  O  |
| 300    | Longest Increasing Subsequence | medium   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/DP/300_Longest_Increasing_Subsequence) |     | O    |   |
| 1277    | Count Square Submatrices with All Ones     | medium   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/DP/1277_Count_Square_Submatrices_with_All_Ones) |     | O    |    |

### Two Pointers

| Index | Question Name                               | Type   | Note | C++ | Java | Go | Comment |
|-------|---------------------------------------------|--------|------|-----|------|----|-------|
| 26    | Remove Duplicates from Sorted Array                            | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/TwoPointers/26_Remove_Duplicates_from_Sorted_Array) | O |  | O | 使用golang沒用到two pointer, java的版本有使用到 |
| 27    | Remove Element            | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/TwoPointers/27_Remove_Element) | O | O |  | 2019年曾用java寫過, 2020重複寫這題時，沒有一開始想到要用two pointer technique |
| 167    | Two Sum II                                 | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/TwoPointers/167_Two_Sum_II) | O | O |  | 這題雖然easy, 但沒有善用two pointer技巧容易遇到Time Limited的狀況！ |
| 209    | Minimum Size Subarray Sum | medium   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/TwoPointers/485_Max_Consecutive_Ones) |  |  | O | 值得再嘗試的題目，使用Two Pointer技巧才能夠達成O(n) |
| 344    | Reverse String                                   | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/TwoPointers/344_Reverse_String) |  |  | O | |
| 485    | Max Consecutive Ones                                   | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/TwoPointers/485_Max_Consecutive_Ones) | O | O | O | |
| 561    | Array Partition I                                  | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/TwoPointers/561_Array_Partition) | O |  |  | 有使用c++ sort()函式，可用QuickSort來優化解法|
| 986    | Interval List Intersections     | medium   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/TwoPointers/986_Interval_List_Intersections) |     | O    |    | |



### Hash Table

| Index | Question Name                               | Type   | Note | C++ | Java | Go |
|-------|---------------------------------------------|--------|------|-----|------|----|
| 451    | Sort Characters By Frequency                                   | medium   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/HashTable/451_Sort_Characters_By_Frequency) |     | O    |    |
### Bit Manipulation

| Index | Question Name                               | Type   | Note | C++ | Java | Go |
|-------|---------------------------------------------|--------|------|-----|------|----|
| 137    | Single Number II                                   | medium   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/BitManipuation/137_Single_Number_II) |     | O    |    |

### Math

| Index | Question Name                               | Type   | Note | C++ | Java | Go |
|-------|---------------------------------------------|--------|------|-----|------|----|
| 441    | Arrange Coin                               | medium   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Math/441_Arrangeing_Coin) |     | O    |    |
| 858    | Mirror Reflection                                    | medium   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/Math/858_Mirror_Reflection) |  O   |     |    |

### String

| Index | Question Name                               | Type   | Note | C++ | Java | Go | Comment|
|-------|---------------------------------------------|--------|------|-----|------|----|---|
| 14    | Longest Common Prefix                                   | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/String/14_Longest_Common_Prefix) |  O  |     |  O  | |
| 28    | Implement strStr                                   | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/String/28_Implement_strStr) |  O  |     |  O  | |
| 67    | Add Binary                                   | easy   | [link](https://github.com/a10000005588/Leetcode-2020/tree/master/String/67_Add_Binary) |  O  |     |    | 此題也可以歸類成Math，但我目前解法比較像是Two Pointer|

### Queue

| Index | Question Name                               | Type   | Note | C++ | Java | Go | Comment|
|-------|---------------------------------------------|--------|------|-----|------|----|---|
| 346    | Moving Average from Data Stream            | easy   | [link](https://github.com/a10000005588/Leetcode-2020/blob/master/Queue/346_Moving_Average_from_Data_Stream/) |    |     |  O  | 此題進階版的解法可用Double Ended Queue, 尚未嘗試，需補做此解法|

## 如何高效率刷Leetcode

再ptt上看到神人分享如何準備刷Leetcode找工作，[8個偷吃步教你如何高效率的刷leetcode by 安妮](https://www.youtube.com/watch?v=fyf-GRH1Ceo&feature=emb_title)，如何在有全職工作的情況下，1個半月內刷了300多題的Leetcode

Tips:
* 養成找edge case及time complexity的習慣
* 先把時間花在想題目身上，而不是寫code
  * 思考步驟
    1. 有哪些edge case
    2. 採用的方法time & space complexity是什麼？
* 注重在medium的題目
  * 會了medium的題目，基本上easy也可以迎刃而解
* 一題不要卡太久
  * 可以參考別人的解法，不要看別人的code
* 每個題目只要有一個不差的解法，就可以通過了

## 學習筆記

### String & Array

* [字串操作 String Manipuation](https://github.com/a10000005588/Leetcode-2020/blob/master/notes/String/string_manipulation.md)
### Trees

* [二元搜尋樹介紹 Binary Search Tree](https://github.com/a10000005588/Leetcode-2020/blob/master/notes/Tree/binary_serach_tree.md)
## 參考資源

* Leetcode網站
  * https://leetcode.com/

* Google面试經驗談-The Five Essential Phone-Screen Questions
  * https://sites.google.com/site/steveyegge2/five-essential-phone-screen-questions

* dingjikerbo/Leetcode-Java: 一位大陸神人在Github上做Leetcode的紀錄
  * https://github.com/dingjikerbo/Leetcode-Java/blob/master/doc/Attention.md

* Mock interview的網站:
  * https://leetcode.com/interview/