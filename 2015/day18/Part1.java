import java.io.File;
import java.util.Scanner;
import java.util.LinkedList;
public class Part1 {

    char[][] grid;

    public class Point {
        int row, col;

        public Point(int r, int c){
            row = r;
            col = c;
        }

        public Point[] neighbors(){
            Point[] result = new Point[8];
            int index = 0;
            for(int r = -1; r <= 1; r++)
                for(int c = -1; c <= 1; c++)
                    if(r != 0 || c != 0)
                        result[index++] = new Point(row+r, col+c);
            return result;
        }
    }

    public void step(){
        char[][] newGrid = new char[grid.length][grid[0].length];
        for(int r = 0; r < grid.length; r++)
            for(int c = 0; c < grid[0].length; c++){
                Point[] n = (new Point(r, c)).neighbors();
                int adjOn = 0;
                for(int i = 0; i < n.length; i++)
                    try { if(grid[n[i].row][n[i].col] == '#') adjOn++; } catch(Exception e){}
                if(grid[r][c] == '#' && (adjOn == 2 || adjOn == 3)) newGrid[r][c] = '#';
                else if(adjOn == 3) newGrid[r][c] = '#';
            }
        grid = newGrid;
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            LinkedList<char[]> list = new LinkedList<>();
            while(scan.hasNextLine())
                list.add(scan.nextLine().toCharArray());
            grid = new char[list.size()][list.get(0).length];
            int index = 0;
            for(char[] c : list)
                grid[index++] = c;
            for(int i = 0; i < 100; i++)
                step();
            int total = 0;
            for(int r = 0; r < grid.length; r++)
                for(int c = 0; c < grid[0].length; c++)
                    if(grid[r][c] == '#') total++;
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