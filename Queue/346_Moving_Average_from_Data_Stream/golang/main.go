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
		return float64(total) / float64(this.size)
	}
	push(val)
	// 沒有超過queue的size, 直接計算
	for _, val := range queue {
		total += val
	}
	if len(queue) == 0 {
		return float64(0)
	}
	return float64(total) / float64((len(queue)))
}

