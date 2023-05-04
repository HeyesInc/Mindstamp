package com.heyesinc.api.mindstamp.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Entity
public class Post {

    @Id
    private final int id;
    private final User user;
    private String content;
    private List<Reaction> reactionList;
    private List<Comment> commentList;

}
