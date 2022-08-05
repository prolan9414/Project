package Object;


public class Thing {
    String pcode;
    String pro_name;
    int quantity;
    int sale;
    double price;

    public Thing(String pcode, String pro_name, int quantity, int sale, double price) {
        this.pcode = pcode;
        this.pro_name = pro_name;
        this.quantity = quantity;
        this.sale = sale;
        this.price = price;
    }
    public Thing() {
        
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%-15s|%-15s|%-15d|%-15d|%f" ,pcode, pro_name, quantity, sale, price);
    }
    
    
}
