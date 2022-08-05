
package DTO;

public class Place {
    private int noPlace;
    private String placeName;

    public Place(int noPlace, String placeName) {
        this.noPlace = noPlace;
        this.placeName = placeName;
    }

    public int getNoPlace() {
        return noPlace;
    }

    public void setNoPlace(int noPlace) {
        this.noPlace = noPlace;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
    
    @Override
    public String toString(){
        return String.format("%-10s|%-30s|" , noPlace, placeName);
    }
}
