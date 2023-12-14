import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
public class Part2 {

    public int nextNum(ArrayList<Integer> nums){
        boolean same = true;
        for(int i : nums)
            if(i != nums.get(0)) same = false;
        if(same) return nums.get(0);
        ArrayList<Integer> dNums = new ArrayList<>();
        for(int i = 0; i < nums.size()-1; i++){
            dNums.add(nums.get(i+1) - nums.get(i));
        }
        return nums.get(0) - nextNum(dNums);
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            int sum = 0;
            while(scan.hasNextLine()){
                String[] stringNums = scan.nextLine().split(" ");
                ArrayList<Integer> nums = new ArrayList<>();
                for(int i = 0; i < stringNums.length; i++)
                    nums.add(Integer.parseInt(stringNums[i]));
                sum += nextNum(nums);
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