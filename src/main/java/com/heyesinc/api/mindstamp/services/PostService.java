package com.heyesinc.api.mindstamp.services;

import com.heyesinc.api.mindstamp.dtos.Post;
import com.heyesinc.api.mindstamp.dtos.PostRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    Post getPostById(int postId);
    String editPostById(int postId, PostRequest newPost);
    String deletePostById(int postId);
    Post addLike(int postId, HttpServletRequest request);
    Post addDislike(int postId, HttpServletRequest request);

    Post getInteractions(int postId);
}
