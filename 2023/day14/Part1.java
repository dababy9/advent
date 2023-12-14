import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
public class Part1 {

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
            long sum = 0;
            for(int row = 0; row < map.length; row++)
                for(int col = 0; col < map[row].length; col++)
                    if(map[row][col] == 'O') sum += map.length-row;
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