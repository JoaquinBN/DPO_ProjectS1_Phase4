package BusinessLayer.Entities;

public class Doctor extends Master{

    public Doctor(String name) {
        super(name);
        setForm("doctor");
        setPrintByForm(name + ", PhD");
    }

    public Doctor(String name, int investigationPoints) {
        super(name, investigationPoints);
        setForm("doctor");
        setPrintByForm(name + ", PhD");
    }

    public void addInvestigationPoints(int investigationPoints) {
        if(investigationPoints > 0) {
            super.addInvestigationPoints(2*investigationPoints);
        }else {
            super.addInvestigationPoints(investigationPoints);
        }
    }
}
