

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Food implements Serializable{
    private String ID;
    String name;
    float weight;
    String type;
    String place;
    Date expirationDate=null;
    int soluong;

    
    public Food(String ID, String name, float weight, String type, String place, Date expirationDate, int soluong) {
        this.ID = ID;
        this.name = name;
        this.weight = weight;
        this.type = type;
        this.place = place;
        this.expirationDate = expirationDate;
        this.soluong = soluong;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    
    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    @Override
    public String toString(){
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        String exDate = sdf.format(expirationDate);
        return String.format("%-15s|%-15s|%-13.2f|%-15s|%-24s|%-15s|%-15s" ,ID, name, weight,type,place, exDate, soluong);
    }
}
