package py.com.evaluacion.ws.registrousuarios.model;



import java.time.LocalDateTime;
import java.util.List;


import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.Email;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name = "name")
    private String name;

    @Email(message = "Email is not valid", regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    @Column(name = "email", unique=true)
    private String email;

    @Column(name = "password")
    private String password;

    @Getter
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Phone> phones;

    @Column(name = "created", columnDefinition="TIMESTAMP")
    private LocalDateTime created;

    @Column(name = "modified", columnDefinition="TIMESTAMP")
    private LocalDateTime  modified;

    @Column(name = "last_login",columnDefinition="TIMESTAMP")
    private LocalDateTime  lastLogin;

    @Column(name = "token")
    private String token;

    @Column(name = "isactive")
    private Boolean isactive;


    public void setPhones(List<Phone> phones) {
        this.phones = phones;
        phones.forEach(phone -> phone.setUser(this));
    }

}
