package com.board.springbootboard.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<PostsEntity,Long> {

    // Querydsl: id별 내림 차순 다건 조회
    @Query("SELECT p FROM PostsEntity p ORDER BY p.id DESC")
    List<PostsEntity> findAllDesc();

}
