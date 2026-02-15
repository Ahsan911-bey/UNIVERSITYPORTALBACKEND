package com.universityportal.dto;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String email;
    private String role; // Optional, if you want to send role back

    public JwtResponse(String accessToken, String email) {
        this.token = accessToken;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
