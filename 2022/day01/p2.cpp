#include <iostream>
#include <fstream>
using namespace std;

int toInt(string);
int pow(int, int);

int main(){
  ifstream fin("input.txt");
  int n = 0;
  string s;
  while(fin){
    getline(fin, s);
    if(s == "")
      n++;
  }

  ifstream fin1("input.txt");
  int cals[n];
  for(int i = 0; i < n; i++)
    cals[i] = 0;
  int index = 0;
  while(fin1){
    getline(fin1, s);
    if(s != "")
      cals[index] += toInt(s);
    else
      index++;
  }

  int top[3];

  for(int j = 0; j < 3; j++){
    int imax = 0;
    for(int i = 0; i < n; i++){
      if(cals[i] > cals[imax])
        imax = i;
    }
    top[j] = cals[imax];
    cals[imax] = 0;
  }

  int sum = top[0] + top[1] + top[2];
  cout << sum << endl;

  return 0;
}

int toInt(string s){
  int total = 0;
  for(int i = 1; i <= s.length(); i++)
    total += (s[s.length()-i]-'0')*pow(10, i-1);
  return total;
}

int pow(int base, int exp){
  if(exp == 0)
    return 1;
  return pow(base, exp-1)*base;
}