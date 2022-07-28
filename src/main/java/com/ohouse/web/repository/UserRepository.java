package com.ohouse.web.repository;

import com.ohouse.web.dto.UserDto;
import com.ohouse.web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDto,String> {
    Optional<User> findByUseEmail(String email);
    boolean existsByUserEmail(String email);
}
