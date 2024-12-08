import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InstructorNotQualifiedException;
import gym.Exception.InvalidAgeException;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.SessionType;
import java.time.LocalDate;

import java.util.ArrayList;

public class Secretary extends Person{
  private int sallary;
 // private Person sec;

    private static ArrayList<Client> allClients;
    public static ArrayList<String> logs =new ArrayList<>();

  public Secretary(Person p1, int sal){
      super(p1);
      //we need to make sure there is only one sec and the old ond cant do any changes.
       //   this.sec=p1;
          this.sallary=sal;
          allClients=new ArrayList<>();//neww
      logs.add("A new secretary has started working at the gym: "+this.getName());
       // allClients.add(new Client(p1));//neww
  }

  public Client registerClient(Person p1) throws InvalidAgeException, DuplicateClientException {
      if(p1.getAge()<18){
          throw new InvalidAgeException();
      }
      if (!checkIfExist(p1)){
          throw new DuplicateClientException();
      }

      Client c1=new Client(p1);
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

      ///need to be completed
      logs.add("Hired new instructor: Shachar with salary per hour: 70");

      return null;
  }

public Session addSession(SessionType s, String str, ForumType f_type, Instructor ins) throws InstructorNotQualifiedException {
      //need to chack if qualified, if he's available

      if(ins.getqualifiedList().contains(s)) {
          Session  ses = new Session(s, str, f_type, ins);
          logs.add("Created new session: "+s.toString()+" on "+str+" with instructor: "+ ins); //not full
          //Created new session: Pilates on 2025-01-23T10:00 with instructor: Yuval

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
          System.out.println("Failed registration: Client doesn't have enough balance");
          return false;
      } else if (comp<0) {
          logs.add("Failed registration: Session is not in the future");
          System.out.println(logs.getLast());
          
      } else if (s.getCapacity()==s.getPart()) {
          System.out.println("Failed registration: No available spots for session");
          return false;

      } else if (!c.getMyForums().contains(s.getForum())){
          if(s.getForum()==ForumType.Seniors){
          System.out.println("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
          }
          else{
              System.out.println("Failed registration: Client's gender doesn't match the session's gender requirements");
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
      }

}
public void notify(String str1, String str2){
      LocalDate date1 = LocalDate.parse(str1);
      for (int i=0; i<allClients.size(); i++){
          for (int j=0; j<allClients.get(i).getpersonalSessionList().size();j++){
              String sub=allClients.get(i).getpersonalSessionList().get(j).getDate().substring(0,10);
              LocalDate date2=LocalDate.parse(sub);
              if (date1.compareTo(date2)==0 && !allClients.get(i).getNotifications().contains(str2)){
                  allClients.get(i).setNotifications(str2);
              }
          }

      }
  }

public void notify(String str){
    for(int i=0; i<allClients.size();i++){
       allClients.get(i).setNotifications(str);
    }

  }

  public void paySalaries(){
      this.setBalance((-1)*sallary);
      Gym.getInstance().setBalance(Gym.getInstance().getBalance()-sallary);

      //need to resolve pay rate

  }

  public void printActions(){


      //need to print all actions
  }




}
