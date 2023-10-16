package py.com.evaluacion.ws.registrousuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.evaluacion.ws.registrousuarios.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

