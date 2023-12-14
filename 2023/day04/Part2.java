import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.TreeMap;
public class Part2 {

    public static void main(String[] args){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            int sum = 0;
            TreeMap<Integer, Integer> map = new TreeMap<>();
            int card = 1;
            while(scan.hasNextLine()){
                String s = scan.nextLine().split("\\s+", 3)[2];
                String[] winningStrings = s.split("\\|")[0].split(" ");
                String[] cardStrings = s.split("\\|")[1].split(" ");
                ArrayList<Integer> winningNums = new ArrayList<>();
                ArrayList<Integer> cardNums = new ArrayList<>();
                for(int i = 0; i < winningStrings.length; i++)
                    if(!winningStrings[i].equals(""))
                        winningNums.add(Integer.parseInt(winningStrings[i]));
                for(int i = 0; i < cardStrings.length; i++)
                    if(!cardStrings[i].equals(""))
                        cardNums.add(Integer.parseInt(cardStrings[i]));
                int cardSum = 0;
                for(int i = 0; i < winningNums.size(); i++)
                        if(cardNums.contains(winningNums.get(i)))
                            cardSum++;
                int copies = 1 + (map.get(card) == null ? 0 : map.get(card));
                sum += copies;
                for(int i = 0; i < copies; i++){
                    for(int j = card+1; j <= card+cardSum; j++)
                        map.put(j, (map.get(j) == null ? 1 : map.get(j) + 1));
                }
                card++;
            }
            System.out.println(sum);
        } catch(Exception e){
            System.out.println("File does not exist.");
            e.printStackTrace();
        }
    }
} 