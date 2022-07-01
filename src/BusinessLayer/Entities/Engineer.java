package BusinessLayer.Entities;

public class Engineer extends Player {

    public Engineer(String name){
        super(name);
        setForm("engineer");
        setPrintByForm(name);
        setHasEvolved(false);
    }

    public Engineer(String name, int investigationPoints) {
        super(name, investigationPoints);
        setForm("engineer");
        setPrintByForm(name);
        setHasEvolved(false);
    }

    @Override
    public void addInvestigationPoints(int investigationPoints) {
        super.addInvestigationPoints(investigationPoints);
    }
}
