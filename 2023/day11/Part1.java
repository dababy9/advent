import java.io.File;
import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.TreeMap;
public class Part1 {

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
            while(scan.hasNextLine()){
                String s = scan.nextLine();
                map.add(s);
                boolean empty = true;
                for(int i = 0; i < s.length(); i++)
                    if(s.charAt(i) != '.'){
                        empty = false;
                        nonEmptyCols.add(i);
                    }
                if(empty) map.add(s);
            }
            ArrayList<String> newMap = new ArrayList<>();
            for(int row = 0; row < map.size(); row++){
                String newS = "";
                for(int i = 0; i < map.get(row).length(); i++){
                    newS += map.get(row).charAt(i);
                    if(!nonEmptyCols.contains(i)){
                        newS += '.';
                    }
                }
                newMap.add(newS);
            }
            map = newMap;
            ArrayList<Point> galaxies = new ArrayList<>();
            for(int row = 0; row < map.size(); row++)
                for(int col = 0; col < map.get(row).length(); col++)
                    if(map.get(row).charAt(col) != '.') galaxies.add(new Point(row, col));
            int sum = 0;
            for(int i = 0; i < galaxies.size()-1; i++)
                for(int j = i+1; j < galaxies.size(); j++)
                    sum += Math.abs(galaxies.get(i).row - galaxies.get(j).row) + Math.abs(galaxies.get(i).col - galaxies.get(j).col);
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