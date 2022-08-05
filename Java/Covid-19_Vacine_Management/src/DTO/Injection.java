package DTO;

import java.io.Serializable;


public class Injection implements Serializable{
    private String injectID;
    private String firstPlace;
    private String firstDate;
    private String secondPlace;
    private String secondDate;
    private String stuID;
    private String stuName;
    private String vacID;
    private String vacName;

    public Injection(String injectID, String firstPlace, String firstDate, String secondPlace, String secondDate, String stuID, String stuName, String vacID, String vacName) {
        this.injectID = injectID;
        this.firstPlace = firstPlace;
        this.firstDate = firstDate;
        this.secondPlace = secondPlace;
        this.secondDate = secondDate;
        this.stuID = stuID;
        this.stuName = stuName;
        this.vacID = vacID;
        this.vacName = vacName;
    }
    
    public Injection(String injectID, String firstPlace, String firstDate, String secondPlace, String secondDate, String stuID, String vacID) {
        this.injectID = injectID;
        this.firstPlace = firstPlace;
        this.firstDate = firstDate;
        this.secondPlace = secondPlace;
        this.secondDate = secondDate;
        this.stuID = stuID;
        this.vacID = vacID;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getVacName() {
        return vacName;
    }

    public void setVacName(String vacName) {
        this.vacName = vacName;
    }

    

    public String getInjectID() {
        return injectID;
    }

    public void setInjectID(String injectID) {
        this.injectID = injectID;
    }

    public String getFirstPlace() {
        return firstPlace;
    }

    public void setFirstPlace(String firstPlace) {
        this.firstPlace = firstPlace;
    }

    public String getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(String firstDate) {
        this.firstDate = firstDate;
    }

    public String getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(String secondPlace) {
        this.secondPlace = secondPlace;
    }

    public String getSecondDate() {
        return secondDate;
    }

    public void setSecondDate(String secondDate) {
        this.secondDate = secondDate;
    }

    public String getStuID() {
        return stuID;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    public String getVacID() {
        return vacID;
    }

    public void setVacID(String vacID) {
        this.vacID = vacID;
    }

    @Override
    public String toString(){
        return String.format("|%-15s|%-15s|%-24s|%-15s|%-15s|%-20s|%-15s|%-20s|%-15s" ,injectID, stuID, stuName,vacID,vacName,firstPlace,firstDate,secondPlace,secondDate);
    }
    
    
    
}
