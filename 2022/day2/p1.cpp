#include <fstream>
#include <iostream>
using namespace std;

int win(char, char);

int main(){
  ifstream fin("input.txt");
  char them, me;
  int total = 0;
  while(fin >> them >> me){
    total += win(them, me);
    if(me == 'X')
      total++;
    else
      total += me == 'Y' ? 2 : 3;
  }
  cout << total << endl;
  return 0;
}

int win(char a, char b){
  if(a == b-23)
    return 3;
  if(a == 'A')
    return b == 'Y' ? 6 : 0;
  if(a == 'B')
    return b == 'Z' ? 6 : 0;
  return b == 'X' ? 6 : 0;
}