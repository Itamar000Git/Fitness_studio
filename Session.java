public class Session{
    private int cost;
    private SessionType type;
    private ForumType forum;
    private String date;
    private Instructor i;
    private int part;
    private int capacity;

    public Session(SessionType type, String date, ForumType forum, Instructor i){
        this.type=type;
        this.date=date;
        this.forum=forum;
        this.i=i;
    }



}
