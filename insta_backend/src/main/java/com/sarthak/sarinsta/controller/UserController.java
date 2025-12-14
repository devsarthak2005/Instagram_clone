package com.sarthak.sarinsta.controller;

import com.sarthak.sarinsta.dto.ProfileResponse;
import com.sarthak.sarinsta.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    public ProfileResponse getProfile(@PathVariable String username) {
        return userService.getProfile(username);
    }

    @PostMapping("/{username}/follow")
    public void follow(@PathVariable String username) {
        userService.followUser(username);
    }

    @PostMapping("/{username}/unfollow")
    public void unfollow(@PathVariable String username) {
        userService.unfollowUser(username);
    }
}

