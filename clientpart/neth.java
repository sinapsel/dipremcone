
import Net.*;

public class neth {

    /**
     * @param args the command line arguments url {c/s}(matser/client) KEY [PASSWORD] 
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        String url = "http://localhost" , key = "FHNLYSEPEZSGCRDZ";
        String login = "root", pass = "toor";
        String desision = "";
        boolean bb = false;
        if (args.length > 0) {
            url = args[0];
        }
        if (args.length > 1) {
            if ("c".equals(args[1])) {
                desision = "c";
                bb = true;
            }
            if ("s".equals(args[1])) {
                desision = "s";
                bb = true;
            }
        }
        if(args.length > 2){
            if(desision.equals("c"))
                key = args[2];
            if(desision.equals("s"))
                login = args[2];
        }
        if(args.length > 3){
            pass = args[3];
        }
        
        java.util.Scanner in = new java.util.Scanner(System.in);
        Service service = null;
        while (true) {
            if (!bb) {
                desision = in.next();
            }
            if (desision.equals("c")) {
                service = new Client(key, url, "client.php");
            } 
            else if (desision.equals("s"))
            {
                service = new Master(login, pass, url, "master.php");
            } else if (desision.equals("e")) {
                System.exit(0);
            } else {
                System.out.println("Uncorrect command\nType c|s|e to start client, server or exit");
                continue;
            }
            break;
        }
        service.start();
    }

}
