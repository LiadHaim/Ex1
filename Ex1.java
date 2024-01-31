import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

/**
 * Introduction to Computer Science, Ariel University, Ex1 (manual Example + a Template for your solution)
 * See: https://docs.google.com/document/d/1C1BZmi_Qv6oRrL4T5oN9N2bBMFOHPzSI/edit?usp=sharing&ouid=113711744349547563645&rtpof=true&sd=true
 *Name: Liad haim, id: 212274054
 * Ex1 Bulls & Cows - ,utomatic solution.
 * This Java program provides an automatic solution to the Bulls & Cows game. It includes methods for manual and automatic gameplay,
 * interacting with the BP_Server class. This README outlines the structure and functionality of the code.
 **** General Solution (algorithm) ****
 * The automatic solution follows a randomized approach to guess the secret code. It starts by generating a set of all possible codes
 * based on the specified number of digits. The program then randomly selects a code from the set and plays a round with the BP_Server.
 * Based on the Bulls (B) and Cows (C) feedback received, the set of possible codes is dynamically adjusted to improve subsequent guesses.
 * This process continues until the secret code is successfully identified.
 * **** Results ****
 * Make sure to state the average required guesses
 * for 2,3,4,5,6 digit code:
 * the aberavg is: 9.650000000000002
 *
 * Average required guesses 2: 8.49
 * Average required guesses 3: 9.21
 * Average required guesses 4: 9.78
 * Average required guesses 5:  10.24
 * Average required guesses 6: 10.53
 */
public class Ex1 {
    public static final String Title = "Ex1 demo: manual Bulls & Cows game";
    public static int ind = 1;
//    public static int numOfDigits;

    public static void main(String[] args) {
        BP_Server game = new BP_Server();   // Starting the "game-server"
        long myID = 212274054;             // Your ID should be written here
        int numOfDigits = 6;                // Number of digits [2,6]
        game.startGame(myID, numOfDigits);  // Starting a game
        System.out.println(Title + " with code of " + numOfDigits + " digits");
//        manualEx1Game(game);
        autoEx1Game(game); // you should implement this function )and any additional required functions).
    }

    public static void manualEx1Game(BP_Server game) {
        Scanner sc = new Scanner(System.in);
        ind = 1;// Index of the guess
        int numOfDigits = game.getNumOfDigits();
        double max = Math.pow(10, numOfDigits);
        while (game.isRunning()) {           // While the game is running (the code has not been found yet
            System.out.println(ind + ") enter a guess: ");
            int g = sc.nextInt();
            if (g >= 0 && g < max) {
                int[] guess = toArray(g, numOfDigits); // int to digit array
                int[] res = game.play(guess); // Playing a round and getting the B,C
                if (game.isRunning()) { // While the game is running
                    System.out.println(ind + ") B: " + res[0] + ",  C: " + res[1]); // Prints the Bulls [0], and the Cows [1]
                    ind += 1;  // Increasing the index
                }
            } else {
                System.out.println("ERR: wrong input, try again");
            }
        }
        System.out.println(game.getStatus());
    }



    /**
     * Simple parsing function that gets an int and returns an array of digits
     *
     * @param a    - a natural number (as a guess)
     * @param size - number of digits (to handle the 00 case).
     * @return an array of digits
     */
    private static int[] toArray(int a, int size) {
        int[] c = new int[size];
        for (int j = 0; j < c.length; j += 1) {
            c[j] = a % 10;
            a = a / 10;
        }
        return c;
    }
////////////////////////////////////////////////////

    /**
     * This function solves the Bulls & Cows game automatically.
     * You should implement
     * **** Add a specific explanation for each function ****
     */
/**
 * This function plays the Bulls & Cows game automatically. It initializes an array called allOptions,
 * where each index represents a possible code, and sets all of them to true initially.
 * The function then enters a loop, generating a guess using the findTheNextGuess function,
 * playing the guess with the game server, printing the result, and updating the allOptions array
 * by filtering out the codes that do not match the result.
 * The loop continues until the game is no longer running.
 */
    public static void autoEx1Game(BP_Server game) {
        ind = 1;
        int numOfDigits = game.getNumOfDigits();
        boolean[] allOptions = new boolean[(int) Math.pow(10,numOfDigits)];
        for (int i = 0; i <Math.pow(10,numOfDigits) ; i++) {
            allOptions[i] = true;
        }
        while(game.isRunning())
        {
            int[] guess = findTheNextGuess(allOptions,numOfDigits);
            int[] result = game.play(guess);
            System.out.println(ind + ") "+Arrays.toString(guess)+"B: " + result[0] + ",  C: " + result[1]);
            filterBC(numOfDigits,allOptions,guess,result);
            ind++;
        }
    }
    /**
//This function updates the allOptions array based on the result of a guess.
 //It filters out the codes that do not match the provided result.*/
    public static boolean [] filterBC (int numOfDigits, boolean[]allOption, int [] guess1, int [] result){
        for (int i =0; i< Math.pow(10, numOfDigits); i++) {
            int[] copyOption = toArray(i, numOfDigits);
            int [] guess  = new int[numOfDigits];
            int [] boolAndCow = {0,0};

            for (int j =0; j<numOfDigits;j++){
                guess[j]= guess1[j];
            }
            for (int j =0; j<numOfDigits; j++){
                if (copyOption[j] == guess[j]){
                    boolAndCow[0]++;
                    copyOption[j] = -1;
                    guess[j] = -1;

                }

            }
             for (int j=0; j< numOfDigits; j++){
                 for (int k=0; k<numOfDigits;k++){
                     if ((guess[k]==copyOption[j])&& (guess[k]!= -1)){
                         boolAndCow[1]++;
                         guess [k] = -1;
                         copyOption [j] = -1;

                     }
                 }
             }
             if (!((result[0]==boolAndCow[0]) && (result[1]==boolAndCow[1]))){
                 allOption[i]=false;
             }
        }
        return allOption;

    }
/**
 * Finds the next guess based on the remaining valid options.
 *
 * The function takes two parameters:
 * - allOptions: A boolean array indicating which codes are still considered valid.
 * - numOfDigits: The number of digits in each code.
 *
 * It iterates through the possible codes and returns the first valid code found.
 * The validity of a code is determined by the corresponding value in the allOptions array.
 */
    public static int[] findTheNextGuess(boolean[] allOptions,int numOfDigits)
    {
       int[] a = new int[numOfDigits];
        for (int i = 0; i < Math.pow(10,numOfDigits); i++) {
            if (allOptions[i] == true){
               // int[] a = new int[numOfDigits];
                a= toArray(i, numOfDigits);
              //  i= (int) Math.pow(10,numOfDigits);
                return a;
        }
     //
        }
        return a;
    }


}























