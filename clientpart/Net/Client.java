package Net;

public class Client extends Service{
    public Client(){
    }
    public Client(String KEY){
        setKEY(KEY);
    }
    public Client(String KEY, int timeDelay){
        setKEY(KEY);
        setTimeDelay(timeDelay);
    }
    public Client(String KEY, String controllHost, String manageSection){
        setKEY(KEY);
        setControllHost(controllHost);
        setManageSection(manageSection);
    }
    public Client(String KEY, String controllHost, String manageSection,int timeDelay){
        setKEY(KEY);
        setControllHost(controllHost);
        setManageSection(manageSection);
        setTimeDelay(timeDelay);
    }
    
    @Override
    public void start() throws Exception{
        //String KEY = "FHNLYSEPEZSGCRDZ";
        java.util.Scanner in = new java.util.Scanner(System.in);
        if(this.controllHost == null){
            System.out.println("ENTER THE ADDRESS");
            setControllHost(in.next());
            setManageSection("client.php");
        }
        if(this.KEY == null){
            System.out.println("ENTER THE PRESENT KEY OR PRESS ENTER TO REG");
            setKEY(in.next());
        }
        java.util.Map<String, String> Params = new java.util.HashMap<>();
        Params.put("KEY", this.KEY);
        Params.put("PCUser", System.getProperty("user.name"));
        Threads.ClientThread thread = new Threads.ClientThread(Params);
        thread.run();
    }
}
