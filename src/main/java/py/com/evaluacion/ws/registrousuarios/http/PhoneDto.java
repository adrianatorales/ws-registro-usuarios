package py.com.evaluacion.ws.registrousuarios.http;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class PhoneDto {
    private String number;
    private String citycode;
    private String contrycode;
}
