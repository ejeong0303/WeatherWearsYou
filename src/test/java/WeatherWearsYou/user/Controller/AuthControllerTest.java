package WeatherWearsYou.user.Controller;

import WeatherWearsYou.user.*;
import WeatherWearsYou.user.payloads.LoginRequest;
import WeatherWearsYou.user.payloads.SignUpRequest;
import WeatherWearsYou.user.security.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepo userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider tokenProvider;

    @InjectMocks
    private AuthController authController;

    private LoginRequest loginRequest;

    private SignUpRequest signUpRequest;

    @BeforeEach
    void setUp() {
        loginRequest = new LoginRequest();
        loginRequest.setUsernameOrEmail("testUser");
        loginRequest.setPassword("testPassword");

        signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("testUsername");
        signUpRequest.setEmail("testEmail");
        signUpRequest.setPassword("testPassword");
        signUpRequest.setGender("male");
    }

    @Test
    void testAuthenticateUser() {
        Authentication auth = mock(Authentication.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);
        when(tokenProvider.generateToken(any(Authentication.class))).thenReturn("dummyToken");

        ResponseEntity<?> responseEntity = authController.authenticateUser(loginRequest);

        assertEquals(200, responseEntity.getStatusCodeValue());
        // Further assertions can be made on the content of the response
    }

    @Test
    public void testRegisterUser() throws Exception {
        // Create a signup request object
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("testUser");
        signUpRequest.setPassword("testPassword");
        signUpRequest.setEmail("testEmail@test.com");
        signUpRequest.setGender("male");

        // Convert signup request to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String signUpRequestJson = objectMapper.writeValueAsString(signUpRequest);

        // Perform a POST request to /api/auth/signup
        mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(signUpRequestJson))
                .andExpect(status().isOk());
    }

}
