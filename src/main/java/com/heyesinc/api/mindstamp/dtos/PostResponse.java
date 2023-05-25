package com.heyesinc.api.mindstamp.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private int likeCount;
    private int dislikeCount;
}
