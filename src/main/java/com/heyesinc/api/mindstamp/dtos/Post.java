package com.heyesinc.api.mindstamp.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @Column(name = "post_id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String content;
}
