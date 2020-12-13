#include <iostream>
#include <string>

using namespace std;

string addBinary(string a, string b) {
    if (a.empty() && b.empty()) { return "";}
    if (a.empty() || b.empty()) { return a + b;}
    string ans;
    string shortStr;
    string longStr;
    // 先確認ab哪個比較多位數
    if (a.length() > b.length()) {
        longStr = a;
        shortStr = b;
    } else {
        shortStr = a;
        longStr = b;
    }

    bool isCarry = false;
    int indexLong = longStr.size()-1;
    int indexShort = shortStr.size()-1;
    // 將shortStr加到longStr上
    for(string::iterator it = longStr.begin(); it != longStr.end(); --it) {
        if (indexShort < 0) break;
        if (isCarry) {
            if (longStr[indexLong] == '0' && shortStr[indexShort] == '0') {
                longStr[indexLong] = '1';
                isCarry = false;
                --indexLong;
                --indexShort;
                continue;
            }
            if (longStr[indexLong] == '1' && shortStr[indexShort] == '0') {
                longStr[indexLong] = '0';
                isCarry = true;
                --indexLong;
                --indexShort;
                continue;
            }
            if (longStr[indexLong] == '0' && shortStr[indexShort] == '1') {
                longStr[indexLong] = '0';
                isCarry = true;
                --indexLong;
                --indexShort;
                continue;
            }
            if (longStr[indexLong] == '1' && shortStr[indexShort] == '1') {
                isCarry = true;
                --indexLong;
                --indexShort;
                continue;
            }
        } else {
            if (longStr[indexLong] == '0' && shortStr[indexShort] == '0') {
                --indexLong;
                --indexShort;
                continue;
            }
            if (longStr[indexLong] == '1' && shortStr[indexShort] == '0') {
                --indexLong;
                --indexShort;
                continue;
            }
            if (longStr[indexLong] == '0' && shortStr[indexShort] == '1') {
                longStr[indexLong] = '1';
                --indexLong;
                --indexShort;
                continue;
            }
            if (longStr[indexLong] == '1' && shortStr[indexShort] == '1') {
                longStr[indexLong] = '0';
                isCarry = true;
                --indexLong;
                --indexShort;
                continue;
            }
        }
    }

    // 把剩下的處理完
    if (indexLong >= 0) {
        for (int i=indexLong; i >=0; --i) {
            if (isCarry) {
                if (longStr[indexLong] == '0') {
                    longStr[indexLong] = '1';
                    --indexLong;
                    isCarry = false;
                } else if(longStr[indexLong] == '1') {
                    longStr[indexLong] = '0';
                    --indexLong;
                    isCarry = true;
                }
            } else {
                break;
            }
        }
    }
    // 最後一位若是1，還有進位，那就
    if (isCarry) {
        return "1" + longStr;
    }

    return longStr;
}

int main() {
    string a = "1";
    string b = "111";
    string ans = addBinary(a,b);

    std::cout << "Hello, World!" << std::endl;
    return 0;
}
