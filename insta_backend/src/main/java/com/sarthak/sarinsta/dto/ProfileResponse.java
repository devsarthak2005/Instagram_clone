package com.sarthak.sarinsta.dto;

import java.util.List;

public record ProfileResponse(
        Long id,
        String username,
        int followers,
        int following,
        List<PostResponse> posts,
        boolean isFollowing
) {}
