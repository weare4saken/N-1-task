package com.skypro.demo.repository;

import com.skypro.demo.model.Comment;
import com.skypro.demo.projection.CommentProj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT c.id, c.body, c.post_id AS postId FROM comment AS c WHERE c.post_id IN (?1) ", nativeQuery = true)
    List<CommentProj> findByPostIdIn(List<Long> postIds);
}
