#include <fstream>
#include <iostream>
using namespace std;

char myplay(char, char);

int main(){
  ifstream fin("input.txt");
  char them, outcome;
  int total = 0;
  while(fin >> them >> outcome){
    if(outcome == 'Y')
      total += 3;
    if(outcome == 'Z')
      total += 6;
    char me = myplay(them, outcome);
    total += me-'A'+1;
  }
  cout << total << endl;
  return 0;
}

char myplay(char them, char outcome){
  if(outcome == 'Y')
    return them;
  if(outcome == 'X'){
    if(them == 'A')
      return 'C';
    if(them == 'B')
      return 'A';
    return 'B';
  }
  if(them == 'A')
    return 'B';
  if(them == 'B')
    return 'C';
  return 'A';
}