# 346. Moving Average from Data Stream

Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Implement the MovingAverage class:

* MovingAverage(int size) Initializes the object with the size of the window size.
* double next(int val) Returns the moving average of the last size values of the stream.

Example 1:
```
Input
["MovingAverage", "next", "next", "next", "next"]
[[3], [1], [10], [3], [5]]
Output
[null, 1.0, 5.5, 4.66667, 6.0]

Explanation
MovingAverage movingAverage = new MovingAverage(3);
movingAverage.next(1); // return 1.0 = 1 / 1
movingAverage.next(10); // return 5.5 = (1 + 10) / 2
movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3
```

Constraints:
```
1 <= size <= 1000
-105 <= val <= 105
At most 104 calls will be made to next.
```

## 我的作法

用queue，並在next()方法內定義：
1. 若超過queue的大小就將最舊的值給去掉，並計算總和
2. 沒有超過，直接計算queue內的總和

### Golang

```go=
var queue []int

func push(val int) []int {
    queue = append(queue, val)
    return queue
}

func pop() int {
    element := queue[0]
    queue = queue[1:]
    return element
}

func size() int {
    return len(queue)
}

type MovingAverage struct {
    size int
}

/** Initialize your data structure here. */
func Constructor(size int) MovingAverage {
    movingAverage := MovingAverage{size: size}
    queue = make([]int, 0)
    return movingAverage
}

func (this *MovingAverage) Next(val int) float64 {
    total := 0

    // 若放入超過queue的size, 就把最舊的刪去，放入最新的 並作加總計算
    if size() >= this.size {
        pop()
        push(val)
        
        for _, val := range queue {
            total += val
        }
        return float64(total)/float64(this.size)
    }
    push(val)
    // 沒有超過queue的size, 直接計算
    for _, val := range queue {
        total += val
    }
    if len(queue) == 0 {
        return float64(0)
    }
    return float64(total)/float64((len(queue)))
}
```

Time Complexity : O(n)

```
Runtime: 24 ms, faster than 35.51% of Go online submissions for Moving Average from Data Stream.
Memory Usage: 6.9 MB, less than 91.59% of Go online submissions for Moving Average from Data Stream.
```