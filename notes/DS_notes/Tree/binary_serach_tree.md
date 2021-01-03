# 二元搜尋樹介紹 Binary Search Tree

給一棵樹, 其特性要符合如下:

1. 任一節點的左子樹所有節點的值要小於自己的值
2. 任一節點的右子樹所有節點的值要大於自己的值
3. 節點的左子樹與右子樹都是二元搜尋樹！

範例:

```
   10
  / \
 1   12
  \
   5
```

## 若用In order traversal的話可以得到小到大的排序資料

參考: [Binary Tree: Traversal(尋訪)](http://alrightchiu.github.io/SecondRound/binary-tree-traversalxun-fang.html)


## 新增與刪除節點 (有空補一下用Java實作的code)

假設有一個值: 4 要插入, 這棵樹會變成這樣

```
       10
      /  \
     1    12
      \
       5
      / 
     4
```

若要刪除值為5的節點

```
       10
      /  \
     1    12
      \
       X
      / 
     4
```

找左子樹最大的來補, 或是右子樹最小的來補

參考: [BST的排序與刪除](http://alrightchiu.github.io/SecondRound/binary-search-tree-sortpai-xu-deleteshan-chu-zi-liao.html)
