import java.util.Scanner;
//-------------------------------------
// Assignment 0 due to February 05, 2021
// Written by: Andrei Skachkou 40134189
// Comp 249
// The program is a game called Ladder And Snake. Starting at zero, users roll a dice one by one and move on a game
// board. If a user steps on square where a bottom of a ladder is, he/she goes up to the top of this ladder.
// If a user steps on square where a head of a snake is, he/she goes down to the snake's tail square.
// If a player steps exactly on square 100, he/she is considered as a winner and the game is over.
//-------------------------------------
/**
 * Driver class for program test.
 */
public class Driver {
    /**
     * main() method for the driver program.
     * Prompts user to enter a number of players from 2 to 4(inclusively), creates an object of LadderAndSnake class.
     * Start the game process.
     * @param args stores incoming command argument for the program.
     */
    public static void main(String[] args)
    {
        int counter=1;
        int numberOfPlayers;
        Scanner scanner=new Scanner(System.in);
        // Output welcome message
        System.out.println("--------------------------------------------------" +
                "\n----------Welcome to Ladder And Snake game--------\n" +
                "---------------Author: Andrei Skachkou------------"+
        "\n--------------------------------------------------");
        System.out.print("Please enter the # of players for your game " +
                "- Number must be between 2 and 4 inclusively: ");
        numberOfPlayers=scanner.nextInt();

        //loop for incorrect user entry check, repeat 3 times until a correct number of players (2-4) is provided.
        //The programm will be terminated after 3 incorrect attempts.
        while (numberOfPlayers<2||numberOfPlayers>4)
        {

            if (counter<3)
            {
                System.out.print("Bad Attempt "+ counter+" - Invalid # of players. Please enter a # between" +
                        " 2 and 4 inclusively: ");
                numberOfPlayers=scanner.nextInt();
                counter++;
            }
            else if (counter==3)
            {
                System.out.print("Bad Attempt " + counter+" - Invalid # of players. Please enter a # between 2 " +
                        "and 4 inclusively. This is your last attempt: ");
                numberOfPlayers=scanner.nextInt();
                counter++;
            }
            else
            {
                System.out.print("Bad Attempt " + counter+"!"+" You have exhausted all your chances. " +
                        "Programm will terminate! ");
                System.exit(0);
            }
        }
        // LadderAndSnake class object instantiation
        LadderAndSnake Game1 = new LadderAndSnake(numberOfPlayers);
        // Players turns determination method of LadderAndSnake class
        Game1.turnsDetermination();
        // Method to start the game process
        Game1.play();
    }
}