package BusinessLayer.Entities;

public class BudgetRequest extends Trials{
    private final String entityName;
    private final int budgetAmount;

    public BudgetRequest(String name, String entityName, int budgetAmount) {
        super(name, "Budget request");
        this.entityName = entityName;
        this.budgetAmount = budgetAmount;
    }

    @Override
    public int getRewardIP() {
        return 0;
    }

    @Override
    public int getPenalizationIP() {
        return 2;
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
        dataToWrite[3] = Integer.toString(budgetAmount);
        return dataToWrite;
    }

    @Override
    public String printTrialOutput(String playerName) {
        if (budgetAcquired(10)){
            setPassed(true);
            return "The research group got the budget";
        }else {
            setPassed(false);
            return "The research group did not get the budget";
        }
    }

    public boolean budgetAcquired(int sumIPs){
        return sumIPs > Math.log(budgetAmount) / Math.log(2);


    }
}
