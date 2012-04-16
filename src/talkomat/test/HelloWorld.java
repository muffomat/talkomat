package talkomat.test;

import talkomat.server.Serve;
import talkomat.server.Servlet;
import talkomat.server.StatusCode;

/**
 * Created by MuFF
 * User: MuFF
 * Date: 16.04.12
 * Time: 21:44
 */
public class HelloWorld extends Servlet {
    @Serve
    public void hai() {
        // done
        response.setStatus(StatusCode.OK);
        response.setMessage("Hai");
        request.setServed();
    }
}
