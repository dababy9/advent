#include <iostream>
#include <fstream>
using namespace std;

struct Point {
  int x, y;
};

struct Node {
  Point data;
  Node* next;
};

void add2front(Point, Node*&);
bool contains(Point, Node*);
int lNode(Node*);
void del(Node*);
void compute(Point, Point&);

int main(){
  ifstream fin("input.txt");
  Node* List = new Node{{0, 0}, NULL};
  Point h = {0, 0};
  Point rope[9];
  for(int i = 0; i < 9; i++)
    rope[i] = {0, 0};
  char dir;
  int n;
  while(fin >> dir >> n){
    for(int i = 0; i < n; i++){
      switch(dir){
        case 'R': {
          h.x++;
          break;
        } case 'U': {
          h.y++;
          break;
        } case 'L': {
          h.x--;
          break;
        } case 'D': {
          h.y--;
          break;
        }
      }
      compute(h, rope[0]);
      for(int i = 1; i < 9; i++)
        compute(rope[i-1], rope[i]);
      if(!contains(rope[8], List))
        add2front(rope[8], List);
    }
  }
  cout << lNode(List) << endl;
  del(List);
  return 0;
}

void add2front(Point p, Node*& A){
  Node* temp = new Node {p, A};
  A = temp;
}

bool contains(Point p, Node* A){
  if(!A)
    return false;
  if(A->data.x == p.x && A->data.y == p.y)
    return true;
  return contains(p, A->next);
}

int lNode(Node* A){
  if(!A)
    return 0;
  return 1 + lNode(A->next);
}

void del(Node* A){
  if(A){
    del(A->next);
    delete A;
  }
}

void compute(Point h, Point& t){
  int dx = h.x-t.x, dy = h.y-t.y;
  if(dx < 0)
    dx *= -1;
  if(dy < 0)
    dy *= -1;
  if(dx < 2 && dy < 2)
    return;
  if(dy == 2){
    t.y += (h.y-t.y > 0 ? 1 : -1);
    if(dx == 1)
      t.x += (h.x-t.x > 0 ? 1 : -1);
  }
  if(dx == 2){
    t.x += (h.x-t.x > 0 ? 1 : -1);
    if(dy == 1)
      t.y += (h.y-t.y > 0 ? 1 : -1);
  }
}