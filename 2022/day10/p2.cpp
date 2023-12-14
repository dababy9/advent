#include <iostream>
#include <fstream>
using namespace std;

int main(){
  ifstream fin("input.txt");
  int cyc = 0, val = 1, add, index = 0;
  string s;
  while(fin >> s){
    cyc++;
    if(cyc%40 == val+1 || cyc%40 == val || cyc%40 == val+2)
      cout << '#';
    else {
      cout << ' ';
    }
    if(cyc%40 == 0)
      cout << endl;
    if(s != "noop"){
      cyc++;
      if(cyc%40 == val+1 || cyc%40 == val || cyc%40 == val+2)
        cout << '#';
      else {
        cout << ' ';
      }
      if(cyc%40 == 0)
        cout << endl;
      fin >> add;
      val += add;
    }
  }
  return 0;
}