import java.io.File;
import java.util.Scanner;
public class Part1 {
    public static void main(String[] args){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            int sum = 0;
            while(scan.hasNextLine()){
                String s = scan.nextLine();
                char n1 = 0, n2 = 0;
                for(int i = 0; i < s.length(); i++){
                    n1 = s.charAt(i);
                    if(n1 >= 48 && n1 <= 57)
                        break;
                }
                for(int i = s.length()-1; i >= 0; i--){
                    n2 = s.charAt(i);
                    if(n2 >= 48 && n2 <= 57)
                        break;
                }
                sum += charsToNum(n1, n2);
            }
            System.out.println(sum);
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static int charsToNum(char n1, char n2){
        return (n1-48)*10 + (n2-48);
    }
} 