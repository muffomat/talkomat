package talkomat.server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;

/**
 * Created by MuFF
 * User: MuFF
 * Date: 16.04.12
 * Time: 21:19
 */
public class ServerWorker implements Runnable {
    private Socket socket;
    private Server server;
    private HttpRequest request;
    private HttpResponse response;

    public ServerWorker(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            work();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    /**
     * @return
     * @throws IOException
     */
    private String readGetHeader() throws IOException {
        InputStreamReader reader = new InputStreamReader(socket.getInputStream());
        String getHeader = new String();
        char[] buffer = new char[255];
        int read = 0;
        while(reader.ready() && ((read = reader.read(buffer)) != -1)) {
            getHeader = getHeader + String.valueOf(buffer, 0, read);

            // reset buffer
            buffer = new char[255];
        }
        return getHeader;
    }

    private void writeResponse() throws IOException, InvocationTargetException, IllegalAccessException {
        if(!socket.isConnected())
            return;
        // read get/post
        OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());

        // work
        for(Servlet servlet : this.server.getServlets()) {
            if(request.getServed())
                break;
            servlet.apply(request, response);
        }

        // info
        System.out.print("[" + response.getStatusCode() + "]");

        // reply
        String response = this.response != null ? this.response.toString() : "";
        writer.write(response, 0, response.length());
        writer.close();
        socket.close();
    }

    /**
     *
     * @throws IOException
     */
    private void work() throws IOException, InvocationTargetException, IllegalAccessException {
        System.out.print("New request from " + socket.getInetAddress() + ":");
        String getHeader = readGetHeader();
        request = new HttpRequest(getHeader);
        System.out.print(":"+request.getRequestUri()+"\t");
        response = new HttpResponse();
        writeResponse();
        System.out.println(" [OK]");
    }
}
