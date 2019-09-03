package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum ReactionType {
    NONE(0),
    LIKE(1),
    DISLIKE(2);

    int id;

    ReactionType(int id) {
        this.id = id;
    }

    @JsonValue
    int getId() {
        return id;
    }

    @JsonCreator
    static ReactionType fromId(int id){
        return Stream.of(ReactionType.values()).filter(type -> type.id == id).findFirst().get();
    }
}
