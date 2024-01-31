import org.junit.jupiter.api.Test;

import java.util.Timer;

import static org.junit.jupiter.api.Assertions.*;

class Ex1Test {
    public static double counter;

     public void autoEx1Game() {
//         BP_Server game = new BP_Server();
//         int numOfDigits = 4; //
//         game.startGame(1234, numOfDigits);
//
//         Ex1.autoEx1Game(game);
//
//         assertTrue(!game.isRunning());
     }



    @Test
    void removeBCZero() {


    }

    @Test
    void bildArray() {
//            int size = 5;
//            int numOfDigits = 3;
//
//            int[] result;
//            result = Ex1.bildArray(size, numOfDigits);
//
//            assertEquals(size, result.length);
//
//            for (int code : result) {
//                int codeLength = (int) Math.log10(code) + 1;
//                assertEquals(numOfDigits, codeLength);
//            }
    }
    /**
     * This test method, cheakAv(), is designed to evaluate the performance of the autoEx1Game function in the Ex1 class.
     * It calculates the average number of rounds required to win the game for each number of digits (2 to 6).
     * The test is conducted over multiple iterations to obtain a more reliable average.
     * The results are printed, including the overall average and the average for each number of digits.
     *
     * The test steps include:
     * 1. Initializing variables and the game server.
     * 2. Iterating over different numbers of digits.
     * 3. For each number of digits, playing the game for a specified number of iterations.
     * 4. Calculating the average number of rounds for each number of digits.
     * 5. Printing the results.
     *
     * Note: Adjust the 'numberOfIterations' variable based on the desired number of test iterations.
     */
    @Test
    void cheakAv() {
        double aberavg[] = new double[6];
        double sum = 0;
        int numOfDigits = 2;
        int numberOfIterations = 100; // Adjust the number of iterations as needed
        BP_Server game = new BP_Server();   // Starting the "game-server"
        long myID = 212274054;             // Your ID should be written here
        game.startGame(myID, 2);
        Ex1.autoEx1Game(game);
////        System.out.println("check"+Ex1.ind);
        double [] saveTheAverage = new double[5];
        double sum_of_average = 0;
        double average = 0;
        for (numOfDigits = 2; numOfDigits <= 6; numOfDigits++) {
            sum = 0;
            for (int i = 0; i < 100; i++) {

                game.startGame(myID, numOfDigits);  // Starting a game
                Ex1.autoEx1Game(game);
                System.out.println("checkTheRounds " + Ex1.ind);
                sum += Ex1.ind;
            }
            average = 0;
            average = sum / 100;
            saveTheAverage[numOfDigits-2] = average;
            sum_of_average += average;
        }

        double averageOfaverage=sum_of_average / 5;
        ;

     //   for (int i = 2; i <= 6; i++) {
      //      sum += aberavg[numOfDigits - 1];
       // }

      //  sum /= 5;
     //   double ind = Ex1.ind;
        System.out.println("the aberavg is: " +averageOfaverage);
        for (int i=2;i<=6;i++){
        System.out.println("The average for "+i+" is:  "+saveTheAverage[i-2] );
    }
}}

