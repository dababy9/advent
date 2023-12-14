import java.io.File;
import java.security.MessageDigest;
import java.util.Scanner;
import java.security.MessageDigest;
import java.math.BigInteger;
public class Part2 {

    public void run(){
        try {
            File f = new File("input.txt");
            Scanner scan = new Scanner(f);
            MessageDigest md = MessageDigest.getInstance("MD5");
            String original = scan.nextLine();
            for(int i = 0; true; i++){
                String appended = original + String.valueOf(i);
                md.update(appended.getBytes("UTF-8"));
                String result = (new BigInteger(1, md.digest())).toString(16);
                result = "0".repeat(32 - result.length()) + result;
                if(result.substring(0, 6).equals("000000")){
                    System.out.println(i);
                    break;
                }
            }
        } catch(Exception e){
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args){
        Part2 run = new Part2();
        run.run();
    }
} 