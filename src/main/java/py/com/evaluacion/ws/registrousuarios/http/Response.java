package py.com.evaluacion.ws.registrousuarios.http;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Response {
    private Long userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime  created;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime  modified;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime lastLogin;
    private String token;
    private Boolean isactive;
    private String codeStatus;
    private String message;
}
