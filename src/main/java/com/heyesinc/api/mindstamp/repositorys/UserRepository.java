package com.heyesinc.api.mindstamp.repositorys;

import com.heyesinc.api.mindstamp.dtos.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
