import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Thread;
public class Part2 {

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
            for(int i = 0; i < seeds.length; i += 2){
                long minRange = Long.parseLong(seeds[i]);
                long maxRange = minRange + Long.parseLong(seeds[i+1]) - 1;
                long localMin = findMin(minRange, maxRange, mapList);
                if(localMin < min)
                    min = localMin;
            }
            System.out.println(min);
        } catch(Exception e){
            System.out.println("File does not exist.");
            e.printStackTrace();
        }
    }

    public long findMin(long minRange, long maxRange, ArrayList<MapFunction> mapList){
        boolean searching = true;
        long prev = performMaps(minRange, mapList);
        long prevAddition = 0;
        long localMin = prev;
        for(long addition = 1; searching; addition *= 2){
            if(addition == 0) addition = 1;
            long curr = minRange + addition;
            curr = performMaps(curr, mapList);
            if(searching && addition != 1 && (curr-prev != addition-prevAddition)){
                minRange += prevAddition;
                addition = prevAddition = 0;
                continue;
            }
            if(minRange + addition > maxRange) searching = false;;
            if(curr < localMin) localMin = curr;
            prevAddition = addition;
            prev = curr;
        }
        return localMin;
    }

    public long performMaps(long num, ArrayList<MapFunction> mapList){
        for(MapFunction m : mapList)
            num = m.mapNum(num);
        return num;
    }

    public static void main(String[] args){
        Part2 run = new Part2();
        run.run();
    }
} 