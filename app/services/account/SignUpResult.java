package services.account;

import models.Account;

public class SignUpResult {

    private long id;

    private String username;

    public SignUpResult(Account account) {
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
