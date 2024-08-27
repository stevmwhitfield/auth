package dev.stevenwhitfield.auth.model;

/**
 * Represents the response body for a token request.
 *
 */
public record TokenResponse(String accessToken, String tokenType, Integer expiresIn) {
    public TokenResponse(String accessToken, String tokenType, Integer expiresIn) {
        this.accessToken = accessToken;
        this.tokenType = tokenType != null ? tokenType : "Bearer";
        this.expiresIn = expiresIn != null ? expiresIn : 3600;
    }

    public TokenResponse(String accessToken) {
        this(accessToken, "Bearer", 3600);
    }
}
