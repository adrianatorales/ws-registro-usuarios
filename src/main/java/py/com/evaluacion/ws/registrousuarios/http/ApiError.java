package py.com.evaluacion.ws.registrousuarios.http;

public class ApiError {

    private String message;


    public ApiError(Object errors) {
        super();
        this.message = errors.toString();

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
