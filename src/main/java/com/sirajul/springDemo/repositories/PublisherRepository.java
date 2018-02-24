package com.sirajul.springDemo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sirajul.springDemo.model.Publisher;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {

}
