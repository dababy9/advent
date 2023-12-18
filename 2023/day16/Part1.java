import java.io.File;
import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;
public class Part1 {

    HashSet<Beam> beamStates = new HashSet<>();

    public class Beam {
        public static int rowBound, colBound;
        public static ArrayList<Beam> beams = new ArrayList<>();
        
        public int row, col;
        public char dir;

        public Beam(int r, int c, char d){
            row = r;
            col = c;
            dir = d;
        }

        public static void setBounds(int rBound, int cBound){
            rowBound = rBound;
            colBound = cBound;
        }

        public boolean move(char[][] grid, boolean[][] energized){
            try {
                switch(dir){
                    case 'N': row--; break;
                    case 'S': row++; break;
                    case 'W': col--; break;
                    case 'E': col++; break;
                }
                energized[row][col] = true;
                switch(grid[row][col]){
                    case '/': {
                        switch(dir){
                            case 'N': dir = 'E'; break;
                            case 'S': dir = 'W'; break;
                            case 'W': dir = 'S'; break;
                            case 'E': dir = 'N'; break;
                        }
                        break;
                    } case '\\': {
                        switch(dir){
                            case 'N': dir = 'W'; break;
                            case 'S': dir = 'E'; break;
                            case 'W': dir = 'N'; break;
                            case 'E': dir = 'S'; break;
                        }
                        break;
                    } case '|': {
                        if(dir == 'W' || dir == 'E'){
                            dir = 'N';
                            Beam.beams.add(new Beam(row, col, 'S'));
                        }
                        break;
                    } case '-': {
                        if(dir == 'N' || dir == 'S'){
                            dir = 'W';
                            Beam.beams.add(new Beam(row, col, 'E'));
                        }
                        break;
                    }
                }
                return true;
            } catch(Exception e){ return false; }
        }

        @Override
        public boolean equals(Object o){
            if(o == null || getClass() != o.getClass()) return false;
            Beam b = (Beam)o;
            return row == b.row && col == b.col && dir == b.dir;
        }

        @Override
        public int hashCode(){
            int hash = 7;
            hash = 71 * hash + row;
            hash = 71 * hash + col;
            return hash + dir;
        }
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            ArrayList<char[]> oldGrid = new ArrayList<>();
            while(scan.hasNextLine())
                oldGrid.add(scan.nextLine().toCharArray());
            char[][] grid = new char[oldGrid.size()][oldGrid.get(0).length];
            boolean[][] energized = new boolean[grid.length][grid[0].length];
            for(int i = 0; i < oldGrid.size(); i++)
                grid[i] = oldGrid.get(i);
            Beam.beams.add(new Beam(0, -1, 'E'));
            boolean propogating = true;
            while(propogating){
                ArrayList<Beam> currBeams = Beam.beams;
                Beam.beams = new ArrayList<>();
                for(Beam b : currBeams)
                    if(b.move(grid, energized) && !beamStates.contains(b)){
                        Beam.beams.add(b);
                        beamStates.add(new Beam(b.row, b.col, b.dir));
                    }
                propogating = Beam.beams.size() > 0;
            }
            int sum = 0;
            for(int i = 0; i < energized.length; i++)
                for(int j = 0; j < energized[0].length; j++)
                    if(energized[i][j]) sum++;
            System.out.println(sum);
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part1 run = new Part1();
        run.run();
    }
} 