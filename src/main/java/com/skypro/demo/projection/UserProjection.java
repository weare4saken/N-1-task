package com.skypro.demo.projection;

import com.skypro.demo.dto.UserDTO;

public interface UserProjection {

    Long getUserId();
    String getUsername();
    Long getAllPostCount();
    Long getAllCommentsCount();
    Long getLastPostId();

    default UserDTO fromUserProgectionToUserDTO() {
        UserDTO dto = new UserDTO();
        dto.setId(getUserId());
        dto.setUsername(getUsername());
        dto.setCountPosts(getAllPostCount());
        dto.setCountComments(getAllCommentsCount());
        dto.setLatestPostId(getLastPostId());
        return dto;
    }

}
