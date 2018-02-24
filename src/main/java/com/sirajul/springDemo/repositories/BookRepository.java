package com.sirajul.springDemo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sirajul.springDemo.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}
