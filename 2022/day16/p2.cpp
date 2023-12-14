#include <iostream>
#include <fstream>
#include <vector>
#include <string.h>
using namespace std;

struct Node {
  string name;
  int flow;
  vector<Node*> links;
  vector<int> steps;
};

struct Attempt {
  vector<Node*> path;
  int steps, totalFlow;
};

vector<Node*> generateConnections(vector<Node*>);
int shortestPath(Node*, Node*);
void filter(vector<Attempt>&);
void selectionSort(vector<Attempt>&);
vector<Node*> importantValves(vector<Node*>);
bool visited(Node*, vector<Node*>);
Node* findNode(string, vector<Node*>);
vector<Node*> read(string);

int main(){
  vector<Node*> valves = read("input.txt");
  Node* AA = findNode("AA", valves);
  valves = importantValves(valves);
  vector<Attempt> attempts, tAttempts;
  vector<Node*> v = generateConnections(valves);
  for(int i = 0; i < valves.size(); i++){
    int steps = shortestPath(AA, valves[i]);
    Attempt a = {{findNode(valves[i]->name, v)}, steps, valves[i]->flow*(26-steps)};
    attempts.push_back(a);
  }
  for(int depth = 0; depth < 6; depth++){
    vector<Attempt> nAttempts;
    for(int i = 0; i < attempts.size(); i++){
      Attempt a = attempts[i];
      Node* curr = a.path[a.path.size()-1];
      if(a.steps == 26)
        nAttempts.push_back(a);
      else {
        int additions = 0;
        for(int j = 0; j < curr->links.size(); j++){
          if(a.steps+curr->steps[j] < 26 && !visited(curr->links[j], a.path)){
            additions++;
            Attempt newA = a;
            newA.path.push_back(curr->links[j]);
            newA.steps += curr->steps[j];
            newA.totalFlow += curr->links[j]->flow*(26-newA.steps);
            nAttempts.push_back(newA);
          }
        }
        if(additions == 0){
          a.steps = 26;
          nAttempts.push_back(a);
        }
      }
    }
    for(int i = 0; i < attempts.size(); i++)
      tAttempts.push_back(attempts[i]);
    attempts.clear();
    attempts = nAttempts;
  }
  for(int i = 0; i < tAttempts.size(); i++)
    attempts.push_back(tAttempts[i]);
  filter(attempts);
  selectionSort(attempts);
  int maxFlow = 0;
  for(int i = 1; i < attempts.size(); i++)
    for(int j = 0; j < i; j++){
      bool hasMatch = false;
      int dualFlow = attempts[i].totalFlow+attempts[j].totalFlow;
      if(dualFlow < maxFlow)
        break;
      for(int k = 0; k < attempts[j].path.size() && !hasMatch; k++)
        if(visited(attempts[j].path[k], attempts[i].path))
          hasMatch = true;
      if(!hasMatch)
        maxFlow = dualFlow;
    }
  cout << maxFlow << endl;
  for(int i = 0; i < valves.size(); i++)
    delete valves[i];
  valves.clear();
  return 0;
}

vector<Node*> generateConnections(vector<Node*> v){
  vector<Node*> A;
  for(int i = 0; i < v.size(); i++)
    A.push_back(new Node {v[i]->name, v[i]->flow, {}, {}});
  for(int nV = 0; nV < A.size(); nV++){
    vector<Node*> links;
    vector<int> steps;
    for(int nVV = 0; nVV < A.size(); nVV++){
      if(nVV != nV){
        steps.push_back(shortestPath(v[nV], v[nVV]));
        links.push_back(A[nVV]);
      }
    }
    A[nV]->steps = steps;
    A[nV]->links = links;
  }
  return A;
}

int shortestPath(Node* A, Node* B){
  int nSteps, visitedI = 0;
  vector<Node*> visitedNodes = {A};
  bool found = false;
  for(nSteps = 0; !found; nSteps++){
    int vSize = visitedNodes.size();
    for(int i = visitedI; i < vSize && !found; i++){
      for(int j = 0; j < visitedNodes[i]->links.size() && !found; j++){
        if(!visited(visitedNodes[i]->links[j], visitedNodes)){
          if(visitedNodes[i]->links[j] == B)
            found = true;
          visitedNodes.push_back(visitedNodes[i]->links[j]);
        }
      }
      visitedI++;
    }
  }
  visitedNodes.clear();
  return nSteps+1;
}

void filter(vector<Attempt>& v){
  vector<Attempt>::iterator i;
  int n;
  for(n = 0, i = v.begin(); n < v.size(); i++, n++)
    if(v[n].path.size() < 3){
      v.erase(i);
      i--;
      n--;
    }
}

void selectionSort(vector<Attempt>& v){
  for(int l = v.size(); l > 1; l--){
    int imax = 0, i;
    for(i = 1; i < l; i++)
      if(v[imax].totalFlow > v[i].totalFlow)
        imax = i;
    Attempt t = v[imax];
    v[imax] = v[l-1];
    v[l-1] = t;
  }
}

vector<Node*> importantValves(vector<Node*> v){
  vector<Node*> a;
  for(int i = 0; i < v.size(); i++)
    if(v[i]->flow != 0)
      a.push_back(v[i]);
  return a;
}

bool visited(Node* A, vector<Node*> v){
  for(int i = 0; i < v.size(); i++)
    if(v[i] == A)
      return true;
  return false;
}

Node* findNode(string name, vector<Node*> v){
  for(int i = 0; i < v.size(); i++)
    if(v[i]->name == name)
      return v[i];
  return NULL;
}

vector<Node*> read(string fname){
  vector<Node*> v;
  ifstream fin1(fname);
  ifstream fin2(fname);
  string n, s;
  while(fin1 >> s >> n >> s >> s){
    char c;
    for(int i = 0; i < 5; i++)
      fin1 >> c;
    int f;
    fin1 >> f;
    getline(fin1, s);
    v.push_back(new Node {n, f, {}, {}});
  }
  for(int i = 0; i < v.size(); i++){
    for(int j = 0; j < 9; j++)
      fin2 >> s;
    getline(fin2, s);
    for(int j = 1; j < s.length(); j += 4)
      v[i]->links.push_back(findNode(s.substr(j, 2), v));
  }
  return v;
}