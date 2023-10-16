package py.com.evaluacion.ws.registrousuarios.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import py.com.evaluacion.ws.registrousuarios.config.CustomAuthenticationManager;
import py.com.evaluacion.ws.registrousuarios.http.Response;
import py.com.evaluacion.ws.registrousuarios.model.JwtRequest;
import py.com.evaluacion.ws.registrousuarios.model.JwtResponse;
import py.com.evaluacion.ws.registrousuarios.model.User;
import py.com.evaluacion.ws.registrousuarios.repository.UserRepository;
import py.com.evaluacion.ws.registrousuarios.config.JwtTokenUtil;

import java.time.LocalDateTime;


@Log4j2
@Service("userService")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    private CustomAuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }


    @Override
    public Response register(User userRequest) throws Exception {
        final JwtResponse jwtResponse = createAuthenticationToken(new JwtRequest(userRequest.getEmail(), userRequest.getPassword()));
        userRequest.setToken(jwtResponse.getJwttoken());
        userRequest.setCreated(LocalDateTime.now());
        userRequest.setModified(LocalDateTime.now());
        userRequest.setIsactive(true);
        final User createdUser = userRepository.save(userRequest);
        final Response response = new Response();
        response.setUserId(createdUser.getUserId());
        response.setCreated(createdUser.getCreated());
        response.setModified(createdUser.getModified());
        response.setLastLogin(createdUser.getLastLogin());
        response.setToken(createdUser.getToken());
        response.setIsactive(createdUser.getIsactive());
        return response;
    }

    public JwtResponse createAuthenticationToken(JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new JwtResponse(token);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
