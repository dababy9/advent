#include <iostream>
#include <fstream>
#include <vector>
using namespace std;

struct Point {
  int x, y;
};

struct Sensor {
  Point pos;
  Point nBeacon;
};

struct Bound {
  int low, hi;
};

int abs(int);
bool addNoDuplicate(int, vector<int>&);
void eliminate(Point, int, int, vector<Bound>&);
bool fix(vector<Bound>&, int, int);

int main(){
  ifstream fin("input.txt");
  vector<Sensor> sensors;
  string s;
  char c;
  int targetRow = 2000000;
  vector<int> positions;
  int beaconsOnTargetRow = 0;
  while(fin >> s >> s >> c >> c){
    Point sensor, beacon;
    fin >> sensor.x >> c >> c >> c >> sensor.y >> c >> s >> s >> s >> s >> c >> c >> beacon.x >> c >> c
      >> c >> beacon.y;
    sensors.push_back({sensor, beacon});
    if(beacon.y == targetRow)
      if(addNoDuplicate(beacon.x, positions))
        beaconsOnTargetRow++;
  }
  vector<Bound> bounds;
  for(int i = 0; i < sensors.size(); i++){
    Point S = sensors[i].pos, B = sensors[i].nBeacon;
    int d = abs(S.x-B.x)+abs(S.y-B.y);
    if(abs(S.y-targetRow) <= d)
      eliminate(S, d, targetRow, bounds);
  }
  for(int i = 0; i < bounds.size(); i++)
    for(int j = 0; j < bounds.size(); j++)
      if(i != j && fix(bounds, i, j)){
        i = 0;
        j = 0;
      }
  int total = 0;
  for(int i = 0; i < bounds.size(); i++)
    total += bounds[i].hi-bounds[i].low;
  cout << total-beaconsOnTargetRow+1 << endl;
  sensors.clear();
  positions.clear();
  bounds.clear();
  return 0;
}

int abs(int a){
  if(a < 0)
    return a*-1;
  return a;
}

bool addNoDuplicate(int n, vector<int>& v){
  for(int i = 0; i < v.size(); i++)
    if(v[i] == n)
      return false;
  v.push_back(n);
  return true;
}

void eliminate(Point S, int d, int targetRow, vector<Bound>& v){
  int vd = abs(S.y-targetRow);
  v.push_back({S.x-(d-vd), S.x+(d-vd)});
}

bool fix(vector<Bound>& bounds, int i1, int i2){
  Bound b1 = bounds[i1], b2 = bounds[i2];
  vector<Bound>::iterator i1Erase = bounds.begin();
    for(int i = 0; i < i1; i++)
      i1Erase++;
  vector<Bound>::iterator i2Erase = bounds.begin();
    for(int i = 0; i < i2; i++)
      i2Erase++;
  if(b1.low <= b2.low && b1.hi >= b2.hi){
    bounds.erase(i2Erase);
    return true;
  }
  if(b2.low <= b1.low && b2.hi >= b1.hi){
    bounds.erase(i1Erase);
    return true;
  }
  if(b1.hi >= b2.low && b1.low < b2.hi && b1.low < b2.low){
    bounds[i1] = {b1.low, b2.hi};
    bounds.erase(i2Erase);
    return true;
  }
  return false;
}