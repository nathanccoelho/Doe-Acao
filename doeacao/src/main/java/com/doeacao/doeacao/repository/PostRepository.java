package com.doeacao.doeacao.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.doeacao.doeacao.model.Post;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    public List<Post> findAllByTitleContainingIgnoreCase(@Param("title")String title);


}


