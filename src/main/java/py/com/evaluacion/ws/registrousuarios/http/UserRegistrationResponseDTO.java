package py.com.evaluacion.ws.registrousuarios.http;

import java.sql.Timestamp;
import java.util.UUID;

public class UserRegistrationResponseDTO {
    private UUID id;
    private Timestamp  created;
    private Timestamp modified;
    private Timestamp last_login;
    private String token;

    private boolean isactive;
}
