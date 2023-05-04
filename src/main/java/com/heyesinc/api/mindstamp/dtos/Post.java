package com.heyesinc.api.mindstamp.dtos;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class Post {

    @Id
    @Column(name = "post_id")
    private final int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private final User user;
    private String content;
}
