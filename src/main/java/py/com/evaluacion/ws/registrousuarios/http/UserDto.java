package py.com.evaluacion.ws.registrousuarios.http;

import lombok.*;

import javax.validation.constraints.Email;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class UserDto {
    private String name;
    @Email(message = "Email is not valid", regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String email;
    private String password;
    private List<PhoneDto> phones;
}
