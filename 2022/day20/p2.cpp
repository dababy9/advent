#include <iostream>
#include <fstream>
using namespace std;

struct Node {
  long int data;
  int id;
  Node* next;
  Node* prev;
};

void append(long int, Node*&, int);
Node* lNode(Node*);
void shift(Node*, int);
Node* findNode(int, Node*);
long int getNum(Node*, int);

int main(){
  ifstream fin("input.txt");
  Node* LIST;
  long int item;
  int size, zeroIndex;
  for(size = 0; fin >> item; size++){
    append(item*811589153, LIST, size);
    if(item == 0)
      zeroIndex = size;
  }
  Node* temp = lNode(LIST);
  temp->next = LIST;
  temp = LIST;
  for(int i = 0; i < size; temp = temp->next, i++)
    temp->next->prev = temp;
  for(int loops = 0; loops < 10; loops++)
    for(int i = 0; i < size; i++)
      shift(findNode(i, LIST), size);
  LIST = findNode(zeroIndex, LIST);
  long int sum = 0;
  for(int i = 1000; i <= 3000; i += 1000)
    sum += getNum(LIST, i%size);
  cout << sum << endl;
  return 0;
}

void append(long int n, Node*& A, int id){
  if(!A)
    A = new Node{n, id, NULL, NULL};
  else
    append(n, A->next, id);
}

Node* lNode(Node* A){
  if(!A->next)
    return A;
  return lNode(A->next);
}

void shift(Node* target, int size){
  if(target->data == 0)
    return;
  target->prev->next = target->next;
  target->next->prev = target->prev;
  int n = target->data%(size-1);
  if(n < 0)
    for(int i = 0; i > n; i--)
      target->next = target->next->prev;
  else
    for(int i = 0; i < n; i++)
      target->next = target->next->next;
  target->prev = target->next->prev;
  target->prev->next = target;
  target->next->prev = target;
}

Node* findNode(int targetID, Node* A){
  if(A->id == targetID)
    return A;
  return findNode(targetID, A->next);
}

long int getNum(Node* A, int n){
  for(int i = 0; i < n; i++)
    A = A->next;
  return A->data;
}