package services.trews;

import daos.TrewsDAO;
import models.Trews;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class TrewsService {

    private TrewsDAO trewsDAO;

    @Inject
    TrewsService(TrewsDAO trewsDAO) {
        this.trewsDAO = trewsDAO;
    }

    public CompletionStage<Trews> create(CreateTrewsParameters parameters) {
        Trews newTrews = new Trews(parameters);
        trewsDAO.create(newTrews);
        return CompletableFuture.completedFuture(newTrews);
    }

    public CompletionStage<List<Trews>> list(ListTrewsParameters parameters) {
        List<Trews> trews = trewsDAO.find(parameters);
        return CompletableFuture.completedFuture(trews);
    }

}
