#include <iostream>
#include <fstream>
using namespace std;

bool overlaps(int, int, int, int);

int main(){
  int total = 0, l1, h1, l2, h2;
  char dump;
  ifstream fin("input.txt");
  while(fin >> l1 >> dump >> h1 >> dump >> l2 >> dump >> h2)
    if(overlaps(l1, h1, l2, h2) || overlaps(l2, h2, l1, h1))
      total++;
  cout << total << endl;
  return 0;
}

bool overlaps(int low1, int hi1, int low2, int hi2){
  return hi1 >= low2 && low1 <= hi2;
}