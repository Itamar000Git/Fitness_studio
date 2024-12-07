import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InstructorNotQualifiedException;
import gym.Exception.InvalidAgeException;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.SessionType;

import java.util.ArrayList;

public class Secretary {
  private int sallary;
  private Person sec;

    private static ArrayList<Client> allClients;

  public Secretary(Person p1, int sal){
          //we need to make sure there is only one sec and the old ond cant do any changes.
          this.sec=p1;
          this.sallary=sal;
          allClients=new ArrayList<>();//neww
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
      return null;
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

  }
  public Instructor hireInstructor(Person p1, int p_rate, ArrayList<SessionType> arr){

      return null;
  }
//neww
public Session addSession(SessionType s, String str, ForumType f_type, Instructor ins) throws InstructorNotQualifiedException {
      //need to chack if qualified, if he's available

      if(ins.getqualifiedList().contains(s)) {
          Session ses = new Session(s, str, f_type, ins);
      }else throw new InstructorNotQualifiedException("Error: Instructor is not qualified to conduct this session type.");


return null;
}
//endneww

    //neww
public boolean registerClientToLesson(Client c, Session s) throws DuplicateClientException, ClientNotRegisteredException {
      if(!allClients.contains(c)){
          throw new ClientNotRegisteredException();
      } else if(c.getpersonalSessionList().contains(s)){
        throw new DuplicateClientException();
      } else if (c.getBalance()<s.getCost()) {
          System.out.println("Failed registration: Client doesn't have enough balance");
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

    c.setBalance(s.getCost());

      //not duplicate schedule
return true;

}
    //endneww
public void notify(Session s, String str){

}
public void notify(String str1, String str2){

  }
public void notify(String str){

  }

  public void paySalaries(){

  }

  public void printActions(){
      //need to print all actions
  }




}
