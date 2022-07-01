package BusinessLayer.Entities;

public class Master extends Engineer{

    public Master(String name){
        super(name);
        setForm("master");
        setPrintByForm("Master " + name);
        setHasEvolved(true);
    }

    public Master(String name, int investigationPoints) {
        super(name, investigationPoints);
        setForm("master");
        setPrintByForm("Master " + name);
        setHasEvolved(false);
    }

    public void addInvestigationPoints(int investigationPoints) {
        if(investigationPoints > 0)
            super.addInvestigationPoints(investigationPoints);
        else
            super.addInvestigationPoints((int)Math.floor((float)(investigationPoints/2)));
    }
}

