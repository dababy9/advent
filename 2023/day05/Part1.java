import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
public class Part1 {

    public class MapFunction {
        private ArrayList<SingleFunction> functions = new ArrayList<>();

        public MapFunction(ArrayList<String> list){
            for(String s : list)
                functions.add(new SingleFunction(s.split(" ")));
        }

        public long mapNum(long num){
            for(SingleFunction f : functions)
                if(f.contains(num))
                    return f.map(num);
            return num;
        }
    }

    public class SingleFunction {
        private long srcStart, subtract, range;

        public SingleFunction(String[] s){
            srcStart = Long.parseLong(s[1]);
            subtract = srcStart - Long.parseLong(s[0]);
            range = Long.parseLong(s[2]);
        }

        public boolean contains(long num){ return num >= srcStart && num < srcStart+range; }

        public long map(long num){
            if(!contains(num)) return num;
            return num-subtract;
        }
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            ArrayList<MapFunction> mapList = new ArrayList<>();
            ArrayList<String> mapValues = new ArrayList<>();
            String[] seeds = scan.nextLine().split(" ", 2)[1].split(" ");
            scan.nextLine();
            while(true){
                String s = scan.nextLine();
                for(s = scan.nextLine(); !s.equals("") && scan.hasNextLine(); s = scan.nextLine())
                    mapValues.add(s);
                mapList.add(new MapFunction(mapValues));
                mapValues.clear();
                if(!scan.hasNextLine()) break;
            }
            long min = Long.MAX_VALUE;
            for(int i = 0; i < seeds.length; i++){
                long curr = Long.parseLong(seeds[i]);
                for(MapFunction m : mapList)
                    curr = m.mapNum(curr);
                if(curr < min)
                    min = curr;
            }
            System.out.println(min);
        } catch(Exception e){
            System.out.println("File does not exist.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Part1 run = new Part1();
        run.run();
    }
} 