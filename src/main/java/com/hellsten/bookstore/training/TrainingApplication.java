package com.hellsten.bookstore.training;

import com.hellsten.bookstore.training.domain.Book;
import com.hellsten.bookstore.training.domain.BookRepo;
import com.hellsten.bookstore.training.domain.Category;
import com.hellsten.bookstore.training.domain.CategoryRepo;
import com.hellsten.bookstore.training.domain.User;
import com.hellsten.bookstore.training.domain.UserRepo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;

@SpringBootApplication
public class TrainingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingApplication.class, args);
	}

    @Bean
	public CommandLineRunner booksDemo(BookRepo books, CategoryRepo categories, UserRepo users) {
		return (args) -> {
			System.out.println("save a couple of books");
			categories.save(new Category("Fiction"));
			categories.save(new Category("Fantasy"));
			categories.save(new Category("History"));

			books.save(new Book("A Farewell to Arms", "Ernest Hemingway", 1929,"122323233232-2", categories.findByName("Fiction").get(0)));
        	books.save(new Book("Animal Farm", "George Orwell", 1945, "22123322-3", categories.findByName("Fantasy").get(0)));
			
			System.out.println("fetch all Books");
			for (Book book : books.findAll()) {
				System.out.println(book.toString());
			}
			String pw1 = BCrypt.hashpw("user", BCrypt.gensalt(13));
			String pw2 = BCrypt.hashpw("admin", BCrypt.gensalt(13));

			User user1 = new User("user", pw1, "USER");
			User user2 = new User("admin",pw2, "ADMIN");

			System.out.println(user1);
			System.out.println(user2);
			users.save(user1);
			users.save(user2);

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
