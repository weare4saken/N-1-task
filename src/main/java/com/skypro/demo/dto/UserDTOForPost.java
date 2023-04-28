package com.skypro.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTOForPost {

    private Long id;
    private String username;
    private Long countPosts;
    private Long countComments;
    private Long latestPostId;

}
