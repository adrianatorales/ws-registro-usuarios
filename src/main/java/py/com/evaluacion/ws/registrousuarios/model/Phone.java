package py.com.evaluacion.ws.registrousuarios.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Data
@Table(name = "phone")
public class Phone {

    @Id
    @Column(name = "phone_id", insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long phoneId;

    @Column(name = "number")
    private String number;

    @Column(name = "city_code")
    private String citycode;

    @Column(name = "contry_code")
    private String contrycode;

    @ManyToOne(optional = false)
    @JoinColumn(name="user_id")
    private User user;

}
