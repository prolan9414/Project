package DTO;


public class Vaccine {
    private String vacID;
    private String vacName;

    public Vaccine(String vacID, String vacName) {
        this.vacID = vacID;
        this.vacName = vacName;
    }

    public String getVacID() {
        return vacID;
    }

    public void setVacID(String vacID) {
        this.vacID = vacID;
    }

    public String getVacName() {
        return vacName;
    }

    public void setVacName(String vacName) {
        this.vacName = vacName;
    }
    
    @Override
    public String toString(){
        return String.format("%-15s|%-20s" , vacID, vacName);
    }
}
