package com.sirajul.springDemo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sirajul.springDemo.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {

}
