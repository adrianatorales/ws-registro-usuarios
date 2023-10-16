package py.com.evaluacion.ws.registrousuarios.controller;


import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import py.com.evaluacion.ws.registrousuarios.http.Response;
import py.com.evaluacion.ws.registrousuarios.http.UserDto;
import py.com.evaluacion.ws.registrousuarios.model.User;
import py.com.evaluacion.ws.registrousuarios.service.UserService;

import javax.validation.Valid;


@RestController
@RequestMapping(value ="api/user")
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    private UserService userService;

    private static final String USER_CREATED = "User created";

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Response> createUser(@RequestBody @Valid UserDto userDto) throws Exception {

        // convert DTO to entity
        final User userRequest = modelMapper.map(userDto, User.class);

        Response response = userService.register(userRequest);
        response.setCodeStatus(String.valueOf(HttpStatus.CREATED.value()));
        response.setMessage(USER_CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
