package BusinessLayer;

import BusinessLayer.Entities.Trials.*;
import PersistenceLayer.TrialsFileDAO.TrialsFileManager;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TrialManager is a class that takes care of the trial management .
 */
public class TrialManager {
    private final ArrayList<Trials> trials;
    private TrialsFileManager trialsFileManager;
    private String errorMessage;

    /**
     * Constructor for TrialManager
     */
    public TrialManager() {
        this.trials = new ArrayList<>();
    }

    /**
     * Add a new trial to the list of trials
     * @param trialInfo the trial info
     */
    public void addTrial(String[] trialInfo){
        switch(trialInfo[1]) {
            case "Paper publication" -> trials.add(new PaperSubmission(trialInfo[0], trialInfo[2], trialInfo[3], Integer.parseInt(trialInfo[4]), Integer.parseInt(trialInfo[5]), Integer.parseInt(trialInfo[6])));
            case "Master studies" -> trials.add(new MasterStudies(trialInfo[0], trialInfo[2], Integer.parseInt(trialInfo[3]), Integer.parseInt(trialInfo[4])));
            case "Doctoral thesis defense" -> trials.add(new DoctoralThesis(trialInfo[0], trialInfo[2], Integer.parseInt(trialInfo[3])));
            case "Budget request" -> trials.add(new BudgetRequest(trialInfo[0], trialInfo[2], Long.parseLong(trialInfo[3])));
        }
    }

    /**
     * Remove a trial from the list of trials
     * @param index the index of the trial
     */
    public void removeTrial(int index) {
        trials.remove(index);
    }

    /**
     * Get the numbers of trials in the system
     * @return the amount of trials in the system
     */
    public int getNumberOfTrials() {
        return trials.size();
    }

    /**
     * Get the trial at the given index
     * @param index the index of the trial
     * @return the trial at the given index
     */
    public Trials getTrial(int index) {
        return trials.get(index);
    }

    /**
     * Get the trial by the name of the trial
     * @param name the name of the trial
     * @return the trial with the given name
     */
    public Trials getTrialByName(String name){
        for(Trials trial: trials){
            if(trial.getTrialName().equals(name)){
                return trial;
            }
        }
        return null;
    }

    /**
     * Get the trial type by the name of the trial
     * @param name the name of the trial
     * @return the trial type of the given name's trial
     */
    public String getTrialTypeByName(String name){
        for(Trials trial: trials){
            if(trial.getTrialName().equals(name)){
                return trial.getTypeOfTrial();
            }
        }
        return null;
    }

    /**
     * Check if the name is unique in the system
     * @param trialName the name of the trial
     * @return true if the name is unique, false otherwise
     */
    public boolean checkUniqueName(String trialName) {
        for (Trials trial : trials) {
            if (trial.getTrialName().equals(trialName)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if string is empty
     * @param trialType the type of the trial
     * @return true if the string is empty, false otherwise
     */
    public boolean checkEmptyString(String trialType) {
        return trialType.equals("");
    }

    /**
     * Check if quartile is valid
     * @param quartile the quartile of the trial
     * @return true if the quartile is valid, false otherwise
     */
    public boolean checkQuartile(String quartile) {
        return !quartile.equals("Q1") && !quartile.equals("Q2") && !quartile.equals("Q3") && !quartile.equals("Q4");
    }

    /**
     * Check if the probability is valid
     * @param probability the probability of the trial
     * @return true if the probability is valid, false otherwise
     */
    public boolean checkProbability(int probability) {
        return probability < 0 || probability > 100;
    }

    /**
     * Check if the sum of the probabilities is 100
     * @param sumProbabilities the sum of the probabilities
     * @return true if the sum of the probabilities is 100, false otherwise
     */
    public boolean checkSumProbabilities(int sumProbabilities) {
        return sumProbabilities == 100;
    }

    /**
     * Check if the probabilities is over 100.
     * @param limitProbabilities probability inputted by user
     * @return true if the probabilities is over 100, false otherwise
     */
    public boolean checkLimitProbabilities(int limitProbabilities) {
        return limitProbabilities > 100;
    }

    /**
     * Check if master studies ECTS is valid
     * @param masterECTS the ECTS of the master studies
     * @return true if the ECTS is valid, false otherwise
     */
    public boolean checkMasterECTS(String masterECTS) {
        return !(60 <= Integer.parseInt(masterECTS) && Integer.parseInt(masterECTS) <= 120);
    }

    /**
     * Check if the doctoral thesis defense difficulty is valid
     * @param PhDifficulty the difficulty of the doctoral thesis defense
     * @return true if the difficulty is valid, false otherwise
     */
    public boolean checkPhDDifficulty(String PhDifficulty) {
        return !(1 <= Integer.parseInt(PhDifficulty) && Integer.parseInt(PhDifficulty) <= 10);
    }

    /**
     * Check if the budget request amount is valid
     * @param budgetAmount the amount of the budget request
     * @return true if the amount is valid, false otherwise
     */
    public boolean checkBudgetAmount(String budgetAmount) {
        return !(1000 <= Long.parseLong(budgetAmount) && Long.parseLong(budgetAmount) <= 2000000000);
    }

    /**
     * Write the trials to the file
     * @return true if the trials are written to the file, false otherwise
     */
    public boolean writeTrials(){
        try {
            trialsFileManager.writeTrials(trials);
            return true;
        } catch (IOException e) {
            errorMessage = "Error writing trials to file";
            return false;
        }
    }

    /**
     * Read the trials from the file
     * @return true if the trials are read from the file, false otherwise
     */
    public boolean readTrials(){
        List<String[]> allTrials;
        try {
            allTrials = trialsFileManager. readTrials();
            for(String[] trial : allTrials){
                addTrial(trial);
            }
            return true;
        } catch (IOException | CsvException e) {
            errorMessage = "Error reading trials from file";
            return false;
        }
    }

    /**
     * Get the error message
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Set trials file manager
     * @param trialsFileManager the trials file manager
     */
    public void setTrialsFileManager(TrialsFileManager trialsFileManager) {
        this.trialsFileManager = trialsFileManager;
    }
}
