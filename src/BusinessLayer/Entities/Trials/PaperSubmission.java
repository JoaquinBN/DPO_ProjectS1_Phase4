package BusinessLayer.Entities.Trials;

import java.util.Random;

/**
 * PaperSubmission is a class that represents a paper submission trial.
 */
public class PaperSubmission extends Trials {
    //name of the journal
    private final String publicationName;
    //quartile of the journal
    private final String quartile;
    //probability of acceptance
    private final int acceptProbability;
    //probability of revision
    private final int revisionProbability;
    //probability of rejection
    private final int rejectProbability;

    /**
     * Constructor for PaperSubmission
     * @param trialName Name of the trial, which can’t be empty and must be unique across all trials
     * @param paperName the name of the paper
     * @param quartile the quartile of the paper
     * @param acceptProbability the probability of acceptance, an integer between [0, 100]
     * @param revisionProbability the probability of revision, an integer between [0, 100]
     * @param rejectProbability the probability of rejection, an integer between [0, 100]
     */
    public PaperSubmission(String trialName, String paperName, String quartile, int acceptProbability, int revisionProbability, int rejectProbability) {
        super(trialName, "Paper publication");
        this.publicationName = paperName;
        this.quartile = quartile;
        this.acceptProbability = acceptProbability;
        this.revisionProbability = revisionProbability;
        this.rejectProbability = rejectProbability;
    }

    @Override
    public int getRewardIP() {
        return switch (quartile) {
            case "Q1" -> 4;
            case "Q2" -> 3;
            case "Q3" -> 2;
            case "Q4" -> 1;
            default -> 0;
        };

    }

    @Override
    public int getPenalizationIP() {
        return switch (quartile) {
            case "Q1" -> -5;
            case "Q2" -> -4;
            case "Q3" -> -3;
            case "Q4" -> -2;
            default -> 0;
        };
    }

    @Override
    public String getTrialInfo() {
        return  "Journal: " + publicationName + " (" + quartile + ")\n" +
                "Chances: " + acceptProbability + "% acceptance, " + revisionProbability + "% revision, " + rejectProbability + "% rejection\n\n";
    }

    @Override
    public String[] getDataToWrite() {
        String[] dataToWrite = new String[7];
        dataToWrite[0] = getTrialName();
        dataToWrite[1] = getTypeOfTrial();
        dataToWrite[2] = publicationName;
        dataToWrite[3] = quartile;
        dataToWrite[4] = Integer.toString(acceptProbability);
        dataToWrite[5] = Integer.toString(revisionProbability);
        dataToWrite[6] = Integer.toString(rejectProbability);
        return dataToWrite;
    }

    /**
     * Calculate if the trial has been passed
     * @return 2 if the trial is in revision, 1 if the trial has been accepted, 0 if the trial has been rejected
     */
    private int checkIfPassed() {
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        if (randomNumber <= acceptProbability) {
            return 1;
        } else if (randomNumber <= acceptProbability + revisionProbability) {
            return 2;
        } else {
            return 0;
        }
    }

    @Override
    public String printTrialOutput(String playerName) {
        int result = checkIfPassed();
        StringBuilder output = new StringBuilder(("\n\t" + playerName + " is submitting... "));
        while(result == 2) {
            output.append("Revisions... ");
            result = checkIfPassed();
        }
        if(result == 0){
            output.append("Rejected.");
            setPassed(false);
        }else{
            output.append("Accepted!");
            setPassed(true);
        }
        return output.toString();
    }
}
