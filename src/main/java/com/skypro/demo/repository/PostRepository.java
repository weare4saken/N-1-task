package com.skypro.demo.repository;

import com.skypro.demo.model.Post;
import com.skypro.demo.projection.PostProj;
import com.skypro.demo.projection.UserProjection;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {

    @Query(value = "SELECT userId, username, " +
            "COUNT(DISTINCT p.id) AS allPostCount, " +
            "COUNT(DISTINCT c2.id) AS allCommentsCount, " +
            "(SELECT p2.id FROM post AS p2 WHERE p2.user_id = t.userId " +
            "ORDER BY (SELECT COUNT(*) FROM comment WHERE post_id = p2.id) " +
            "DESC, p2.id DESC LIMIT 1) AS lastPostId " +
            "FROM (SELECT u.id AS userId, u.username AS username, " +
            "COUNT(c.id) AS countComOfOnePost FROM comment AS c " +
            "JOIN users AS u ON c.user_id=u.id " +
            "WHERE post_id=:postId GROUP BY u.id, u.username ORDER BY countComOfOnePost DESC) AS t " +
            "JOIN post AS p ON t.userId = p.user_id " +
            "LEFT JOIN comment AS c2 ON t.userId = c2.user_id " +
            "GROUP BY userId, username, countComOfOnePost " +
            "ORDER BY countComOfOnePost DESC " +
            "LIMIT 10", nativeQuery = true)
    List<UserProjection> findTopTenUsers(@Param("postId") Long postId);

    @EntityGraph(attributePaths = {"comments", "user"})
    List<Post> findAllBy();

    List<PostProj> findProjBy(PageRequest pageRequest);

}
