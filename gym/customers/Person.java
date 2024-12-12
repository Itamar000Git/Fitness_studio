package gym.customers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Person{
    private int ID;
    private String name;
    private int balance;
    private String b_day;
    private Gender gen;
    private int age;
    private static int ID_runner=1111;

    public Person(String name, int balance, Gender gen, String b_day){
    this.name=name;
    this.balance=balance;
    this.gen=gen;
    this.b_day=b_day;
    this.ID=ID_runner;
    ID_runner++;
    }
    public Person(Person p1){
        this.name=p1.name;
        this.balance=p1.balance;
        this.gen=p1.gen;
        this.b_day=p1.b_day;
        this.ID=p1.ID;
    }

    public Person() {

    }

    public String getName(){
       return this.name;
    }
    public int getAge(){
        //03-07-1998
        //LocalDate today = LocalDate.now();
        LocalDate today = LocalDate.of(2024,12,28);
       int year= Integer.parseInt( b_day.substring(6));
       int m= Integer.parseInt( b_day.substring(3,5));
       int d=Integer.parseInt( b_day.substring(0,2));
       LocalDate birth = LocalDate.of(year,m,d);
       this.age= (int) ChronoUnit.YEARS.between(birth,today);

        return age;
    }
    public int getID(){
        return this.ID;
    }
    public int getBalance(){
        return this.balance;
    }
    public void setBalance(int b){
      this.balance =b;
    }
    public Gender getGen() {
        return gen;
    }

    public String getB_day() {
        return b_day;
    }
}
