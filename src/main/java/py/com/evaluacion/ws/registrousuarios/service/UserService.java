package py.com.evaluacion.ws.registrousuarios.service;

import py.com.evaluacion.ws.registrousuarios.http.Response;
import py.com.evaluacion.ws.registrousuarios.model.User;

public interface UserService {
    Response register (User user) throws Exception;
}
