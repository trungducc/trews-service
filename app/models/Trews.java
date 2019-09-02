package models;

import services.trews.CreateTrewsParameters;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Trews extends AbstractIdentifiable {

    private String title;

    @ManyToOne
    private Account user;

    public Trews() {
    }

    public Trews(CreateTrewsParameters parameters) {
        this.title = parameters.getTitle();
        this.user = parameters.getBearer();
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

}
