package services.trews;

import common.Parameters;

public class CreateTrewsParameters extends Parameters {

    private String title;

    CreateTrewsParameters(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
