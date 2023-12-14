import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
public class Part2 {

    public int rowBound, colBound = -1;

    public class Point {
        public int row, col;

        public Point(int r, int c){
            row = r;
            col = c;
        }

        public Point(Point p){
            row = p.row;
            col = p.col;
        }

        public char dirTo(Point p){
            if(equals(p) || (row != p.row && col != p.col)) return 0;
            if(row < p.row) return 'S';
            if(row > p.row) return 'N';
            if(col < p.col) return 'E';
            if(col > p.col) return 'W';
            return 0;
        }

        public boolean equals(Object obj){
            if(obj == null || getClass() != obj.getClass()) return false;
            Point p = (Point)obj;
            return row == p.row && col == p.col;
        }

        public int hashCode(){
            final int prime = 31;
            int result = 1;
            result = prime * result + row;
            result = prime * result + col;
            return result;
        }

        public String toString(){
            return "(row: " + row + ", col: " + col + ")";
        }
    }

    public Point nextPoint(Point curr, Point prev, ArrayList<String> map){
        char from = curr.dirTo(prev);
        char c = map.get(curr.row).charAt(curr.col);
        Point next = new Point(curr);
        switch(c){
            case '|':
                if(from == 'N') next.row++;
                else next.row--;
                break;
            case '-':
                if(from == 'W') next.col++;
                else next.col--;
                break;
            case 'L':
                if(from == 'N') next.col++;
                else next.row--;
                break;
            case 'J':
                if(from == 'N') next.col--;
                else next.row--;
                break;
            case '7':
                if(from == 'S') next.col--;
                else next.row++;
                break;
            case 'F':
                if(from == 'S') next.col++;
                else next.row++;
                break;
        }
        return next;
    }

    public int floodFill(Point initP, ArrayList<String> map, HashSet<Point> visited, HashSet<Point> discovered){
        LinkedList<Point> fringe = new LinkedList<>();
        int pointsProcessed = 0;
        fringe.add(initP);
        while(!fringe.isEmpty()){
            pointsProcessed++;
            Point p = fringe.poll();
            discovered.add(p);
            int[][] d = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
            for(int i = 0; i < 4; i++){
                Point newP = new Point(initP.row + d[i][0], initP.col + d[i][1]);
                if(!visited.contains(newP) && !discovered.contains(newP) && !fringe.contains(newP) && newP.row >=0 && newP.col >= 0 && newP.row < rowBound && newP.col < colBound) fringe.add(newP);
            }
        }
        return pointsProcessed;
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            ArrayList<String> map = new ArrayList<>();
            Point iPoint = null;
            for(int i = 0; scan.hasNextLine(); i++){
                String s = scan.nextLine();
                for(int j = 0; j < s.length(); j++)
                    if(s.charAt(j) == 'S') iPoint = new Point(i, j);
                map.add(s);
            }
            rowBound = map.size();
            colBound = map.get(0).length();
            Point[] starts = new Point[]{new Point(0, 1), new Point(-1, 0), new Point(0, -1), new Point(1, 0)};
            Point curr = null, prev = new Point(iPoint);
            char dir1 = 0, dir2 = 0;
            for(int i = 0; i < 4; i++){
                char c = 0;
                Point p = new Point(iPoint.row + starts[i].row, iPoint.col + starts[i].col);
                try {
                    c = map.get(p.row).charAt(p.col);
                    switch(i){
                        case 0: if(c == '-' || c == 'J' || c == '7') curr = p; dir2 = dir1; dir1 = 'E'; break;
                        case 1: if(c == '|' || c == 'F' || c == '7') curr = p; dir2 = dir1; dir1 = 'N'; break;
                        case 2: if(c == '-' || c == 'F' || c == 'L') curr = p; dir2 = dir1; dir1 = 'W'; break;
                        case 3: if(c == '|' || c == 'J' || c == 'L') curr = p; dir2 = dir1; dir1 = 'S'; break;
                    }
                } catch(Exception e){ continue; }
            }
            char newS = 'S';
            switch(dir1){
                case 'N':  newS = 'L'; break;
                case 'W': if(dir2 == 'N') newS = 'J'; else newS = '-'; break;
                case 'S': if(dir2 == 'W') newS = '7'; else if(dir2 == 'N') newS = '|'; else newS = 'F'; break;
            }
            boolean found = false;
            HashSet<Point> visited = new HashSet<>();
            visited.add(iPoint);
            while(!found){
                visited.add(curr);
                Point temp = curr;
                curr = nextPoint(curr, prev, map);
                prev = temp;
                if(curr.equals(iPoint)) found = true;
            }
            int enclosed = 0;
            HashSet<Point> discovered = new HashSet<>();
            for(int r = 0; r < rowBound; r++){
                for(int c = 0; c < colBound; c++){
                    Point p = new Point(r, c);
                    if(visited.contains(p) || discovered.contains(p)) continue;
                    int crosses = 0;
                    char prevCross = 0;
                    for(int col = p.col; col < colBound; col++)
                        if(visited.contains(new Point(p.row, col))){
                            char currCross = map.get(p.row).charAt(col);
                            if(iPoint.equals(new Point(p.row, col))) currCross = newS;
                            switch(currCross){
                                case '|': crosses++; break;
                                case 'L': prevCross = currCross; break;
                                case 'F': prevCross = currCross; break;
                                case 'J': if(prevCross == 'F') crosses++; break;
                                case '7': if(prevCross == 'L') crosses++; break;
                            }
                        }
                    if(crosses % 2 == 1)
                        enclosed += floodFill(p, map, visited, discovered);
                    else
                        floodFill(p, map, visited, discovered);
                }
            }
            System.out.println(enclosed);
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part2 run = new Part2();
        run.run();
    }

} 