package Threads;

import Net.HTTPPost;
import java.util.concurrent.TimeUnit;

public class MasterThread extends Net.Master implements Runnable {

    java.util.Map<String, String> Params;
    java.util.Scanner in = new java.util.Scanner(System.in);

    public MasterThread(java.util.Map<String, String> params, String KEY, String controllHost, String manageSection) {
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
                    System.out.print(">");
                    cmd = in.nextLine();
                    if (cmd.equals("<-")) {
                        break;
                    }
                    java.util.Map<String, String> params = new java.util.HashMap<>(Params);
                    params.put("to", "SET");
                    params.put("cmd", cmd);
                    HTTPPost site = new HTTPPost(getAddr(), params, "POST");
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
                } catch (Exception e) {
                    System.err.println("Unable to connect server "  + super.controllHost);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void start() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
