package talkomat.servlets;

import talkomat.server.Serve;
import talkomat.server.Servlet;
import talkomat.server.StatusCode;

/**
 * Created by MuFF
 * User: MuFF
 * Date: 16.04.12
 * Time: 21:44
 */
public class UserServlet extends Servlet {
    @Serve
    public void login() {
        // done
        response.setStatus(StatusCode.OK);
        response.setContentType("application/json");
        response.setMessage("{message:'logged in'}");
        request.setServed();
    }

    @Serve
    public void logout() {
        // done
        response.setStatus(StatusCode.OK);
        response.setContentType("application/json");
        response.setMessage("{message:'logged out'}");
        request.setServed();
    }
}
