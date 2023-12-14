#include <iostream>
#include <fstream>
using namespace std;

struct Node {
  char data;
  Node* next;
};

void append(char, Node*&);
void printEnd(Node*);
void moveEnd(int, Node*&, Node*&);
Node* nPointer(Node*, int);
Node* lastNode(Node*);
int lNode(Node*);

int main(){
  string* lines = new string[9];
  Node** stacks = new Node*[9];
  ifstream fin("input.txt");
  for(int i = 0; i < 9; i++)
    getline(fin, lines[i]);
  int iStack = 0;
  for(int i = 0; i < lines[8].length(); i++){
    if(lines[8][i] >= '0' && lines[8][i] <= '9'){
      for(int j = 7; j >= 0; j--)
        if(lines[j][i] != ' ')
          append(lines[j][i], stacks[iStack]);
      iStack++;
    }
  }
  string dump;
  int n, fromID, toID;
  while(fin >> dump >> n >> dump >> fromID >> dump >> toID){
    int chopID = lNode(stacks[fromID-1])-n;
    if(chopID != 0)
      moveEnd(chopID, stacks[fromID-1], stacks[toID-1]);
    else {
      Node* target = stacks[fromID-1];
      if(!stacks[toID-1])
        stacks[toID-1] = target;
      else
        lastNode(stacks[toID-1])->next = target;
      stacks[fromID-1] = NULL;
    }
  }
  for(int i = 0; i < 9; i++)
    printEnd(stacks[i]);
  cout << endl;
  return 0;
}

void append(char c, Node*& A){
  if(!A)
    A = new Node{c, NULL};
  else
    append(c, A->next);
}

void printEnd(Node* A){
  if(A->next)
    printEnd(A->next);
  else
    cout << A->data;
}

void moveEnd(int chopID, Node*& n1, Node*& n2){
  Node* target = nPointer(n1, chopID-1);
  if(!n2)
    n2 = target;
  else
    lastNode(n2)->next = target;
  target = n1;
  for(int i = 0; i < chopID-1; i++)
    target = target->next;
  target->next = NULL;
}

Node* nPointer(Node* A, int n){
  if(n == 0)
    return A->next;
  return nPointer(A->next, n-1);
}

Node* lastNode(Node* A){
  if(!A->next)
    return A;
  return lastNode(A->next);
}

int lNode(Node* A){
  if(!A)
    return 0;
  return 1 + lNode(A->next);
}