package gym.customers;

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
            int year= Integer.parseInt( b_day.substring(6));
            this.age=2024-year;
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
