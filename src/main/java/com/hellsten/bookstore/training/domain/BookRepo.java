package com.hellsten.bookstore.training.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface BookRepo extends CrudRepository<Book, Long>  {

    List<Book> findByTitle(String title);
    List<Book> findAll();

    
}
