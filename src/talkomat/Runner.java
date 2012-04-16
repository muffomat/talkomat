package talkomat;

import talkomat.server.Server;
import talkomat.servlets.UserServlet;
import talkomat.test.HelloWorld;
import talkomat.test.HelloWorldSubpath;

import java.io.IOException;

public class Runner {
    public static void main(String[] argv) {
        Server s = new Server();

        // add serlvets
        s.addServlet(new HelloWorld(), "/");
        s.addServlet(new HelloWorldSubpath(), "/hello/");
        s.addServlet(new UserServlet(), "/user/");

        try {
            s.start();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}