import java.io.File;
import java.util.Scanner;
import java.util.HashSet;
public class Part1 {

    public class Point {
        public int x, y;

        public Point(int xx, int yy){
            x = xx;
            y = yy;
        }

        @Override
        public boolean equals(Object o){
            if(o == null || getClass() != o.getClass()) return false;
            Point p = (Point)o;
            return x == p.x && y == p.y;
        }

        @Override
        public int hashCode(){
            int hash = 7;
            hash = 71 * hash + x;
            hash = 71 * hash + y;
            return hash;
        }

        public Point newPoint(char c){
            Point p = null;
            switch(c){
                case '<': p = new Point(x-1, y); break;
                case '>': p = new Point(x+1, y); break;
                case 'v': p = new Point(x, y+1); break;
                case '^': p = new Point(x, y-1); break;
            }
            return p;
        }
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            HashSet<Point> set = new HashSet<>();
            char[] c = scan.nextLine().toCharArray();
            Point p = new Point(0, 0);
            set.add(p);
            for(int i = 0; i < c.length; i++){
                p = p.newPoint(c[i]);
                set.add(p);
            }
            System.out.println(set.size());
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part1 run = new Part1();
        run.run();
    }
} 