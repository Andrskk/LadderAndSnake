import java.util.Random;

/**
 * Player class
 * Contains attributes(instance variables) of a Player
 * Contains methods to manipulate with attributes of a Player
 */
public class Player {

    //Instance variables initialization
    private String name;
    private int diceRoll;
    private boolean winner;
    private int tieRound;
    private int position;

    //Default constructor
    public Player()
    {
        this.position=0;
        this.diceRoll = 0;
        this.name = setName();
        this.winner = false;
        this.tieRound=0;
    }

    /**
     * Method to get player's position.
     * @return player position as an integer.
     */
    public int getPosition()
    {
        return position;
    }

    /**
     * Method to set player's position.
     * @param position Receives an integer and assign it to "this" player position.
     */
    public void setPosition(int position)
    {
        this.position = position;
    }

    /**
     * Method to get player's name.
     * @return player name as a String.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Method to get player's dice roll value.
     * @return a player's dice roll value as an integer.
     */
    public int getdiceRoll() {
		return diceRoll;
	}

    /**
     * Method to set player's dice roll value.
     * @param initialPlace Receives an integer and assign it to "this" dice variable.
     */
	public void setdiceRoll(int initialPlace) {
		this.diceRoll = initialPlace;
	}

    /**
     * Method to set player's name as a random 3-letters String.
     * @return player's name as a 3-letters String.
     */
	public String setName()
    {
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random=new Random();
        char letter;
        String name="";
        for (int i=0;i<3;i++)
        {
            letter = abc.charAt(random.nextInt(abc.length()));
            name+=letter;
        }
        return name;
    }

    /**
     * Method to get whether a player winner or not.
     * @return true if a player is winner, otherwise returns false.
     */
    public boolean isWinner(){return winner;}

    /**
     * Method to set a player's status of winner.
     * @param winner Receives a boolean variable and assign it to "this" winner variable.
     */
    public void setWinner(boolean winner)
    {
        this.winner = winner;
    }

    /**
     * Method to get a current round of tie break.
     * @return a current round of tie break as an integer.
     */
    public int getTieRound() { return tieRound; }

    /**
     * Method to set a current round of tie break.
     * @param tieRound Receives a value of current tie break as an integer and assign it to
     * "this" tieRound instance variable.
     */
    public void setTieRound(int tieRound) { this.tieRound = tieRound; }
}
