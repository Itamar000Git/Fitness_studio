import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InstructorNotQualifiedException;
import gym.Exception.InvalidAgeException;

import java.util.ArrayList;

public class Secretary {
  private int sallary;
  private Person sec;

    private static ArrayList<Client> allClients;

  public Secretary(Person p1, int sal){
          //we need to make sure there is only one sec and the old ond cant do any changes.
          this.sec=p1;
          this.sallary=sal;
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
      for(int i=0;i<allClients.size(); i++){
          if(p1.getID() ==allClients.get(i).getID()){
              return false;
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
public Session addSession(SessionType s,String str, ForumType f_type, Instructor ins) throws InstructorNotQualifiedException {
      //need to chack if qualified, if he's available

      if(ins.getqualifiedList().contains(s)) {
          Session ses = new Session(s, str, f_type, ins);
      }else throw new InstructorNotQualifiedException("Error: Instructor is not qualified to conduct this session type.");


return null;
}
//endneww
public void registerClientToLesson(Client c, Session s){
      //balance
      //forume type
      //not already registerd
      //not duplicate schedual


}
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
