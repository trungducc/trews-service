package services.trews;

import common.Parameters;
import models.Account;
import models.ReactionType;

public class TrewsReactParameters extends Parameters {

    private Account bearer;

    private long trewsId;

    private ReactionType type;

    public TrewsReactParameters() {
    }

    public Account getBearer() {
        return bearer;
    }

    public void setBearer(Account bearer) {
        this.bearer = bearer;
    }

    public long getTrewsId() {
        return trewsId;
    }

    public void setTrewsId(long trewsId) {
        this.trewsId = trewsId;
    }

    public ReactionType getType() {
        return type;
    }

    public void setType(ReactionType type) {
        this.type = type;
    }
}
