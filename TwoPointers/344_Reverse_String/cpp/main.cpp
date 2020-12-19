#include <vector>

using namespace std;

void reverseString(vector<char>& s) {
    int backwardIndex = s.size() - 1;
    for (vector<char>::size_type i=0; i< (s.size()/2); ++i) {
        char temp = s[i];
        s[i] = s[backwardIndex];
        s[backwardIndex] = temp;
        --backwardIndex;
    }
}

int main() {
    vector<char> characters = {'c','o','o'};
    reverseString(characters);
    return 0;
}
