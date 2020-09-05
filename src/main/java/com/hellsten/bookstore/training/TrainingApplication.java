package com.hellsten.bookstore.training;

import com.hellsten.bookstore.training.domain.Book;
import com.hellsten.bookstore.training.domain.BookRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrainingApplication implements CommandLineRunner {

	@Autowired
	private BookRepo repository;
	public static void main(String[] args) {
		SpringApplication.run(TrainingApplication.class, args);
	}

	@Override
	public void run(String... args) {
		repository.deleteAll();

        repository.save(new Book("A Farewell to Arms", "Ernest Hemingway", 1929,"122323233232-2",  10.00));
        repository.save(new Book("Animal Farm", "George Orwell", 1945, "22123322-3", 100.00));
	}


}
