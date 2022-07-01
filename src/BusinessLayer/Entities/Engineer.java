package BusinessLayer.Entities;

public class Engineer extends Player {

    public Engineer(String name){
        super(name);

    }

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
