#include <iostream>
#include <fstream>
using namespace std;

struct Node {
  char data;
  Node* next;
};

void append(char, Node*&);
void printEnd(Node*);
void moveEnd(Node*&, Node*&);
Node* lastPointer(Node*);
Node* lastNode(Node*);

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
  while(fin >> dump >> n >> dump >> fromID >> dump >> toID)
    for(int i = 0; i < n; i++)
      moveEnd(stacks[fromID-1], stacks[toID-1]);
  for(int i = 0; i < 9; i++)
    printEnd(stacks[i]);
  cout << endl;
  return 0;
}

void append(char c, Node*& A){
  if(!A)
    A = new Node {c, NULL};
  else
    append(c, A->next);
}

void printEnd(Node* A){
  if(A->next)
    printEnd(A->next);
  else
    cout << A->data;
}

void moveEnd(Node*& n1, Node*& n2){
  Node* target = lastNode(n1);
  if(!n2)
    n2 = target;
  else
    lastNode(n2)->next = target;
  if(!n1->next)
    n1 = NULL;
  else
    lastPointer(n1)->next = NULL;
}

Node* lastPointer(Node* A){
  if(!A->next->next)
    return A;
  return lastPointer(A->next);
}

Node* lastNode(Node* A){
  if(!A->next)
    return A;
  return lastNode(A->next);
}