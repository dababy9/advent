#include <iostream>
#include <fstream>
#include <vector>
using namespace std;

bool s(vector<unsigned char>, vector<unsigned char>&, bool, int);
bool f(vector<unsigned char>, vector<unsigned char>, int);
vector<unsigned char> shift(vector<unsigned char>, int);
vector<vector<unsigned char>> createRocks();

int main(){
  ifstream fin("input.txt");
  string jets;
  fin >> jets;
  int iJ = 0, jL = jets.size();
  vector<vector<unsigned char>> rocks = createRocks();
  vector<unsigned char> tunnel;
  tunnel.push_back(127);
  for(int rock = 0; rock < 2022; rock++){
    vector<unsigned char> fRock = rocks[rock%5];
    int push = 0, d;
    string wind = "";
    for(int i = 0; i < 3; i++, iJ++){
      push += jets[iJ%jL] == '<' ? -1 : 1;
      wind += jets[iJ%jL];
    }
    if(rock%5 == 0 && wind == ">><")
      push = 0;
    fRock = shift(fRock, push);
    for(d = 0; s(tunnel, fRock, jets[iJ%jL] == '>', d) && f(tunnel, fRock, d+1); iJ++, d++);
    iJ++;
    for(int i = 0; i < fRock.size(); i++, d--){
      if(d > 0)
        tunnel[tunnel.size()-d] |= fRock[i];
      else
        tunnel.push_back(fRock[i]);
    }
    if(iJ >= jL)
      iJ %= jL;
    fRock.clear();
  }
  cout << tunnel.size()-1 << endl;
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