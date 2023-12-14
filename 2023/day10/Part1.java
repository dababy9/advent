import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
public class Part1 {

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
            for(int i = 0; i < 4 && curr == null; i++){
                char c = 0;
                Point p = new Point(iPoint.row + starts[i].row, iPoint.col + starts[i].col);
                try {
                    c = map.get(p.row).charAt(p.col);
                    switch(i){
                        case 0: if(c == '-' || c == 'J' || c == '7') curr = p; break;
                        case 1: if(c == '|' || c == 'F' || c == '7') curr = p; break;
                        case 2: if(c == '-' || c == 'F' || c == 'L') curr = p; break;
                        case 3: if(c == '|' || c == 'J' || c == 'L') curr = p; break;
                    }
                } catch(Exception e){ continue; }
            }
            boolean found = false;
            HashSet<Point> visited = new HashSet<>();
            int steps;
            for(steps = 1; !found; steps++){
                visited.add(curr);
                Point temp = curr;
                curr = nextPoint(curr, prev, map);
                prev = temp;
                if(curr.equals(iPoint)) found = true;
            }
            System.out.println(steps/2);
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part1 run = new Part1();
        run.run();
    }

} 