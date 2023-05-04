package com.heyesinc.api.mindstamp.repositorys;

import com.heyesinc.api.mindstamp.dtos.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
