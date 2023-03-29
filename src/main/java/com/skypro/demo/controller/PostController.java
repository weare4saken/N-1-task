package com.skypro.demo.controller;

import com.skypro.demo.dto.UserDTO;
import com.skypro.demo.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<UserDTO>> getTopTenUsers(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getTopTenUsersPerPost(id));
    }

}
