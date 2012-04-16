package talkomat.server;

import java.nio.charset.Charset;

/**
 * Created by MuFF
 * User: MuFF
 * Date: 16.04.12
 * Time: 21:30
 */
public class HttpResponse {
    private StatusCode statusCode = StatusCode.NOT_FOUND;
    private String message = new String();
    private Charset charset = Charset.forName("UTF-8");
    private String contentType = "text/html";

    public void setStatus(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public String toString() {
        return "HTTP/1.1 " + statusCode.getCode() + " " + statusCode.getMessage() + "\n" +
                "Accept-Ranges: bytes\n" +
                "Content-Length: " + message.getBytes(charset) + "\n" +
                "Connection: close\n" +
                "Content-Type: " + contentType + "; charset=" + charset.name() + "\r\n\r\n" +
                message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }
}
