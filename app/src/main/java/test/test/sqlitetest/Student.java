package test.test.sqlitetest;

public class Student {
    public String name,rollNum,ID;
    public Student(){}
    public Student(String ID,String name,String rollNum){
        setStudent(ID,name,rollNum);
    }
    public void setStudent(String ID,String name,String rollNum){
        this.ID = ID;
        this.name = name;
        this.rollNum = rollNum;
    }
}
