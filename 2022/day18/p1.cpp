#include <iostream>
#include <fstream>
#include <vector>
using namespace std;

struct Point {
  int x, y, z;
};

void checkDir(bool***, Point, int&);

int main(){
  bool*** grid = new bool**[22];
  for(int i = 0; i < 22; i++){
    grid[i] = new bool*[22];
    for(int j = 0; j < 22; j++){
      grid[i][j] = new bool[22];
      for(int k = 0; k < 22; k++)
        grid[i][j][k] = false;
    }
  }
  ifstream fin("input.txt");
  Point p;
  char c;
  vector<Point> visited;
  while(fin >> p.x >> c >> p.y >> c >> p.z){
    grid[p.x+1][p.y+1][p.z+1] = true;
    visited.push_back({p.x+1, p.y+1, p.z+1});
  }
  int openSides = 0;
  for(int i = 0; i < visited.size(); i++){
    Point curr = visited[i];
    checkDir(grid, curr, openSides);
  }
  cout << openSides << endl;
  for(int i = 0; i < 21; i++){
    for(int j = 0; j < 21; j++)
      delete [] grid[i][j];
    delete [] grid[i];
  }
  delete [] grid;
  return 0;
}

void checkDir(bool*** grid, Point p, int& sides){
  if(!grid[p.x-1][p.y][p.z])
    sides++;
  if(!grid[p.x+1][p.y][p.z])
    sides++;
  if(!grid[p.x][p.y-1][p.z])
    sides++;
  if(!grid[p.x][p.y+1][p.z])
    sides++;
  if(!grid[p.x][p.y][p.z-1])
    sides++;
  if(!grid[p.x][p.y][p.z+1])
    sides++;
}