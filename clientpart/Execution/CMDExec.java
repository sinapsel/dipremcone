package Execution;

import java.io.*;
import java.util.*;

public class CMDExec {
    public static String execute(String command) throws IOException{
        if(command.equals("NOCMD")) return "NORES";
        Process prc = (Runtime.getRuntime().exec("sh"));
        
        OutputStream stdin = prc.getOutputStream();
        InputStream stderr = prc.getErrorStream();
        InputStream stdout = prc.getInputStream();
        
        String incmd = "firefox\n";
        stdin.write(incmd.getBytes());
        stdin.flush();
        stdin.close();
        
        
        //OutputStream isr = prc.getOutputStream();
        System.out.println("sss---");
        //isr.write("ls".getBytes());
        InputStream osr = prc.getInputStream();
        System.out.println(osr.read());
        BufferedReader br = new BufferedReader(new InputStreamReader(prc.getInputStream(), "UTF-8"));
        String l;
        StringBuilder sb = new StringBuilder();
        while ((l=br.readLine())!=null) {
            sb.append(l).append("\n");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
    

    public static HashMap execute(String[] command) throws IOException{
        Map<String, String> hm = new HashMap<>();
        for(String cmd: command){
            hm.put(cmd, execute(cmd));
        }
        return (HashMap) hm;
    }
}
