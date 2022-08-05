package List;


import Object.Train;


public class BSTNode {
    BSTNode left;
    BSTNode right;
    Train info;
    
    public BSTNode(){
        left = right = null;
    }

    public BSTNode(Train info) {
        this.info = info;
        left = right = null;
    }
    
    public BSTNode(String tcode, String train_name, int seat, int booked, double depart_time, String depart_place){
        info = new Train(tcode, train_name, seat, booked, depart_time, depart_place);
        left = right =  null;
    }
    
}
