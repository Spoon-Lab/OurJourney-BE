package pudding.toy.ourJourney.dto.auth;


import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponse {
    @JsonProperty("user_id")
    private Long userId;
    private String email;
    private Boolean authentication;
    private String authorization;

    public AuthResponse(Long userId, String email, Boolean authentication, String authorization) {
        this.userId = userId;
        this.email = email;
        this.authentication = authentication;
        this.authorization = authorization;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Boolean authentication) {
        this.authentication = authentication;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
