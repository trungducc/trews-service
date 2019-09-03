package services.trews;

import daos.ReactionDAO;
import daos.TrewsDAO;
import models.Reaction;
import models.ReactionType;
import models.Trews;
import models.TrewsResponse;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class TrewsService {

    private TrewsDAO trewsDAO;

    private ReactionDAO reactionDAO;

    @Inject
    TrewsService(TrewsDAO trewsDAO, ReactionDAO reactionDAO) {
        this.trewsDAO = trewsDAO;
        this.reactionDAO = reactionDAO;
    }

    public CompletionStage<TrewsResponse> create(CreateTrewsParameters parameters) {
        Trews newTrews = new Trews(parameters);
        trewsDAO.create(newTrews);
        return CompletableFuture.completedFuture(new TrewsResponse(newTrews, ReactionType.NONE));
    }

    public CompletionStage<List<TrewsResponse>> list(ListTrewsParameters parameters) {
        List<Trews> trewsList = trewsDAO.find(parameters);

        List<TrewsResponse> trewsResponse = new ArrayList<>();
        for (Trews trews: trewsList) {
            Reaction reaction = reactionDAO.firstRecord(trews.getId(), parameters.getBearer().getId());
            ReactionType type = reaction == null ? ReactionType.NONE : reaction.getType();
            trewsResponse.add(new TrewsResponse(trews, type));
        }

        return CompletableFuture.completedFuture(trewsResponse);
    }

    public CompletionStage<TrewsResponse> react(TrewsReactParameters parameters) {
        long trewsId = parameters.getTrewsId();
        long accountId = parameters.getBearer().getId();
        ReactionType type = parameters.getType();

        Trews trews = trewsDAO.firstRecord("id", trewsId);
        long likeCount = trews.getLikeCount();
        long dislikeCount = trews.getDislikeCount();

        Reaction reaction = reactionDAO.firstRecord(trewsId, accountId);
        if (reaction != null) {
            if (reaction.getType() == ReactionType.LIKE) {
                likeCount--;
            } else if (reaction.getType() == ReactionType.DISLIKE) {
                dislikeCount--;
            }
            reactionDAO.delete(reaction);
        }

        if (type != ReactionType.NONE) {
            Reaction newReaction = new Reaction(trewsId, accountId, type);
            reactionDAO.create(newReaction);

            if (type == ReactionType.LIKE) {
                likeCount++;
            } else if (type == ReactionType.DISLIKE) {
                dislikeCount++;
            }
        }

        trews.setLikeCount(likeCount);
        trews.setDislikeCount(dislikeCount);
        trews = trewsDAO.update(trews);

        return CompletableFuture.completedFuture(new TrewsResponse(trews, type));
    }

}
