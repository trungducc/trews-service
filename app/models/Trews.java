package models;

import services.trews.CreateTrewsParameters;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Trews extends AbstractIdentifiable {

    private String title;

    @ManyToOne
    private Account user;

    private long likeCount;

    private long dislikeCount;

    public Trews() {
    }

    public Trews(CreateTrewsParameters parameters) {
        this.title = parameters.getTitle();
        this.user = parameters.getBearer();
        this.likeCount = 0;
        this.dislikeCount = 0;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    public long getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(long dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

}
