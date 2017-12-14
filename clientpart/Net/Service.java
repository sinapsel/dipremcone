package Net;

public abstract class Service{
    protected String KEY;
    protected String login;
    protected String password;
    protected String controllHost;
    protected String manageSection;
    protected int timeDelay = 2500;
    
    public final void setKEY(String KEY){
        this.KEY = KEY;
    }
    public final void setLogin(String login){
        this.login = login;
    }
    public final void setPassword(String password){
        this.password = password;
    }
    public final void setControllHost(String controllHost){
        this.controllHost = controllHost;
    }
    public final void setManageSection(String manageSection){
        this.manageSection = manageSection;
    }
    public final void setTimeDelay(int timeDelay){
        this.timeDelay = timeDelay;
    }
    public final String getAddr(){
        return this.controllHost.concat(this.manageSection);
    }
    
    public abstract void start() throws Exception;
    
}
