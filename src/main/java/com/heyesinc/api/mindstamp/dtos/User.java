package com.heyesinc.api.mindstamp.dtos;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
@Entity
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final int id;
    private String username;
    private String password;
    private List<Post> posts;

}
