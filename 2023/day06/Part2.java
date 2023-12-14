import java.io.File;
import java.util.Scanner;
public class Part2 {
    public static void main(String[] args){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            long time = Long.parseLong(scan.nextLine().replaceAll("\\s", "").substring(5));
            long dist = Long.parseLong(scan.nextLine().replaceAll("\\s", "").substring(9));
            double sqrt = Math.sqrt((double)(time*time - 4*dist));
            System.out.println((long)(Math.floor(((double)time + sqrt)/2) - Math.floor(((double)time - sqrt)/2)));
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static int charsToNum(char n1, char n2){
        return (n1-48)*10 + (n2-48);
    }
} 