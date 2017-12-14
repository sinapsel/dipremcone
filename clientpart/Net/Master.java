package Net;

public class Master extends Service {

    public Master() {
    }

    public Master(String controllHost, String manageSection) {
        setControllHost(controllHost);
        setManageSection(manageSection);
    }

    public Master(String login, String controllHost, String manageSection) {
        setLogin(login);
        setControllHost(controllHost);
        setManageSection(manageSection);
    }

    public Master(String login, String password, String controllHost, String manageSection) {
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
        while (true) {
            site = new HTTPPost(getAddr(), Params, "POST");
            site.connect();
            System.out.println(site.getResponse());
            String id = in.nextLine();
            if (id.equals("e")) {
                break;
            }
            Params.put("id", id);
            Threads.MasterThread thread = new Threads.MasterThread(Params, super.KEY, super.controllHost, super.manageSection);
            thread.run();
        }
    }
}
