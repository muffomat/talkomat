package talkomat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by MuFF
 * User: MuFF
 * Date: 16.04.12
 * Time: 21:17
 */
public class Server {
    private ServerSocket ss;

    private ArrayList<Servlet> servlets = new ArrayList<Servlet>();

    public ArrayList<Servlet> getServlets() {
        return servlets;
    }

    public void addServlet(Servlet s, String path) {
        this.servlets.add(s);
        s.setServletPath(path);
    }

    public void start() throws IOException {
        ss = new ServerSocket(8080);
        Socket s;
        while((s = ss.accept()) != null) {
            Runnable r = new ServerWorker(s, this);
            new Thread(r).start();
        }
    }
}
