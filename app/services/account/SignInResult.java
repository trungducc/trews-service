package services.account;

import models.Account;

public class SignInResult {

    private String token;

    public SignInResult() {
    }

    public SignInResult(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
