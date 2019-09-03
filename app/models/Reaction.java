package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Reaction extends AbstractIdentifiable {

    private long trewsId;

    private long accountId;

    @Enumerated(EnumType.ORDINAL)
    private ReactionType type;

    public Reaction() {
    }

    public Reaction(long trewsId, long accountId, ReactionType type) {
        this.trewsId = trewsId;
        this.accountId = accountId;
        this.type = type;
    }

    public long getTrewsId() {
        return trewsId;
    }

    public void setTrewsId(long trewsId) {
        this.trewsId = trewsId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public ReactionType getType() {
        return type;
    }

    public void setType(ReactionType type) {
        this.type = type;
    }
}
