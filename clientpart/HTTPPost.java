import java.util.*;
import java.io.*;
import java.net.*;

public class HTTPPost {
    private String URL;
    private String Params = null;
    private String Method = "GET";
    private String Charset= "UTF-8";
    private String UserAgent = "Mozilla/5.0(X11; Debian; Linux x86_64; rv:54.0) Gecko/20100101 Firefox/54.0";
    private String ErrorMessage;
    private HttpURLConnection connection;
    
    public final void setURL(String URL){
        this.URL = URL;
    }
    public final void setParams(String Params){
        this.Params = Params;
    }
    public final void setParams(Map<String, String> Params){
        StringBuilder par = new StringBuilder();
        Params.entrySet().stream().forEach((entry) -> {
            par.append("&").append(entry.getKey()).append("=").append(entry.getValue());
        });
        this.Params = par.deleteCharAt(0).toString();
    }
    public final void setMethod(String Method){
        this.Method = Method;
    }
    public final void setCharset(String Charset){
        this.Charset = Charset;
    }
    public final void setUserAgent(String UserAgent){
        this.UserAgent = UserAgent;
    }
    public String getErrorMessage(){
        return this.ErrorMessage;
    }
    
    HTTPPost(){
    }
    HTTPPost(String URL){
        setURL(URL);
    }
    HTTPPost(String URL, String Params, String Method){
        setURL(URL);
        setParams(Params);
        setMethod(Method);
    }
    HTTPPost(String URL, Map<String, String> Params, String Method){
        setURL(URL);
        setParams(Params);
        setMethod(Method);
    }
        
    public void connect() throws IOException{
        this.connection = (HttpURLConnection)new URL(this.URL).openConnection();
        this.connection.setRequestMethod(this.Method);
        this.connection.setUseCaches(false);
        this.connection.setRequestProperty("User-Agent", this.UserAgent);
        this.connection.setConnectTimeout(1250);
        this.connection.setReadTimeout(1250);
        this.connection.setDoOutput(true);
        if(this.Params != null){
            try (DataOutputStream wr = new DataOutputStream(this.connection.getOutputStream())) {
                wr.writeBytes(this.Params);
              wr.flush();
            }
        }else
            this.connection.connect();
    }
    public String getResponse() throws IOException{
        StringBuilder sb = new StringBuilder();
            if(this.connection.getResponseCode() != HttpURLConnection.HTTP_OK){
                this.ErrorMessage = ("err:" + this.connection.getResponseCode() + "::" + this.connection.getResponseMessage());
                return this.ErrorMessage;
            }else{
                BufferedReader in = new BufferedReader(new InputStreamReader(this.connection.getInputStream(), this.Charset));
                String line;
                while((line = in.readLine()) != null){
                    sb.append(line).append("\n");
                }
                return sb.toString();
            }
    }
    public Map<String, String> getResponse(boolean a) throws IOException{
        String srcansw = getResponse();
        System.out.println(srcansw);
        String[] aResp = srcansw.split("\n");
            Map<String, String> arr = new HashMap<>();
            for (String aResp1 : aResp) {
                arr.put(aResp1.split(":")[0], aResp1.split(":")[1]);
            }
        return arr;
    }
    
    public void close(){
        this.connection.disconnect();
    }
    
}