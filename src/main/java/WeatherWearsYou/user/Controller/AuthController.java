package WeatherWearsYou.user.Controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;

import WeatherWearsYou.user.UserRepo;
import WeatherWearsYou.user.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import WeatherWearsYou.user.security.JwtTokenProvider;
import WeatherWearsYou.user.payloads.ApiResponse;
import WeatherWearsYou.user.payloads.JwtAuthenticationResponse;
import WeatherWearsYou.user.payloads.LoginRequest;
import WeatherWearsYou.user.payloads.SignUpRequest;
import WeatherWearsYou.user.User;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepo userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Get the authenticated user's details
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        // Find the user in the database to get the gender
        User user = userRepository.findById(principal.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + principal.getId()));

        // Get the user's gender
        String gender = user.getGender().name();

        // Generate the JWT
        String jwt = tokenProvider.generateToken(authentication);

        // Create a new response with the JWT and the gender
        JwtAuthenticationResponse response = new JwtAuthenticationResponse(jwt);
        response.setGender(gender);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword(), signUpRequest.getGender());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}

