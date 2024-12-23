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



/**
 * The Secretary class represents a specific role in the gym management system.
   A secretary is responsible for managing clients, instructors, sessions, and performing
   administrative operations. This class enforces a single object to ensure
   only one active secretary at a time.

 * Key responsibilities include:
   1. Registering and managing gym clients.
   2. Scheduling and managing gym sessions.
   3. Hiring and managing instructors.
   4. Processing payments and updating financial records.
   5. Sending notifications to clients and staff.
   6. Maintaining logs of administrative actions.

 * The Secretary class also ensures that former secretaries do not retain access to sensitive operations.
 */

public class Secretary extends Person {
  private int sallary;   // Salary of the secretary
  private boolean access;  // Indicates if the secretary has active permissions

    private static ArrayList<Client> allClients; // List of all clients
    private static ArrayList<Person> allPersons; // List of all persons
    private static ArrayList<Instructor> insArr; // List of instructors
    private static ArrayList<Session> sesList; // List of sessions
    private static ArrayList<String> logs =new ArrayList<>(); //List off all actions history

  private Secretary(Person p1, int sal){
      super(p1);

      this.access=true;  // Initialized the access field in the new secretary to true.
      this.sallary=sal;
      if (allClients == null) { // Initialize static lists if not already initialized (initialized just when the first secretary created)
          allClients=new ArrayList<>();
          insArr=new ArrayList<>();
          sesList=new ArrayList<>();
          allPersons=new ArrayList<>();
      }
      logs.add("A new secretary has started working at the gym: "+this.getName());
  }

    /**
     * This function is part of the implement of the factory.
     * The object constractor is private and the factory call this function for creating the object,
     * This is the only place that "new" being used with secretary object.
     * @param p1
     * @param sal
     * @return
     */
    public static Secretary myContructor(Person p1, int sal){
        return new Secretary(p1, sal);
    }
    // Checks if the secretary is allowed to perform actions, Sends back "NullPointerException()" if doesn't have access.
  private void checkaccess(){
      if (!this.isAccess()) {
          throw new NullPointerException();
      }
  }


    /**
     *This method registers a new client by validating their age and checking if the client already exists.
      If the client is valid and does not exist, they are created and added to the system.
      The client is added as an observer, meaning they will now wait for updates from the secretary.
      In the creation of thr client we use a factory int the method " createClient(p1) ".
      In this function we use self build exceptions.
     * @param p1
     * @return
     * @throws InvalidAgeException
     * @throws DuplicateClientException
     */
    public Client registerClient(Person p1) throws InvalidAgeException, DuplicateClientException {
      checkaccess();  //Make sure that is the right secretary.


          if (p1.getAge() < 18) {           // If the checkAccess function returns false, the method exits immediately and does not print the logs
              throw new InvalidAgeException();
          }
          if (!checkIfExist(p1)) {          // Ensures the client does not already exist
              throw new DuplicateClientException("Error: The client is already registered");
          }

          Client c1 = factory.createClient(p1);    // Creates a new client using the factory
          allPersons.add(p1);
          allClients.add(c1);
          logs.add("Registered new client: " + c1.getName());
          return c1;
  }


    /**
     * This function is helper tho check if some client (or just person) exist in the "allClients" array.
     * We Check equals by ID compare.
     * @param p1
     * @return
     */
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

    /**
     * This method unregisters a client from the system.
      If the client is not registered, an exception is thrown.
      The client is removed from the list of clients and will no longer receive updates from the secretary
      This is equivalent to removing the client as an observer in the observer pattern,
      meaning the client will no longer be notified of changes or updates from the system.
      The practical way to do that is to remove this client from "allCalient" arraylist.
     * @param c
     * @throws ClientNotRegisteredException
     */
  public void unregisterClient(Client c) throws ClientNotRegisteredException {
      checkaccess();  // If the checkAccess function returns false, the method exits immediately and does not print the logs
     if(checkIfExist(c)){ // checking if the person is a client
         throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");
     }

     allClients.remove(c);   //remove this client from gym clients
     logs.add("Unregistered client: "+c.getName());

  }

    /**
     * This method hires a new instructor by creating an Instructor object,
     * adding it to the list of instructors, and logging the hiring action
     * @param p1
     * @param p_rate
     * @param arr
     * @return
     */
  public Instructor hireInstructor(Person p1, int p_rate, ArrayList<SessionType> arr){
      checkaccess();   // If the checkAccess function returns false, the method exits immediately and does not print the logs
      Instructor ins = factory.createInstructor(p1,p_rate,arr); //create instructor with the factory.
                  insArr.add(ins); // adds this instructor to array of the instructors
      logs.add("Hired new instructor: "+p1.getName()+" with salary per hour: "+p_rate);

      return ins;
  }


    /**
     * This method adds a new session to the system if the instructor is qualified.
     * It first checks the access rights, validates the instructor's qualifications,
     * and then creates the session while updating the instructor's balance.
     * Adding to the log the right message using "PatternModifier" function.
     * @param s
     * @param str
     * @param f_type
     * @param ins
     * @return
     * @throws InstructorNotQualifiedException
     */
    public Session addSession(SessionType s, String str, ForumType f_type, Instructor ins) throws InstructorNotQualifiedException {
    checkaccess(); // If the checkAccess function returns false, the method exits immediately and does not print the logs
    String patt_str=PatternModify.strPattern(str); //Changing the string to mach the right way.

      if(ins.getqualifiedList().contains(s)) { //Instructor validation check
          Session ses = SessionFactory.createSession(s, str, f_type, ins); //create new session using session factory.
          logs.add("Created new session: "+ s +" on "+patt_str+" with instructor: "+ ins.getName());
          sesList.add(ses); // Add session to session list
          return ses;
      }else throw new InstructorNotQualifiedException(); // If instructor validation check failed throw exception
}


    /**
     * Registers a client to a specific lesson session.
     * The method performs the following validations:
      1. Ensures the secretary has access permissions.
      2. Verifies the client is registered in the system.
      3. Checks that the client is not already registered for the session.
      4. Ensures the session is scheduled for a future date.
      5. Validates the client's balance.
      6. Ensures the session capacity isn't full.
      7. Ensure the client forum match (or include by) the session forum.
     * After all of the above the function register the client:
     * With the following steps:
      1. Decrease the client balance and increase the gym balance (make payment).
      2. Adds 1 to the participant session number.
      3. Adds the session to the client sessions list.
      4. Adds the client to the session clients list.
     * Few notes: In this function used self build exceptions.
     * This function Used live today date for compare the client registration in real time.
     * @param c
     * @param s
     * @return
     * @throws DuplicateClientException
     * @throws ClientNotRegisteredException
     */
    public boolean registerClientToLesson(Client c, Session s) throws DuplicateClientException, ClientNotRegisteredException {
    checkaccess();
    //LocalDate today = LocalDate.of(2024,12,28); //if wanted some date change here
    LocalDate today = LocalDate.now();
    LocalDate secDate=PatternModify.Dateptr(s);
    int comp=secDate.compareTo(today);
    boolean b=true;
    if(!allClients.contains(c)){ // Check if the client is registered
          throw new ClientNotRegisteredException("Error: The client is not registered with the gym and cannot enroll in lessons");
    }
    if(c.getpersonalSessionList().contains(s)){ // Check if the client is already registered for the session
        throw new DuplicateClientException("Error: The client is already registered for this lesson");
    }
    if (comp<0) {  // Ensure the session is in the future
        logs.add("Failed registration: Session is not in the future");
        b= false;
    }
       if (!c.getMyForums().contains(s.getForum())){  // Check if the client is on forumType requirements
          if(s.getForum()==ForumType.Seniors){
              logs.add("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
          }
          else{
              logs.add("Failed registration: Client's gender doesn't match the session's gender requirements");
          }
          b= false;
      }

       if (c.getBalance()<s.getCost()) {   //checking if the client balance is on session cost requirements.
          logs.add("Failed registration: Client doesn't have enough balance");
          b= false;
      }
        if (s.getCapacity()==s.getPart()) { // checking if the session not on full capacity.
          logs.add("Failed registration: No available spots for session");
          b= false;
      }
      if (!b){  // if the client not have all session requirements return false.
          return b;
      }
        // Updates data after successful registration
      c.setBalance(c.getBalance()-s.getCost()); //set balance of client after register
      s.setPart();    //set the number of session participants
      c.addToMySession(s); //add this session to the list session of client
      s.getPartArr().add(c); //add the client to the list of this session
      Gym.getInstance().setBalance(Gym.getInstance().getBalance()+s.getCost());  // set balance gym

      logs.add("Registered client: "+c.getName()+" to session: "+s.getType()+" on "+PatternModify.strPattern(s.getDate())+" for price: "+s.getCost());

return b;

}

// Sends a notification to all participants of a specific session, use with GymNotify class
public void notify(Session s, String str){
    checkaccess();
    GymNotify.notify(s,str,logs);
}
// Sends a notification to clients registered for sessions on a specific date, use with GymNotify class.
public void notify(String str1, String str2){
    checkaccess();
    GymNotify.notify(str1,str2,allClients,logs);
  }
// Sends a general notification to all clients in the gym,use with GymNotify class.
public void notify(String str){
    checkaccess();
    GymNotify.notify(str,allClients,logs);

  }

    /**
     * This function processes salary payments for the secretary and instructors,
     *  updates their balances, and synchronizes the balances with the clients.
     */
  public void paySalaries(){
      checkaccess();
      this.setBalance(this.getBalance()+sallary);

      Gym.getInstance().setBalance(Gym.getInstance().getBalance()-sallary); //set gym balance after paying to secretary

      //set instructors & gym balance after pay all salary's
      for (int i=0;i<insArr.size();i++){
        int sal=insArr.get(i).getSessionCount()*insArr.get(i).getPayrate();

          insArr.get(i).setBalance( insArr.get(i).getBalance()+sal);
          Gym.getInstance().setBalance(Gym.getInstance().getBalance()-sal);


      }
      logs.add("Salaries have been paid to all employees");

  }

    /**
     *This function print out the record of all previous actions(including actions of previous secretaries).
     */
  public void printActions(){
      checkaccess();   // If the checkAccess function returns false, the method exits immediately and does not print the logs
      for (int i=0;i<logs.size(); i++){ //print all actions.
          System.out.println(logs.get(i));
      }
  }



    //Getters & Setters
    public void setAccess(boolean access) {
        this.access = access;
    }
    public boolean isAccess(){
        return this.access;
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
}
