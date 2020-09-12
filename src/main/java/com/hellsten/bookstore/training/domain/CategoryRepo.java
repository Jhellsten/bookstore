package com.hellsten.bookstore.training.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<Category, Long>  {

    List<Category> findByName(String name);
    List<Category> findAll();

    
}
