#include <iostream>
#include <fstream>
using namespace std;

int before(string, string);
string getArray(string, int&);

int main(){
  ifstream fin("input.txt");
  int total = 0;
  string s1, s2;
  for(int i = 1; getline(fin, s1) && getline(fin, s2); i++){
    if(before(s1, s2) < 0)
      total += i;
    getline(fin, s1);
  }
  cout << total << endl;
  return 0;
}

int before(string s1, string s2){
  int i1 = 0, i2 = 0;
  while(i1 < s1.length() && i2 < s2.length()){
    if(s1[i1] == ',')
      i1++;
    if(s2[i2] == ',')
      i2++;
    if(s1[i1] == '['){
      if(s2[i2] == '['){
        int compare = before(getArray(s1, i1), getArray(s2, i2));
        if(compare != 0)
          return compare;
      } else {
        string ns2 = "";
        ns2 += s2[i2];
        if(ns2 == "1" && s2[i2+1] == '0'){
          i2++;
          ns2 += s2[i2];
        }
        int compare = before(getArray(s1, i1), ns2);
        i2++;
        if(compare != 0)
          return compare;
      }
    } else if(s2[i2] == '['){
      string ns1 = "";
      ns1 += s1[i1];
      if(ns1 == "1" && s1[i1+1] == '0'){
        i1++;
        ns1 += s1[i1];
      }
      int compare = before(ns1, getArray(s2, i2));
      i1++;
      if(compare != 0)
        return compare;
    } else {
      bool s1is10 = s1[i1] == '1' && s1[i1+1] == '0', s2is10 = s2[i2] == '1' && s2[i2+1] == '0';
      if(s1is10){
        if(!s2is10)
          return 1;
        else
          i2++;
        i1++;
      } else if(s2is10){
        return -1;
      } else {
        if(s1[i1] < s2[i2])
          return -1;
        if(s1[i1] > s2[i2])
          return 1;
      }
      i1++;
      i2++;
    }
  }
  if(i1 != s1.length())
    return 1;
  if(i2 != s2.length())
    return -1;
  return 0;
}

string getArray(string s, int& index){
  string newS = "";
  int openBrackets = 0;
  int initialIndex = index;
  for(; openBrackets > 0 || index == initialIndex; index++){
    if(s[index] == '['){
      if(openBrackets != 0)
        newS += s[index];
      openBrackets++;
    } else if(s[index] == ']'){
      if(openBrackets != 1)
        newS += s[index];
      openBrackets--;
    } else
      newS += s[index];
  }
  return newS;
}