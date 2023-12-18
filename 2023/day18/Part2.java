import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Part2 {

    public class Point {
        long row, col;

        public Point(long r, long c){
            row = r;
            col = c;
        }

        public Point move(char dir, long steps){

            Point p = new Point(row, col);
            switch(dir){
                case '0': p.col += steps; break;
                case '1': p.row += steps; break;
                case '2': p.col -= steps; break;
                case '3': p.row -= steps; break;
            }
            return p;
        }
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            Point trace = new Point(0, 0);
            long result = 0, lines = 0;
            double offset = 0;

            while(scan.hasNextLine()){
                String line = scan.nextLine().split(" ")[2];
                char dir = line.charAt(7);
                long steps = Long.parseLong(line.substring(2, 7), 16);

                Point next = trace.move(dir, steps);
                result += (long)trace.col * next.row;
                result -= (long)trace.row * next.col;
                trace = next;
                offset += (steps-1)/2.0;
                lines++;       
            }
            
            lines /= 2;
            offset += (lines+2)*0.75 + (lines-2)*0.25;
            if(result < 0) result *= -1;
            result /= 2;
            result += offset;
            System.out.println(result);
        } catch(FileNotFoundException e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part2 run = new Part2();
        run.run();
    }
} 