package BusinessLayer.Entities.Players;


/**
 * Class for the master player.
 */
public class Master extends Engineer{

    /**
     * Constructor for Master
     * @param name the name of the player
     */
    public Master(String name){
        super(name);
    }

    /**
     * Add the corresponding investigation points to the player
     * @param name the name of the player
     * @param investigationPoints the number of investigation points to add
     */
    public Master(String name, int investigationPoints) {
        super(name, investigationPoints);
    }

    @Override
    public String getType() {
        return "master";
    }

    @Override
    public String getTypeDisplay() {
        return "Master " + getName();
    }

    /**
     * Add the corresponding investigation points to the player
     * @param investigationPoints the number of investigation points to add
     */
    public void addInvestigationPoints(int investigationPoints) {
        if(investigationPoints > 0)
            super.addInvestigationPoints(investigationPoints);
        else
            super.addInvestigationPoints((int)Math.floor((float)(investigationPoints/2)));
    }
}

