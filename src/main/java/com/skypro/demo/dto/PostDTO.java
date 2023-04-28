package com.skypro.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.skypro.demo.model.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PostDTO {

    private Long id;
    private String title;
    private String body;
    private List<CommentDTO> comments;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserDTO user;

    public static PostDTO fromPostToPostDTO (Post post) {
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setBody(post.getBody());
        List<CommentDTO> comments = post.getComments().stream()
                .map(CommentDTO::fromCommentToCommentDTO)
                .collect(Collectors.toList());
        dto.setComments(comments);
        dto.setUser(UserDTO.fromUserToUserDTO(post.getUser()));
        return dto;
    }

}
