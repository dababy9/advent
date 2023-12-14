#include <iostream>
#include <fstream>
using namespace std;

int score(int, int, int**, int, int);

int main(){
  string s;
  ifstream finNum("input.txt");
  getline(finNum, s);
  int y, x = s.length(), hiScore = 0;
  for(y = 1; getline(finNum, s); y++);
  int** grid = new int*[y];
  for(int i = 0; i < y; i++)
    grid[i] = new int[x];
  ifstream fin("input.txt");
  char c;
  for(int i = 0; i < y; i++)
    for(int j = 0; j < x; j++){
      fin >> c;
      grid[i][j] = c-'0';
    }
  for(int i = 0; i < y; i++)
    for(int j = 0; j < x; j++){
      int currScore = score(i, j, grid, y, x);
      if(currScore > hiScore)
        hiScore = currScore;
    }
  cout << hiScore << endl;
  for(int i = 0; i < y; i++)
    delete [] grid[i];
  delete [] grid;
  return 0;
}

int score(int x, int y, int** grid, int cols, int rows){
  if(x == 0 || y == 0 || x == rows-1 || y == cols-1)
    return 0;
  bool d[4] = {true, true, true, true};
  int nx = x, px = x, ny = y, py = y;
  int curr = grid[y][x];
  while(d[0] || d[1] || d[2] || d[3]){
    if(d[0])
      nx--;
    if(d[1])
      ny--;
    if(d[2])
      px++;
    if(d[3])
      py++;
    if(d[0] && (nx <= 0 || grid[y][nx] >= curr))
      d[0] = false;
    if(d[1] && (ny <= 0 || grid[ny][x] >= curr))
      d[1] = false;
    if(d[2] && (px == rows-1 || grid[y][px] >= curr))
      d[2] = false;
    if(d[3] && (py == cols-1 || grid[py][x] >= curr))
      d[3] = false;
  }
  return (x-nx)*(y-ny)*(px-x)*(py-y);
}