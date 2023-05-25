package com.heyesinc.api.mindstamp.services.impl;

import com.heyesinc.api.mindstamp.authentication.JwtService;
import com.heyesinc.api.mindstamp.dtos.Post;
import com.heyesinc.api.mindstamp.dtos.PostRequest;
import com.heyesinc.api.mindstamp.dtos.PostResponse;
import com.heyesinc.api.mindstamp.dtos.User;
import com.heyesinc.api.mindstamp.repositorys.PostRepository;
import com.heyesinc.api.mindstamp.repositorys.UserRepository;
import com.heyesinc.api.mindstamp.services.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private UserRepository userRepository;
    private JwtService jwtService;
    @Autowired
    public PostServiceImpl(PostRepository postRepository,
                           UserRepository userRepository,
                           JwtService jwtService){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
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
    @Transactional
    public String deletePostById(int postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        postRepository.delete(post);
        return "Post Deleted";
    }
    @Override
    @Transactional
    public PostResponse addLike(int postId, HttpServletRequest request) {

        User user = userRepository.findByUsername(jwtService.emailFromJwt(request)).orElseThrow();
        Post post = postRepository.findById(postId).orElseThrow();

        boolean alreadyLiked = post.getUsersThatLiked().contains(user.getUsername());
        boolean alreadyDisliked = post.getUsersThatDisliked().contains(user.getUsername());

        if (!alreadyDisliked && !alreadyLiked) {
            post.getUsersThatLiked().add(user.getUsername());
        }else if(alreadyDisliked && !alreadyLiked){
            post.getUsersThatDisliked().remove(user.getUsername());
            post.getUsersThatLiked().add(user.getUsername());
        }
        return PostResponse.builder()
                .likeCount(post.getUsersThatLiked().size())
                .dislikeCount(post.getUsersThatDisliked().size())
                .build();
    }
    @Override
    @Transactional
    public PostResponse addDislike(int postId, HttpServletRequest request) {
        User user = userRepository.findByUsername(jwtService.emailFromJwt(request)).orElseThrow();
        Post post = postRepository.findById(postId).orElseThrow();

        boolean alreadyLiked = post.getUsersThatLiked().contains(user.getUsername());
        boolean alreadyDisliked = post.getUsersThatDisliked().contains(user.getUsername());

        if (!alreadyDisliked && !alreadyLiked) {
            post.getUsersThatDisliked().add(user.getUsername());
        }else if(alreadyLiked && !alreadyDisliked){
            post.getUsersThatLiked().remove(user.getUsername());
            post.getUsersThatDisliked().add(user.getUsername());
        }
        return PostResponse.builder()
                .likeCount(post.getUsersThatLiked().size())
                .dislikeCount(post.getUsersThatDisliked().size())
                .build();
    }

    @Override
    public PostResponse getInteractions(int postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        return PostResponse.builder()
                .dislikeCount(post.getUsersThatDisliked().size())
                .likeCount(post.getUsersThatLiked().size())
                .build();
    }
}
