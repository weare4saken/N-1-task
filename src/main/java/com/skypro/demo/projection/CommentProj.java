package com.skypro.demo.projection;

import com.skypro.demo.dto.CommentDTO;

public interface CommentProj {

    Long getId();
    String getBody();
    Long getPostId();

    default CommentDTO fromCommentProjToCommentDTO () {
        CommentDTO dto = new CommentDTO();
        dto.setId(getId());
        dto.setBody(getBody());
        return dto;
    }

}
