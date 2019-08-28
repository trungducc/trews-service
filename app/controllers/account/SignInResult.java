package controllers.account;

import models.Account;

public class SignInResult {

    private long id;

    private String username;

    public SignInResult() {
    }

    public SignInResult(Account account) {
        this.id = account.getId();
        this.username = account.getUsername();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
