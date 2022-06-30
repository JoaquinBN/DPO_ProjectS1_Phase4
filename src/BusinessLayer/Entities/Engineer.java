package BusinessLayer.Entities;

public class Engineer extends Player {

    public Engineer(String name){
        super(name);
        setForm("engineer");
    }

    public Engineer(String name, int investigationPoints) {
        super(name, investigationPoints);
        setForm("engineer");
    }

    @Override
    public void addInvestigationPoints(int investigationPoints) {
        super.addInvestigationPoints(investigationPoints);
    }
}
