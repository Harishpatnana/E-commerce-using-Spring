package com.harish.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harish.ecommerce.model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Long>{

	Users findByUsername(String username);

}
