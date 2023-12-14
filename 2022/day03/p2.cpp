#include <iostream>
#include <fstream>
using namespace std;

char findSame(string, string, string);
int calc(char);

int main(){
  ifstream finNum("input.txt");
  int n;
  string dump;
  for(n = 0; finNum >> dump; n++);
  string* ruck = new string[n];
  char* items = new char[n/3];
  ifstream fin("input.txt");
  for(n = 0; fin >> dump; n++)
    ruck[n] = dump;
  for(int i = 0; i < n; i += 3)
    items[i/3] = findSame(ruck[i], ruck[i+1], ruck[i+2]);
  int total = 0;
  for(int i = 0; i < n/3; i++)
    total += calc(items[i]);
  cout << total << endl;
  return 0;
}

char findSame(string s1, string s2, string s3){
  for(int i = 0; i < s1.length(); i++)
    for(int j = 0; j < s2.length(); j++)
      if(s1[i] == s2[j])
        for(int k = 0; k < s3.length(); k++)
          if(s3[k] == s1[i])
            return s1[i];
  return ' ';
}

int calc(char c){
  if(c >= 'a' && c <= 'z')
    return c-'a'+1;
  return c-'A'+27;
}