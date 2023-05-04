package com.heyesinc.api.mindstamp.services.impl;

import com.heyesinc.api.mindstamp.dtos.Post;
import com.heyesinc.api.mindstamp.repositorys.PostRepository;
import com.heyesinc.api.mindstamp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return null;
    }

    @Override
    public Post getPostById(int postId) {
        return null;
    }

    @Override
    public Post editPostById(int postId, Post post) {
        return null;
    }

    @Override
    public String deletePostById(int postId) {
        return null;
    }
}
