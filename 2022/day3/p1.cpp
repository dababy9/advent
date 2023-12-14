#include <iostream>
#include <fstream>
using namespace std;

char findSame(string);
int calc(char);

int main(){
  ifstream finNum("input.txt");
  int n;
  string dump;
  for(n = 0; finNum >> dump; n++);
  string* ruck = new string[n];
  char* items = new char[n];
  ifstream fin("input.txt");
  for(n = 0; fin >> dump; n++)
    ruck[n] = dump;
  for(int i = 0; i < n; i++)
    items[i] = findSame(ruck[i]);
  int total = 0;
  for(int i = 0; i < n; i++)
    total += calc(items[i]);
  cout << total << endl;
  return 0;
}

char findSame(string s){
  string s1 = "", s2 = "";
  for(int i = 0; i < s.length(); i++)
    if(i < s.length()/2)
      s1 += s[i];
    else
      s2 += s[i];
  for(int i = 0; i < s1.length(); i++)
    for(int j = 0; j < s2.length(); j++)
      if(s1[i] == s2[j])
        return s1[i];
  return ' ';
}

int calc(char c){
  if(c >= 'a' && c <= 'z')
    return c-'a'+1;
  return c-'A'+27;
}