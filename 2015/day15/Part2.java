import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
public class Part2 {

    Ingredient[] arr;

    public class Ingredient {
        int cap, dur, flav, text, cal;

        public Ingredient(String[] line){
            cap = Integer.parseInt(line[2].substring(0, line[2].length()-1));
            dur = Integer.parseInt(line[4].substring(0, line[4].length()-1));
            flav = Integer.parseInt(line[6].substring(0, line[6].length()-1));
            text = Integer.parseInt(line[8].substring(0, line[8].length()-1));
            cal = Integer.parseInt(line[10]);
        }
    }

    public int calculate(int[] amounts){
        int[] totals = new int[4];
        for(int i = 0; i < arr.length; i++){
            totals[0] += amounts[i] * arr[i].cap;
            totals[1] += amounts[i] * arr[i].dur;
            totals[2] += amounts[i] * arr[i].flav;
            totals[3] += amounts[i] * arr[i].text;
        }
        for(int i = 0; i < 4; i++)
            if(totals[i] < 0) return 0;
        return totals[0] * totals[1] * totals[2] * totals[3];
    }

    public int calCount(int[] amounts){
        int total = 0;
        for(int i = 0; i < arr.length; i++)
            total += amounts[i] * arr[i].cal;
        return total;
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            ArrayList<Ingredient> list = new ArrayList<>();
            while(scan.hasNextLine()){
                String[] line = scan.nextLine().split(" ");
                list.add(new Ingredient(line));
            }
            arr = new Ingredient[list.size()];
            int index = 0;
            for(Ingredient i : list)
                arr[index++] = i;
            int[] amounts = new int[arr.length];
            ArrayList<int[]> solutions = new ArrayList<>();
            for(int i = 0; i <= 100; i++){
                amounts[0] = i;
                for(int j = 0; j <= 100-i; j++){
                    amounts[1] = j;
                    for(int k = 0; k <= 100-i-j; k++){
                        amounts[2] = k;
                        int l = 100-i-j-k;
                        amounts[3] = l;
                        int[] trial = new int[amounts.length];
                        System.arraycopy(amounts, 0, trial, 0, amounts.length);
                        if(calCount(trial) == 500) solutions.add(trial);
                    }
                }
            }
            int maxValue = 0;
            for(int[] a : solutions){
                int value = calculate(a);
                if(value > maxValue) maxValue = value;
            }
            System.out.println(maxValue);
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part2 run = new Part2();
        run.run();
    }
} 