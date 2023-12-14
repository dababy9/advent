#include <iostream>
#include <fstream>
#include <vector>
using namespace std;

struct Node {
  string name;
  Node* parent;
  vector<Node*> children;
  vector<int> files;
};

int toInt(string);
int pow(int, int);
Node* findChild(string, vector<Node*>);
int computeSize(int&, Node*);
void del(Node*&);

int main(){
  ifstream fin("input.txt");
  Node* root = new Node;
  root->parent = NULL;
  root->name = "/";
  Node* curr = root;
  string inp;
  fin >> inp >> inp >> inp >> inp;
  while(fin >> inp){
    if(inp == "ls"){
      string s1;
      while(fin >> s1 && s1 != "$"){
        fin >> inp;
        if(s1 == "dir")
          curr->children.push_back(new Node {inp, curr, {}, {}});
        else
          curr->files.push_back(toInt(s1));
      }
    } else {
      fin >> inp;
      if(inp == "..")
        curr = curr->parent;
      else
        curr = findChild(inp, curr->children);
      fin >> inp;
    }
  }
  int total = 0;
  int g = computeSize(total, root);
  cout << total << endl;
  del(root);
  return 0;
}

int toInt(string s){
  int total = 0;
  for(int i = 1; i <= s.length(); i++)
    total += (s[s.length()-i]-'0')*pow(10, i-1);
  return total;
}

int pow(int base, int exp){
  if(exp == 0)
    return 1;
  return pow(base, exp-1)*base;
}

Node* findChild(string s, vector<Node*> v){
  for(int i = 0; i < v.size(); i++)
    if(v[i]->name == s)
      return v[i];
  return NULL;
}

int computeSize(int& total, Node* A){
  int t = 0;
  for(int i = 0; i < A->files.size(); i++)
    t += A->files[i];
  for(int i = 0; i < A->children.size(); i++)
    t += computeSize(total, A->children[i]);
  if(t <= 100000)
    total += t;
  return t;
}

void del(Node*& A){
  for(int i = 0; i < A->children.size(); i++)
    del(A->children[i]);
  A->children.clear();
  A->files.clear();
  delete A;
}