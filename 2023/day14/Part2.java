import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.TreeMap;
public class Part2 {

    public long runCycle(char[][] map){

        //north
        for(int row = 0; row < map.length; row++){
            char[] c = map[row];
            for(int col = 0; col < c.length; col++){
                if(map[row][col] != 'O') continue;
                int trialRow;
                for(trialRow = row-1; trialRow >= 0 && map[trialRow][col] == '.'; trialRow--);
                map[row][col] = '.';
                map[++trialRow][col] = 'O';
            }
        }

        //west
        for(int col = 0; col < map[0].length; col++){
            for(int row = 0; row < map.length; row++){
                if(map[row][col] != 'O') continue;
                int trialCol;
                for(trialCol = col-1; trialCol >= 0 && map[row][trialCol] == '.'; trialCol--);
                map[row][col] = '.';
                map[row][++trialCol] = 'O';
            }
        }

        //south
        for(int row = map.length-1; row >= 0; row--){
            char[] c = map[row];
            for(int col = 0; col < c.length; col++){
                if(map[row][col] != 'O') continue;
                int trialRow;
                for(trialRow = row+1; trialRow < map.length && map[trialRow][col] == '.'; trialRow++);
                map[row][col] = '.';
                map[--trialRow][col] = 'O';
            }
        }

        //east
        for(int col = map[0].length-1; col >= 0; col--){
            for(int row = 0; row < map.length; row++){
                if(map[row][col] != 'O') continue;
                int trialCol;
                for(trialCol = col+1; trialCol < map[0].length && map[row][trialCol] == '.'; trialCol++);
                map[row][col] = '.';
                map[row][--trialCol] = 'O';
            }
        }

        //calculate load
        long sum = 0;
        for(int row = 0; row < map.length; row++)
            for(int col = 0; col < map[row].length; col++)
                if(map[row][col] == 'O') sum += map.length-row;

        return sum;
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            ArrayList<char[]> oldMap = new ArrayList<>();
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                oldMap.add(line.toCharArray());
            }
            char[][] map = new char[oldMap.get(0).length][oldMap.size()];
            for(int i = 0; i < oldMap.size(); i++)
                map[i] = oldMap.get(i);
            
            ArrayList<Long> results = new ArrayList<>();
            for(int i = 0; i < 200; i++){
                long result = runCycle(map);
                results.add(result);
            }
            int period;
            for(period = 1; period < 50; period++){
                long result1 = results.get(199), result2 = results.get(199-period), result3 = results.get(199-(period*2));
                if(result1 == result2 && result2 == result3) break;
            }
            int remainder = 999999801 % period;
            System.out.println(results.get(198 - period + remainder));
            System.out.println(period);
            //for(Long l : results)
                //System.out.println(l);
        } catch(Exception e){
            System.out.println("File does not exist.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Part2 run = new Part2();
        run.run();
    }
} 