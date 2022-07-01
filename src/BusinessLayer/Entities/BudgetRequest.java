package BusinessLayer.Entities;

public class BudgetRequest extends Trials{
    private final String entityName;
    private final long budgetAmount;

    public BudgetRequest(String name, String entityName, long budgetAmount) {
        super(name, "Budget request");
        this.entityName = entityName;
        this.budgetAmount = budgetAmount;
    }

    @Override
    public int getRewardIP() {
        return (int) Math.ceil(getDataNeeded()/2);
    }

    @Override
    public int getPenalizationIP() {
        return -2;
    }

    @Override
    public String getTrialInfo() {
        return  "Entity: " + entityName + "\n" +
                "Budget: " + budgetAmount + " €\n\n";
    }

    @Override
    public String[] getDataToWrite() {
        String[] dataToWrite = new String[5];
        dataToWrite[0] = getTrialName();
        dataToWrite[1] = getTypeOfTrial();
        dataToWrite[2] = entityName;
        dataToWrite[3] = Long.toString(budgetAmount);
        return dataToWrite;
    }

    @Override
    public String printTrialOutput(String playerName) {
        return "\n" + playerName + ".";
    }

    public boolean budgetAcquired(){
        return getDataNeeded() > Math.log(budgetAmount) / Math.log(2);


    }
}
