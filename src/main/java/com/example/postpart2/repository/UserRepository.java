package com.example.postpart2.repository;


import com.example.postpart2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);
//    Optional<Post> findByEmail(String email);


}
