import java.io.File;
import java.util.Scanner;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
public class Part1 {

    HashSet<Point> visited = new HashSet<>();
    int minRow = Integer.MAX_VALUE, minCol = Integer.MAX_VALUE, maxRow = Integer.MIN_VALUE, maxCol = Integer.MIN_VALUE;

    public class Point {
        int row, col;

        public Point(int r, int c){
            row = r;
            col = c;
        }

        public Point move(char dir){
            Point p = new Point(row, col);
            switch(dir){
                case 'U': p.row--; break;
                case 'D': p.row++; break;
                case 'L': p.col--; break;
                case 'R': p.col++; break;
            }
            return p;
        }

        public LinkedList<Point> neighbors(){
            LinkedList<Point> list = new LinkedList<>();
            list.add(new Point(row-1, col));
            list.add(new Point(row+1, col));
            list.add(new Point(row, col-1));
            list.add(new Point(row, col+1));
            return list;
        }

        @Override
        public boolean equals(Object o){
            if(o == null || getClass() != o.getClass()) return false;
            Point p = (Point)o;
            return row == p.row && col == p.col;
        }

        @Override
        public int hashCode(){
            int hash = 7;
            hash = 71 * hash + row;
            hash = 71 * hash + col;
            return hash;
        }
    }

    public boolean inside(Point p){
        int crosses = 0;
        for(int col = p.col; col >= minCol; col--)
            if(visited.contains(new Point(p.row, col)))
                crosses++;
        return crosses == 1;
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            Point trace = new Point(0, 0);
            while(scan.hasNextLine()){
                String[] line = scan.nextLine().split(" ");
                int steps = Integer.parseInt(line[1]);
                char dir = line[0].charAt(0);
                for(int i = 0; i < steps; i++){
                    trace = trace.move(dir);
                    visited.add(new Point(trace.row, trace.col));
                }
            }
            for(Point p : visited){
                if(p.row < minRow) minRow = p.row;
                if(p.row > maxRow) maxRow = p.row;
                if(p.col < minCol) minCol = p.col;
                if(p.col > maxCol) maxCol = p.col;
            }
            Random r = new Random();
            Point rand;
            for(rand = new Point(r.nextInt(maxRow-minRow) + minRow, r.nextInt(maxCol-minCol) + minCol); visited.contains(rand) || !inside(rand); rand = new Point(r.nextInt(maxRow-minRow) + minRow, r.nextInt(maxCol-minCol) + minCol));
            HashSet<Point> filled = new HashSet<>();
            LinkedList<Point> fringe = new LinkedList<>();
            fringe.add(rand);
            while(!fringe.isEmpty()){
                Point curr = fringe.poll();
                if(!visited.contains(curr) && !filled.contains(curr)){
                    filled.add(curr);
                    fringe.addAll(curr.neighbors());
                }
            }
            int total = visited.size() + filled.size();
            System.out.println(total);
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part1 run = new Part1();
        run.run();
    }
} 