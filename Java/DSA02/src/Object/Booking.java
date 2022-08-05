package Object;


public class Booking {
    private String tcode;
    private String ccode;
    private int seat;

    public Booking(String tcode, String ccode, int seat) {
        this.tcode = tcode;
        this.ccode = ccode;
        this.seat = seat;
    }

    public String getTcode() {
        return tcode;
    }

    public void setTcode(String tcode) {
        this.tcode = tcode;
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }
    
    @Override
    public String toString() {
        return String.format("|%-15s|%-15s|%-15d|" ,tcode, ccode, seat);
    }
}
