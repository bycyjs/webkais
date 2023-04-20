package com.liuhao.webkaishi.entity;

public class Tokens {

    private String token;
    private String userId;
    private long expiresAt;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(long expiresAt) {
        this.expiresAt = expiresAt;
    }

    @Override
    public String toString() {
        return "Tokens{" +
                "token='" + token + '\'' +
                ", userId='" + userId + '\'' +
                ", expiresAt='" + expiresAt + '\'' +
                '}';
    }
}
