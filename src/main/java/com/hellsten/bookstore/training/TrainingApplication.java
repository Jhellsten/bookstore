package com.hellsten.bookstore.training;

import com.hellsten.bookstore.training.domain.Book;
import com.hellsten.bookstore.training.domain.BookRepo;
import com.hellsten.bookstore.training.domain.Category;
import com.hellsten.bookstore.training.domain.CategoryRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrainingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingApplication.class, args);
	}

    @Bean
	public CommandLineRunner booksDemo(BookRepo books, CategoryRepo categories) {
		return (args) -> {
			System.out.println("save a couple of books");
			categories.save(new Category("IT"));
			categories.save(new Category("Business"));
			categories.save(new Category("Law"));

			books.save(new Book("A Farewell to Arms", "Ernest Hemingway", 1929,"122323233232-2", categories.findByName("IT").get(0)));
        	books.save(new Book("Animal Farm", "George Orwell", 1945, "22123322-3", categories.findByName("Business").get(0)));
			
			System.out.println("fetch all Books");
			for (Book book : books.findAll()) {
				System.out.println(book.toString());
			}

		};
	}

	// @Override
	// public void run(String... args) {
	// 	Category cat = new Category("General");
	// 	System.out.println(cat);
	// 	categories.save(cat);
	// 	// Category category = categories.findByName("General").get(0);
	// 	// System.out.println(category);
    //     // books.save(new Book("A Farewell to Arms", "Ernest Hemingway", 1929,"122323233232-2", category));
    //     // books.save(new Book("Animal Farm", "George Orwell", 1945, "22123322-3", category));
	// }


}
