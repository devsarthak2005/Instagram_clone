package com.sarthak.sarinsta.repository;

import com.sarthak.sarinsta.entity.Post;
import com.sarthak.sarinsta.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUserInOrderByCreatedAtDesc(Set<User> users);
}
