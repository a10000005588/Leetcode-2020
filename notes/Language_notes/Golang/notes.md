# Golang: Basic Knowledge

## String & Array 常用的語法

### 型態轉換

轉換int型態為string
```
strconv.Itoa(val)
```

轉換char型態為string
```
string(val)
```

### Swap 交換

```
i, j := j, i
```

### 要把string部分內容截斷

欲截斷對象： string = "helloworld william"

目標: "world", 找到world的初始位置 `pos` 計算world的 `length`

```
string = string[:pos] + string[ (pos + len(target)  :  ]
```