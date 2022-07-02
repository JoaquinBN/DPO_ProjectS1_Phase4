package BusinessLayer.Entities.Trials;

public class DoctoralThesis extends Trials{
    private final String fieldOfStudy;
    private final int defenseDifficulty;

    public DoctoralThesis(String name, String fieldOfStudy, int defenseDifficulty) {
        super(name, "Doctoral thesis defense");
        this.fieldOfStudy = fieldOfStudy;
        this.defenseDifficulty = defenseDifficulty;
    }

    @Override
    public int getRewardIP() {
        return 5;
    }

    @Override
    public int getPenalizationIP() {
        return -5;
    }

    @Override
    public String getTrialInfo() {
        return  "Field: " + fieldOfStudy + "\n" +
                "Difficulty: " + defenseDifficulty + "\n\n";
    }

    @Override
    public String[] getDataToWrite() {
        String[] dataToWrite = new String[4];
        dataToWrite[0] = getTrialName();
        dataToWrite[1] = getTypeOfTrial();
        dataToWrite[2] = fieldOfStudy;
        dataToWrite[3] = Integer.toString(defenseDifficulty);
        return dataToWrite;
    }

    @Override
    public String printTrialOutput(String playerName) {
        String output;
        if(isPassed()){
            output = "\n" + playerName + " was successful. Congrats!";
            setPassed(true);
        }
        else{
            output = "\n" + playerName + " failed. Sorry...";
            setPassed(false);
        }
        return output;
    }

    private boolean isPassed(){
        int valueToPass = 0;
        for(int i = 1; i <= defenseDifficulty; i++){
            valueToPass += 2*i - 1;
        }
        return getDataNeeded() > (Math.sqrt(valueToPass));
    }
}
