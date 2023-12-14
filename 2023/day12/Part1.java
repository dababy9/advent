import java.io.File;
import java.util.Scanner;
import java.util.LinkedList;
public class Part1 {

    int[] groups;
    LinkedList<Integer> permutations;
    int maxShift;

    public int num(String line){
        char[] c = line.split(" ")[0].toCharArray();
        int operationalMask = 0, damagedMask = 0;
        maxShift = 1;
        for(int i = 0; i < c.length; i++){
            operationalMask <<= 1;
            maxShift++;
            damagedMask <<= 1;
            if(c[i] == '.') operationalMask++;
            if(c[i] == '#') damagedMask++;
        }

        String[] stringGroups = line.split(" ")[1].split(",");
        groups = new int[stringGroups.length];
        for(int i = 0; i < stringGroups.length; i++)
            groups[i] = Integer.parseInt(stringGroups[i]);

        permutations = new LinkedList<>();
        int sum = 0;
        generatePermutations(0, 0, 0);
        for(int p : permutations)
            if((p & operationalMask) == 0 && (p | damagedMask) == p) sum++;
        return sum;
    }

    public void generatePermutations(int currNum, int groupIndex, int shiftNum){
        if(shiftNum >= maxShift) return;
        if(groupIndex == groups.length) permutations.add(currNum);
        int tempNum = currNum, tempShift = shiftNum;
        if(currNum % 2 == 0 && !(groupIndex == groups.length)){
            for(int i = 0; i < groups[groupIndex]; i++){
                tempNum <<= 1;
                tempNum++;
                tempShift++;
            }
            generatePermutations(tempNum, groupIndex + 1, tempShift);
        }
        if(groupIndex != 0) generatePermutations(currNum << 1, groupIndex, ++shiftNum);
    }

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            int sum = 0;
            while(scan.hasNextLine())
                sum += num(scan.nextLine());
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