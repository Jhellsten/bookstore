package com.hellsten.bookstore.training.web;

import java.util.List;

import com.hellsten.bookstore.training.domain.Book;
import com.hellsten.bookstore.training.domain.BookRepo;
import com.hellsten.bookstore.training.domain.Category;
import com.hellsten.bookstore.training.domain.CategoryRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {

    @Autowired
    private BookRepo books;
    @Autowired
    private CategoryRepo categories;
    
    // Get all books
    @GetMapping("/booklist")
    public String getFriends(Model model) {
        List<Book> allBooks = books.findAll();
        model.addAttribute("books", allBooks);
        for (Book book : allBooks) {
            System.out.println(book);
        }
        return "book";
    }

    // Delete book by id
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    books.deleteById(bookId);
    return "redirect:../booklist";
    }   

    // Get values for new book
    @RequestMapping(value = "/newbook", method = RequestMethod.GET)
    public String newBook(Model model) {
    model.addAttribute("book", new Book());
    model.addAttribute("categories", categories.findAll());
    return "addbook";
    }   

    // Edit existing book
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBook(@PathVariable("id") Long bookId, Model model) {
    model.addAttribute("book", books.findById(bookId));
    model.addAttribute("categories", categories.findAll());
    return "editbook";
    }   

    // Save new book
    @RequestMapping(value = "/newbook", method = RequestMethod.POST)
    public String saveBook(@ModelAttribute Book book, Model model) {
    System.out.println(book);
        books.save(book);
    return "redirect:booklist";
    }   
 
}
