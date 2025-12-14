package com.sarthak.sarinsta.dto;

public record CommentResponse(
        Long id,
        String text,
        UserResponse user
) {}
