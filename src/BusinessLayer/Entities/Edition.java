package BusinessLayer.Entities;

/**
 * Edition is a class that represents a set of trials, passed by a number of players, in a given year.
 */
public class Edition {
    private final int year;
    private final int numberOfPlayers;
    private final String[] trials;

    /**
     * Constructor for Edition
     * @param year the year of the edition
     * @param numberOfPlayers the number of players in the edition
     * @param numberOfTrials the number of trials in the edition
     */
    public Edition(int year, int numberOfPlayers, int numberOfTrials) {
        this.year = year;
        this.numberOfPlayers = numberOfPlayers;
        this.trials = new String[numberOfTrials];
    }

    /**
     * Get the year of the edition
     * @return the year of the edition
     */
    public int getYear() {
        return year;
    }

    /**
     * Get the number of players in the edition
     * @return the number of players in the edition
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * Get the number of trials in the edition
     * @return the number of trials in the edition
     */
    public int getNumberOfTrials() {
        return trials.length;
    }

    /**
     * Get the trials in the edition
     * @return the trials in the edition
     */
    public String[] getTrials() {
        return trials;
    }

    /**
     * Set the trials in the edition
     * @param trialName the trial name
     * @param position the array's position to be added
     */
    public void addTrial(String trialName, int position) {
        this.trials[position] = trialName;
    }
}
