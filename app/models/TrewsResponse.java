package models;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class TrewsResponse extends Trews {

    @Enumerated(EnumType.ORDINAL)
    private ReactionType reactionType;

    public TrewsResponse(Trews trews, ReactionType type) {
        this.id = trews.getId();
        this.createdDate = trews.getCreatedDate();
        this.reactionType = type;
        this.setTitle(trews.getTitle());
        this.setUser(trews.getUser());
        this.setLikeCount(trews.getLikeCount());
        this.setDislikeCount(trews.getDislikeCount());
    }

    public ReactionType getReactionType() {
        return reactionType;
    }

    public void setReactionType(ReactionType reactionType) {
        this.reactionType = reactionType;
    }
}
