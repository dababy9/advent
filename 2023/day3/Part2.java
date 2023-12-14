import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.TreeMap;
public class Part2 {

    public class Point implements Comparable<Point> {
        public int x, y;
        public Point(int xx, int yy){
            x = xx;
            y = yy;
        }
        public int compareTo(Point p){
            if(x == p.x){
                if(y == p.y) return 0;
                return y - p.y;
            }
            return x - p.x;
        }
    }

    public void run(){
        ArrayList<String> list = new ArrayList<>();
        TreeMap<Point, Integer> singleMap = new TreeMap<>();
        TreeMap<Point, Integer> doubleMap = new TreeMap<>();
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            while(scan.hasNextLine())
                list.add(scan.nextLine());
        } catch(Exception e){
            System.out.println("File does not exist.");
        }

        int sum = 0;
        for(int row = 0; row < list.size(); row++){
            String s = list.get(row);
            for(int col = 0; col < s.length(); col++){
                if(s.charAt(col) < 48 || s.charAt(col) > 57)
                    continue;
                int stop;
                for(stop = col+1; stop < s.length() && s.charAt(stop) >= 48 && s.charAt(stop) <= 57; stop++);
                int num = Integer.parseInt(s.substring(col, stop));
                if(row != 0){
                    int index = list.get(row-1).substring((col == 0 ? col : col-1), (stop == s.length() ? stop : stop+1)).indexOf('*');
                    if(index != -1)
                        tryPut(singleMap, doubleMap, num, new Point(index+(col == 0 ? col : col-1), row-1));
                }
                if(row != list.size()-1){
                    int index = list.get(row+1).substring((col == 0 ? col : col-1), (stop == s.length() ? stop : stop+1)).indexOf('*');
                    if(index != -1)
                        tryPut(singleMap, doubleMap, num, new Point(index+(col == 0 ? col : col-1), row+1));
                }
                if(col != 0 && s.charAt(col-1) == '*') tryPut(singleMap, doubleMap, num, new Point(col-1, row));
                if(stop != s.length() && s.charAt(stop) == '*') tryPut(singleMap, doubleMap, num, new Point(stop, row));
                col = stop;
            }
        }
        for(int num : doubleMap.values())
            sum += num;
        System.out.println(sum);
    }

    public void tryPut(TreeMap<Point, Integer> singleMap, TreeMap<Point, Integer> doubleMap, int num, Point p){
        if(doubleMap.containsKey(p)){
            doubleMap.remove(p);
            return;
        }
        if(!singleMap.containsKey(p))
            singleMap.put(p, num);
        else
            doubleMap.put(p, num*singleMap.get(p));
    }

    public static void main(String[] args){
        Part2 run = new Part2();
        run.run();
    }
} 