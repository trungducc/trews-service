package services.trews;

import common.Parameters;
import models.Account;

public class ListTrewsParameters extends Parameters {

    private Account bearer;

    public ListTrewsParameters() {
    }

    public ListTrewsParameters(Account bearer) {
        this.bearer = bearer;
    }

    public Account getBearer() {
        return bearer;
    }

    public void setBearer(Account bearer) {
        this.bearer = bearer;
    }
}
