# 字串 String

字串由unicode characters組成，通常就透過 `array`來去遍歷string內容

## 字串比較函式 String Compare Function

string通常有自己的`compare function`，
但要注意的是，是否能夠用 `==` 來進行比較，需要依據下列假設
```
該programming language support "operator overloading" ??
```

`operator overloading`的意思是，運算子`==`是否有其他的功能，會隨著參數的不同而呈現不同的行為。

例如 `C++` 有支援 `operator overloading`，會根據參數是string類別而進行string內容比較

```c++=
#include <iostream>

int main() {
    string s1 = "Hello World";
    cout << "s1 is \"Hello World\"" << endl;
    string s2 = s1;
    cout << "s2 is initialized by s1" << endl;
    string s3(s1);
    cout << "s3 is initialized by s1" << endl;
    // compare by '=='
    cout << "Compared by '==':" << endl;
    cout << "s1 and \"Hello World\": " << (s1 == "Hello World") << endl;
    cout << "s1 and s2: " << (s1 == s2) << endl;
    cout << "s1 and s3: " << (s1 == s3) << endl;
    // compare by 'compare'
    cout << "Compared by 'compare':" << endl;
    cout << "s1 and \"Hello World\": " << !s1.compare("Hello World") << endl;
    cout << "s1 and s2: " << !s1.compare(s2) << endl;
    cout << "s1 and s3: " << !s1.compare(s3) << endl;
}
```

像是`Java`就不支援`operator overloading`，因為對java來說，當其使用 `==`對String物件進行比較，會直接是對這String物件們比較是否是相同的物件，而非是判斷是否為相同的String值了

```
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        // initialize
        String s1 = "Hello World";
        System.out.println("s1 is \"" + s1 + "\"");
        String s2 = s1;
        System.out.println("s2 is another reference to s1.");
        String s3 = new String(s1);
        System.out.println("s3 is a copy of s1.");
        // compare using '=='
        System.out.println("Compared by '==':");
        // true since string is immutable and s1 is binded to "Hello World"
        System.out.println("s1 and \"Hello World\": " + (s1 == "Hello World"));
        // true since s1 and s2 is the reference of the same object
        System.out.println("s1 and s2: " + (s1 == s2));
        // false since s3 is refered to another new object
        System.out.println("s1 and s3: " + (s1 == s3));
        // compare using 'equals'
        System.out.println("Compared by 'equals':");
        System.out.println("s1 and \"Hello World\": " + s1.equals("Hello World"));
        System.out.println("s1 and s2: " + s1.equals(s2));
        System.out.println("s1 and s3: " + s1.equals(s3));
        // compare using 'compareTo'
        System.out.println("Compared by 'compareTo':");
        System.out.println("s1 and \"Hello World\": " + (s1.compareTo("Hello World") == 0));
        System.out.println("s1 and s2: " + (s1.compareTo(s2) == 0));
        System.out.println("s1 and s3: " + (s1.compareTo(s3) == 0));
    }
}
```

## 字串是否為Immutable or Mutable

* Immutable表示 string一但被創建，就不能修改，Mutable反之
  * C++為mutable
  * Java為immutable

## Extra Operations

針對string的改變操作，需要注意
* 各語言內建的方法其 `time complexity`為多少
  * 例如像是 finding operation 或 substring operation都花費 `O(N)`。

C++:

```c++=
#include <iostream>

int main() {
    string s1 = "Hello World";
    // 1. concatenate
    s1 += "!";
    cout << s1 << endl;
    // 2. find
    cout << "The position of first 'o' is: " << s1.find('o') << endl;
    cout << "The position of last 'o' is: " << s1.rfind('o') << endl;
    // 3. get substr
    cout << s1.substr(6, 5) << endl;
}
```

Java
```java=
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        String s1 = "Hello World";
        // 1. concatenate
        s1 += "!";
        System.out.println(s1);
        // 2. find
        System.out.println("The position of first 'o' is: " + s1.indexOf('o'));
        System.out.println("The position of last 'o' is: " + s1.lastIndexOf('o'));
        // 3. get substring
        System.out.println(s1.substring(6, 11));
    }
}
```

## 遇到Immutable String的問題與對應解法

### Immutable 操作注意事項

如果要更改Immutable String內容，只能透過產生新的String時做更改。

當Java做這樣操作時，其string因為是immutable，故以下操作  (C++不會有影響)

```java=
public class Main {
    public static void main(String[] args) {
        String s = "";
        int n = 10000;
        for (int i = 0; i < n; i++) {
            s += "hello";
        }
    }
}
```
會進行的動作與時間為
1. 分配足夠的空間給新的string
2. 複製原本string的內容到新的string
3. 將要疊加的內容放在新的string後面

總共的Time Complexity為 O(n^2)
```
// hello 為5個字元
// 第一次loop產生新的string放入hello (5*1)
// 第二次loop產生新的string放入hellohello (5*2)
// 以此類推
5 * 1 + 5 * 2 + 5 * 3 + ... + 5 * n 
= 5 * (1 + 2 + 3 + 4 +....+ n)
= 5 * n * (n + 1) / 2

Time Complexity: O(n^2)
```

透過上面過程可以看到Java的字串concatenation會非常慢

### 對Java的解法，轉換成 `char array`

透過`toCharArray()` 可以避免contenation時太過緩慢的問題
```java=
public class Main {
    public static void main(String[] args) {
        String s = "Hello World";
        char[] str = s.toCharArray();
        str[5] = ',';
        System.out.println(str);
    }
}
```

在string操作頻繁的情況下，更可以用`StringBuilder()`來建立String，可達到 `O(n)`

```java=
public class Main {
    public static void main(String[] args) {
        int n = 10000;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < n; i++) {
            str.append("hello");
        }
        String s = str.toString();
    }
}
```


## Reference

* Introduction to String
  * https://leetcode.com/explore/learn/card/array-and-string/203/introduction-to-string/1158/

