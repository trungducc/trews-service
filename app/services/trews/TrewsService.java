package services.trews;

import daos.TrewsDAO;
import models.Trews;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class TrewsService {

    private TrewsDAO trewsDAO;

    @Inject
    TrewsService(TrewsDAO trewsDAO) {
        this.trewsDAO = trewsDAO;
    }

    public CompletionStage<Trews> create(CreateTrewsParameters parameters) {
        return null;
    }

}
