package BusinessLayer.Entities.Players;

public class Master extends Engineer{

    public Master(String name){
        super(name);
    }


    @Override
    public String getType() {
        return "master";
    }

    @Override
    public String getTypeDisplay() {
        return "Master " + getName();
    }

    public Master(String name, int investigationPoints) {
        super(name, investigationPoints);
    }

    public void addInvestigationPoints(int investigationPoints) {
        if(investigationPoints > 0)
            super.addInvestigationPoints(investigationPoints);
        else
            super.addInvestigationPoints((int)Math.floor((float)(investigationPoints/2)));
    }
}

