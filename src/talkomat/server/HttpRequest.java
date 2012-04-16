package talkomat.server;

/**
 * Created by MuFF
 * User: MuFF
 * Date: 16.04.12
 * Time: 21:30
 */
public class HttpRequest {
    private String requestUri = null;
    private boolean served = false;

    public HttpRequest(String header) {
        for(String row : header.split("\r\n")) {
            if(row.matches("(GET (.*) HTTP/.+)")) {
                String[] parts = row.split(" ");
                requestUri = parts[1];
            }
        }
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setServed() {
        setServed(true);
    }

    public void setServed(boolean served) {
        this.served = served;
    }

    public boolean getServed() {
        return served;
    }
}
