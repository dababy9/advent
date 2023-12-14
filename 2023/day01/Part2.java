import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
public class Part2 {
    public static void main(String[] args){
        String[] words = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            int sum = 0;
            while(scan.hasNextLine()){
                String s = scan.nextLine();
                char n1 = 0, n2 = 0;
                int i1 = 0, i2 = 0;
                boolean flag = false;
                for(int i = 0; i < s.length() && !flag; i++){
                    n1 = s.charAt(i);
                    if(n1 >= 48 && n1 <= 57){
                        i1 = (n1-48)*10;
                        break;
                    }
                    for(int j = 0; j < 9; j++){
                        if((i+words[j].length() <= s.length()) && s.substring(i, i+words[j].length()).equals(words[j])){
                            i1 = (j+1)*10;
                            flag = true;
                            break;
                        }
                    }
                }
                flag = false;
                for(int i = s.length()-1; i >= 0 && !flag; i--){
                    n2 = s.charAt(i);
                    if(n2 >= 48 && n2 <= 57){
                        i2 = n2-48;
                        break;
                    }
                    for(int j = 0; j < 9; j++){
                        if((i-words[j].length()+1 >= 0) && s.substring(i-words[j].length()+1, i+1).equals(words[j])){
                            i2 = j+1;
                            flag = true;
                            break;
                        }
                    }
                }
                sum += i1+i2;
            }
            System.out.println(sum);
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }
} 