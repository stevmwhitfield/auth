package dev.stevenwhitfield.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.stevenwhitfield.auth.service.TokenService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    // DISABLED FOR NOW
    // @PostMapping("/signup")
    // public String signup() {
    // return "Signup";
    // }

    @PostMapping("/token")
    public String token(Authentication auth) {
        LOG.debug("Token requested for {}", auth.getName());
        String token = this.tokenService.generateToken(auth);
        LOG.debug("Token granted: {}", token);
        return token;
    }

}
