package com.sarthak.sarinsta.service;

import com.sarthak.sarinsta.dto.*;
import com.sarthak.sarinsta.entity.Comment;
import com.sarthak.sarinsta.entity.Post;
import com.sarthak.sarinsta.entity.User;
import com.sarthak.sarinsta.repository.CommentRepository;
import com.sarthak.sarinsta.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserService userService;

    public Post createPost(PostRequest request) {
        User user = userService.getCurrentUser();

        Post post = new Post();
        post.setImageUrl(request.imageUrl());
        post.setCaption(request.caption());
        post.setUser(user);

        return postRepository.save(post);
    }

        public void likePost(long postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        post.getLikes().add(userService.getCurrentUser());
        postRepository.save(post);
    }

    public void unlikePost(long postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        post.getLikes().remove(userService.getCurrentUser());
        postRepository.save(post);
    }

    public Comment addComment(long postId, CommentRequest request) {
        Comment comment = new Comment();
        comment.setPost(postRepository.findById(postId).orElseThrow());
        comment.setUser(userService.getCurrentUser());
        comment.setText(request.text());
        return commentRepository.save(comment);
    }

    public List<Post> getFeed() {
        return postRepository.findByUserInOrderByCreatedAtDesc(
                userService.getCurrentUser().getFollowing()
        );
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(long id) {
        return postRepository.findById(id).orElseThrow();
    }
}

