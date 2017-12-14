import Net.*;

public class neth {
    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    
    public static void main(String[] args) throws Exception{
        //System.out.println(System.getProperty("os.name"));
        java.util.Scanner in = new java.util.Scanner(System.in);
        Service service = null;
        while(true){
            String desision = in.next();
            if (desision.equals("c"))
                service = new Client("FHNLYSEPEZSGCRDZ", "http://rcu.web/", "client.php");
                
            else if(desision.equals("s"))
                
                service = new Master("root", "toor", "http://rcu.web/", "master.php");
            else if(desision.equals("e"))
                System.exit(0);
            else{
                System.out.println("Uncorrect command\nType c|s|e to start client, server or exitc");
                continue;
            }
            break;
        }
        service.start();
    }
    
}
