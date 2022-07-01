package BusinessLayer.Entities;

public class Doctor extends Master{

    public Doctor(String name) {
        super(name);
        setForm("doctor");
        setPrintByForm(name + ", PhD");
        setHasEvolved(true);
    }

    public Doctor(String name, int investigationPoints) {
        super(name, investigationPoints);
        setForm("doctor");
        setPrintByForm(name + ", PhD");
        setHasEvolved(false);
    }

    public void addInvestigationPoints(int investigationPoints) {
        if(investigationPoints > 0) {
            super.addInvestigationPoints(2*investigationPoints);
        }else {
            super.addInvestigationPoints(investigationPoints);
        }
    }
}
