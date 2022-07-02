package BusinessLayer.Entities.Players;

/**
 * Class for the doctor player
 */
public class Doctor extends Master{

    /**
     * Constructor for Doctor
     * @param name the name of the player
     */
    public Doctor(String name) {
        super(name);
    }

    /**
     * Constructor for Doctor with investigation points
     * @param name the name of the player
     * @param investigationPoints the number of investigation points
     */
    public Doctor(String name, int investigationPoints) {
        super(name, investigationPoints);
    }

    /**
     * Add the correspodgin investigation points to the player
     * @param investigationPoints the number of investigation points to add
     */
    public void addInvestigationPoints(int investigationPoints) {
        if(investigationPoints > 0) {
            super.addInvestigationPoints(2*investigationPoints);
        }else {
            super.addInvestigationPoints(investigationPoints);
        }
    }

    @Override
    public String getType() {
        return "doctor";
    }

    @Override
    public String getTypeDisplay() {
        return getName() + ", PhD";
    }
}
