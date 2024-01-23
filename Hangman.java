
import java.util.*;
public class Hangman
{
    static FileParser fileParser = new FileParser();
    public static void main(String args[]) throws Exception{
        Scanner in = new Scanner(System.in);
        String wordChosen, playerGuess;
        char playerChar;
        int noOfTries = 3, failedAttempts = 0;
        int i = 1;
        System.out.println("=============================================");
        System.out.println("\t WELCOME TO HANGMAN \t");
        System.out.println("=============================================");
        System.out.println("Enter the max number of tries: ");
        noOfTries = in.nextInt();

        System.out.println("The Computer is thinking of a word"); 
        Thread.sleep(1000);
        wordChosen = fileParser.getWord();
        char correctGuess[] = new char[wordChosen.length()];
        System.out.println("The computer has thought of a word");
        Thread.sleep(250);
        System.out.println("The word is: "+wordToUnderScore(wordChosen));
        System.out.println("You may now begin with your guesses");

        while(failedAttempts <= noOfTries){
            if(containsAllChars(correctGuess, wordChosen)){
                System.out.println("======================================");
                System.out.println("YOU HAVE GUESSED THE WORD CORRECTLY: "+wordChosen);
                System.out.println("Failed Attemps: "+failedAttempts);
                System.out.println("======================================");
                break;
            }
            System.out.println(i +". Guess a character: ");
            playerChar = in.next().charAt(0);
            i++;
            if(Character.isDigit(playerChar)){
                System.out.println("The character entered is a digit which is not allowed ");
                continue;
            }
            if(containsChar(correctGuess,playerChar)){
                System.out.println("You have already guessed this letter");
                continue;
            }
            if(wordContainsChar(wordChosen, playerChar)){
                System.out.println("==========================================");
                System.out.println("Correct Guess!");
                correctGuess = addCharToArray(correctGuess,playerChar);
                System.out.println(wordToUnderScore(wordChosen, correctGuess));
            }else{
                System.out.println("Wrong Guess. Try again");
                failedAttempts++;
                System.out.println("Failed Attempts: "+ failedAttempts);
                continue;
            }
            System.out.println("===========================================");
            System.out.println("Do you want to guess the word? \n [Y]Yes \n [N]No" );
            String input = in.next();
            if(input.equals("Y") || input.equalsIgnoreCase("Yes")){
                System.out.println("Enter your guess (word): ");
                playerGuess = in.next();
                if(playerGuess.equalsIgnoreCase(wordChosen)){
                    System.out.println("YOU HAVE GUESSED THE WORD CORRECTLY: "+wordChosen);
                    System.out.println("Failed Attemps: "+failedAttempts);
                }else{
                    System.out.println("Unfortunately, the guess is incorrect.");
                    System.out.println("Failed Attemps: "+failedAttempts);
                }
            }
            System.out.println("===========================================");
        }
        if(failedAttempts > noOfTries){
            System.out.println("=========================================================");
            System.out.println("You failed to guess the word. The word was: " + wordChosen);
            System.out.println("=========================================================");
        }
    }

    public static char[] addCharToArray(char[] array, char c) {
        char[] newArray = new char[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        newArray[newArray.length - 1] = c;
        return newArray;
    }

    public static boolean containsChar(char[] array, char c) {
        for (char ch : array) {
            if (ch == c) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsAllChars(char[] array, String word) {
        for (char c : word.toCharArray()) {
            if (!containsChar(array, c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean wordContainsChar(String word,char chr){
        for(int i = 0; i<word.length(); i++){
            if(word.charAt(i) == chr){
                return true;
            }
        }
        return false;
    }

    public static String wordToUnderScore(String word, char... excludedChars){
        String underScoreString = "";
        for(int i = 0; i<word.length();i++){
            boolean contains = false;
            for(char ch: excludedChars){
                if(word.charAt(i) == ch){
                    contains = true;
                    underScoreString = underScoreString + word.charAt(i);
                    break;
                }
            }
            if(!contains){
                underScoreString = underScoreString + "_ ";
            }
        }
        return underScoreString.trim();
    }
}
