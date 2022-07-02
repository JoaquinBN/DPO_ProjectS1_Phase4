package BusinessLayer.Entities.Players;


/**
 * Abstract class for the player
 */
public abstract class Player {
    //unique name of the player
    private final String name;
    //number of investigation points, initially set to 5
    private int investigationPoints;
    //boolean determining if the player lost or not
    private boolean isDead;

    /**
     * Constructor for Player
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
        this.investigationPoints = 5;
        this.isDead = false;
    }

    /**
     * Constructor for Player with investigation points, used when loading data from the execution file
     * @param name the name of the player
     * @param investigationPoints the number of investigation points
     */
    public Player(String name, int investigationPoints) {
        this.name = name;
        this.investigationPoints = investigationPoints;
        this.isDead = false;
    }

    /**
     * Get the name of the player
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Get the type of the player
     * @return the type of the player
     */
    public abstract String getType();

    /**
     * Get the print for a specific type of player
     * @return the print type of the player as a string
     */
    public abstract String getTypeDisplay();

    /**
     * Get the number of investigation points
     * @return the number of investigation points
     */
    public int getInvestigationPoints() {
        return investigationPoints;
    }

    /**
     * Add investigation points to the player
     * @param investigationPoints the number of investigation points to add
     */
    public void addInvestigationPoints(int investigationPoints) {
        this.investigationPoints += investigationPoints;
        if(this.investigationPoints <= 0) {
            this.investigationPoints = 0;
            isDead = true;
        }
    }

    /**
     * Check if the player is dead
     * @return true if the player is dead, false otherwise
     */
    public boolean isDead(){
        return isDead;
    }


    /**
     * Get information from the player
     * @return the information of the player
     */
    public String[] getInfo(){
        String[] info = new String[3];
        info[0] = name;
        info[1] = Integer.toString(investigationPoints);
        info[2] = getType();
        return info;
    }

}
