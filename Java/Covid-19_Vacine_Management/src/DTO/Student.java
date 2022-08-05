package DTO;


public class Student {
    private String stuId;
    private String stuName;
    private boolean firstInject = false;
    private boolean secondInject = false;

    public Student(String stuId, String stuName, boolean firstInject, boolean secondInject, boolean fullInject) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.firstInject = firstInject;
        this.secondInject = secondInject;
    }
    
    public Student(String stuId, String stuName) {
        this.stuId = stuId;
        this.stuName = stuName;
    }

    public boolean isFirstInject() {
        return firstInject;
    }

    public void setFirstInject(boolean firstInject) {
        this.firstInject = firstInject;
    }

    public boolean isSecondInject() {
        return secondInject;
    }

    public void setSecondInject(boolean secondInject) {
        this.secondInject = secondInject;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }
    
    @Override
    public String toString(){
        return String.format("|%-15s|%-20s|" , stuId, stuName);
    }
}
