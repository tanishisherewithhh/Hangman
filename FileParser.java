import java.io.*;
import java.util.Random;

public class FileParser
{
    public String selectedWord = "";
    public String getWord(){
        File file = new File(
                System.getProperty("user.dir") + File.separator +"words.txt");
        try(BufferedReader br
        = new BufferedReader(new FileReader(file))){
            int lineNo = getRandomNumber();
              for (int i = 1; i < lineNo; i++) {
                br.readLine();
            }
            selectedWord = br.readLine();
        }
        catch(Exception io){
            System.out.println(io);
        }
        return selectedWord;
    }
     public static int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(1000) + 1;
    }
}
