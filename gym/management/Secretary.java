package gym.management;

import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InstructorNotQualifiedException;
import gym.Exception.InvalidAgeException;
import gym.customers.Client;
import gym.customers.Instructor;
import gym.customers.Person;
import gym.management.Sessions.Session;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.SessionType;

import java.time.LocalDate;
import java.util.ArrayList;

public class Secretary extends Person{
  private int sallary;
 // private gym.customers.Person sec;

    private static ArrayList<Client> allClients;
    public static ArrayList<String> logs =new ArrayList<>();

  public Secretary(Person p1, int sal){
      super(p1);
      //we need to make sure there is only one sec and the old ond cant do any changes.
       //   this.sec=p1;
          this.sallary=sal;
          allClients=new ArrayList<>();
      logs.add("A new secretary has started working at the gym: "+this.getName());
  }

  public Client registerClient(Person p1) throws InvalidAgeException, DuplicateClientException {
      if(p1.getAge()<18){
          throw new InvalidAgeException();
      }
      if (!checkIfExist(p1)){
          throw new DuplicateClientException();
      }

      Client c1=factory.createClient(p1);//neww
      allClients.add(c1);
      logs.add("Registered new client: "+c1.getName());
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
     if(checkIfExist((Person) c)){
         throw new ClientNotRegisteredException();
     }
     allClients.remove(c);
     logs.add("Unregistered client: "+c.getName());

  }
  public Instructor hireInstructor(Person p1, int p_rate, ArrayList<SessionType> arr){
      Instructor ins = factory.createInstructor(p1,p_rate,arr);//neww

      ///need to be completed
      logs.add("Hired new instructor: "+p1.getName()+" with salary per hour: "+p_rate);

      return ins;
  }

public Session addSession(SessionType s, String str, ForumType f_type, Instructor ins) throws InstructorNotQualifiedException {
      //need to chack if qualified, if he's available

      if(ins.getqualifiedList().contains(s)) {
          Session ses =factory.createSession(s, str, f_type, ins);//neww

          logs.add("Created new session: "+s.toString()+" on "+str+" with instructor: "+ ins); //not full


          return ses;
      }else throw new InstructorNotQualifiedException();
}


public boolean registerClientToLesson(Client c, Session s) throws DuplicateClientException, ClientNotRegisteredException {
    LocalDate today = LocalDate.now();
    String str1=s.getDate().substring(0,10);
    LocalDate secDate = LocalDate.parse(str1);
    int comp=secDate.compareTo(today);



      if(!allClients.contains(c)){
          throw new ClientNotRegisteredException();
      } else if(c.getpersonalSessionList().contains(s)){
        throw new DuplicateClientException();
      } else if (c.getBalance()<s.getCost()) {
          logs.add("Failed registration: gym.customers.Client doesn't have enough balance");
          //System.out.println();
          return false;
      } else if (comp<0) {
          logs.add("Failed registration: gym.management.Sessions.Session is not in the future");
         // System.out.println(logs.getLast());
          
      } else if (s.getCapacity()==s.getPart()) {
          logs.add("Failed registration: No available spots for session");
          //System.out.println();
          return false;

      } else if (!c.getMyForums().contains(s.getForum())){
          if(s.getForum()==ForumType.Seniors){
              logs.add("Failed registration: gym.customers.Client doesn't meet the age requirements for this session (Seniors)");
         // System.out.println();
          }
          else{
              logs.add("Failed registration: gym.customers.Client's gender doesn't match the session's gender requirements");
              //System.out.println();
          }
          return false;
      }

      c.setBalance(c.getBalance()-s.getCost());
      s.setPart();
      c.getpersonalSessionList().add(s);
      s.getPartArr().add(c);
      logs.add("Registered client: "+c.getName()+" to session: "+s.getType()+" on "+s.getDate()+" for price: "+s.getCost());

      //not duplicate schedule

return true;

}

public void notify(Session s, String str){

      for(int i=0; i<s.getPart();i++){
        s.getPartArr().get(i).setNotifications(str);
        logs.add("A message was sent to everyone registered for session "+s.getType()+" on "+s.getDate()+" : The instructor will be a few minutes late for the session");
      }

}
public void notify(String str1, String str2){
      LocalDate date1 = LocalDate.parse(str1);
      boolean flag=false;
      for (int i=0; i<allClients.size(); i++){
          for (int j=0; j<allClients.get(i).getpersonalSessionList().size();j++){
              String sub=allClients.get(i).getpersonalSessionList().get(j).getDate().substring(0,10);
              LocalDate date2=LocalDate.parse(sub);
              if (date1.compareTo(date2)==0 && !allClients.get(i).getNotifications().contains(str2)){
                  allClients.get(i).setNotifications(str2);
                  flag=true;
              }
          }

      }
      if (flag){
          logs.add("A message was sent to everyone registered for a session on "+str1+" : Heavy traffic reported around the gym today. Plan ahead to avoid missing your session!");
      }

  }

public void notify(String str){
    boolean flag=false;
    for(int i=0; i<allClients.size();i++){
       allClients.get(i).setNotifications(str);
       flag=true;
    }

    if (flag){
        logs.add("A message was sent to all gym clients: Happy New Year to all our valued clients!");
    }

  }

  public void paySalaries(){
      this.setBalance(this.getBalance()+sallary);
      Gym.getInstance().setBalance(Gym.getInstance().getBalance()-sallary);

      //need to resolve pay rate

  }

  public void printActions(){

      for (int i=0;i<logs.size(); i++){
          System.out.println(logs.get(i));
      }
  }




}
