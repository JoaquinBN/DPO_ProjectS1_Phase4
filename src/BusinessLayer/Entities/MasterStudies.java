package BusinessLayer.Entities;

public class MasterStudies extends Trials{

    private final String masterName;
    private final int numberOfCredits;
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
    public int hasWonTrial() {
        int creditsPassed = 0;
        for(int i = 0; i < numberOfCredits; i++){
            if(Math.random() * 100 < probabilityOfPassing){
                creditsPassed = i + 1;
            }
        }
        return creditsPassed;
    }
}
