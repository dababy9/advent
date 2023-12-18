import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
public class Part1 {

    Ingredient[] arr;

    public class Ingredient {
        int cap, dur, flav, text;

        public Ingredient(String[] line){
            cap = Integer.parseInt(line[2].substring(0, line[2].length()-1));
            dur = Integer.parseInt(line[4].substring(0, line[4].length()-1));
            flav = Integer.parseInt(line[6].substring(0, line[6].length()-1));
            text = Integer.parseInt(line[8].substring(0, line[8].length()-1));
        }
    }

    public int calculate(Ingredient[] arr, int[] amounts){
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

    public int maximize(int[] amounts){
        int currBest = calculate(arr, amounts);
        boolean found = false;
        while(!found){
            int[] tempBest = null;
            for(int i = 0; i < arr.length; i++)
                for(int j = 0; j < arr.length; j++)
                    if(i != j){
                        int[] newAmounts = new int[amounts.length];
                        System.arraycopy(amounts, 0, newAmounts, 0, amounts.length);
                        newAmounts[i]++;
                        newAmounts[j]--;
                        if(newAmounts[i] > 100 || newAmounts[j] < 0) continue;
                        int trial = calculate(arr, newAmounts);
                        if(trial > currBest){
                            currBest = trial;
                            tempBest = newAmounts;
                        }
                    }
            amounts = tempBest;
            if(amounts == null)
                found = true;
        }
        return currBest;
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
            Random r = new Random();
            int maxValue = 0;
            for(int i = 0; i < 100; i++){
                int available = 100;
                for(int j = 0; j < amounts.length - 1; j++){
                    amounts[j] = r.nextInt(available+1);
                    available -= amounts[j];
                }
                amounts[amounts.length-1] = available;
                int value = maximize(amounts);
                if(value > maxValue) maxValue = value;
            }
            System.out.println(maxValue);
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part1 run = new Part1();
        run.run();
    }
} 