package gym.Exception;

public class ClientNotRegisteredException extends Exception {
//    public ClientNotRegisteredException() {
//            System.out.println("Error: Registration is required before attempting to unregister");
//    }

    public String getMessage(){
        return "Error: Registration is required before attempting to unregister";
    }
}
