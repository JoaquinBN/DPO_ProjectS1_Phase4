package PresentationLayer.Controllers;

import BusinessLayer.EditionManager;
import BusinessLayer.TrialManager;
import PresentationLayer.Views.ComposerView;

public class ComposerController {
    private final EditionManager editionManager;
    private final TrialManager trialManager;
    private final ComposerView composerView;

    /**
     * Constructor for the ComposerController.
     * @param editionManager - the edition manager
     * @param trialManager - the trial manager
     * @param composerView - the composer view
     */
    public ComposerController(EditionManager editionManager, TrialManager trialManager, ComposerView composerView) {
        this.editionManager = editionManager;
        this.trialManager = trialManager;
        this.composerView = composerView;
    }

    /**
     * Starts the composer view.
     */
    public void start(){
        boolean shutDown = printExceptionMessage(editionManager.readEditions(), true);
        if(printExceptionMessage(trialManager.readTrials(), false))
            shutDown = true;
        if(!shutDown)
            managementMode();
    }

    public boolean printExceptionMessage(boolean isException, boolean isEditionManager){
        if(!isException){
            composerView.showError("\n" + (isEditionManager?editionManager.getErrorMessage():trialManager.getErrorMessage()) + "\n");
            return true;
        }
        return false;
    }
    /**
     * Starts the management mode of the composer view.
     */
    private void managementMode(){
        String option;
        composerView.showMessage("\nEntering management mode...\n");
        option = composerView.managementMenu();
        switch (option) {
            case "1" -> this.manageTrials();
            case "2" -> this.manageEditions();
            case "3" -> this.exitProgram();
            default -> {
                composerView.showError("\nWrong option. Please try again:");
                managementMode();
            }
        }
    }

    /**
     * Starts the trials' management mode of the composer view.
     */
    private void manageTrials(){
        String option;
        option = composerView.manageTrialsMenu();
        switch (option) {
            case "a" -> this.createTrial();
            case "b" -> this.listTrials();
            case "c" -> this.deleteTrial();
            case "d" -> this.managementMode();
            default -> {
                composerView.showError("\nWrong option. Please try again:");
                manageTrials();
            }
        }
    }


    /**
     * Starts the editions' management mode of the composer view.
     */
    private void manageEditions(){
        if (trialManager.getNumberOfTrials() == 0) {
            composerView.showError("\nThere are no trials available.");
            managementMode();
        } else {
            String option;
            option = composerView.manageEditionsMenu();
            switch (option) {
                case "a" -> this.createEdition();
                case "b" -> this.listEditions();
                case "c" -> this.duplicateEdition();
                case "d" -> this.deleteEdition();
                case "e" -> this.managementMode();
                default -> {
                    composerView.showError("\nWrong option. Please try again:");
                    manageEditions();
                }
            }
        }
    }

    /**
     * Get the trials attributes depending on the type.
     *
     * @return the attribute type.
     */

    private String getTrialName(){
        String attribute;
        attribute = composerView.readName("trial's name");
        if (!trialManager.checkUniqueName(attribute)) {
            composerView.showError("\nTrial name already exists.");
            attribute = "";
        } else if (trialManager.checkEmptyString(attribute)) {
            composerView.showError("\nTrial name cannot be empty.");
            attribute = "";
        }
        return attribute;
    }

    private int getNumberOfAttributes(int trialType){
        switch(trialType){
            case 1 -> { return 7; }
            case 2 -> { return 5; }
            case 3, 4 -> { return 4;}
            default -> { return -1; }
        }
    }

    private String getTrialTypeFromInput(int trialType){
        switch(trialType){
            case 1 -> { return "Paper publication"; }
            case 2 -> { return "Master studies"; }
            case 3 -> { return "Doctoral thesis defense"; }
            case 4 -> { return "Budget request"; }
            default -> { return ""; }
        }
    }

    private String getPaperPublicationAttributes(int attributeType){
        String probability, attribute;

        switch(attributeType){
            case 4 -> probability = "acceptance";
            case 5 -> probability = "revision";
            case 6 -> probability = "rejection";
            default -> probability = "";
        }

        switch (attributeType) {
            case 2 -> {
                attribute = composerView.readName("journal's name");
                if (trialManager.checkEmptyString(attribute)) {
                    composerView.showError("\nName of the publication cannot be empty.");
                    attribute = "";
                }
            }
            case 3 -> {
                attribute = composerView.readName("journal's quartile");
                if (trialManager.checkQuartile(attribute)) {
                    composerView.showError("\nThe quartile must be one of the following values: Q1, Q2, Q3, Q4.");
                    attribute = "";
                }
            }
            case 4, 5, 6 -> {
                attribute = composerView.readProbability(probability);
                if (trialManager.checkProbability(Integer.parseInt(attribute)) && !attribute.equals("-1")) {
                    composerView.showError("\nThe " + probability + " probability must be between 0 and 100.");
                    attribute = "";
                }
            }
            default -> attribute = "";
        }
        return attribute;
    }

    private String getMasterStudiesAttributes(int attributeType){
        String attribute = "";
        switch (attributeType) {
            case 2 -> {
                attribute = composerView.readName("master's name");
                if (trialManager.checkEmptyString(attribute)) {
                    composerView.showError("\nThe name of the master cannot be empty.");
                    attribute = "";
                }
            }
            case 3 -> {
                attribute = String.valueOf(composerView.readIntegerValue("master's ECTS number"));
                if (trialManager.checkMasterECTS(attribute)) {
                    composerView.showError("\nThe master's ECTS must be an integer between 60 and 120.");
                    attribute = "";
                }
            }
            case 4 -> {
                attribute = composerView.readProbability("credit pass");
                if (trialManager.checkProbability(Integer.parseInt(attribute)) && !attribute.equals("-1")) {
                    composerView.showError("\nThe credit pass probability must be between 0 and 100.");
                    attribute = "";
                }
            }
        }
        return attribute;
    }

    private String getPhDAttributes(int attributeType){
        String attribute = "";
        switch (attributeType) {
            case 2 -> {
                attribute = composerView.readName("thesis field of study");
                if (trialManager.checkEmptyString(attribute)) {
                    composerView.showError("\nThe thesis field of study cannot be empty.");
                    attribute = "";
                }
            }
            case 3 -> {
                attribute = String.valueOf(composerView.readIntegerValue("defense difficulty"));
                if (trialManager.checkPhDDifficulty(attribute)) {
                    composerView.showError("\nThe defense difficulty must be an integer between 1 and 10.");
                    attribute = "";
                }
            }
        }
        return attribute;
    }

    private String getBudgetRequestAttributes(int attributeType){
        String attribute = "";
        switch (attributeType) {
            case 2 -> {
                attribute = composerView.readName("entity's name");
                if (trialManager.checkEmptyString(attribute)) {
                    composerView.showError("\nThe entity's name cannot be empty.");
                    attribute = "";
                }
            }
            case 3 -> {
                attribute = composerView.readName("budget amount");
                if (trialManager.checkBudgetAmount(attribute)) {
                    composerView.showError("\nThe budget amount must be an integer between 1000 and 2000000000.");
                    attribute = "";
                }
            }
        }
        return attribute;
    }

    /**
     * Creates a trial.
     */
    private void createTrial() {
        String[] attributes = new String[0];
        boolean errorInput = false;
        int trialType;
        composerView.showTrialTypes();
        trialType = composerView.getTrialTypeInput();
        if (trialType < 1 || trialType > 4) {
            composerView.showError("\nThe index entered must be between 1 and 4.");
            errorInput = true;
        }
        if(!errorInput) {
            attributes = new String[getNumberOfAttributes(trialType)];
            composerView.showMessage("\n");
            attributes[0] = getTrialName();
            attributes[1] = getTrialTypeFromInput(trialType);
            if(attributes[0].equals("")){
                errorInput = true;
            }
            if(!errorInput) {
                for (int i = 2; i < attributes.length; i++) {
                    switch (trialType) {
                        case 1 -> {
                            attributes[i] = getPaperPublicationAttributes(i);
                            if (i == 5 && trialManager.checkLimitProbabilities(Integer.parseInt(attributes[4]) + Integer.parseInt(attributes[5]))) {
                                composerView.showError("\nThe acceptance and revision probabilities sum cannot be greater than 100.");
                                errorInput = true;
                            } else if (i == 6 && !trialManager.checkSumProbabilities(Integer.parseInt(attributes[4]) + Integer.parseInt(attributes[5]) + Integer.parseInt(attributes[6]))) {
                                composerView.showError("\nThe acceptance, revision and rejection probabilities sum cannot be greater than 100.");
                                errorInput = true;
                            }
                        }
                        case 2 -> attributes[i] = getMasterStudiesAttributes(i);
                        case 3 -> attributes[i] = getPhDAttributes(i);
                        case 4 -> attributes[i] = getBudgetRequestAttributes(i);
                    }

                    if (attributes[i].equals("") || attributes[i].equals("-1") || errorInput) {
                        break;
                    }
                }
            }
        }
        if (!errorInput) {
            trialManager.addTrial(attributes);
            composerView.createSuccess("Trial");
        }
        composerView.showMessage("\nRedirecting to previous menu...\n");
        manageTrials();
    }

    /**
     * Lists the trials.
     */
    private void listTrials(){
        int trialIndex;
        boolean errorInput = false;

        if(trialManager.getNumberOfTrials() == 0){
            composerView.showError("\nThere are no trials to display.");
        }else {
            composerView.showList("trials");
            showAllTrials();
            composerView.showBack(trialManager.getNumberOfTrials() + 1);

            trialIndex = composerView.getIndexInput();
            if (trialIndex < 0 || trialIndex > trialManager.getNumberOfTrials()) {
                composerView.showError("\nThe index entered must be an integer between 1 and " + (trialManager.getNumberOfTrials() + 1) + ".");
                errorInput = true;
            }

            if (trialIndex != trialManager.getNumberOfTrials() && !errorInput)
                composerView.showMessage(trialManager.getTrial(trialIndex).displayTrialInfo());
        }
        composerView.showMessage("\nRedirecting to previous menu...\n");
        manageTrials();
    }

    /**
     * Deletes a trial.
     */
    private void deleteTrial(){
        int trialIndex;
        boolean errorInput = false;
        String deletionConfirmation;

        if(trialManager.getNumberOfTrials() == 0) {
            composerView.showError("\nThere are no trials to delete.");
        }else {
            composerView.showDelete("trial");
            showAllTrials();
            composerView.showBack(trialManager.getNumberOfTrials() + 1);

            trialIndex = composerView.getIndexInput();
            if (trialIndex < 0 || trialIndex > trialManager.getNumberOfTrials()) {
                composerView.showError("\nThe index entered must be between 1 and " + trialManager.getNumberOfTrials() + ".");
                errorInput = true;
            }

            if (trialIndex != trialManager.getNumberOfTrials() && !errorInput) {
                if(!editionManager.trialIsUsed(trialManager.getTrial(trialIndex).getTrialName())) {
                    deletionConfirmation = composerView.showDeletionConfirmation("trial's name");
                    if(trialManager.getTrial(trialIndex).getTrialName().equals(deletionConfirmation)) {
                        trialManager.removeTrial(trialIndex);
                        composerView.deleteSuccess("trial");
                    }else if(deletionConfirmation.equals("cancel"))
                        composerView.showMessage("\nOperation cancelled.\n");
                    else
                        composerView.showError("\nThe edition's year did not match the one you want to delete.");
                }else {
                    composerView.showError("\nThe trial cannot be deleted because it is used in an edition.");
                }
            }
        }
        composerView.showMessage("\nRedirecting to previous menu...\n");
        manageTrials();
    }

    /**
     * Shows all trials.
     */
    private void showAllTrials(){
        for(int i = 0; i < trialManager.getNumberOfTrials(); i++){
            composerView.listTrials(i + 1, trialManager.getTrial(i).getTrialName());
        }
        composerView.showMessage("\n");
    }

    /**
     * Creates an edition.
     */
    private void createEdition(){
        int year, numberOfPlayers = -1, numberOfTrials = 1;
        boolean errorDisplay = false;

        year = composerView.readIntegerValue("edition's year");
        if (editionManager.checkUniqueYear(year)){
            composerView.showError("\nThis edition already exists.");
            errorDisplay = true;
        }else if (editionManager.checkValidYear(year)){
            composerView.showError("\nThe year of the edition must equal or greater than the current year (2022).");
            errorDisplay = true;
        }

        if(!errorDisplay) {
            numberOfPlayers = composerView.readIntegerValue("initial number of players");
            if(editionManager.checkPlayersRange(numberOfPlayers)){
                composerView.showError("\nThe number of players must be between 1 and 5.");
                errorDisplay = true;
            }
        }

        if (!errorDisplay) {
            numberOfTrials = composerView.readIntegerValue("number of trials");
            if(!editionManager.checkTrialsRange(numberOfTrials)){
                composerView.showError("\nThe number of trials must be between 3 and 12.");
                errorDisplay = true;
            }
        }

        if(!errorDisplay) {
            editionManager.addEdition(year, numberOfPlayers, numberOfTrials);
            composerView.showMessage("\n\t--- Trials ---\n");
            showAllTrials();
            composerView.showMessage("\n");
            int trialIndex;
            for (int j = 0; j < numberOfTrials; j++) {
                trialIndex = composerView.pickTrial(trialManager.getNumberOfTrials(), j + 1, numberOfTrials) - 1;
                editionManager.addTrialToEdition(trialManager.getTrial(trialIndex).getTrialName(), j);
            }
            composerView.createSuccess("edition");
        }

        composerView.showMessage("\nRedirecting to previous menu...\n");
        manageEditions();
    }

    /**
     * Lists the editions.
     */
    private void listEditions(){
        boolean errorDisplay = false;
        int editionIndex, k;

        if (editionManager.getNumberOfEditions() == 0) {
            composerView.showError("\nThere are no editions to display.");
        } else {
            composerView.showList("editions");
            editionIndex = showAllEditions();
            if(editionIndex < 0 || editionIndex > editionManager.getNumberOfEditions()){
                composerView.showError("\nThe edition index selected must be between 1 and " + (editionManager.getNumberOfEditions() + 1) + ".");
                errorDisplay = true;
            }
            if(editionIndex != editionManager.getNumberOfEditions() && !errorDisplay) {
                composerView.showEdition(editionManager.getEditionYear(editionIndex), editionManager.getEditionByIndex(editionIndex).getNumberOfPlayers());
                k = 1;
                for(String trialName : editionManager.getEditionByIndex(editionIndex).getTrials()){
                    composerView.listEditionTrials(k, trialName, trialManager.getTrialTypeByName(trialName));
                    k++;
                }
            }
        }

        composerView.showMessage("\nRedirecting to previous menu...\n");
        manageEditions();
    }

    /**
     * Duplicates an edition by copyings its trials into a new edition.
     */
    private void duplicateEdition(){
        int editionIndex = -1, year = -1, numberOfPlayers = -1;
        boolean errorDisplay = false;
        if (editionManager.getNumberOfEditions() == 0) {
            composerView.showError("\nThere are no editions to duplicate.");
        } else {
            composerView.showMessage("\nWhich edition do you want to clone?\n\n");

            editionIndex = showAllEditions();
            if(editionIndex < 0 || editionIndex > editionManager.getNumberOfEditions()){
                composerView.showError("\nThe edition index selected must be between 1 and " + (editionManager.getNumberOfEditions() + 1) + ".");
                errorDisplay = true;
            }
            if(editionIndex != editionManager.getNumberOfEditions() && !errorDisplay) {
                composerView.showMessage("\n");
                year = composerView.readIntegerValue("new edition's year");
                if (editionManager.checkUniqueYear(year)){
                    composerView.showError("\nThis edition already exists.");
                    errorDisplay = true;
                }else if (editionManager.checkValidYear(year)){
                    composerView.showError("\nThe year of the edition must equal or greater than the current year (2022).");
                    errorDisplay = true;
                }

                if(!errorDisplay) {
                    numberOfPlayers = composerView.readIntegerValue("new edition's initial number of players");
                    if(editionManager.checkPlayersRange(numberOfPlayers)){
                        composerView.showError("\nThe number of players must be between 1 and 5.");
                        errorDisplay = true;
                    }
                }
            }
        }
        if(!errorDisplay){
            editionManager.duplicateEditions(year, numberOfPlayers, editionIndex);
            composerView.duplicateEditionSuccess();
        }
        composerView.showMessage("\nRedirecting to previous menu...\n");
        manageEditions();
    }

    /**
     * Deletes an edition.
     */
    private void deleteEdition(){
        boolean errorDisplay = false;
        String deletionConfirmation;
        int editionIndex;

        if (editionManager.getNumberOfEditions() == 0) {
            composerView.showError("\nThere are no editions to delete.");
        } else {
            composerView.showDelete("edition");
            editionIndex = showAllEditions();
            if(editionIndex < 0 || editionIndex > editionManager.getNumberOfEditions() ){
                composerView.showError("\nThe edition index selected must be between 1 and " + (editionManager.getNumberOfEditions() + 1) + ".");
                errorDisplay = true;
            }
            if(editionIndex != editionManager.getNumberOfEditions() && !errorDisplay) {
                deletionConfirmation = composerView.showDeletionConfirmation("edition's year");
                if(String.valueOf(editionManager.getEditionYear(editionIndex)).equals(deletionConfirmation)) {
                    editionManager.removeEdition(editionIndex);
                    printExceptionMessage(editionManager.deleteStoredState(editionManager.getEditionYear(editionIndex) == 2022), true);
                    composerView.deleteSuccess("edition");
                }else if(deletionConfirmation.equals("cancel"))
                    composerView.showMessage("\nOperation cancelled.\n");
                else
                    composerView.showError("\nThe edition's year did not match the one you want to delete.");
            }
        }
        composerView.showMessage("\nRedirecting to previous menu...\n");
        manageEditions();
    }

    /**
     * Shows the edition's menu.
     * @return Index of the edition selected.
     */
    private int showAllEditions(){
        for(int i = 0; i < editionManager.getNumberOfEditions(); i++){
            composerView.listEditions(i + 1, editionManager.getEditionYear(i));
        }
        composerView.showBack(editionManager.getNumberOfEditions() + 1);
        return composerView.getIndexInput();
    }

    /**
     * Shows the main menu.
     */
    private void exitProgram(){
        printExceptionMessage(trialManager.writeTrials(), false);
        printExceptionMessage(editionManager.writeEditions(), true);
        composerView.exitProgram();
    }
}
