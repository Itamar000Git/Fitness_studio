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
import java.util.ArrayList;

public class Secretary extends Person {
  private int sallary;
  private boolean access;

    private static ArrayList<Client> allClients;
    private static ArrayList<Person> allPersons;
    private static ArrayList<Instructor> insArr;
    private static ArrayList<Session> sesList;
    public static ArrayList<String> logs =new ArrayList<>();

  private Secretary(Person p1, int sal){
      super(p1);

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

  public boolean checkaccess(){
      if (!this.isAccess()) {
          throw new NullPointerException();
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
              throw new DuplicateClientException("Error: The client is already registered");
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
         throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");
     }

     allClients.remove(c);
     logs.add("Unregistered client: "+c.getName());

  }
  public Instructor hireInstructor(Person p1, int p_rate, ArrayList<SessionType> arr){
      if(!checkaccess()){
          return null;
      }
      Instructor ins = factory.createInstructor(p1,p_rate,arr);
                  insArr.add(ins);
      logs.add("Hired new instructor: "+p1.getName()+" with salary per hour: "+p_rate);

      return ins;
  }

public Session addSession(SessionType s, String str, ForumType f_type, Instructor ins) throws InstructorNotQualifiedException {
    if(!checkaccess()){
        return null;
    }
    String patt_str=PatternModify.strPattern(str);

      if(ins.getqualifiedList().contains(s)) {
          Session ses = SessionFactory.createSession(s, str, f_type, ins); //need to change to session factory
          logs.add("Created new session: "+ s +" on "+patt_str+" with instructor: "+ ins.getName());
          sesList.add(ses);
          return ses;
      }else throw new InstructorNotQualifiedException();
}

public boolean registerClientToLesson(Client c, Session s) throws DuplicateClientException, ClientNotRegisteredException {
    if(!checkaccess()){
        return false;
    }
    LocalDate today = LocalDate.now();
    LocalDate todaytmp =LocalDate.of(2024,12,28);/////*****for test
    LocalDate secDate=PatternModify.Dateptr(s);
    int comp=secDate.compareTo(todaytmp);/////*****for test


   // int comp=secDate.compareTo(today);

    boolean b=true;
    if(!allClients.contains(c)){
          throw new ClientNotRegisteredException("Error: The client is not registered with the gym and cannot enroll in lessons");
    }
    if(c.getpersonalSessionList().contains(s)){
        throw new DuplicateClientException("Error: The client is already registered for this lesson");
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
      Gym.getInstance().setBalance(Gym.getInstance().getBalance()+s.getCost());

    if(this.getID()==c.getID()){
        Balance.syncsecBalance(c,this);
    }

    for(int i=0;i<insArr.size(); i++){
        if (insArr.get(i).getID()==c.getID()){
            Balance.syncinsBalance(c,insArr.get(i));
        }
    }
    for (int i=0; i<allPersons.size(); i++){
        if (allPersons.get(i).getID()==c.getID()){
            Balance.syncPerBalance(c,allPersons.get(i));
        }
    }
      logs.add("Registered client: "+c.getName()+" to session: "+s.getType()+" on "+PatternModify.strPattern(s.getDate())+" for price: "+s.getCost());

return b;

}

public void notify(Session s, String str){
    if(!checkaccess()){
        return;
    }
    GymNotify.notify(s,str,logs);
}
public void notify(String str1, String str2){
    if(!checkaccess()){
        return;
    }
    GymNotify.notify(str1,str2,allClients,logs);
  }

public void notify(String str){
    if(!checkaccess()){
        return;
    }
    GymNotify.notify(str,allClients,logs);

  }


  public void paySalaries(){
      if(!checkaccess()){
          return;
      }
      this.setBalance(this.getBalance()+sallary);

      Gym.getInstance().setBalance(Gym.getInstance().getBalance()-sallary);

      for (int i=0;i<insArr.size();i++){
        int sal=insArr.get(i).getSessionCount()*insArr.get(i).getPayrate();
          insArr.get(i).setBalance( insArr.get(i).getBalance()+sal);
          Gym.getInstance().setBalance(Gym.getInstance().getBalance()-sal);
          for (int j=0; j<allClients.size(); j++){
              if(allClients.get(j).getID()==insArr.get(i).getID()) {
                  Balance.syncClientBalance(allClients.get(j), insArr.get(i));
              }
              if (allClients.get(j).getID()==this.getID()) {
                  Balance.syncClientBalance(allClients.get(j),this);
              }
          }

      }
      logs.add("Salaries have been paid to all employees");

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
      return ("ID: "+this.getID()+" | Name: "+this.getName()+" | Gender: "+ this.getGen()+
              " | Birthday: "+this.getB_day()+" | Age: "+this.getAge()+" | Balance: "+ this.getBalance()+" | Role: Secretary"+" | Salary per Month: "+this.sallary);
    }
//    public String toString2(){ //maybe not importent
//        // ID: 1113 | Name: Maayan | gym.customers.Gender: Female | Birthday: 21-12-2005 | Age: 19 | Balance: 50 | Role: gym.management.Secretary | Salary per Month: 8000
//        return ("ID: "+this.getID()+" | Name: "+this.getName()+" | Gender: "+ this.getGen()+
//                " | Birthday: "+this.getB_day()+" | Age: "+this.getAge()+" | Balance: "+ this.getBalance()+" | Role: Secretary | Salary per Month: "+this.sallary);
//    }
}
