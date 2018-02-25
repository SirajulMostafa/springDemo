package com.sirajul.springDemo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sirajul.springDemo.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);

	User findByConfirmationToken(String confirmationToken);
}
