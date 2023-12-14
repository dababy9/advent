#include <iostream>
#include <fstream>
#include <vector>
using namespace std;

struct Point {
  int x, y;
};

void pathfind(vector<string>, Point, Point);
vector<Point> checkDir(vector<string>, Point);
bool hasVisited(Point, vector<Point>);

ostream& operator << (ostream& out, Point p){
  return out << '(' << p.x << ", " << p.y << ')';
}
bool operator == (Point p1, Point p2){
  return p1.x == p2.x && p1.y == p2.y;
}

int main(){
  ifstream fin("input.txt");
  string s;
  vector<string> map;
  while(fin >> s)
    map.push_back(s);
  Point start, finish;
  for(int y = 0; y < map.size(); y++)
    for(int x = 0; x < map[0].length(); x++){
      if(map[y][x] == 'E'){
        finish = {x, y};
        map[y][x] = 'z';
      }
      if(map[y][x] == 'S'){
        start = {x, y};
        map[y][x] = 'a';
      }
    }
  pathfind(map, finish, start);
  return 0;
}

void pathfind(vector<string> map, Point f, Point s){
  bool found = false;
  vector<Point> visited = {s};
  vector<bool> checked = {false};
  for(int steps = 1; !found; steps++){
    int num = visited.size();
    for(int i = 0; i < num; i++){
      if(!checked[i]){
        checked[i] = true;
        Point curr = visited[i];
        vector<Point> moves;
        moves = checkDir(map, curr);
        for(int j = 0; j < moves.size(); j++){
          if(moves[j] == f){
            cout << steps << endl;
            found = true;
            visited.clear();
          }
          if(!found && !hasVisited(moves[j], visited)){
            visited.push_back(moves[j]);
            checked.push_back(false);
          }
        }
        moves.clear();
      }
    }
  }
}

vector<Point> checkDir(vector<string> map, Point curr){
  vector<Point> moves;
  char currC = map[curr.y][curr.x];
  if(curr.x-1 >= 0 && map[curr.y][curr.x-1] <= currC+1)
    moves.push_back({curr.x-1, curr.y});
  if(curr.x+1 < map[0].length() && map[curr.y][curr.x+1] <= currC+1)
    moves.push_back({curr.x+1, curr.y});
  if(curr.y-1 >= 0 && map[curr.y-1][curr.x] <= currC+1)
    moves.push_back({curr.x, curr.y-1});
  if(curr.y+1 < map.size() && map[curr.y+1][curr.x] <= currC+1)
    moves.push_back({curr.x, curr.y+1});
  return moves;
}

bool hasVisited(Point curr, vector<Point> prevLocs){
  for(int i = 0; i < prevLocs.size(); i++)
    if(prevLocs[i] == curr)
      return true;
  return false;
}