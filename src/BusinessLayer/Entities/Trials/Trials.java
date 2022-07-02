package BusinessLayer.Entities.Trials;

public abstract class Trials{
    private final String trialName;
    private final String typeOfTrial;
    private boolean passed;
    private int dataNeeded;

    /**
     * Constructor for Trials
     * @param trialName Name of the trial, which can’t be empty and must be unique across all trials
     * @param typeOfTrial the type of the trial     */
    public Trials(String trialName, String typeOfTrial) {
        this.trialName = trialName;
        this.typeOfTrial = typeOfTrial;
        this.dataNeeded = 0;
    }

    /**
     * Get the name of the trial
     * @return the name of the trial
     */
    public String getTrialName(){
        return trialName;
    }

    /**
     * Get the type of the trial
     * @return the type of the trial
     */
    public String getTypeOfTrial(){
        return typeOfTrial;
    }

    /**
     * Get the reward IP of the trial
     * @return the reward IP of the trial
     */
    public abstract int getRewardIP();

    /**
     * Get the penalization IP of the trial
     * @return the penalization IP of the trial
     */
    public abstract int getPenalizationIP();

    /**
     * Display the trial information
     * @return the trial information
     */
    public String displayTrialInfo(){
        return "\nTrial: " + getTrialName() + " (" + getTypeOfTrial() + ")\n" + getTrialInfo();
    }

    /**
     * Get the trial information
     * @return the trial information
     */
    public abstract String getTrialInfo();

    /**
     * Get the data to write to the file
     * @return the data to write to the file
     */
    public abstract String[] getDataToWrite();

    /**
     * Set trial as passed
     * @param passed true if the trial is passed, false otherwise
     */
    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    /**
     * Check if the trial is passed
     * @return true if the trial is passed, false otherwise
     */
    public boolean getPassed() {
        return passed;
    }

    /**
     * Get the data needed for the trial
     * @return the data needed for the trial
     */
    public int getDataNeeded() {
        return dataNeeded;
    }

    /**
     * Set the data needed for the trial
     * @param dataNeeded the data needed for the trial
     */
    public void setDataNeeded(int dataNeeded) {
        this.dataNeeded = dataNeeded;
    }

    public abstract String printTrialOutput(String playerName);


}
