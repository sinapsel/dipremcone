import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Master extends Service{ 
    Master(){
    }
    Master(String controllHost, String manageSection){
        setControllHost(controllHost);
        setManageSection(manageSection);
    }
    Master(String login, String controllHost, String manageSection){
        setLogin(login);
        setControllHost(controllHost);
        setManageSection(manageSection);
    }
    Master(String login, String password, String controllHost, String manageSection){
        setLogin(login);
        setPassword(password);
        setControllHost(controllHost);
        setManageSection(manageSection);
    }

    @Override
    public void start() throws Exception {
        java.util.Scanner in = new java.util.Scanner(System.in);
        HTTPPost site = null;
        java.util.Map<String, String> Params = new java.util.HashMap<>();
        Params.put("login", this.login);
        Params.put("password", this.password);
        String cmd = "";
        while(true){
            site = new HTTPPost(getAddr(),Params, "POST");
            site.connect();
            System.out.println(site.getResponse());
            String id = in.next();
            if(id.equals("e"))
                break;
            
            while(true){
                Params.put("id", id);
                try{
                    System.out.print(">");
                    cmd = in.nextLine();
                    if(cmd.equals("<-"))
                        break;
                    java.util.Map<String, String> params = new java.util.HashMap<>(Params);                 
                    params.put("to", "SET");
                    params.put("cmd", cmd);
                    site = new HTTPPost(getAddr(), params, "POST");
                    site.connect();
                    System.out.println(site.getResponse());
                    site.close();
                    TimeUnit.MILLISECONDS.sleep(this.timeDelay);
                    params = new java.util.HashMap<>(Params);
                    params.put("to", "GET");
                    site = new HTTPPost(getAddr(), params, "POST");
                    site.connect();
                    String resp = site.getResponse();
                    System.out.println(">>\n".concat(resp).concat("\n"));
                }catch(IOException e){
                    e.printStackTrace();
                    TimeUnit.MILLISECONDS.sleep(this.timeDelay);
                }
                finally{
                site.close();
                }
            }
        }
    }  
}