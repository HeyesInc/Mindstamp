package com.heyesinc.api.mindstamp.services;

import com.heyesinc.api.mindstamp.dtos.Post;

import java.util.List;

public interface PostService {

    public List<Post> getAllPosts();

    public Post getPostById(int postId);

    public Post editPostById(int postId, Post post);

    public String deletePostById(int postId);
}
