package com.sarthak.sarinsta.controller;

import com.sarthak.sarinsta.dto.CommentRequest;
import com.sarthak.sarinsta.dto.PostRequest;
import com.sarthak.sarinsta.entity.Comment;
import com.sarthak.sarinsta.entity.Post;
import com.sarthak.sarinsta.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public Post create(@RequestBody PostRequest req) {
        return postService.createPost(req);
    }

    @PostMapping("/{id}/like")
    public void like(@PathVariable Long id) {
        postService.likePost(id);
    }

    @PostMapping("/{id}/unlike")
    public void unlike(@PathVariable Long id) {
        postService.unlikePost(id);
    }

    @PostMapping("/{id}/comment")
    public Comment comment(@PathVariable Long id,
                           @RequestBody CommentRequest req) {
        return postService.addComment(id, req);
    }

    @GetMapping("/feed")
    public List<Post> feed() {
        return postService.getFeed();
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PostMapping("/{id}/comments")
    public Comment addComment(@PathVariable Long id,
                              @RequestBody CommentRequest req) {
        return postService.addComment(id, req);
    }
}

