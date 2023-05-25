package com.heyesinc.api.mindstamp.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Posts")
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private int id;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private String username;
    private String content;
    private int likeCount;
    private int dislikeCount;
    private List<String> usersThatDisliked;
    private List<String> usersThatLiked;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
