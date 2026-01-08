package com.harish.ecommerce.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harish.ecommerce.model.Orders;
import com.harish.ecommerce.model.Users;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Long>{

	List<Orders> findByUserId(Long userId);

}
