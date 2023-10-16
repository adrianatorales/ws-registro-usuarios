package py.com.evaluacion.ws.registrousuarios.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import py.com.evaluacion.ws.registrousuarios.config.JwtAuthenticationEntryPoint;
import py.com.evaluacion.ws.registrousuarios.config.JwtTokenUtil;
import py.com.evaluacion.ws.registrousuarios.controller.UserController;
import py.com.evaluacion.ws.registrousuarios.http.PhoneDto;
import py.com.evaluacion.ws.registrousuarios.http.Response;
import py.com.evaluacion.ws.registrousuarios.http.UserDto;
import py.com.evaluacion.ws.registrousuarios.model.Phone;
import py.com.evaluacion.ws.registrousuarios.model.User;
import py.com.evaluacion.ws.registrousuarios.service.JwtUserDetailsService;
import py.com.evaluacion.ws.registrousuarios.service.UserService;

import java.time.LocalDateTime;
import java.util.List;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private JwtUserDetailsService jwtUserDetailsService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean
    private UserService userService;

    private static final String USER_CREATED = "User created";

    private static final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZHJpc2lsODAwQGdtYWlsLmNvbSIsImV4cCI6MTY5ODAxODEyNCwiaWF0IjoxNjk3NDEzMzI0fQ.hwSn3ZoBysw3Tq0_nITjVXVR2s-JpFSzxLyuOawzjdI_Q57HH-e5qPFrh4gMZUmW2oUwbSpU-IG96iQK_3hqYg";

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createUser() throws Exception {
        final PhoneDto phoneDto = new PhoneDto("446544", "1", "2");
        final UserDto userDto = new UserDto("Adriana", "adrisil650@gmail.com", "12345", List.of(phoneDto));
        final User userRequest = getUser(userDto, phoneDto);
        final Response response = getResponse();
        when(modelMapper.map(userDto, User.class)).thenReturn(userRequest);
        when(userService.register(userRequest)).thenReturn(response);

        mockMvc.perform(post("/api/user").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andDo(print());

    }

    private User getUser(final UserDto userDto, final PhoneDto phoneDto) {
        User userRequest = new User();
        userRequest.setName(userDto.getName());
        userRequest.setEmail(userDto.getEmail());
        userRequest.setPassword(userDto.getPassword());
        Phone phone = new Phone();
        phone.setNumber(phoneDto.getNumber());
        phone.setCitycode(phoneDto.getCitycode());
        phone.setContrycode(phoneDto.getContrycode());
        userRequest.setPhones(List.of(phone));
        return userRequest;
    }

    private Response getResponse() {
        Response response = new Response();
        response.setUserId(1L);
        response.setCreated(LocalDateTime.now());
        response.setLastLogin(LocalDateTime.now());
        response.setToken(TOKEN);
        response.setIsactive(true);
        response.setCodeStatus("201");
        response.setMessage(USER_CREATED);
        return response;
    }
}
