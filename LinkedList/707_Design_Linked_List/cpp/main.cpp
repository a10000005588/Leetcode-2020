#include <iostream>

#include "MyLinkedList.h"


int main() {
  MyLinkedList *myLinkedList = new MyLinkedList();
  myLinkedList->addAtHead(1);
  myLinkedList->addAtTail(3);
  myLinkedList->addAtIndex(1, 2);    // linked list becomes 1->2->3
  std::cout << myLinkedList->get(1) << std::endl;              // return 2
  myLinkedList->deleteAtIndex(1);    // now the linked list is 1->3
  std::cout << myLinkedList->get(1) << std::endl;            // return 3

  return 0;
}