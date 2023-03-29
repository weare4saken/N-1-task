package com.skypro.demo.projection;

import com.skypro.demo.dto.PostDTO;

public interface PostProj {

    Long getId();
    String getTitle();
    String getBody();

    default PostDTO fromPostProjToPostDTO () {
        PostDTO dto = new PostDTO();
        dto.setId(getId());
        dto.setTitle(getTitle());
        dto.setBody(getBody());
        return dto;
    }

}
