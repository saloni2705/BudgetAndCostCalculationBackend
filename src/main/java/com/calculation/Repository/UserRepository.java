package com.calculation.Repository;

import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;

import com.calculation.entity.User;


public interface UserRepository extends JpaRepository<User,Long>{

	Optional<User> findByName(String name);

	boolean existsByName(String Name);

	boolean existsByEmail(String email);
	
	Optional<User> findById(Long userid);
	

	
}
