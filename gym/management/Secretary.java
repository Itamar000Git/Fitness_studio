package gym.management;

import gym.Exception.*;
import gym.customers.Client;
import gym.customers.Instructor;
import gym.customers.Person;
import gym.customers.factory;
import gym.management.Sessions.Session;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.SessionType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Secretary extends Person {
  private int sallary;
  private boolean access;

    private static ArrayList<Client> allClients;
    private static ArrayList<Person> allPersons; //neww
    private static ArrayList<Instructor> insArr;
    private static ArrayList<Session> sesList;
    public static ArrayList<String> logs =new ArrayList<>();

  private Secretary(Person p1, int sal){
      super(p1);
     // this.setBalance(p1.getBalance());
      this.access=true;
      this.sallary=sal;
      if (allClients == null) {
          allClients=new ArrayList<>();
          insArr=new ArrayList<>();
          sesList=new ArrayList<>();
          allPersons=new ArrayList<>();
      }
      logs.add("A new secretary has started working at the gym: "+this.getName());
  }
  private Secretary(){
      super();
  }

  public boolean checkaccess(){
      if (!this.isAccess()) {
          System.out.println("Error: Former secretaries are not permitted to perform actions");
          return false;
      }
      return true;
  }


    public Client registerClient(Person p1) throws InvalidAgeException, DuplicateClientException {
      if(!checkaccess()){
          return null;
      }

          if (p1.getAge() < 18) {
              throw new InvalidAgeException();
          }
          if (!checkIfExist(p1)) {
              throw new DuplicateClientException();
          }

          Client c1 = factory.createClient(p1);
          allPersons.add(p1);
          allClients.add(c1);
          logs.add("Registered new client: " + c1.getName());
          return c1;


  }
  private boolean checkIfExist(Person p1){
      if(!allClients.isEmpty()){
          for(int i=0;i<allClients.size(); i++){
              if(p1.getID() ==allClients.get(i).getID()){
                  return false;
              }
          }
      }

      return true;
  }

  public void unregisterClient(Client c) throws ClientNotRegisteredException {
      if(!checkaccess()){
          return;
      }
     if(checkIfExist((Person) c)){
         throw new ClientNotRegisteredException();
     }
     allClients.remove(c);
     logs.add("Unregistered client: "+c.getName());

  }
  public Instructor hireInstructor(Person p1, int p_rate, ArrayList<SessionType> arr){
      if(!checkaccess()){
          return null;
      }
      //Instructor ins = null;
     // if(!allClients.contains(p1)){
      Instructor ins = factory.createInstructor(p1,p_rate,arr);
      //    insArr.add(ins);
     // }//else{
       //   for(int i=0;i<allClients.size();i++){
             // if (allClients.get(i).equals(p1)){
                 //  ins =(Instructor) p1;
                //  ins.setp_rate(p_rate);
                //  ins.setarr(arr);
                  insArr.add(ins);
        //    //  }
        //  }
     // }


      logs.add("Hired new instructor: "+p1.getName()+" with salary per hour: "+p_rate);

      return ins;
  }


  private String strPattern(String str){
      int year,month,day,hour,minute;
      year=Integer.parseInt(str.substring(6,10));
      month=Integer.parseInt(str.substring(3,5));
      day=Integer.parseInt(str.substring(0,2));
      hour=Integer.parseInt(str.substring(11,13));
      minute=Integer.parseInt(str.substring(14));
     // "01-01-2025 14:00"
      //2025-01-23T10:00
      LocalDateTime dateTime =LocalDateTime.of(year,month,day,hour,minute);
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
      String pattern_str=dateTime.format(formatter);
        return pattern_str;
  }

public Session addSession(SessionType s, String str, ForumType f_type, Instructor ins) throws InstructorNotQualifiedException {
    if(!checkaccess()){
        return null;
    }
    String patt_str=strPattern(str);

      if(ins.getqualifiedList().contains(s)) {
          Session ses =factory.createSession(s, str, f_type, ins);
          logs.add("Created new session: "+s.toString()+" on "+patt_str+" with instructor: "+ ins.getName());
          sesList.add(ses);
          return ses;
      }else throw new InstructorNotQualifiedException();
}


public boolean registerClientToLesson(Client c, Session s) throws DuplicateClientException, ClientNotRegisteredException {
    if(!checkaccess()){
        return false;
    }
    int y,m,d;
    LocalDate today = LocalDate.now();
    y=Integer.parseInt(s.getDate().substring(6,10));
    m=Integer.parseInt(s.getDate().substring(3,5));
    d=Integer.parseInt(s.getDate().substring(0,2));
    LocalDate secDate=LocalDate.of(y,m,d);
    int comp=secDate.compareTo(today);
    boolean b=true;


    if(!allClients.contains(c)){
          throw new ClientNotRegisteredException();
    }
    if(c.getpersonalSessionList().contains(s)){
        throw new DuplicateClientException();
    }
    if (comp<0) {
        logs.add("Failed registration: Session is not in the future");
        b= false;
    }
       if (!c.getMyForums().contains(s.getForum())){
          if(s.getForum()==ForumType.Seniors){
              logs.add("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");

          }
          else{
              logs.add("Failed registration: Client's gender doesn't match the session's gender requirements");

          }
          b= false;
      }

       if (c.getBalance()<s.getCost()) {
          logs.add("Failed registration: Client doesn't have enough balance");

          b= false;
      }
        if (s.getCapacity()==s.getPart()) {
          logs.add("Failed registration: No available spots for session");

          b= false;
      }
      if (!b){
          return b;
      }

      c.setBalance(c.getBalance()-s.getCost());
      s.setPart();
      c.getpersonalSessionList().add(s);
      s.getPartArr().add(c);
    Gym.getInstance().setBalance(Gym.getInstance().getBalance()+s.getCost()); //neww

    if(this.getID()==c.getID()){//neww
        syncsecBalance(c,this);
    }

    for(int i=0;i<insArr.size(); i++){
        if (insArr.get(i).getID()==c.getID()){
            syncinsBalance(c,insArr.get(i));
        }
    }
    for (int i=0; i<allPersons.size(); i++){ //neww
        if (allPersons.get(i).getID()==c.getID()){
            syncPerBalance(c,allPersons.get(i));
        }
    }


      logs.add("Registered client: "+c.getName()+" to session: "+s.getType()+" on "+strPattern(s.getDate())+" for price: "+s.getCost());

return b;

}
public void syncPerBalance(Client c, Person p){ //neww
        p.setBalance(c.getBalance());
  }
public void syncinsBalance(Client c, Instructor ins){
   ins.setBalance(c.getBalance());
}
public void syncsecBalance(Client c, Secretary sec){ //neww
      sec.setBalance(c.getBalance());
}


public void notify(Session s, String str){
    if(!checkaccess()){
        return;
    }
      for(int i=0; i<s.getPartArr().size();i++){
        s.getPartArr().get(i).update(str); //neww
      }
    logs.add("A message was sent to everyone registered for session "+s.getType()+" on "+strPattern(s.getDate())+" : The instructor will be a few minutes late for the session");

}
public void notify(String str1, String str2){
    if(!checkaccess()){
        return;
    }
//    int y,m,d;
//    LocalDate today = LocalDate.now();
//    y=Integer.parseInt(s.getDate().substring(6,10));  //need to change to comparator
//    m=Integer.parseInt(s.getDate().substring(3,5));
//    d=Integer.parseInt(s.getDate().substring(0,2));
//    LocalDate secDate=LocalDate.of(y,m,d);
//    int comp=secDate.compareTo(today);

     // LocalDate date1 = LocalDate.parse(str1);
      boolean flag=false;
      for (int i=0; i<allClients.size(); i++){
          for (int j=0; j<allClients.get(i).getpersonalSessionList().size();j++){

              String sub=allClients.get(i).getpersonalSessionList().get(j).getDate().substring(0,10);
              //LocalDate date2=LocalDate.parse(sub);
              if (str1.equals(sub) && !allClients.get(i).getNotifications().contains(str2)){
                  allClients.get(i).update(str2);
                  flag=true;
              }
          }

      }
      if (flag){
          logs.add("A message was sent to everyone registered for a session on "+strPattern(str1+"T11:11").substring(0,10)+" : Heavy traffic reported around the gym today. Plan ahead to avoid missing your session!");//neww
      }

  }

public void notify(String str){
    if(!checkaccess()){
        return;
    }
    boolean flag=false;
    for(int i=0; i<allClients.size();i++){
       allClients.get(i).update(str);
       flag=true;
    }

    if (flag){
        logs.add("A message was sent to all gym clients: Happy New Year to all our valued clients!");
    }

  }

  public void paySalaries(){
      if(!checkaccess()){
          return;
      }

      this.setBalance(this.getBalance()+sallary);

      Gym.getInstance().setBalance(Gym.getInstance().getBalance()-sallary);

      for (int i=0;i<insArr.size();i++){
        int sal=insArr.get(i).getSessionCount()*insArr.get(i).getPayrate();
        //insArr.get(i).updatedSallery(sal);
          insArr.get(i).setBalance( insArr.get(i).getBalance()+sal);
          Gym.getInstance().setBalance(Gym.getInstance().getBalance()-sal);
          for (int j=0; j<allClients.size(); j++){
              if(allClients.get(j).getID()==insArr.get(i).getID()) {
                  syncClientBalance(allClients.get(j), insArr.get(i));
              }
              if (allClients.get(j).getID()==this.getID()) {  //neww
                  syncClientBalance(allClients.get(j),this);
              }
          }

      }
      logs.add("Salaries have been paid to all employees");

  }
    public void syncClientBalance(Client c, Instructor ins){
        c.setBalance(ins.getBalance());
    }
    public void syncClientBalance(Client c, Secretary sec){ //neww
        c.setBalance(sec.getBalance());
    }
  public void printActions(){
      if(!checkaccess()){
          return;
      }

      for (int i=0;i<logs.size(); i++){
          System.out.println(logs.get(i));
      }
  }

  public boolean isAccess(){
      return this.access;
  }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public static Secretary myContructor(Person p1, int sal){

        return new Secretary(p1, sal);
    }

    public ArrayList<Client> getAllClients(){
        return allClients;
    }
    public ArrayList<Instructor> getinsArr(){
      return insArr;
    }
    public ArrayList<Session> getSesList(){
      return sesList;
    }
    @Override
    public String toString(){
        ;
       // ID: 1113 | Name: Maayan | gym.customers.Gender: Female | Birthday: 21-12-2005 | Age: 19 | Balance: 50 | Role: gym.management.Secretary | Salary per Month: 8000
      return ("ID: "+this.getID()+" | Name: "+this.getName()+" | Gender: "+ this.getGen()+
              " | Birthday: "+this.getB_day()+" | Age: "+this.getAge()+" | Balance: "+ this.getBalance()+" | Role: Secretary"+" | Salary per Month: "+this.sallary);
    }
    public String toString2(){

        // ID: 1113 | Name: Maayan | gym.customers.Gender: Female | Birthday: 21-12-2005 | Age: 19 | Balance: 50 | Role: gym.management.Secretary | Salary per Month: 8000
        return ("ID: "+this.getID()+" | Name: "+this.getName()+" | Gender: "+ this.getGen()+
                " | Birthday: "+this.getB_day()+" | Age: "+this.getAge()+" | Balance: "+ this.getBalance()+" | Role: Secretary | Salary per Month: "+this.sallary);
    }


}
