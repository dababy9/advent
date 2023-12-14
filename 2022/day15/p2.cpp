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

int calcBounds(vector<Sensor>, int);
int abs(int);
void addNoDuplicate(Point, vector<Point>&);
void eliminate(Point, int, int, vector<Bound>&);
bool fix(vector<Bound>&, int, int);

int main(){
  ifstream fin("input.txt");
  vector<Sensor> sensors;
  string s;
  char c;
  vector<Point> bPositions;
  while(fin >> s >> s >> c >> c){
    Point sensor, beacon;
    fin >> sensor.x >> c >> c >> c >> sensor.y >> c >> s >> s >> s >> s >> c >> c >> beacon.x >> c >> c
      >> c >> beacon.y;
    sensors.push_back({sensor, beacon});
    addNoDuplicate(beacon, bPositions);
  }
  int maxCoord = 4000000;
  for(int i = 0; i <= maxCoord; i++){
    int xCoord = calcBounds(sensors, i);
    if(xCoord != -1){
      long long int total = (long long)(xCoord)*4000000;
      total += i;
      cout << total << endl;
      break;
    }
  }
  sensors.clear();
  bPositions.clear();
  return 0;
}

int calcBounds(vector<Sensor> sensors, int targetRow){
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
  if(bounds.size() == 1)
    return -1;
  if(bounds[0].low > bounds[1].low)
    return bounds[1].hi+1;
  return bounds[0].hi+1;
}

int abs(int a){
  if(a < 0)
    return a*-1;
  return a;
}

void addNoDuplicate(Point p, vector<Point>& v){
  for(int i = 0; i < v.size(); i++)
    if(v[i].x == p.x && v[i].y == p.y)
      return;
  v.push_back(p);
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
  if(b1.hi >= b2.low-1 && b1.low < b2.hi && b1.low < b2.low){
    bounds[i1] = {b1.low, b2.hi};
    bounds.erase(i2Erase);
    return true;
  }
  return false;
}