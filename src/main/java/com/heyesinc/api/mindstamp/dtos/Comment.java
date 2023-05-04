package com.heyesinc.api.mindstamp.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@Entity
public class Comment {
    @Id
    private final int id;
    private final User user;
    private final Date time;
    private final String content;

}
