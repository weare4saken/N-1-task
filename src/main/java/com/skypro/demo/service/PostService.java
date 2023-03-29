package com.skypro.demo.service;

import com.skypro.demo.dto.PostDTO;
import com.skypro.demo.dto.UserDTOForPost;
import com.skypro.demo.exception.PostNotFoundException;
import com.skypro.demo.model.Post;
import com.skypro.demo.projection.CommentProj;
import com.skypro.demo.projection.PostProj;
import com.skypro.demo.projection.UserProjection;
import com.skypro.demo.repository.CommentRepository;
import com.skypro.demo.repository.PostRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.JoinType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public List<UserDTOForPost> getTopTenUsersPerPost(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);

        if(postOptional.isEmpty()) {
            throw new PostNotFoundException();
        }

        List<UserProjection> users = postRepository.findTopTenUsers(id);
        return users.stream().map(UserProjection::fromUserProjectionToUserDTO)
                .collect(Collectors.toList());
    }

    public List<PostDTO> getAllPosts(){
        return postRepository.findAllBy().stream()
                .map(PostDTO::fromPostToPostDTO)
                .collect(Collectors.toList());
    }

    public List<PostDTO> getAllPostWithPageable(Integer pageNumber, Integer pageSize) {
        List<PostProj> projBy = postRepository.findProjBy(PageRequest.of(pageNumber - 1, pageSize));
        List<CommentProj> byPostIdIn = commentRepository.findByPostIdIn(projBy.stream()
                                                        .map(PostProj::getId)
                                                        .collect(Collectors.toList()));
        return projBy.stream()
                .map(PostProj::fromPostProjToPostDTO)
                .peek(postDTO -> postDTO.setComments((byPostIdIn.stream()
                                .filter(comment -> comment.getPostId().equals(postDTO.getId())))
                                .map(CommentProj::fromCommentProjToCommentDTO).collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public List<PostDTO> getPostsWithBodyLike(String body) {
        return postRepository.findAll((root, query, criteriaBuilder) -> {
                    root.fetch("comments", JoinType.LEFT).fetch("user", JoinType.LEFT);
                    query.distinct(true);
                    return criteriaBuilder.like(root.join("comments").get("body"), "%" + body + "%");
                })
                .stream()
                .map(PostDTO::fromPostToPostDTO)
                .collect(Collectors.toList());
    }
}