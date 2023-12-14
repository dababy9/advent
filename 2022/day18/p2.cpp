#include <iostream>
#include <fstream>
#include <vector>
using namespace std;

struct Point {
  int x, y, z;
};

vector<Point> checkDir(bool***, Point, int&);
bool hasVisited(Point, vector<Point>);

int main(){
  bool*** grid = new bool**[22];
  for(int i = 0; i < 22; i++){
    grid[i] = new bool*[22];
    for(int j = 0; j < 22; j++){
      grid[i][j] = new bool[22];
      for(int k = 0; k < 22; k++)
        grid[i][j][k] = true;
    }
  }
  ifstream fin("input.txt");
  Point p;
  char c;
  while(fin >> p.x >> c >> p.y >> c >> p.z)
    grid[p.x+1][p.y+1][p.z+1] = false;
  vector<Point> visited = {{0, 0, 0}};
  int vID = 0, n = 1, openSides = -2904;
  while(vID != n){
    for(int i = vID; i < n; i++){
      Point curr = visited[i];
      vector<Point> moves = checkDir(grid, curr, openSides);
      for(int j = 0; j < moves.size(); j++)
        if(!hasVisited(moves[j], visited))
          visited.push_back(moves[j]);
      moves.clear();
    }
    vID = n;
    n = visited.size();
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

vector<Point> checkDir(bool*** grid, Point p, int& sides){
  vector<Point> moves;
  if(p.x > 0 && grid[p.x-1][p.y][p.z])
    moves.push_back({p.x-1, p.y, p.z});
  else
    sides++;
  if(p.x < 21 && grid[p.x+1][p.y][p.z])
    moves.push_back({p.x+1, p.y, p.z});
  else
    sides++;
  if(p.y > 0 && grid[p.x][p.y-1][p.z])
    moves.push_back({p.x, p.y-1, p.z});
  else
    sides++;
  if(p.y < 21 && grid[p.x][p.y+1][p.z])
    moves.push_back({p.x, p.y+1, p.z});
  else
    sides++;
  if(p.z > 0 && grid[p.x][p.y][p.z-1])
    moves.push_back({p.x, p.y, p.z-1});
  else
    sides++;
  if(p.z < 21 && grid[p.x][p.y][p.z+1])
    moves.push_back({p.x, p.y, p.z+1});
  else
    sides++;
  return moves;
}

bool hasVisited(Point p, vector<Point> prev){
  for(int i = 0; i < prev.size(); i++)
    if(prev[i].x == p.x && prev[i].y == p.y && prev[i].z == p.z)
      return true;
  return false;
}