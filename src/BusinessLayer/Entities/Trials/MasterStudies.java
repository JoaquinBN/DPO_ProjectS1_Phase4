package BusinessLayer.Entities.Trials;

/**
 * MasterStudies is a trial that represents a master studies.
 */
public class MasterStudies extends Trials{
    //name of the master studies
    private final String masterName;
    //amount of credits of the master studies
    private final int numberOfCredits;
    //probability of passing each credit
    private final int probabilityOfPassing;

    /**
     * Constructor for Trials
     *
     * @param trialName   Name of the trial, which can’t be empty and must be unique across all trials
     * @param masterName  Name of the master, which can’t be empty
     * @param numberOfCredits Number of credits of the master, an integer between [60, 120]
     * @param probabilityOfPassing Probability of passing the master, an integer between [0, 100]
     */
    public MasterStudies(String trialName, String masterName, int numberOfCredits, int probabilityOfPassing) {
        super(trialName, "Master studies");
        this.masterName = masterName;
        this.numberOfCredits = numberOfCredits;
        this.probabilityOfPassing = probabilityOfPassing;
    }

    @Override
    public int getRewardIP() {
        return 3;
    }

    @Override
    public int getPenalizationIP() {
        return -3;
    }

    @Override
    public String getTrialInfo() {
        return  "Master: " + masterName + "\n" +
                "ECTS: " + numberOfCredits + ", with a " + probabilityOfPassing + "% chance to pass each one\n\n";
    }

    @Override
    public String[] getDataToWrite() {
        String[] dataToWrite = new String[5];
        dataToWrite[0] = getTrialName();
        dataToWrite[1] = getTypeOfTrial();
        dataToWrite[2] = masterName;
        dataToWrite[3] = Integer.toString(numberOfCredits);
        dataToWrite[4] = Integer.toString(probabilityOfPassing);
        return dataToWrite;
    }

    @Override
    public String printTrialOutput(String playerName) {
        int creditsPassed = getNumberOfCreditsPassed();
        String output = "\n" + playerName + " passed " + creditsPassed + "/" + numberOfCredits + " ECTS. ";
        if(creditsPassed > numberOfCredits - creditsPassed){
            output += "Congrats!";
            setPassed(true);
        }
        else{
            output += "Sorry...";
            setPassed(false);
        }
        return output;
    }

    /**
     * Get the number of credits passed.
     * @return the number of credits passed.
     */
    private int getNumberOfCreditsPassed() {
        int creditsPassed = 0;
        for(int i = 0; i < numberOfCredits; i++){
            if(Math.random() * 100 < probabilityOfPassing){
                creditsPassed++;
            }
        }
        return creditsPassed;
    }

}
