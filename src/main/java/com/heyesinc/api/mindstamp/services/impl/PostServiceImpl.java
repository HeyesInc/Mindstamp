package com.heyesinc.api.mindstamp.services.impl;

import com.heyesinc.api.mindstamp.authentication.JwtService;
import com.heyesinc.api.mindstamp.dtos.Post;
import com.heyesinc.api.mindstamp.dtos.PostRequest;
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
    public Post addLike(int postId, HttpServletRequest request) {
        User user = userRepository.findByUsername(jwtService.emailFromJwt(request)).orElseThrow();
        Post post = postRepository.findById(postId).orElseThrow();
        if (!post.getUsersThatDisliked().contains(user.getUsername()) && !post.getUsersThatLiked().contains(user.getUsername())) {
            post.getUsersThatLiked().add(user.getUsername());
            post.setLikeCount(post.getLikeCount() + 1);
        }else if(post.getUsersThatDisliked().contains(user.getUsername()) && !post.getUsersThatLiked().contains(user.getUsername())){
            post.getUsersThatDisliked().remove(user.getUsername());
            post.getUsersThatLiked().add(user.getUsername());
            post.setDislikeCount(post.getDislikeCount() -1);
            post.setLikeCount(post.getLikeCount() +1);
        }
        return Post.builder()
                .likeCount(post.getLikeCount())
                .dislikeCount(post.getDislikeCount())
                .build();
    }
    @Override
    @Transactional
    public Post addDislike(int postId, HttpServletRequest request) {
        User user = userRepository.findByUsername(jwtService.emailFromJwt(request)).orElseThrow();
        Post post = postRepository.findById(postId).orElseThrow();
        if (!post.getUsersThatDisliked().contains(user.getUsername()) && !post.getUsersThatLiked().contains(user.getUsername())) {
            post.getUsersThatDisliked().add(user.getUsername());
            post.setDislikeCount(post.getDislikeCount() + 1);
        }else if(!post.getUsersThatDisliked().contains(user.getUsername()) && post.getUsersThatLiked().contains(user.getUsername())){
            post.getUsersThatLiked().remove(user.getUsername());
            post.getUsersThatDisliked().add(user.getUsername());
            post.setDislikeCount(post.getDislikeCount() +1);
            post.setLikeCount(post.getLikeCount() -1);
        }
        return Post.builder()
                .likeCount(post.getLikeCount())
                .dislikeCount(post.getDislikeCount())
                .build();
    }

    @Override
    public Post getInteractions(int postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        return Post.builder()
                .dislikeCount(post.getDislikeCount())
                .likeCount(post.getLikeCount())
                .build();
    }
}
