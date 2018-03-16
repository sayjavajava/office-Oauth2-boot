package com.example.Repositories;

import com.example.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findById(long id);

}
