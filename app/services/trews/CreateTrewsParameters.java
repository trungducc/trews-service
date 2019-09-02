package services.trews;

import common.Parameters;
import models.Account;

public class CreateTrewsParameters extends Parameters {

    private Account bearer;

    private String title;

    public CreateTrewsParameters() {
    }

    public CreateTrewsParameters(Account bearer, String title) {
        this.bearer = bearer;
        this.title = title;
    }

    public Account getBearer() {
        return bearer;
    }

    public void setBearer(Account bearer) {
        this.bearer = bearer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
