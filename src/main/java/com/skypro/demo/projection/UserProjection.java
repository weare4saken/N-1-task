package com.skypro.demo.projection;

import com.skypro.demo.dto.UserDTOForPost;

public interface UserProjection {

    Long getUserId();
    String getUsername();
    Long getAllPostCount();
    Long getAllCommentsCount();
    Long getLastPostId();

    default UserDTOForPost fromUserProjectionToUserDTO() {
        UserDTOForPost dto = new UserDTOForPost();
        dto.setId(getUserId());
        dto.setUsername(getUsername());
        dto.setCountPosts(getAllPostCount());
        dto.setCountComments(getAllCommentsCount());
        dto.setLatestPostId(getLastPostId());
        return dto;
    }

}
