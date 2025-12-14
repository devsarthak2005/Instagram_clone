package com.sarthak.sarinsta.repository;

import com.sarthak.sarinsta.entity.Comment;
import com.sarthak.sarinsta.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
