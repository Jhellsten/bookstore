package com.hellsten.bookstore.training.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BookRepo extends CrudRepository<Book, Long>  {

    List<Book> findByTitle(@Param("title") String title);
    List<Book> findAll();

    
}
