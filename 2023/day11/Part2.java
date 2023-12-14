import java.io.File;
import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.TreeMap;
public class Part2 {

    public class Point {
        public int row, col;

        public Point(int r, int c){
            row = r;
            col = c;
        }

        public boolean equals(Point p){
            return row == p.row && col == p.col;
        }
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            ArrayList<String> map = new ArrayList<>();
            HashSet<Integer> nonEmptyCols = new HashSet<>();
            HashSet<Integer> emptyRows = new HashSet<>();
            for(int row = 0; scan.hasNextLine(); row++){
                String s = scan.nextLine();
                map.add(s);
                boolean empty = true;
                for(int i = 0; i < s.length(); i++)
                    if(s.charAt(i) != '.'){
                        empty = false;
                        nonEmptyCols.add(i);
                    }
                if(empty) emptyRows.add(row);
            }
            HashSet<Integer> emptyCols = new HashSet<>();
            for(int i = 0; i < map.get(0).length(); i++)
                if(!nonEmptyCols.contains(i)) emptyCols.add(i);
            ArrayList<Point> galaxies = new ArrayList<>();
            for(int row = 0; row < map.size(); row++)
                for(int col = 0; col < map.get(row).length(); col++)
                    if(map.get(row).charAt(col) != '.') galaxies.add(new Point(row, col));
            long sum = 0;
            for(int i = 0; i < galaxies.size()-1; i++)
                for(int j = i+1; j < galaxies.size(); j++){
                    int minRow = Math.min(galaxies.get(i).row, galaxies.get(j).row), maxRow = Math.max(galaxies.get(i).row, galaxies.get(j).row);
                    int minCol = Math.min(galaxies.get(i).col, galaxies.get(j).col), maxCol = Math.max(galaxies.get(i).col, galaxies.get(j).col);
                    for(int row = minRow; row < maxRow; row++)
                        sum += (emptyRows.contains(row) ? 1000000 : 1);
                    for(int col = minCol; col < maxCol; col++)
                        sum += (emptyCols.contains(col) ? 1000000 : 1);
                }
            System.out.println(sum);
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part2 run = new Part2();
        run.run();
    }
} 