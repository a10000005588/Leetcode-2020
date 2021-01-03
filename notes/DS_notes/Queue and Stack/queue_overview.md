# Queue: First in first out 的結構

Queue為一個資料先進，先出來的結構

* front: 指向容器的第一個值
* back: 指向容器的最後一個值
* dequeue(): 將front指向的開頭給除掉
* enqueue(): 將back指向的結尾再多一個值

![](pics/queue.png)

## Queue 實作版本

Queue可用dynamic array當作容器，index指向該容器的head

因為 enqueue() 需要操作 back指向的地方、dequeue() 需要操作 front指向的地方

### Cpp 版本

```cpp=
#include <iostream>

class MyQueue {
    private:
        // store elements
        vector<int> data;       
        // a pointer to indicate the start position
        int p_start;            
    public:
        MyQueue() {
          p_start = 0;
        }
        /** Insert an element into the queue. Return true if the operation is successful. */
        bool enQueue(int x) {
            data.push_back(x);
            return true;
        }
        /** Delete an element from the queue. Return true if the operation is successful. */
        bool deQueue() {
            if (isEmpty()) {
                return false;
            }
            p_start++;
            return true;
        };
        /** Get the front item from the queue. */
        int Front() {
            return data[p_start];
        };
        /** Checks whether the queue is empty or not. */
        bool isEmpty()  {
            return p_start >= data.size();
        }
};

int main() {
    MyQueue q;
    q.enQueue(5);
    q.enQueue(3);
    if (!q.isEmpty()) {
        cout << q.Front() << endl;
    }
    q.deQueue();
    if (!q.isEmpty()) {
        cout << q.Front() << endl;
    }
    q.deQueue();
    if (!q.isEmpty()) {
        cout << q.Front() << endl;
    }
}
```

### Java 版本

```java=
// "static void main" must be defined in a public class.

class MyQueue {
    // store elements
    private List<Integer> data;         
    // a pointer to indicate the start position
    private int p_start;            
    public MyQueue() {
        data = new ArrayList<Integer>();
        p_start = 0;
    }
    /** Insert an element into the queue. Return true if the operation is successful. */
    public boolean enQueue(int x) {
        data.add(x);
        return true;
    };    
    /** Delete an element from the queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty() == true) {
            return false;
        }
        p_start++;
        return true;
    }
    /** Get the front item from the queue. */
    public int Front() {
        return data.get(p_start);
    }
    /** Checks whether the queue is empty or not. */
    public boolean isEmpty() {
        return p_start >= data.size();
    }     
};

public class Main {
    public static void main(String[] args) {
        MyQueue q = new MyQueue();
        q.enQueue(5);
        q.enQueue(3);
        if (q.isEmpty() == false) {
            System.out.println(q.Front());
        }
        q.deQueue();
        if (q.isEmpty() == false) {
            System.out.println(q.Front());
        }
        q.deQueue();
        if (q.isEmpty() == false) {
            System.out.println(q.Front());
        }
    }
}
```

### Golang 版本

```go=
package main

import "fmt"

type Queue struct {
	data []int
	p_start int
}

// 只有Queue struct才能夠使用該method.
func (q *Queue) MyQueue() Queue {
	q.data = []int{}
	q.p_start = 0
	return *q // return Queue pointer
}

func (q *Queue) enQueue(x int) bool {
	q.data = append(q.data, x)
	return true
}

func (q *Queue) deQueue() bool {
	if q.isEmpty() {
		return false
	}
	q.p_start++
	return true
}

func (q *Queue) Front() int {
	return q.data[q.p_start]
}

func (q *Queue) isEmpty() bool {
	return q.p_start >= len(q.data)
}

func main() {
	queue := Queue{}
	queue.MyQueue() // initialize the struct

	queue.enQueue(5)
	queue.enQueue(3)
	if !queue.isEmpty() {
		fmt.Printf("%d\n", queue.Front())
	}
	queue.deQueue()
	if !queue.isEmpty() {
		fmt.Printf("%d\n", queue.Front())
	}
	queue.deQueue()
	if !queue.isEmpty() {
		fmt.Printf("%d\n", queue.Front())
	} else {
		fmt.Print("Queue is Empty!")
	}
}
```