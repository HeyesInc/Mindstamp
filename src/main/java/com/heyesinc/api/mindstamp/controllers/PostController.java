package com.heyesinc.api.mindstamp.controllers;

import com.heyesinc.api.mindstamp.dtos.Post;
import com.heyesinc.api.mindstamp.dtos.PostRequest;
import com.heyesinc.api.mindstamp.services.PostService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
@CrossOrigin
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping()
    public ResponseEntity<List<Post>> getAllPosts(){
        return ResponseEntity.ok().body(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Integer postId){
        return ResponseEntity.ok().body(postService.getPostById(postId));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Integer postId){
        return ResponseEntity.ok().body(postService.deletePostById(postId));
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> editPostById(@PathVariable("id") Integer postId, @RequestBody PostRequest newPost){
        return ResponseEntity.ok().body(postService.editPostById(postId, newPost));
    }
    @PostMapping("/{id}/like")
    public ResponseEntity<Post> addLike(@PathVariable("id") Integer postId, HttpServletRequest request){
        return ResponseEntity.ok().body(postService.addLike(postId, request));
    }
    @PostMapping("/{id}/dislike")
    public ResponseEntity<Post> addDislike(@PathVariable("id") Integer postId, HttpServletRequest request){
        return ResponseEntity.ok().body(postService.addDislike(postId, request));
    }

    @GetMapping("/{id}/interactions")
    public ResponseEntity<Post> getInteractions(@PathVariable("id") Integer postId){
        return ResponseEntity.ok().body(postService.getInteractions(postId));
    }
}
