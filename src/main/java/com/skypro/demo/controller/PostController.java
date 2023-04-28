package com.skypro.demo.controller;

import com.skypro.demo.dto.PostDTO;
import com.skypro.demo.dto.UserDTOForPost;
import com.skypro.demo.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<UserDTOForPost>> getTopTenUsers(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getTopTenUsersPerPost(id));
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts(@RequestParam(required = false) Integer pageNumber,
                                                     @RequestParam(required = false) Integer size) {
        if (pageNumber == null && size == null) {
            return ResponseEntity.ok(postService.getAllPosts());
        }
        if (size == null) {
            size = 5;
        }
        return ResponseEntity.ok(postService.getAllPostWithPageable(pageNumber, size));
    }

    @GetMapping ("/comments")
    public ResponseEntity<List<PostDTO>> getAllPostWhereCommentBodyLike(@RequestParam String body) {
        return ResponseEntity.ok(postService.getPostsWithBodyLike(body));
    }

}
