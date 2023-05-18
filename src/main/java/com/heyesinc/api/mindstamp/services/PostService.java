package com.heyesinc.api.mindstamp.services;

import com.heyesinc.api.mindstamp.dtos.Post;
import com.heyesinc.api.mindstamp.dtos.PostRequest;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    Post getPostById(int postId);
    String editPostById(int postId, PostRequest newPost);
    String deletePostById(int postId);
    String addLike(int postId);
    String addDislike(int postId);
}
