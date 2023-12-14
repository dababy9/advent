#include <iostream>
#include <fstream>
#include <vector>
using namespace std;

struct Monkey {
  vector<long long int> items;
  int inspections = 0;
  int t;
  int f;
};

void round(int, Monkey*);
void operation(int, long long int&);
bool test(int, long long int);

int main(){
  ifstream fin("input.txt");
  Monkey* m = new Monkey[8];
  for(int i = 0; i < 8; i++){
    string s;
    fin >> s >> s >> s >> s;
    char c;
    long long int item;
    while(fin >> item >> c && c == ',')
      m[i].items.push_back(item);
    m[i].items.push_back(item);
    int monkeyID;
    while(fin >> s && s != "monkey");
    fin >> monkeyID;
    m[i].t = monkeyID;
    fin >> s >> s >> s >> s >> s >> monkeyID;
    m[i].f = monkeyID;
  }
  for(int i = 0; i < 10000; i++){
    for(int j = 0; j < 8; j++){
      round(j, m);
      m[j].inspections += m[j].items.size();
      m[j].items.clear();
    }
  }
  vector<int> hi;
  for(int t = 0; t < 2; t++){
    int imax = 0;
    for(int i = 0; i < 8; i++)
      if(m[i].inspections > m[imax].inspections)
        imax = i;
    hi.push_back(m[imax].inspections);
    m[imax].inspections = 0;
  }
  cout << (long int)(hi[0])*hi[1] << endl;
  hi.clear();
  for(int i = 0; i < 8; i++)
    m[i].items.clear();
  delete [] m;
  return 0;
}

void round(int mi, Monkey* m){
  for(int i = 0; i < m[mi].items.size(); i++){
    operation(mi, m[mi].items[i]);
    if(test(mi, m[mi].items[i]))
      m[m[mi].t].items.push_back(m[mi].items[i]);
    else
      m[m[mi].f].items.push_back(m[mi].items[i]);
  }
}

void operation(int monkey, long long int& item){
  if(monkey == 0)
    item *= 5;
  if(monkey == 1)
    item = item*item;
  if(monkey == 2)
    item *= 7;
  if(monkey == 3)
    item++;
  if(monkey == 4)
    item += 3;
  if(monkey == 5)
    item += 5;
  if(monkey == 6)
    item += 8;
  if(monkey == 7)
    item += 2;
  bool flag = false;
  item = item%9699690;
}

bool test(int monkey, long long int item){
  if(monkey == 0)
    return item%11 == 0;
  if(monkey == 1)
    return item%2 == 0;
  if(monkey == 2)
    return item%5 == 0;
  if(monkey == 3)
    return item%17 == 0;
  if(monkey == 4)
    return item%19 == 0;
  if(monkey == 5)
    return item%7 == 0;
  if(monkey == 6)
    return item%3 == 0;
  return item%13 == 0;
}