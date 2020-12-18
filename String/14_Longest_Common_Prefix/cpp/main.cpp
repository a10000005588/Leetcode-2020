#include <string>
#include <vector>

using namespace std;

string longestCommonPrefix(vector<string>& strs) {
    if (strs.empty()) return "";

    int minIndex = 0;
    string minString = strs[0];
    for (vector<string>::size_type i=1; i != strs.size(); ++i) {
        if (strs[i].size() < minString.size()) {
            minString = strs[i];
            minIndex = i;
        }
    }

    string ans = "";
    for (string::size_type i=0; i<minString.size(); i++) {
        // loop each string in strs
        for (string::size_type j=0; j<strs.size(); j++) {
            string curString = strs[j];
            if (minString[i] != curString[i]) return ans;
        }
        ans += minString[i];
    }

    return ans;
}
int main() {
    vector<string> strs = {"flower", "flow", "flight"};
    assert(longestCommonPrefix(strs) == "fl");
    vector<string> strs2 = {"kkkkl", "kkk", "kkkk"};
    assert(longestCommonPrefix(strs2) == "kkk");
    vector<string> strs3 = {"1", "kkk", "23"};
    assert(longestCommonPrefix(strs3) == "");
    return 0;
}
