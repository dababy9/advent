#include <iostream>
#include <fstream>
using namespace std;

bool contains(int, int, int, int);

int main(){
  int total = 0, l1, h1, l2, h2;
  char dump;
  ifstream fin("input.txt");
  while(fin >> l1 >> dump >> h1 >> dump >> l2 >> dump >> h2)
    if(contains(l1, h1, l2, h2) || contains(l2, h2, l1, h1))
      total++;
  cout << total << endl;
  return 0;
}

bool contains(int outlow, int outhi, int inlow, int inhi){
  return outlow <= inlow && outhi >= inhi;
}