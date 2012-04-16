package talkomat.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by MuFF
 * User: MuFF
 * Date: 16.04.12
 * Time: 21:44
 */
public class Servlet {
    protected HttpRequest request;
    protected HttpResponse response;

    private String servletPath;

    public final void apply(HttpRequest request, HttpResponse response) throws InvocationTargetException, IllegalAccessException {
        this.request = request;
        this.response = response;

        this.work();
    }

    private final void work() throws InvocationTargetException, IllegalAccessException {
        Method[] methods = getClass().getMethods();
        for(Method m : methods) {
            if(!m.isAnnotationPresent(Serve.class))
                continue;
            // check if name is correct
            if(request.getRequestUri() == null || !request.getRequestUri().equals(servletPath + m.getName()))
                continue;
            m.invoke(this);
        }
    }

    public void setServletPath(String servletPath) {
        this.servletPath = servletPath;
    }
}
