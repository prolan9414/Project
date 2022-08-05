package DTO;

import Feature.Colour;
import Feature.Inputter;
import Feature.ReadFile;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;



public class InjectionList extends ArrayList<Injection> implements ReadFile, Colour{
    ArrayList<Student> stu = new ArrayList<>();
    ArrayList<Vaccine> vac = new ArrayList<>();
    ArrayList<Place> pla = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    
    
    public InjectionList() {
        super();
    }
    
    public boolean continueYN(String nn){
        System.out.println("---------------------------------------------------\n");
        System.out.println(TEXT_BLUE+"Do you want to " +nn+" ?"+TEXT_RESET);
        System.out.println(TEXT_BLUE+"1. Yes\n2. No"+TEXT_RESET);
        System.out.print(TEXT_PURPLE+"Input: "+TEXT_RESET);
        boolean check=true;
        int choice=0;
        while(check){
            try{
                choice = Integer.parseInt(sc.nextLine());
                if(choice==1||choice==2){
                    check=false;
                }
                else{
                    System.out.print(TEXT_RED +"Please enter 1 or 2: "+ TEXT_RESET);  
                }
            }catch(NumberFormatException e){
                System.out.print(TEXT_RED + "Wrong format. Please enter again: " + TEXT_RESET);
            }
        }
        System.out.println("\n");
        return choice==1;
    }
    
    public Injection searchInject(String ID){
        ID=ID.trim().toUpperCase();
        for (Injection aThi : this) {
            if (aThi.getInjectID().equals(ID)) {
                return aThi;
            }
        }
        return null;
    }
    public Injection searchInject2(String ID){
        ID=ID.trim().toUpperCase();
        for (Injection aThi : this) {
            if (aThi.getStuID().equals(ID)) {
                return aThi;
            }
        }
        return null;
    }
    public Student searchStudent(String ID){
        ID=ID.trim().toUpperCase();
        for (Student aThi : stu) {
            if (aThi.getStuId().replaceAll("[^a-zA-Z0-9]", "").equalsIgnoreCase(ID)) {
                return aThi;
            }
        }
        return null;
    }
    public Vaccine searchVaccine(String ID){
        ID=ID.trim().toUpperCase();
        for (Vaccine aThi : vac) {
            if (aThi.getVacID().equals(ID)) {
                return aThi;
            }
        }
        return null;
    }
    public Place searchPlace(String place){
        place=place.trim();
        for (Place aThi : pla) {
            if (aThi.getPlaceName().replaceAll("[^a-zA-Z0-9]", "").equalsIgnoreCase(place.replaceAll("[^a-zA-Z0-9]", ""))) {
                return aThi;
            }
        }
        return null;
    }
    
    private boolean isIdDupplicated(String ID){
        ID = ID.trim().toUpperCase();
        return searchInject(ID) != null;
    }
    
    public Vaccine getVaccineChoice(){
        int result = 0;
        String chosen;
        boolean cont = true;
        //System.out.println("===================================================");
        if(vac.size()>0){
            for(int i=0; i<vac.size(); i++)
                System.out.println(TEXT_BLUE+(i+1)+" - "+vac.get(i).getVacName()+TEXT_RESET);
            System.out.println("===================================================");
            System.out.print(TEXT_PURPLE+"Please select a vaccine: "+TEXT_RESET);
            do{
                try{
                    result = Integer.parseInt(sc.nextLine());
                    if(result<1||result>6) System.out.print(TEXT_RED+"Please enter the number from 1 to 6: "+TEXT_RESET);
                    cont=false;
                }catch(NumberFormatException e){
                    System.out.print(TEXT_RED+"Wrong format. Please enter again: "+TEXT_RESET);
                    cont=true;
                }
            }while(cont==true||result<1||result>6);
            
            
        }
        return vac.get(result-1);
    }
    
    public void addInject() throws ParseException{
        
        checkIn();
        String newInID, newPlace;
        String newStuName="", newStuID;
        String newVacName, newVacID;
        boolean q=true, stuCheck = true, vacCheck = true, placeCheck = true;
        Date firstDate = null;
        String secondDate = "N/A";
        String sePlace = "N/A"; 
        int newsoluong;
        boolean idDup;
        //Input Student ID and find student name
        System.out.println("\n");
        printAllStudent();
        do{
            newStuID = Inputter.inputStrId(TEXT_PURPLE+"Student ID: "+TEXT_RESET);
            newStuID=newStuID.toUpperCase();
            if(searchStudent(newStuID)==null) {
                System.out.println(TEXT_RED+"No student can be found."+TEXT_RESET);
                continue;
            }
            Student stu1 = searchStudent(newStuID);
            if(stu1.isFirstInject()){
                System.out.println(TEXT_RED+"Student already has a Injection information."+TEXT_RESET);
            }
            else{
                //System.out.println("======================================");
                System.out.println(TEXT_GREEN+"\nFOUND!!!"+TEXT_RESET);
                System.out.format("|%-15s|%-20s|\n" ,"ID STUDENT", "STUDENT NAME");
                System.out.println("|===============|====================|");
                //System.out.println("|===================|===========================|");
                System.out.println(stu1);
                searchStudent(newStuID).setFirstInject(true);
                newStuName = stu1.getStuName();
                stuCheck = false;
            }
        }while(stuCheck);
        //=====================input Injection ID===================================
        System.out.println("\n");
        do{
            newInID = Inputter.inputPattern(TEXT_PURPLE+"Injection ID: "+TEXT_RESET, "[sI][sN][sJ][\\d]{6}");
            newInID=newInID.toUpperCase();
            idDup=isIdDupplicated(newInID);
            if(idDup) System.out.println(TEXT_RED+"The injection ID is dupplicated. Please enter again."+TEXT_RESET);
        }while(idDup==true);
        //chose vaccine
        System.out.println("");
        Vaccine choVac = getVaccineChoice();
        newVacID = choVac.getVacID();
        newVacName = choVac.getVacName();
        System.out.println(TEXT_GREEN+"Vaccine: "+newVacName+TEXT_RESET);
        System.out.println("===================================================");
        //input place
        System.out.println("\n");
        do{
            newPlace = Inputter.inputStr(TEXT_PURPLE+"First Injection Province: "+TEXT_RESET).trim();
            Place pl = searchPlace(newPlace);
            if(pl==null) System.out.println(TEXT_RED+"No province can be found."+TEXT_RESET);
            else {
                newPlace = pl.getPlaceName();
                placeCheck=false;
            }
        }while(placeCheck);
        //inputDate
        //ngay dau tien ngay 8/3/2021 va truoc ngay hien tai
        //mui 2 sau mui 4 tuan
        String fDate="8/3/2021";  
        Date fDate1=new SimpleDateFormat("dd/MM/yyyy").parse(fDate);  
        while(q){
            try{
                SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
                System.out.print(TEXT_PURPLE+"The first injection date(dd/MM/yyyy): "+TEXT_RESET);
                String dateInput = sc.nextLine();
                df.setLenient(false);
                firstDate=df.parse(dateInput);
                if(fDate1.before(firstDate)){
                    q=false;
                }
                else{
                    System.out.println(TEXT_RED+"The date input must be after 8/3/2021"+TEXT_RESET);
                }
            }catch(ParseException e){
                System.out.println(TEXT_RED+"Wrong day format. Please enter again."+TEXT_RESET);
            }
        }
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        String strFirstDate = dateFormat.format(firstDate);  
        
        Injection newInjection = new Injection(newInID, newPlace, strFirstDate, sePlace, secondDate, newStuID, newStuName, newVacID, newVacName);
        this.add(newInjection);
        System.out.println("---------------------------------------------------");
        System.out.println(TEXT_GREEN+"    New injection information has been added!!!"+TEXT_RESET);
        saveToFile();
        if(continueYN("continue to add more Injection")){            
            addInject();          
        }
    }
    
    public void updateInject() throws ParseException{
        Date seDate = null;
        String sePlace;
        boolean placeCheck = true, q = true;
        Date date1, date2;
        if(this.isEmpty()){
            System.out.println(TEXT_RED+"Empty list. Update option cannot be performed!"+TEXT_RESET);
            return;
        }
        else{
            //System.out.println("--------------------------------------------------"); 
            String sId = Inputter.inputPattern(TEXT_PURPLE+"Input Injection ID to update: "+TEXT_RESET, "[sI][sN][sJ][\\d]{6}");
            Injection st = this.searchInject(sId);
            if(st==null){
                System.out.println(TEXT_RED+"Injection ID "+sId+" doesn't existed!"+TEXT_RESET);
                return;
            }
            else{
                if(searchStudent(st.getStuID()).isSecondInject()){
                    System.out.println(TEXT_RED+"Student has completed 2 injections."+TEXT_RESET);
                    return;
                }
                //input place
                do{
                    sePlace = Inputter.inputStr(TEXT_PURPLE+"Second Injection Province: "+TEXT_RESET).trim();
                    Place pl = searchPlace(sePlace);
                    if(pl==null) System.out.println(TEXT_RED+"No province can be found."+TEXT_RESET);
                    else {
                        sePlace = pl.getPlaceName();
                        placeCheck=false;
                    }
                }while(placeCheck);
                
                String fDate = st.getFirstDate();
                Date fDate1=new SimpleDateFormat("dd/MM/yyyy").parse(fDate);
                SimpleDateFormat to=new SimpleDateFormat("dd/MM/yyyy");
                Calendar c4 = Calendar.getInstance();
                Calendar c12 = Calendar.getInstance();
                c4.setTime(fDate1);
                c4.add(Calendar.DATE, 28);
                date1 = c4.getTime();
                c12.setTime(fDate1);
                c12.add(Calendar.DATE, 84);
                date2 = c12.getTime();
                while(q){
                    try{
                        SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
                        System.out.print(TEXT_PURPLE+"The second injection date(dd/MM/yyyy): "+TEXT_RESET);
                        String dateInput = sc.nextLine();
                        df.setLenient(false);
                        seDate=df.parse(dateInput);
                        if((date1.before(seDate)||date1.equals(seDate))&&(date2.after(seDate)||date2.equals(seDate))){
                            q=false;
                        }
                        else{
                            System.out.println(TEXT_RED+"The 2nd Injection must be from 4 weeks("+to.format(date1)+") to 12 weeks("+to.format(date2)+")!!!"+TEXT_RESET);
                        }
                    }catch(ParseException e){
                        System.out.println(TEXT_RED+"Wrong day format. Please enter again."+TEXT_RESET);
                    }
                }
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        String strSeDate = dateFormat.format(seDate);
        st.setSecondDate(strSeDate);
        st.setSecondPlace(sePlace);
        System.out.format("%-15s|%-15s|%-24s|%-15s|%-15s|%-20s|%-15s|%-20s|%-15s\n" 
                ,"INJECTION ID","STUDENT ID","STUDENT NAME","VACCINE ID","VACCINE NAME","FIRST PLACE","FIRST DATE","SECOND PLACE","SECOND DATE");
        System.out.println(st);
        if(continueYN("update the information")){
            this.searchInject(sId).setSecondPlace(sePlace);
            this.searchInject(sId).setSecondDate(strSeDate);
            searchStudent(st.getStuID()).setSecondInject(true);
            saveToFile();
            System.out.println("---------------------------------------------------");
            System.out.println(TEXT_GREEN+"Injection ID "+sId+" has been updated."+TEXT_RESET);
        }
            }
        }
    }
    
    public void deleteInject(){
        if(this.isEmpty()){
            System.out.println(TEXT_RED+"Empty list. Delete option cannot be performed!"+TEXT_RESET);
            return;
        }
            
        else{
            System.out.println("--------------------------------------------------"); 
            String sId = Inputter.inputPattern(TEXT_PURPLE+"Input Injection ID to removed: "+TEXT_RESET, "[sI][sN][sJ][\\d]{6}");
            Injection st = this.searchInject(sId);
            if(st==null){
                System.out.println(TEXT_RED+"Injection ID "+sId+" doesn't existed!"+TEXT_RESET);
                return;
            }
                
            else{
                if(continueYN("remove the information")){
                    this.remove(st);
                    saveToFile();
                    System.out.println(TEXT_GREEN+"Injection ID "+sId+" has been removed."+TEXT_RESET);
                }
            }
        }
        if(!this.isEmpty()){
           printInjectionList(); 
        }
        
    }
    
    public void searchInjectInfo(){
        if(this.isEmpty()){
            System.out.println(TEXT_RED+"Empty list. No search can be performed!"+TEXT_RESET);
            return;
        }
        else{
            String sstuID = Inputter.inputStrId(TEXT_PURPLE+"Input student ID to search: "+TEXT_RESET);
            Injection st = searchInject2(sstuID);
            if(st==null){
                System.out.println(TEXT_RED+"NOT FOUND!!!"+TEXT_RESET);
            }
            else{
                System.out.println(TEXT_GREEN+"FOUND!!!"+TEXT_RESET);
                System.out.format("%-15s|%-15s|%-24s|%-15s|%-15s|%-20s|%-15s|%-20s|%-15s\n" 
                    ,"INJECTION ID","STUDENT ID","STUDENT NAME","VACCINE ID","VACCINE NAME","FIRST PROVINCE","FIRST DATE","SECOND PROVINCE","SECOND DATE");
                System.out.println(st);
                
            }
        }
        if(continueYN("continue to search")){
                searchInjectInfo();
        }
    }
    
    public void searchVaccine2(String vac){
        int count=0;
        vac=vac.trim();
        for (Injection aThi : this) {
            if (aThi.getVacName().replaceAll("[^a-zA-Z0-9]", "").equalsIgnoreCase(vac.replaceAll("[^a-zA-Z0-9]", ""))) {
                if(count==0) System.out.format("%-15s|%-15s|%-24s|%-15s|%-15s|%-20s|%-15s|%-20s|%-15s\n" 
                    ,"INJECTION ID","STUDENT ID","STUDENT NAME","VACCINE ID","VACCINE NAME","FIRST PROVINCE","FIRST DATE","SECOND PROVINCE","SECOND DATE");
                System.out.println(aThi);
                count++;
            }
        }
        if(count==0) System.out.println(TEXT_RED+"NOT FOUND!!!"+TEXT_RESET);
    }
    
    public void searchInjectInfoVaccine(){
        if(this.isEmpty()){
            System.out.println(TEXT_RED+"Empty list. No search can be performed!"+TEXT_RESET);
            return;
        }
        else{
            String sstuVac = Inputter.inputStr(TEXT_PURPLE+"Input Vaccine name to search: "+TEXT_RESET);
            searchVaccine2(sstuVac);
        }
        if(continueYN("continue to search")){
                searchInjectInfoVaccine();
        }
    }
    
    
    public void checkIn(){
        for(Student stu1 : stu){
            for(Injection in : this){
                if(stu1.getStuId().equals(in.getStuID())){
                    stu1.setFirstInject(true);
                    if(in.getSecondPlace().replaceAll("[^a-zA-Z0-9]", "").equalsIgnoreCase("NA"))
                        stu1.setSecondInject(false);
                    else stu1.setSecondInject(true);
                }
            }
        }
    }
    
//    public void saveToFile(){
//        if(this.isEmpty()){
//            System.out.println(TEXT_RED+"Empty list."+TEXT_RESET);
//            return;
//        }
//        try{
//            File f = new File("InjectionList.txt");
//            FileWriter faw = new FileWriter(f);
//            PrintWriter pw = new PrintWriter(faw);
//            for(Injection in : this){
//                pw.println(in.getInjectID()+";"+in.getStuID()+";"+in.getStuName()+";"+in.getVacID()
//                        +";"+in.getVacName()+";"+in.getFirstPlace()+";"+in.getFirstDate()
//                        +";"+in.getSecondPlace()+";"+in.getSecondDate());
//            }
//            pw.close(); faw.close();
//        }catch(IOException e){
//            System.out.println(e);
//        }
//    }
    public void saveToFile(){
        if(this.isEmpty()){
            System.out.println(TEXT_RED+"Empty list."+TEXT_RESET);
            return;
        }
        try{
            File f = new File("InjectionList.txt");
            FileWriter faw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(faw);
            for(Injection in : this){
                pw.println(in.getInjectID()+";"+in.getStuID()+";"+in.getVacID()
                        +";"+in.getFirstPlace()+";"+in.getFirstDate()
                        +";"+in.getSecondPlace()+";"+in.getSecondDate());
            }
            pw.close(); faw.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    public void readFromFileInjection() {
        File file = new File("InjectionList.txt");
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
                String[] tmp = line.split(";");
                if (tmp.length == 7) {
                    String injectID = tmp[0];
                    String stuID = tmp[1];
                    String vacID = tmp[2];
                    String firstPlace = tmp[3];
                    String firstDate = tmp[4];
                    String secondPlace = tmp[5];
                    String secondDate = tmp[6];
                    Injection inject1 = new Injection(injectID, firstPlace, firstDate, secondPlace, secondDate, stuID, vacID);
                    this.add(inject1);
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
    
    public void addRead(){
        
        for(Injection in : this){
            for(Student stu1 : stu){
                if(stu1.getStuId().equals(in.getStuID())){
                    in.setStuName(stu1.getStuName());
                }
            }
            for(Vaccine vac1 : vac){
                if(vac1.getVacID().equals(in.getVacID())){
                    in.setVacName(vac1.getVacName());
                }
            }
        }
    }
//    public void readFromFileInjection() {
//        File file = new File("InjectionList.txt");
//        FileReader f = null;
//        BufferedReader bf = null;
//        
//        String line;
//        try {
//            if (file.exists() == false) {
//                file.createNewFile();
//            }
//            f = new FileReader(file);
//            bf = new BufferedReader(f); 
//            while ((line = bf.readLine()) != null){ 
//                String[] tmp = line.split(";");
//                if (tmp.length == 9) {
//                    String injectID = tmp[0];
//                    String stuID = tmp[1];
//                    String stuName = tmp[2];
//                    String vacID = tmp[3];
//                    String vacName = tmp[4];
//                    String firstPlace = tmp[5];
//                    String firstDate = tmp[6];
//                    String secondPlace = tmp[7];
//                    String secondDate = tmp[8];
//                    Injection inject1 = new Injection(injectID, firstPlace, firstDate, secondPlace, secondDate, stuID, stuName, vacID, vacName);
//                    this.add(inject1);
//                }
//            }       
//        } catch (IOException e) {
//            System.out.println(e);
//        }
//        finally {
//            try {
//                if (f != null) f.close();
//                if (bf!= null) bf.close();
//            } catch (IOException e) {
//                System.out.println(e);
//            }
//        }
//    }
    
    @Override
    public void readFromFileStudent() {
        File file = new File("student.txt");
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
                String[] tmp = line.split(";");
                if (tmp.length == 2) {
                    String stuID = tmp[0];
                    String stuName = tmp[1];
                    Student stu1 = new Student(stuID, stuName);
                    stu.add(stu1);
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
    
    @Override
    public void readFromFileVaccine() {
        File file = new File("vaccine.txt");
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
                String[] tmp = line.split("\\;");
                if (tmp.length == 2) {
                    String vacID = tmp[0];
                    String vacName = tmp[1];
                    Vaccine va1 = new Vaccine(vacID, vacName);
                    vac.add(va1);
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
    
    @Override
    public void readFromFilePlace() {
        File file = new File("place.txt");
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
                String[] tmp = line.split("\\t");
                if (tmp.length == 2) {
                    int noPlace = Integer.valueOf(tmp[0]);
                    String placeName = tmp[1];
                    Place pla1 = new Place(noPlace, placeName);
                    pla.add(pla1);
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
    
    public void printAllStudent(){
        if(stu.isEmpty())
            System.out.println(TEXT_RED+"Empty list."+TEXT_RESET);
        else{
            System.out.format("|%-15s|%-20s|\n" ,"ID STUDENT", "STUDENT NAME");
            System.out.println("|===============|====================|");
            for(Student st: stu){
                System.out.println(st);
            }
            System.out.println("|====================================|");
            System.out.println("|        Total: "+stu.size()+" student(s).        |");
        }
    }
    
    public void printAllVaccine(){
        if(vac.isEmpty())
            System.out.println(TEXT_RED+"Empty list."+TEXT_RESET);
        else{
            System.out.format("%-15s|%-20s|\n" ,"ID VACCINE", "VACCINE NAME");
            System.out.println("|===============|====================|");
            for(Vaccine st: vac){
                System.out.println(st);
            }
            System.out.println("|====================================|");
            System.out.println("Total: "+vac.size()+" vaccine(s).");
        }
    }
    
    public void printAllPlace(){
        if(pla.isEmpty())
            System.out.println(TEXT_RED+"Empty list."+TEXT_RESET);
        else{
            System.out.println("-------------------------------------------------------------------------------------------------------");
            System.out.format("%-10s|%-30s|\n" ,"NO ", "PROVINE NAME");
            int count=0;
            for(Place st: pla){
                if(count%5==0) {
                    System.out.println(st);
                }
                System.out.print(st);
                count++;
            }
            System.out.println("-------------------------------------------------------------------------------------------------------");
        }
    }
    
    public void printInjectionList(){
        if(this.isEmpty())
            System.out.println(TEXT_RED+"Empty list."+TEXT_RESET);
        else{
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.format("%-15s|%-15s|%-24s|%-15s|%-15s|%-20s|%-15s|%-20s|%-15s\n" ,"INJECTION ID","STUDENT ID","STUDENT NAME","VACCINE ID","VACCINE NAME","FIRST PLACE","FIRST DATE","SECOND PLACE","SECOND DATE");
            System.out.println("---------------|---------------|------------------------|---------------|---------------|--------------------|"
                    + "---------------|--------------------|-----------------");
            for(Injection st: this){
                System.out.println(st);
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
        
    }
    
}
