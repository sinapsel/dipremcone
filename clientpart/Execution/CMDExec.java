package Execution;

import java.io.*;
import java.util.*;

public class CMDExec {

    public static String execute(String command) throws IOException {
        if (command.equals("NOCMD")) {
            return "NORES";
        }
        String osType = System.getProperty("os.name").toLowerCase();
        String estr;
        if (osType.contains("win")) {
            estr = "cmd.exe";
            if (command.charAt(command.length() - 1) != '\n'){
                command = command.concat("\n");
            }
        }  else {
            estr = "sh";
        }
        
        Process prc = (Runtime.getRuntime().exec(estr));

        InputStream stderr = prc.getErrorStream();
        InputStream stdout = prc.getInputStream();
        OutputStream stdin = prc.getOutputStream();

        stdin.write(command.getBytes());
        stdin.flush();
        stdin.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(stdout, "CP866"));
        String l;
        StringBuilder sb = new StringBuilder();
        while ((l = br.readLine()) != null) {
            sb.append(l).append("\n");
        }
        //System.out.println(sb.toString());
        br = new BufferedReader(new InputStreamReader(stderr, "CP866"));
        while ((l = br.readLine()) != null) {
            sb.append(l).append("\n");
        }
        //System.out.println(sb.toString());
        return sb.toString();
    }

    public static HashMap execute(String[] command) throws IOException {
        Map<String, String> hm = new HashMap<>();
        for (String cmd : command) {
            hm.put(cmd, execute(cmd));
        }
        return (HashMap) hm;
    }
}
