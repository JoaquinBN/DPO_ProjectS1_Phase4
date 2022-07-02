package BusinessLayer.Entities.Players;

/**
 * Class for the Engineer player.
 */
public class Engineer extends Player {

    /**
     * Constructor for Engineer
     * @param name the name of the player
     */
    public Engineer(String name){
        super(name);

    }

    /**
     * Constructor for Engineer with investigation points
     * @param name the name of the player
     * @param investigationPoints the number of investigation points
     */
    public Engineer(String name, int investigationPoints) {
        super(name, investigationPoints);
    }

    @Override
    public String getType() {
        return "engineer";
    }

    @Override
    public void addInvestigationPoints(int investigationPoints) {
        super.addInvestigationPoints(investigationPoints);
    }

    @Override
    public String getTypeDisplay() {
        return getName();
    }
}
