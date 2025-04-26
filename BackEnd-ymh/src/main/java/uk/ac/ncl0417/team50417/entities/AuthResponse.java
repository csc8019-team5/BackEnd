package uk.ac.ncl0417.team50417.entities;

public class AuthResponse {
    private String username;
    private String token;

    public AuthResponse(String username, String token) {
        this.username = username;
        this.token = token;
    }

    // Getter & Setter
    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }
}
