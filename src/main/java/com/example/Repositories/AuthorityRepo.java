package com.example.Repositories;

import com.example.Entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepo extends JpaRepository<Authority,Long> {
   Authority findById(int id);
}
