#include <iostream>

struct TreeNode {
    TreeNode() {}
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

TreeNode* findTargetNode(TreeNode* rootNode, TreeNode* target) {
    if (rootNode == nullptr) {
        return nullptr;
    }

    if (rootNode->val == target->val) {
        return rootNode;
    }

    TreeNode* leftNode = findTargetNode(rootNode->left, target);
    TreeNode* rightNode = findTargetNode(rootNode->right, target);
    if (leftNode != nullptr) {
        return leftNode;
    }
    // 一定會有target在Tree內，所以若沒有在left, 那就回傳right的結果
    return rightNode;
}


TreeNode* getTargetCopy(TreeNode* original, TreeNode* cloned, TreeNode* target) {
    TreeNode* ans = findTargetNode(cloned, target);
    return ans;
}
int main() {
    TreeNode node{};
    TreeNode node1{};
    TreeNode node2{};
    TreeNode node3{};
    TreeNode node4{};
    node.val = 7;
    node.left = &node1;
    node.right = &node2;
    node1.val = 4;
    node2.val = 3;
    node2.left = &node3;
    node2.right = &node4;
    node3.val = 6;
    node4.val = 19;

    TreeNode node5{};
    node5.val = 3;
    TreeNode* ans = getTargetCopy(&node, &node, &node5);
    return 0;
}
