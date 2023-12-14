#include <iostream>
#include <fstream>
using namespace std;

bool same(string);

int main(){
  string s;
  ifstream fin("input.txt");
  fin >> s;
  int total = 14;
  for(int i = 13; i < s.length(); i++){
    string sub = "";
    for(int j = 13; j >= 0; j--)
      sub += s[i-j];
    if(!same(sub))
      break;
    total++;
  }
  cout << total << endl;
  return 0;
}

bool same(string s){
  string d = s;
  for(int i = 0; i < s.length(); i++)
    for(int j = 0; j < d.length(); j++)
      if(i != j && s[i] == d[j])
        return true;
  return false;
}