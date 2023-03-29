package com.skypro.demo.dto;

import com.skypro.demo.model.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDTO {

    private Long id;
    private String title;
    private String body;

    public static PostDTO fromPostToPostDTO (Post post) {
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setBody(post.getBody());
        return dto;
    }


}
