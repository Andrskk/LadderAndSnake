import java.util.Random;

/**
 *  LadderAndSnake class
 *  Contains board array with all ladders and snakes
 *  Contains number of players and array of players participated in the game
 */
public class LadderAndSnake {

    //Instance variables initialization
    private int[] board = new int[100];
    private int numberOfPlayers;
    //array of players initialization
    private Player[] players;
    private boolean gameOver;

    //Constructor to initialize a number of game players and a game board
    public LadderAndSnake(int numberOfPlayers)
    {
        this.numberOfPlayers = numberOfPlayers;
        this.gameOver=false;
        board[1]=38;
        board[4]=14;
        board[9]=31;
        board[16]=6;
        board[21]=42;
        board[28]=84;
        board[36]=44;
        board[48]=30;
        board[51]=67;
        board[62]=19;
        board[63]=60;
        board[64]=60;
        board[71]=91;
        board[79]=19;
        board[80]=100;
        board[86]=24;
        board[87]=24;
        board[93]=68;
        board[94]=24;
        board[95]=24;
        board[97]=76;
        board[98]=78;
    }

    /**
     * Method to determine players turn. Each player rolls a dice to obtain the largest possible number.
     * The one that get the largest dice value starts first. In case of
     * a tie between any of players, the process is repeated only between those players. This
     * process is concluded once the order of playing is determined.
     */
    public void turnsDetermination()
    {
        boolean allTie;

        Random random = new Random();

        //array of players instantiating
        players = new Player[this.numberOfPlayers];

        //storing of Player objects into array
        for (int i=0;i<this.numberOfPlayers;i++)
        {
            this.players[i]=new Player();
        }
        //Number of game players output
        System.out.println("Game is playing by "+this.numberOfPlayers+" players;");
        System.out.println("Now deciding which player will start playing;");

        //output dice values for ALL players. Check if there is no tie between ALL of them. Repeat if so.
        do
        {
            for (int i=0;i<players.length;i++)
            {
                int dice;
                dice = 1 + random.nextInt(6);
                players[i].setdiceRoll(dice);
            }
            for (int i=0;i<players.length;i++)
            {
                System.out.println("Player "+"-="+players[i].getName()+"=-" + " got a dice value of "+
                        players[i].getdiceRoll());
            }
            int counter=1;
            for (int i=0;i< 1;i++)
            {
                for (int j=i+1;j< players.length;j++)
                {
                    if (players[i].getdiceRoll() == players[j].getdiceRoll())
                        counter++;
                }
            }
            if (counter==numberOfPlayers)
            {
                System.out.println("Tie between all players, rerolling......");
                allTie=true;
            }
            else allTie=false;
        }
        while (allTie);

        //initial sort of all Players objects in the array according to their dice value.
        for (int i=0;i<players.length;i++)
        {
            for (int j=i+1; j<players.length; j++)
            {
                Player[] temp2 =new Player[players.length];
                if (players[i].getdiceRoll()<players[j].getdiceRoll()&&players[i].getTieRound()==players[j].getTieRound())
                {
                    temp2[j]=players[j];
                    players[j]=players[i];
                    players[i]=temp2[j];
                }
            }
        }
        //check for initial ties
        for (int i=0;i< players.length;i++)
        {
            for (int j=i+1;j< players.length;j++)
            {
                if (players[i].getdiceRoll()==players[j].getdiceRoll())
                {
                    System.out.println("A tie was achieved between "+"-="+players[i].getName()+"=-"+" and " +
                            "-="+players[j].getName()+"=-"
                            + ". Attempting to break the tie.");
                    players[i].setTieRound(1);
                    players[j].setTieRound(1);
                }
            }
        }
        //reroll all initial ties
        players = reRoll(players,0);

        //Next rounds of sort/checkForTies/reroll loop to eliminate ties
        for (int i=1;i<10;i++)
        {
            players = sort(players);
            players = checkForTies(players,i);
            players = reRoll(players,i);
        }

        //Final decision of playing order output
        System.out.print("Reached final decision on order of playing: ");
        for (int i=0;i<players.length;i++)
        {
            System.out.print(" "+"-="+players[i].getName()+"=-"+", ");
        }
        System.out.println();
    }

    /**
     * Sorting method. Sort all Players objects in the array according to their dice value.
     * @param players Receives array of Player objects.
     * @return sorted array of Player objects.
     */
    public Player[] sort(Player[] players)
    {
        Player[] temp2 =new Player[players.length];
        for (int i=0;i<players.length;i++)
        {
            for (int j=i+1; j<players.length; j++)
            {
                if (players[i].getdiceRoll()<players[j].getdiceRoll()&&players[i].getTieRound()==players[j].getTieRound())
                {
                    temp2[j]=players[j];
                    players[j]=players[i];
                    players[i]=temp2[j];
                }
            }
        }
        return players;
    }

    /**
     * Check for ties method. Compares only players in current tie round dice value.
     * Highlights players with ties for the next tie break.
     * @param players Receives array of Player objects.
     * @param tieRound Receives current tie round.
     * @return array of Player objects.
     */
    public Player[] checkForTies(Player [] players, int tieRound)
    {
        for (int i=0;i<players.length;i++)
        {
            for (int j=i+1; j<players.length; j++)
            {
                if (players[i].getdiceRoll()==players[j].getdiceRoll()&&players[i].getTieRound()==players[j].getTieRound())
                {
                    System.out.println("A tie was achieved between "+"-="+players[i].getName()+"=-"+" and "
                            +"-="+players[j].getName()+"=-"
                            + ". Attempting to break the tie.");
                    players[i].setTieRound(tieRound+1);
                    players[j].setTieRound(tieRound+1);
                }
            }
        }
        return players;
    }

    /**
     * Reroll method. Reroll a dice for only players in current tie round.
     * @param players Receives array of Player objects.
     * @param tieRound Receives current tie round.
     * @return array of Player objects.
     */
    public Player[] reRoll(Player[] players, int tieRound)
    {
        tieRound++;
        int dice;
        Random random = new Random();
        //reRoll all ties
        for (int i=0;i< players.length;i++)
        {
            if (players[i].getTieRound()==tieRound)
            {
                dice = 1 + random.nextInt(6);
                players[i].setdiceRoll(dice);
                System.out.println("Player "+"-="+players[i].getName()+"=-" + " got a dice value of "+
                        players[i].getdiceRoll());
            }
        }
        return players;
    }

    /**
     * Method generates a random integer from 1 to 6.
     * @return random integer from 1 to 6.
     */
    public int flipDice()
    {
        Random random = new Random();
        return 1 + random.nextInt(6);
    }

    /**
     * Method launches the game process loop until a winner appears.
     */
    public void play()
    {
        // loop to roll a dice for each player until a winner appears. Changing a player's position according to
        // dice value.
        while (gameOver==false)
        {
            for (int i=0;i<this.players.length;i++)
            {
                int dice = flipDice();
                System.out.println("\n"+"-="+players[i].getName()+"=-"+" got "+dice);
                players[i].setPosition(move(players[i].getPosition(),dice));
                if (gameOver)
                {
                    players[i].setWinner(true);
                    System.out.println("\nCongratulations "+ "-="+players[i].getName()+"=-"+", " +
                            "you have reached square 100. You won!!!!!!!");
                    System.out.println("\n**************  The game is over. Our winner is "+
                            "-="+players[i].getName()+"=-  ****************");
                    break;
                }
            }
            if (!gameOver)
            System.out.println("\n-----------------Game not over; flipping again------------------");
        }
    }

    /**
     * Method to change player position according to dice value.
     * @param currentPosition Receives current player position.
     * @param dice Receives current dice roll value.
     * @return new player position as an integer.
     */
    public int move(int currentPosition,int dice)
    {
        int newPosition=currentPosition;
        int remainder;

        // in case new players position exceeds the maximum possible moves, the player moves
        // backward with the excessive amount.
        if (currentPosition+dice>100)
        {
            remainder=dice-(100-currentPosition);
            newPosition+=(100-currentPosition);
            newPosition-=remainder;
            if (this.board[newPosition]!=0)
            {
                System.out.println("moving to square "+(newPosition)+" " +
                        "and then down to square"+this.board[newPosition]);
                newPosition=this.board[newPosition];
            }
            else
            System.out.println("...moving.....\nnow in square " + newPosition);
        }
        // in case new player position gets finish, the game ends
        else if (currentPosition+dice==100||currentPosition+dice==80)
        {
            if (currentPosition+dice==80)
                System.out.println("moving to square 80 and then up to 100");
            this.gameOver=true;
        }
        //in case new player position does not exceed the maximum possible moves, player moves normally
        // hitting ladder or snake a player goes down or up accordingly.
        else
        {
            if (this.board[currentPosition+dice]!=0)
            {
                if (this.board[currentPosition+dice]>currentPosition)
                {
                    newPosition=this.board[currentPosition+dice];
                    System.out.print("moving to square "+(currentPosition+dice)+" " +
                            "and then up to square "+this.board[currentPosition+dice] );
                }
                else
                {
                    newPosition=this.board[currentPosition+dice];
                    System.out.print("moving to square "+(currentPosition+dice)+" " +
                            "and then down to square "+this.board[currentPosition+dice] );
                }
            }
            else
            {
                newPosition=currentPosition+dice;
                System.out.print("...moving.....\nnow in square " + newPosition);
            }
        }
        return newPosition;
    }
}