package com.heyesinc.api.mindstamp.services.impl;

import com.heyesinc.api.mindstamp.dtos.Post;
import com.heyesinc.api.mindstamp.dtos.PostRequest;
import com.heyesinc.api.mindstamp.repositorys.PostRepository;
import com.heyesinc.api.mindstamp.services.PostService;
import jakarta.transaction.Transactional;
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
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(int postId) {
        return postRepository.findById(postId).orElseThrow();
    }

    @Override
    @Transactional
    public String editPostById(int postId, PostRequest newPost) {
        Post post = postRepository.findById(postId).orElseThrow();
        post.setContent(newPost.getContent());
        return "Post Updated";
    }

    @Override
    public String deletePostById(int postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        postRepository.delete(post);
        return "Post Deleted";
    }
}
