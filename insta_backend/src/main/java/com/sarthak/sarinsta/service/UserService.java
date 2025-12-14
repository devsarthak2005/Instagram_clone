package com.sarthak.sarinsta.service;

import com.sarthak.sarinsta.entity.User;
import com.sarthak.sarinsta.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    public void followUser(long targetUserId) {
        User current = getCurrentUser();
        User target = userRepository.findById(targetUserId).orElseThrow();

        current.getFollowing().add(target);
        userRepository.save(current);
    }

    public void unfollowUser(long targetUserId) {
        User current = getCurrentUser();
        User target = userRepository.findById(targetUserId).orElseThrow();

        current.getFollowing().remove(target);
        userRepository.save(current);
    }

    public void followUser(String username) {
        User current = getCurrentUser();
        User target = userRepository.findByUsername(username).orElseThrow();

        current.getFollowing().add(target);
        userRepository.save(current);
    }

    public void unfollowUser(String username) {
        User current = getCurrentUser();
        User target = userRepository.findByUsername(username).orElseThrow();

        current.getFollowing().remove(target);
        userRepository.save(current);
    }

    public com.sarthak.sarinsta.dto.ProfileResponse getProfile(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        User current = getCurrentUser();
        
        return new com.sarthak.sarinsta.dto.ProfileResponse(
                user.getId(),
                user.getUsername(),
                user.getFollowers().size(),
                user.getFollowing().size(),
                null, // posts - implement if needed
                current.getFollowing().contains(user)
        );
    }
}
