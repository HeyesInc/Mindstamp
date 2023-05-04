package com.heyesinc.api.mindstamp.dtos;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@Entity
public class Reaction {

    public static final String LIKE = "like";
    public static final String HEART = "heart";
    public static final String LAUGH = "laugh";

    // Map to store the count for each reaction type
    private Map<String, Integer> reactions;

    public Reaction() {
        reactions = new HashMap<>();
        reactions.put(LIKE, 0);
        reactions.put(HEART, 0);
        reactions.put(LAUGH, 0);
    }
    public void addReaction(String reactionType) {
        if (reactions.containsKey(reactionType)) {
            reactions.put(reactionType, reactions.get(reactionType) + 1);
        }
    }
    public int getReactionCount(String reactionType) {
        if (reactions.containsKey(reactionType)) {
            return reactions.get(reactionType);
        }
        return 0;
    }
}
