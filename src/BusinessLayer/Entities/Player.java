package BusinessLayer.Entities;

public abstract class Player {
    private final String name;
    private int investigationPoints;
    private String form;
    private String printByForm;
    private boolean isDead;

    /**
     * Constructor for Player
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
        this.investigationPoints = 5;
        this.isDead = false;
    }

    /**
     * Constructor for Player with investigation points
     * @param name the name of the player
     * @param investigationPoints the number of investigation points
     */
    public Player(String name, int investigationPoints) {
        this.name = name;
        this.investigationPoints = investigationPoints;
        this.isDead = false;
    }

    /**
     * Get the name of the player
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Get the number of investigation points
     * @return the number of investigation points
     */
    public int getInvestigationPoints() {
        return investigationPoints;
    }

    /**
     * Add investigation points to the player
     * @param investigationPoints the number of investigation points to add
     */
    public void addInvestigationPoints(int investigationPoints) {
        this.investigationPoints += investigationPoints;
        if(this.investigationPoints <= 0) {
            this.investigationPoints = 0;
            isDead = true;
        }
    }

    /**
     * Check if the player is dead
     * @return true if the player is dead, false otherwise
     */
    public boolean getStatus(){
        return isDead;
    }


    public String getForm(){
        return form;
    }

    /**
     * Get information from the player
     * @return the information of the player
     */
    public String[] getInfo(){
        String[] info = new String[3];
        info[0] = name;
        info[1] = Integer.toString(investigationPoints);
        if(this instanceof Engineer)
            info[2] = "Engineer";
        if(this instanceof Master)
            info[2] = "Master";
        if(this instanceof Doctor)
            info[2] = "Doctor";
        return info;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public void setPrintByForm(String printByForm) {
        this.printByForm = printByForm;
    }

    public String getPrintByForm() {
        return printByForm;
    }
}
