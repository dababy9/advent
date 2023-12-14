#include <iostream>
#include <fstream>
using namespace std;

bool visible(int, int, int**, int, int);

int main(){
  string s;
  ifstream finNum("input.txt");
  getline(finNum, s);
  int y, x = s.length(), total = 0;
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
    for(int j = 0; j < x; j++)
      if(visible(i, j, grid, y, x))
        total++;
  cout << total << endl;
  for(int i = 0; i < y; i++)
    delete [] grid[i];
  delete [] grid;
  return 0;
}

bool visible(int x, int y, int** grid, int cols, int rows){
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
    if(nx < 0 || ny < 0 || px == rows || py == cols)
      return true;
    if(grid[y][nx] >= curr)
      d[0] = false;
    if(grid[ny][x] >= curr)
      d[1] = false;
    if(grid[y][px] >= curr)
      d[2] = false;
    if(grid[py][x] >= curr)
      d[3] = false;
  }
  return false;
}