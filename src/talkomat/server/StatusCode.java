package talkomat.server;

/**
 * Created by MuFF
 * User: MuFF
 * Date: 16.04.12
 * Time: 22:18
 */
public enum StatusCode {
    OK(200, "OK"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private int code;
    private String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String toString() {
        return getCode() + " " + getMessage();
    }
}
