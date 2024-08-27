package dev.stevenwhitfield.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import dev.stevenwhitfield.auth.model.LoginRequest;
import dev.stevenwhitfield.auth.model.TokenResponse;
import dev.stevenwhitfield.auth.service.TokenService;

/**
 * The AuthController class handles requests related to authentication.
 */
@RestController
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;
    private final AuthenticationManager authManager;

    public AuthController(TokenService tokenService, AuthenticationManager authManager) {
        this.tokenService = tokenService;
        this.authManager = authManager;
    }

    /**
     * Handles requests to generate a token for the authenticated user.
     * 
     * @param loginRequest {@code LoginRequest} username and password
     * @return response containing the access token, token type, and expiration time
     */
    @PostMapping("/token")
    public ResponseEntity<TokenResponse> token(@RequestBody LoginRequest loginRequest) {
        LOG.info("Handling token request for user: {}", loginRequest.email());
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.email(), loginRequest.password()));
        String token = this.tokenService.generateToken(auth);
        return ResponseEntity.ok(new TokenResponse(token));
    }

}
