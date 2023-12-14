#include <iostream>
#include <fstream>
using namespace std;

int main(){
  ifstream fin("input.txt");
  int cyc = 0, val = 1, add, total = 0;
  string s;
  while(fin >> s){
    cyc++;
    if(cyc%40 == 20 && cyc <= 220)
      total += cyc*val;
    if(s != "noop"){
      cyc++;
      if(cyc%40 == 20 && cyc <= 220)
        total += cyc*val;
      fin >> add;
      val += add;
    }
  }
  cout << total << endl;
  return 0;
}