#include <iostream>
#include <fstream>
#include <vector>
using namespace std;

struct Rock {
  int nRock, height;
  vector<unsigned char> top;
};

bool s(vector<unsigned char>, vector<unsigned char>&, bool, int);
bool f(vector<unsigned char>, vector<unsigned char>, int);
vector<unsigned char> shift(vector<unsigned char>, int);
vector<vector<unsigned char>> createRocks();

int main(){
  ifstream fin("input.txt");
  string j;
  fin >> j;
  int iJ = 0, jL = j.size();
  vector<vector<unsigned char>> rocks = createRocks();
  vector<unsigned char> tunnel;
  vector<Rock> RockList;
  tunnel.push_back(127);
  Rock r1 = {-1, -1, {}}, r2;
  for(int rock = 0; rock < 3000 && r1.nRock == -1; rock++){
    vector<unsigned char> fRock = rocks[rock%5];
    int push = 0, d = 0;
    string wind = "";
    for(int i = 0; i < 3; i++, iJ++){
      push += j[iJ%jL] == '<' ? -1 : 1;
      wind += j[iJ%jL];
    }
    if(rock%5 == 0 && wind == ">><")
      push = 0;
    fRock = shift(fRock, push);
    for(; s(tunnel, fRock, j[iJ%jL] == '>', d) && f(tunnel, fRock, d+1); d++, wind += j[iJ%jL], iJ++);
    wind += j[iJ%jL];
    iJ++;
    int t = tunnel.size();
    for(int i = 0; i < fRock.size(); i++, d--){
      if(d > 0)
        tunnel[t-d] |= fRock[i];
      else
        tunnel.push_back(fRock[i]);
    }
    if(iJ >= jL)
      iJ %= jL;
    fRock.clear();
    if(rock > 20){
      vector<unsigned char> top;
      for(int i = t-18; i < t; i++)
        top.push_back(tunnel[i]);
      Rock r = {rock, t-1, top};
      RockList.push_back(r);
      top.clear();
      for(int i = 0; i < RockList.size()-1; i++)
        if(r.nRock%5 == RockList[i].nRock%5 && r.top == RockList[i].top){
          r1 = RockList[i];
          r2 = r;
          break;
        }
    }
  }
  int h1 = r1.height, period = r2.nRock-r1.nRock;
  long long int h2 = (r2.height-r1.height)*((1000000000000-r1.nRock)/period);
  int roundsLeft = (1000000000000-r1.nRock)%period;
  int h3 = RockList[r1.nRock+roundsLeft-21].height-r1.height;
  cout << h1+h2+h3 << endl;
  return 0;
}

bool s(vector<unsigned char> tunnel, vector<unsigned char>& fRock, bool dir, int d){
  vector<unsigned char> fRockS = shift(fRock, dir ? 1 : -1);
  for(int i = tunnel.size()-d, j = 0; i < tunnel.size() && j < fRockS.size(); i++, j++)
    if((tunnel[i]&fRockS[j]) != 0)
      return true;
  fRock.clear();
  fRock = fRockS;
  return true;
}

bool f(vector<unsigned char> tunnel, vector<unsigned char> fRock, int d){
  for(int i = tunnel.size()-d, j = 0; i < tunnel.size() && j < fRock.size(); i++, j++)
    if((tunnel[i]&fRock[j]) != 0)
      return false;
  return true;
}

vector<unsigned char> shift(vector<unsigned char> c, int n){
  if(n == 0)
    return c;
  if(n < 0){
    for(int i = 0; i < c.size(); i++)
      if(c[i] >= 64)
        return c;
    for(int i = 0; i < c.size(); i++)
      c[i] = c[i]<<1;
    return shift(c, n+1);
  } else {
    for(int i = 0; i < c.size(); i++)
      if(c[i]%2 != 0)
        return c;
    for(int i = 0; i < c.size(); i++)
      c[i] = c[i]>>1;
    return shift(c, n-1);
  }
}

vector<vector<unsigned char>> createRocks(){
  return {{30}, {8, 28, 8}, {28, 4, 4}, {16, 16, 16, 16}, {24, 24}};
}