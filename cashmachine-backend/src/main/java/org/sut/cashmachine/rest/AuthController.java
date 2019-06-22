package org.sut.cashmachine.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.sut.cashmachine.dao.user.UserRepository;
import org.sut.cashmachine.dataload.test.data.RoleTestData;
import org.sut.cashmachine.exception.BadRequestException;
import org.sut.cashmachine.model.user.AuthType;
import org.sut.cashmachine.model.user.UserModel;
import org.sut.cashmachine.rest.dto.ApiResponseDTO;
import org.sut.cashmachine.rest.dto.AuthResponseDTO;
import org.sut.cashmachine.rest.dto.LoginRequestDTO;
import org.sut.cashmachine.rest.dto.SignUpRequestDTO;
import org.sut.cashmachine.service.oauth.impl.TokenProvider;

import javax.validation.Valid;
import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/auth")
/**
 * Service that is responsible for OAuth2 authentication and sign up of users
 */
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getEmail(),
                        loginRequestDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {
        if(userRepository.getUserByEmail(signUpRequestDTO.getEmail()) != null) {
            throw new BadRequestException("Email address already in use.");
        }

        // Creating user's account
        UserModel user = new UserModel();
        user.setName(signUpRequestDTO.getName());
        user.setEmail(signUpRequestDTO.getEmail());
        user.setPassword(signUpRequestDTO.getPassword());
        user.setRoles(Set.of(RoleTestData.CASHIER_ROLE));
        user.setAuth(AuthType.BASIC);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        UserModel result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponseDTO(true, "User registered successfully"));
    }

}
