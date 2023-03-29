package com.skypro.demo.service;

import com.skypro.demo.dto.UserDTO;
import com.skypro.demo.projection.PostWithTitleAndBody;
import com.skypro.demo.projection.UserProjection;
import com.skypro.demo.repository.CommentRepository;
import com.skypro.demo.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

//    public PostWithTitleAndBody findLatestPostWithMoreQuantityComments() {
//        return postRepository.findLatestPostWithMoreQuantityComments();
//    }

    public List<UserDTO> getTopTenUsersPerPost(Long id) {
//        if (postRepository.findById(id) == null) {
//            //exceptionHendler
//        }
        List<UserProjection> users = postRepository.findTopTenUsers(id);
        return users.stream().map(UserProjection::fromUserProgectionToUserDTO)
                .collect(Collectors.toList());
    }

}