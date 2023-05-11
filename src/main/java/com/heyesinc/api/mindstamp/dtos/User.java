package com.heyesinc.api.mindstamp.dtos;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    private String username;
    private String password;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "post_user",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "post_id", referencedColumnName = "post_id"))
    private List<Post> posts;

}