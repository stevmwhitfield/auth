package dev.stevenwhitfield.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.stevenwhitfield.auth.model.TokenResponse;
import dev.stevenwhitfield.auth.service.TokenService;

/**
 * The AuthController class handles requests related to authentication.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    /**
     * Handles requests to generate a token for the authenticated user.
     * 
     * @param auth {@code Authentication} object containing the user's credentials
     * @return response containing the access token, token type, and expiration time
     */
    @PostMapping("/token")
    public ResponseEntity<TokenResponse> token(Authentication auth) {
        LOG.info("Handling POST request to /api/auth/token with auth: {}", auth);
        String token = this.tokenService.generateToken(auth);
        TokenResponse res = new TokenResponse(token);
        return ResponseEntity.ok(res);
    }

}
