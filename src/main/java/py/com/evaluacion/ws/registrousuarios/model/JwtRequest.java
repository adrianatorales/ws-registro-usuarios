package py.com.evaluacion.ws.registrousuarios.model;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class JwtRequest {
    private String username;
    private String password;
}
