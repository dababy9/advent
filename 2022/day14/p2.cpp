#include <iostream>
#include <fstream>
#include <vector>
using namespace std;

struct Point {
  int x, y;
};

void parseCoord(string, Point&, int&);
int toInt(string);
void createLine(char**, Point, Point, int);
bool fall(char**, Point);

ostream& operator << (ostream& out, Point p){
  return out << '(' << p.x << ", " << p.y << ')';
}

int main(){
  ifstream finNum("input.txt");
  vector<vector<Point>> lines;
  string line;
  int iLine;
  for(int i = 0; getline(finNum, line); i++){
    lines.push_back({});
    for(iLine = 0; iLine < line.length(); iLine++)
      if(line[iLine] >= '0' && line[iLine] <= '9'){
        Point p;
        parseCoord(line, p, iLine);
        lines[i].push_back(p);
      }
  }
  int maxY = lines[0][0].y;
  for(int i = 0; i < lines.size(); i++)
    for(int j = 0; j < lines[i].size(); j++)
      if(lines[i][j].y > maxY)
        maxY = lines[i][j].y;
  int rows = maxY+3, cols = 2*rows+1;
  int minX = 500-rows;
  char** grid = new char*[rows];
  for(int i = 0; i < rows; i++){
    grid[i] = new char[cols];
    for(int j = 0; j < cols; j++)
      if(i == rows-1)
        grid[i][j] = '#';
      else
        grid[i][j] = '.';
  }
  for(int i = 0; i < lines.size(); i++)
    for(int j = 1; j < lines[i].size(); j++)
      createLine(grid, lines[i][j-1], lines[i][j], minX);
  int numSand;
  for(numSand = 0; fall(grid, {rows, 0}); numSand++);
  cout << numSand+1 << endl;
  for(int i = 0; i < rows; i++)
    delete [] grid[i];
  delete [] grid;
  return 0;
}

void parseCoord(string s, Point& p, int& index){
  string ns = "";
  for(; s[index] != ','; index++)
    ns += s[index];
  p.x = toInt(ns);
  ns = "";
  index++;
  for(; index < s.length() && s[index] != ' '; index++)
    ns += s[index];
  p.y = toInt(ns);
  index++;
}

int pow(int base, int exp){
  if(exp == 0)
    return 1;
  return pow(base, exp-1)*base;
}

int toInt(string s){
  int total = 0;
  for(int i = 1; i <= s.length(); i++)
    total += (s[s.length()-i]-'0')*pow(10, i-1);
  return total;
}

void createLine(char** grid, Point p1, Point p2, int minX){
  if(p1.y == p2.y){
    for(int i = p1.x; i != p2.x; i += (p1.x < p2.x ? 1 : -1))
      grid[p1.y][i-minX] = '#';
    grid[p2.y][p2.x-minX] = '#';
  } else {
    for(int i = p1.y; i != p2.y; i += (p1.y < p2.y ? 1 : -1))
      grid[i][p1.x-minX] = '#';
    grid[p2.y][p2.x-minX] = '#';
  }
}

bool fall(char** grid, Point sand){
  bool canFall = true;
  while(canFall){
    if(grid[sand.y+1][sand.x] == '.'){
      grid[sand.y][sand.x] = '.';
      sand.y++;
      grid[sand.y][sand.x] = 'o';
    } else if(grid[sand.y+1][sand.x-1] == '.'){
      grid[sand.y][sand.x] = '.';
      sand.y++;
      sand.x--;
      grid[sand.y][sand.x] = 'o';
    } else if(grid[sand.y+1][sand.x+1] == '.'){
      grid[sand.y][sand.x] = '.';
      sand.y++;
      sand.x++;
      grid[sand.y][sand.x] = 'o';
    } else
      canFall = false;
  }
  if(sand.y == 0)
    return false;
  return true;
}