package List;


import Main.Inputter;
import Object.Train;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class BSTree {
    Scanner sc = new Scanner(System.in);
    BSTNode root;
    
    public BSTree(){
        root = null;
    }

    public BSTree(BSTNode root) {
        this.root = root;
    }
    
    void clear() {
     root=null;   
    }
    
    public boolean isEmpty(){
        return root == null;
    }
    
//////////////////////////Continue function//////////////////////////
    public boolean continueYN(String nn){
        System.out.println("---------------------------------------------------\n");
        System.out.println("Do you want to " +nn+" ?");
        System.out.println("1. Yes\n2. No");
        System.out.print("Input: ");
        boolean check=true;
        int choice=0;
        while(check){
            try{
                choice = Integer.parseInt(sc.nextLine());
                if(choice==1||choice==2){
                    check=false;
                }
                else{
                    System.out.print("Please enter 1 or 2: ");  
                }
            }catch(NumberFormatException e){
                System.out.print("Wrong format. Please enter again: ");
            }
        }
        System.out.println("\n");
        return choice==1;
    }
    
//////////////////////////Check duplicated tcode//////////////////////////
    public boolean isDupTcode(String tcode){
        BSTNode any = root;
        if(isEmpty()) return false;
        while(any != null){
                if(any.info.getTcode().compareTo(tcode)==0){
                    System.out.println(tcode+" is already existed!!!");
                    return true;
                }
                if(any.info.getTcode().compareTo(tcode)>0){
                    any = any.left;
                }
                else any = any.right;
            }
        return false;
    }
    
//////////////////////////Input train information//////////////////////////
    public void input() throws IOException{
        BSTNode any = root;
        BSTNode ptr;
        boolean check1 = true, checkCode = true, checkBooked = true;
        int flag = 0, flag1 =0;
        String tcode = null, train_name, depart_place;
        int seat, booked = 0;
        double depart_time;
        //input tcode
        while(checkCode){
            tcode = Inputter.inputStrId("Enter the train's Code: ");
            checkCode = isDupTcode(tcode);
        }
        //input train_name
        train_name = Inputter.inputStr("Enter the train name: ");
        //input seat
        seat = Inputter.inputInt("Enter the number of seats: ", "Seat");
        //input booked
        while(checkBooked){
            booked = Inputter.inputInt2("Enter number of booked seats: ", "Booked seat");
            if(booked >= seat) System.out.println("Numeber of booked seats cannot be greater than number of seats");
            else checkBooked = false;
        }
        //input depart_time
        depart_time = Inputter.inputDouble("Enter the depature time of the train: ", "Depature time of the train");
        //input depart_place
        depart_place = Inputter.inputStr("Enter the place of departure of the train: ");
        BSTNode temp = new BSTNode(tcode, train_name, seat, booked, depart_time, depart_place);
        if(isEmpty()) root = temp;
        else{
            ptr = root;
            while(check1){
                if(temp.info.getTcode().compareTo(ptr.info.getTcode()) > 0){
                    if(ptr.right != null)
                        ptr = ptr.right;
                    else{
                        ptr.right = temp;
                        check1 = false;
                        }
                    }
                    if(temp.info.getTcode().compareTo(ptr.info.getTcode()) < 0){
                        if(ptr.left != null) {
                            ptr = ptr.left;
                        }else{
                            ptr.left = temp;
                            check1 = false;
                        }
                    }
                            
            }
                
        }
        saveFileInorder();
        if(continueYN("continue to add more train information")){            
            input();          
        }
    }
    
//////////////////////////Search by tcode//////////////////////////
    public void search(){
        if(isEmpty()){
            System.out.println("Empty list!!! Search option cannot be performed.");
            return;
        }
        int flag = 0;
        BSTNode ptr;
        String key = Inputter.inputStrId("Enter train code to searched: ");
        ptr = root;
         while (ptr != null)
         {
            if (ptr.info.getTcode().compareTo(key) == 0) {
                flag = 1;
                System.out.println("\n\t-----RECORD FOUND-----");
                System.out.println("|===============================================================================================|");
                System.out.format("|%-15s|%-15s|%-15S|%-15S|%-15s|%-15s|\n" , "TRAIN CODE", "TRAIN NAME", "N.O OF SEATS", "BOOKED SEATS", "DEPARTURE TIME", "DEPARTURE PLACE");
                System.out.println("|===============================================================================================|");
                System.out.println(ptr.info);
                System.out.println("|===============================================================================================|");
                break;
            }
            if ((ptr.info.getTcode()).compareTo(key) > 0) {
                ptr = ptr.left;
            } else {
                ptr = ptr.right;
            }
        }
        if (flag == 0) {
            System.out.println("-----RECORD NOT FOUND-----");
        }
        
        if(continueYN("continue to search")){            
            search();          
        }
    }
    
    public Train search2(){
        if(isEmpty()){
            System.out.println("Empty list!!! Search option cannot be performed.");
            return null;
        }
        int flag = 0;
        BSTNode ptr;
        String key = Inputter.inputStrId("Enter train code to search: ");
        ptr = root;
         while (ptr != null)
         {
            if (ptr.info.getTcode().compareTo(key) == 0) {
                flag = 1;
                System.out.println("\n\t-----RECORD FOUND-----");
                System.out.println("|===============================================================================================|");
                System.out.format("|%-15s|%-15s|%-15S|%-15S|%-15s|%-15s|\n" , "TRAIN CODE", "TRAIN NAME", "N.O OF SEATS", "BOOKED SEATS", "DEPARTURE TIME", "DEPARTURE PLACE");
                System.out.println("|===============================================================================================|");
                System.out.println(ptr.info);
                System.out.println("|===============================================================================================|");
                return ptr.info;
            }
            if ((ptr.info.getTcode()).compareTo(key) > 0) {
                ptr = ptr.left;
            } else {
                ptr = ptr.right;
            }
        }
        if (flag == 0) {
            System.out.println("-----RECORD NOT FOUND-----");
        }
        return null;
    }
    
//////////////////////////Delete by copying//////////////////////////
    public void delete() throws IOException {
        if(isEmpty()){
            System.out.println("Empty list!!! Delete option cannot be performed.");
            return;
        }
        String x = Inputter.inputStrId("Enter train code you want to delete: ");
        BSTNode f,p;
        f=null; p=root;
        while(p!=null){
            //System.out.println(p.info.getTcode() + " compare " + x);
            if(p.info.getTcode().compareTo(x) == 0){
                break;
            }
            
            f = p;
            if(p.info.getTcode().compareTo(x)>0)
                p = p.left;
            else
                p = p.right;
        }
        //not found
        if(p == null){
            System.out.println("-----RECORD NOT FOUND-----");
            return;
        }
        else{
            System.out.println("|===============================================================================================|");
            System.out.format("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n" , "TRAIN CODE", "TRAIN NAME", "N.O OF SEATS", "BOOKED SEATS", "DEPARTURE TIME", "DEPARTURE PLACE");
            System.out.println("|===============================================================================================|");
            System.out.println(p.info);
            System.out.println("|===============================================================================================|");
            if(continueYN("delete the train information")){            
                deleteByCopy(p, f);
                saveFileInorder();
            }
        } 
    }
    public void deleteByCopy(BSTNode p, BSTNode f){
        //p is a leaf node
        if(p.left==null && p.right==null){
           //p==root
            if(f==null){ 
                root=null;
                return;
            }
            if(p==f.left)
                f.left=null;
            else
                f.right=null;
        }
        // p has left son only
        if(p.left!=null && p.right==null){
            //p==root
            if(f==null) { 
                root=p.left;
                return;
            }
            if(p==f.left)
                f.left=p.left;
            else
                f.right=p.left;
        }
        //p has right son only
        if(p.left==null && p.right!=null){
            if(f==null) { // p==root
                root=p.right;
            return;
            }
            if(p==f.left)
                f.left=p.right;
            else
                f.right=p.right;
        }
        //p has both 2 sons
        if(p.left!=null && p.right!=null){
            BSTNode q=p.left;
            //find the right-most node in the left sub-tree
            BSTNode frp,rp;
            frp=null;rp=q;
            while(rp.right!=null){
                frp=rp;
                rp=rp.right;
            }
            p.info=rp.info;
            //rp==q
            if(frp==null){ 
                p.left=q.left;             
            }
            else{
                frp.right=rp.left;
            }
        }
    }
    
//////////////////////////Count number of trains//////////////////////////
    public int countNode(BSTNode root){
        if(root == null) return 0;
        return 1 + countNode(root.left) + countNode(root.right);
    }
    
//////////////////////////Balancing tree//////////////////////////
    void inOrderBL(ArrayList<Train> t, BSTNode p) {
        if(p==null) return;
        inOrderBL(t,p.left);
        t.add(p.info);
        inOrderBL(t,p.right);
    }
    void balance(ArrayList<Train> t, int i, int j) {
        int flag = 0;
        if(i>j) return;
        int k = (i+j)/2;
        insert(t.get(k));
        balance(t,i,k-1);
        balance(t,k+1,j);
    }
    public void bal() {
        ArrayList<Train> t = new ArrayList<>();
        inOrderBL(t,root);
        clear();
        int n=t.size();
        balance(t,0,n-1);
    }
    
//////////////////////////Display inorder//////////////////////////
    public void inorder(BSTNode p){
        if(p != null){
            inorder(p.left);
            System.out.println(p.info);
            inorder(p.right);
        }
    }
    
    public void displayInorder(){
        if(isEmpty()){
            System.out.println("Empty list!!!");
            return;
        }
        System.out.println("|===============================================================================================|");
        System.out.format("|%-15s|%-15s|%-15S|%-15S|%-15s|%-15s|\n" , "TRAIN CODE", "TRAIN NAME", "N.O OF SEATS", "BOOKED SEATS", "DEPARTURE TIME", "DEPARTURE PLACE");
        System.out.println("|===============================================================================================|");
        inorder(root);
        System.out.println("|===============================================================================================|");
        System.out.println("Number of trains: " + countNode(root));
    }
    
//////////////////////////Display BreadthF//////////////////////////
    void breadth(BSTNode p){
        if(p==null) return;
        MyQueue q = new MyQueue();
        q.enqueue(p); 
        BSTNode r;
        while(!q.isEmpty()){
            r=q.dequeue();
            System.out.println(r.info);
            if(r.left!=null) q.enqueue(r.left);
            if(r.right!=null) q.enqueue(r.right);
        }
    }
    public void displayBreadthF(){
        if(isEmpty()){
            System.out.println("Empty list!!!");
            return;
        }
        System.out.println("|===============================================================================================|");
        System.out.format("|%-15s|%-15s|%-15S|%-15S|%-15s|%-15s|\n" , "TRAIN CODE", "TRAIN NAME", "N.O OF SEATS", "BOOKED SEATS", "DEPARTURE TIME", "DEPARTURE PLACE");
        System.out.println("|===============================================================================================|");
        breadth(root);
        System.out.println("|===============================================================================================|");
        System.out.println("Number of trains: " + countNode(root));
    }
    
//////////////////////////Load from file//////////////////////////
    public void insert(Train x) {
        int flag = 0;
        BSTNode temp = new BSTNode(x);
        if(isDupTcode(x.getTcode())) return;
        boolean check1 = true;
        if(isEmpty()) root = temp;
        else{
            BSTNode ptr = root;
            while(check1){
                if(temp.info.getTcode().compareTo(ptr.info.getTcode()) > 0){
                    if(ptr.right != null)
                        ptr = ptr.right;
                    else{
                        ptr.right = temp;
                        check1 = false;
                        }
                    }
                    if(temp.info.getTcode().compareTo(ptr.info.getTcode()) < 0){
                        if(ptr.left != null) {
                            ptr = ptr.left;
                        }else{
                            ptr.left = temp;
                            check1 = false;
                        }
                    }           
            }    
        }       
    }
    public void loadFile() throws IOException {
        String filename = "Train.txt";
        File file = new File(filename);
        FileReader f = null;
        BufferedReader bf = null;
        String line;
        try {
            if (file.exists() == false) {
                file.createNewFile();
            }
            f = new FileReader(file);
            bf = new BufferedReader(f); 
            while ((line = bf.readLine()) != null){ 
                String[] tmp = line.split("\\|");
                if (tmp.length == 6) {
                    String tcode = tmp[0].trim();
                    String train_name = tmp[1].trim();
                    int seat = Integer.valueOf(tmp[2].replaceAll("[^0-9?!\\.]",""));
                    int booked = Integer.valueOf(tmp[3].replaceAll("[^0-9?!\\.]",""));
                    double depart_time = Double.valueOf(tmp[4].replaceAll("[^0-9?!\\.]",""));
                    String depart_place = tmp[5].trim();
                    insert(new Train(tcode, train_name, seat, booked, depart_time, depart_place));
                }
            }       
        } catch (IOException e) {
            System.out.println(e);
        }
        finally {
            try {
                if (f != null) f.close();
                if (bf!= null) bf.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
    
//////////////////////////Save to File inOrder//////////////////////////
    public void saveFileInorder() throws IOException{
        String fname = "Train.txt";
        FileWriter fw = new FileWriter(fname);
        PrintWriter pw = new PrintWriter(fw);
        inorderSave(pw, root);
        pw.close();
        fw.close();
    }
    public void inorderSave(PrintWriter pw, BSTNode p)throws IOException{
        if(p != null){
            inorderSave(pw, p.left);
            pw.println(p.info.toString());
            inorderSave(pw, p.right);
        }
    }
    
//////////////////////////Drawing BSTree//////////////////////////
    public void print(){
        print1("", root, false);
    }
    public void print1(String prefix, BSTNode n, boolean isLeft) {
        if (n != null){
            System.out.println (prefix + (isLeft ? "|-- " : "\\-- ") + n.info.getTcode());
            print1(prefix + (isLeft ? "|   " : "    "), n.left, true);
            print1(prefix + (isLeft ? "|   " : "    "), n.right, false);
        }
    }
}
