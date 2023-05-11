package com.heyesinc.api.mindstamp.services;

import com.heyesinc.api.mindstamp.dtos.Post;
import com.heyesinc.api.mindstamp.dtos.PostRequest;

import java.util.List;

public interface PostService {

    public List<Post> getAllPosts();

    public Post getPostById(int postId);

    public String editPostById(int postId, PostRequest newPost);

    public String deletePostById(int postId);
}
