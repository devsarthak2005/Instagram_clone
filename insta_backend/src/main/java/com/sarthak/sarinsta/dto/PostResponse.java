package com.sarthak.sarinsta.dto;

import java.time.LocalDateTime;
import java.util.List;

public record PostResponse(
        Long id,
        String imageUrl,
        String caption,
        UserResponse user,
        int likeCount,
        List<CommentResponse> comments,
        LocalDateTime createdAt
) {}
